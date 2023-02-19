package com.jizhi.phonemall.entity;

public class Goods {

    private Integer gid;
    private String gname;
    private String images1;
    private String images2;

    private Double price;

    private Integer stock;//库存
    private String intro;//介绍

    private Integer categoryId;//分类ID

    private Category category;

    private boolean topScroll;//条幅推荐
    private boolean topLarge;//大图推荐
    private boolean topSmall;//小图推荐

    public Goods() {
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", images1='" + images1 + '\'' +
                ", images2='" + images2 + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", intro='" + intro + '\'' +
                ", CategoryId=" + categoryId +
                ", category=" + category +
                ", topScroll=" + topScroll +
                ", topLarge=" + topLarge +
                ", topSmall=" + topSmall +
                '}';
    }

    public Goods(Integer gid, String gname, String images1, String images2, Double price, Integer stock, String intro, Integer categoryId, Category category, boolean topScroll, boolean topLarge, boolean topSmall) {
        this.gid = gid;
        this.gname = gname;
        this.images1 = images1;
        this.images2 = images2;
        this.price = price;
        this.stock = stock;
        this.intro = intro;
        this.categoryId = categoryId;
        this.category = category;
        this.topScroll = topScroll;
        this.topLarge = topLarge;
        this.topSmall = topSmall;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getImages1() {
        return images1;
    }

    public void setImages1(String images1) {
        this.images1 = images1;
    }

    public String getImages2() {
        return images2;
    }

    public void setImages2(String images2) {
        this.images2 = images2;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isTopScroll() {
        return topScroll;
    }

    public void setTopScroll(boolean topScroll) {
        this.topScroll = topScroll;
    }

    public boolean isTopLarge() {
        return topLarge;
    }

    public void setTopLarge(boolean topLarge) {
        this.topLarge = topLarge;
    }

    public boolean isTopSmall() {
        return topSmall;
    }

    public void setTopSmall(boolean topSmall) {
        this.topSmall = topSmall;
    }
}
