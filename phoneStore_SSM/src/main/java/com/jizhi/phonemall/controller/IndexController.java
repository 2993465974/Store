package com.jizhi.phonemall.controller;
import	java.util.ArrayList;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jizhi.phonemall.entity.*;
import com.jizhi.phonemall.service.*;
import com.jizhi.phonemall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

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
    //---------------------

    /**
     * 默认跳转首页
     * @param request
     * @param pageNum
     * @return
     */
    @GetMapping("")
    public String toIndexPage(HttpSession session,HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") int pageNum) {
        request.setAttribute("flag",1);
        List<Category> categoryList = categoryService.findAllCategory(null);

        List<Recommend> scrollRecommendList = recommendService.findAll(Recommend.CATEGORY_SCROLL);

        List<Recommend> fashionRecommendList = recommendService.findAll(Recommend.CATEGORY_FASHION);

        List<Recommend> perfonmanRecommendList = recommendService.findAll(Recommend.CATEGORY_PERFONMANCE);
        List<Goods> goodsList = goodsService.findAllGoods(null, 0);

        PageHelper.startPage(pageNum, 4);
        PageInfo<Goods> pages = new PageInfo<Goods>(goodsList);
        request.setAttribute("recommendList", scrollRecommendList);
        request.setAttribute("fashionRecommendList", fashionRecommendList);
        request.setAttribute("perfonmanRecommendList", perfonmanRecommendList);
        request.setAttribute("goodsList", pages);
        session.setAttribute("categoryLists", categoryList);
        return "/front/index";
    }

//    /**
//     * 前往用户登录页面
//     * @return
//     */
//    @GetMapping("toLogin")
//    public String toLogin() {
//        return "../front/login";
//    }
    //重定向可以将参数一起写入

    /**
     * 查看商品详情
     * @param request
     * @param goodsgid
     * @return
     */
    @GetMapping("detail")
    public String detail(HttpServletRequest request, Integer goodsgid) {
        Goods goods = goodsService.findGoodsById(goodsgid);
        List<Category> allCategory = categoryService.findAllCategory(null);
        request.setAttribute("goods", goods);
        request.setAttribute("categoryList", allCategory);
        return "../front/detail";
    }

//    /**
//     * 查看购物车
//     * @param request
//     * @return
//     */
//    @GetMapping("cart")
//    public String cart(HttpServletRequest request) {
//        List<Category> allCategory = categoryService.findAllCategory(null);
//        List<Orders> ordersList = orderService.findOrdersByUidAndPaytype(,0);
//        request.setAttribute("ordersList", ordersList);
//        return "../front/cart";
//    }
    @GetMapping("toCart")
    public String toCart(HttpSession session,HttpServletRequest request) {
        Object users = session.getAttribute("USERS");
        if(users != null){
            return "../front/cart";
        }else{
            request.setAttribute("message","需要登录后才能查看购物车！");
            return "../front/login";
        }
    }

    /**
     * 加入购物车
     *
     * @param goodsgid
     * @param session
     * @return
     */
//    @PostMapping("buy")
    @PostMapping("operation")
    @ResponseBody//ajax异步提交
//    public String buy(HttpServletRequest request,Integer goodsgid, HttpSession session,Integer type) {
    public String operation(HttpServletRequest request,Integer goodsgid, HttpSession session,Integer type) {
        Goods goods = goodsService.findGoodsById(goodsgid);
        if(goods.getStock() <= 0 && type == 1){
            return "noStack";
        }
        Users user = (Users)session.getAttribute("USERS");
        Orders orders = (Orders) session.getAttribute(INDENT_KEY);

        if( user == null){
            return "login";
        }

        if (orders == null) {
            orders = orderService.creatOrder(user,goods);
        } else {
            orders = orderService.updateAllInfo(orders,goods,type);
        }
        session.setAttribute(INDENT_KEY, orders);
        return "ok";
    }

    @GetMapping("submitOrder")
    public String submitOrder(HttpServletRequest request,Integer oid){
        Orders orders = orderService.findOrdersById(oid);
        request.setAttribute("orderPay",orders);
        return "../front/pay";
    }





    /**
     * 修改状态为已收到货物(即表示订单完成)
     * @return
     */
    @GetMapping("orderFinish")
    public String orderFinish(HttpServletRequest request,Integer oid,Integer status) {
        request.setAttribute("flag",3);
        request.setAttribute("status",(status+1));
        List<Orders> allOrders = new ArrayList<> ();
        Orders orders = orderService.findOrdersById(oid);
        orders.setStatus((status + 1));
        orderService.updateInfo(orders);
        allOrders.add(orders);
        request.setAttribute("orderList",allOrders);
        return "../front/order";
    }


    /**
     * 通过商品名称查询商品
     * @param request
     * @param gname
     * @return
     */
    @GetMapping("searchGoodsByLikeName")
    public String searchGoodsByLikeName(HttpServletRequest request,String gname){
        List<Goods> goodsList = goodsService.findGoodsByLikeName(gname);
        request.setAttribute("flag",1);
        request.setAttribute("goodsList",goodsList);
        return "../front/goods";
    }
    /**
     * 前往用户注册页面
     *
     * @param flag
     * @param request
     * @return
     */
    @GetMapping("toRegisterPage")
    public String toRegisterPage(Integer flag, HttpServletRequest request) {

            request.setAttribute("flag", flag);
            return "../front/register";

    }

    /**
     * 前往个人页面
     * @param request
     * @param session
     * @return
     */
    @GetMapping("toMyPage")
    public String toMyPage( HttpServletRequest request,HttpSession session,@RequestParam(required = false,defaultValue = "4") Integer flag) {
        Object users = session.getAttribute("USERS");
        if(users == null){
            request.setAttribute("message","请登录！");
            return "../front/login";
        }
        if(flag != -1){
            request.setAttribute("flag",flag);
        }
        return "../front/my";
    }

    @GetMapping("toOrderPage")
    public String toOrderPage( HttpServletRequest request,HttpSession session,@RequestParam(required = false,defaultValue = "3") Integer flag) {
        Users users = (Users)session.getAttribute("USERS");
        List<Orders> ordersList = orderService.findOrdersByUid(users.getUid());
        for(Orders order : ordersList){
            order.setOrderItems(orderItemService.findOrdersItemByOrderId(order.getOid()));
        }
        request.setAttribute("flag",flag);
        if(ordersList != null){
            request.setAttribute("ordersList",ordersList);
        }else{
            request.setAttribute("message","没有历史订单");
        }
        return "../front/order";
    }

    @PostMapping("editMydata")
    public String editMydata(HttpSession session,String realname,
                             String phone,String address,String password,String passwordNew){
        Users users = (Users)session.getAttribute("USERS");
        String temp = users.getPassword();
        if(StringUtils.isNotEmpty(realname.trim())){
            users.setRealname(realname);
        }
        if(StringUtils.isNotEmpty(phone.trim())){
            users.setPhone(phone);
        }
        if(StringUtils.isNotEmpty(address.trim())){
            users.setAddress(address);
        }
        if(MD5Util.encode(password).equalsIgnoreCase(users.getPassword())){
            users.setPassword(passwordNew);
            temp = MD5Util.encode(passwordNew);
        }else{
            users.setPassword(null);
        }
        int number = usersService.editUser(users);
        users.setPassword(temp);
        if(number > 0){
            session.removeAttribute("USERS");
            session.removeAttribute(INDENT_KEY);
            return "../front/login";
        }else{
            return "../front/my";
        }
    }

    @GetMapping("toCategoGoodsPage")
    public String toCategoGoodsPage(Integer flag, HttpServletRequest request,Integer cid) {
        request.setAttribute("flag",flag);
        Category category = categoryService.findCategoryById(cid);
        List<Goods> allGoods = goodsService.findAllGoods(cid, 0);
        request.setAttribute("category",category);
        request.setAttribute("goodsList",allGoods);
        return "../front/goods";
    }
    @GetMapping("toTypeGoodsPage")
    public String toTypeGoodsPage(Integer flag, HttpServletRequest request,Integer type) {
        request.setAttribute("flag",flag);
        List<Goods> allGoods = new ArrayList<Goods> ();
        List<Recommend> recommendList = recommendService.findRecommendByType(type);
        for (Recommend recommend : recommendList) {
            allGoods.add(goodsService.findGoodsById(recommend.getGoodsId()));
        }
        request.setAttribute("type",type);
        request.setAttribute("goodsList",allGoods);
        return "../front/goods";
    }

    /**
     * 用户注册
     * @param user
     * @param request
     * @return
     */
    @PostMapping("register")
    public String register(Users user, HttpServletRequest request) {
        String username = user.getUsername();
        if (username.isEmpty()) {
            request.setAttribute("message", "注册错误");
            return "../front/register";
        }
        if (usersService.isExist(username)) {
            request.setAttribute("message", "用户已存在！");
            return "../front/register";
        }
        usersService.addUser(user);
        return "redirect:/";
    }

    /**
     * 前往注册页面
     * @param request
     * @param session
     * @return
     */
    @GetMapping("toLoginPage")
    public String toLoginPage(HttpServletRequest request, HttpSession session) {
        //用户已经登录
        if (session.getAttribute("USERS") != null) {
            return "redirect:/";
        }

        request.setAttribute("flag", 6);
        return "../front/login";

    }

    /**
     * 登录功能(登录时查询购物车,因为在主页面的时候就可能会添加商品)
     * @param user
     * @param request
     * @param session
     * @return
     */
    @PostMapping("login")
    public String login(Users user, HttpServletRequest request, HttpSession session) {
        //进行验证
        if (usersService.checkUser(user.getUsername(), user.getPassword())) {
            //查找用户
            List<Users> usersList = usersService.findUserByLikeName(user.getUsername());
            Users users = usersList.get(0);
            //将用户放入Session中
            session.setAttribute("USERS", users);
            //查找该用户未进行结算的订单(即paytype为0的，status为1可能代表用户点击了结算，但是还未付款，所以不能用status为1判断)
            //先查找总项，再根据总项ID查找购物项
            Orders orders = orderService.findOrdersByUidAndPaytype(users.getUid(),0);
            session.setAttribute(INDENT_KEY,orders);
            return "redirect:/";

        } else {
            request.setAttribute("message", "用户名或密码错误");
            return "../front/login";
        }
    }

    /**
     * 用户退出
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("USERS");
        session.removeAttribute(INDENT_KEY);
        return "redirect:/";
    }
}