package com.jizhi.phonemall.entity;

/**
 * 推荐实体类
 */
public class Recommend {
    //条幅
    public static final byte CATEGORY_SCROLL = 1;
    //热销
    public static final byte CATEGORY_FASHION = 2;
    //性价比
    public static final byte CATEGORY_PERFONMANCE = 3;

    private Integer rid;

    private Byte type;

    private Integer goodsId;

    private Goods goods;

    @Override
    public String toString() {
        return "Recommend{" +
                "rid=" + rid +
                ", type=" + type +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                '}';
    }



    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
