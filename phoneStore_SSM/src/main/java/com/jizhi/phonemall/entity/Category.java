package com.jizhi.phonemall.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer cid;
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    public Category() {
    }

    public Category(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }
}
