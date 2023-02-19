package com.jizhi.phonemall.entity;


/**
 * orderitem
 * @author 
 */
public class OrderItem{
    /**
     * 订单编号
     */
    private Integer id;

    /**
     * 订单项单价
     */
    private Double price;

    /**
     * 订单项数量
     */
    private Integer amount;

    /**
     * 订单编号
     */
    private Integer orderid;

    /**
     * 商品编号
     */
    private Integer goodsid;

    /**
     * 总价
     */
    private Double total;
    /**
     * 商品
     */
    private Goods goods;


    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", price=" + price +
                ", amount=" + amount +
                ", orderid=" + orderid +
                ", goodsid=" + goodsid +
                ", total=" + total +
                ", goods=" + goods +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Double getTotal() {
        this.total = price * amount;
        return total;
    }

    public void setTotal() {
        this.total = price * amount;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}