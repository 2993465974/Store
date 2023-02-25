package com.jizhi.phonemall.controller;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.jizhi.phonemall.entity.Orders;
import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.service.*;
import com.jizhi.phonemall.util.AlipayConfig;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RequestMapping("/pay")
@Controller
public class PayController {
    public static final String INDENT_KEY = "order";
    @Autowired
    @Qualifier("recommendService")
    private RecommendService recommendService;
    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;
    @Autowired
    @Qualifier("orderService")
    private OrderService orderService;
    @Autowired
    @Qualifier("orderItemService")
    private OrderItemService orderItemService;
    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;

    /**
     * 跳转到相关的支付页面
     * @param session
     * @param paytype
     * @param oid
     * @return
     */
    @PostMapping("orderPay")
    public String orderPay(HttpSession session, Integer paytype, Integer oid, HttpServletRequest request,HttpServletResponse response) throws Exception {
        Orders order = orderService.findOrdersById(oid);
        if(order != null && order.getTotal() > 0){
            if (paytype != 2){
                request.setAttribute("message","支付成功！");
                order.setStatus(Orders.STATUS_PAYEND);
                order.setPaytype(paytype);
                orderService.updateInfo(order);
                session.setAttribute(INDENT_KEY,null);
                return "../front/payok";
            }else{
                return "redirect:/pay/goAliPay/"+oid;
            }
        }else{
            request.setAttribute("message","支付失败，好歹买点东西啊！");
            return "../front/payok";
        }
    }


    @RequestMapping(value = "/goAliPay/{oid}",produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAliPay(@PathVariable Integer oid) throws Exception {
        Orders orders = orderService.findOrdersById(oid);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key, "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(orders.getOid().toString().getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(orders.getTotal().toString().getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(("Order no " + orders.getOid()).getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
//        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        String body = "nothing";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "30m";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //      + "\"total_amount\":\""+ total_amount +"\","
        //      + "\"subject\":\""+ subject +"\","
        //      + "\"body\":\""+ body +"\","
        //      + "\"timeout_express\":\"10m\","
        //      + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
        //给支付宝发送请求进行支付操作+
        AlipayTradePagePayResponse payResponse = alipayClient.pageExecute(alipayRequest);
        String result = payResponse.getBody();
        System.out.println(payResponse.isSuccess());
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println(payResponse.getBody());//直接将完整的表单html输出到页面
//        response.getWriter().close();
        return result;
    }


    @RequestMapping("/return_url")
    @ResponseBody
    public String payReturn(HttpServletRequest request,HttpSession session) throws Exception {

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
                AlipayConfig.sign_type); // 调用SDK验证签名
        // ——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("商户订单号---------------------------"+out_trade_no);
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            //修改商品状态
            Integer oid = Integer.parseInt(out_trade_no);
            Orders orders = orderService.findOrdersById(oid);
            orders.setPaytype(Orders.PAYTYPE_ALIPAY);
            orders.setStatus(Orders.STATUS_PAYEND);
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            orders.setSystime(timeStamp);
            orderService.updateInfo(orders);
            Users user = usersService.findUserById(orders.getUserid());
            session.setAttribute("USERS",user);
            session.removeAttribute("order");
            String test = "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount;
            String js = "<script language=\"javascript\" type=\"text/javascript\">\n" +
                    "window.location.href=\"../index\";\n" +
                    "</script>";
            return js;


        } else {
            return "验签失败";
        }
    }

    @RequestMapping("/notify_url")
    @ResponseBody
    public String payNotify(HttpServletRequest request) throws Exception {
        // System.out.println("???????");
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
                AlipayConfig.sign_type); // 调用SDK验证签名
        // ——请在这里编写您的程序（以下代码仅作参考）——
        /*
         * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
         * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
         * 4、验证app_id是否为该商户本身。
         */
        if (signVerified) {// 验证成功
            // 商户订单号
            // String out_trade_no = new
            // String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 支付宝交易号
            // String trade_no = new
            // String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 注意：
                // 付款完成后，支付宝系统发送该交易状态通知
            }
            return "success";
        } else {// 验证失败
            return "fail";
            // 调试用，写文本函数记录程序运行情况是否正常
            // String sWord = AlipaySignature.getSignCheckContentV1(params);
            // AlipayConfig.logResult(sWord);
        }
    }
}

