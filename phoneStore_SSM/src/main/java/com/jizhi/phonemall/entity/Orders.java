package com.jizhi.phonemall.entity;
import	java.util.ArrayList;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * orders
 *
 * @author
 */
public class Orders {
    /**未付款状态*/
    public static final int STATUS_UNPAY = 1;
    /**已付款*/
    public static final int STATUS_PAYEND = 2;
    /**已发货*/
    public static final int STATUS_SEND = 3;
    /**完成订单*/
    public static final int STATUS_FINISH = 4;
    /**支付未定*/
    public static final int PAYTYPE_NOPAY = 0;
    /**支付方式-微信支付*/
    public static final int PAYTYPE_WECHAT = 1;
    /**支付方式-支付宝*/
    public static final int PAYTYPE_ALIPAY = 2;
    /**支付方式-货到付款*/
    public static final int PAYTYPE_OFFINE = 3;

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", total=" + total +
                ", amount=" + amount +
                ", status=" + status +
                ", paytype=" + paytype +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", systime=" + systime +
                ", userid=" + userid +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }

    /**
     * 订单编号
     */
    private Integer oid;

    /**
     * 总价格
     */
    private Double total;

    /**
     * 商品总数
     */
    private Integer amount;

    /**
     * 订单状态(1未付款、2已付款、3已发货、4已收货)
     */
    private Integer status;

    /**
     * 支付方式(1微信、2支付宝、3货到付款)
     */
    private Integer paytype;

    /**
     * 收货人
     */
    private String realname;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 下单时间
     */
    private Date systime;
    /**
     * 下单用户ID
     */
    private Integer userid;

    private Users user;

    //订单项
    private List<OrderItem> orderItems = new ArrayList<OrderItem> ();

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {

        this.realname = realname == null?null:realname.trim();//不为空则去处前后空格
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null?null:address.trim();
    }

    public Date getSystime() {
        return systime;
    }

    public void setSystime(Date systime) {
        this.systime = systime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * 根据购物项集合计算总价格
     */
    public void calTotal(){
        double cal = 0;
        for(OrderItem item : this.orderItems){
            cal += item.getTotal();
        }
        this.total = cal;
    }
}