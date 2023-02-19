package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.Goods;

import java.util.List;

public interface GoodsService {
    /**
     * 按照字符模糊查询
     * @param gname
     * @return
     */
    List<Goods> findGoodsByLikeName(String gname);

    /**
     * 查询(在不同情况下)所有分类
     * @param categoryId 分类id，为null代表全部查询
     * @param status 是否与推荐表一同查询
     * @return
     */
    List<Goods> findAllGoods(Integer categoryId,Integer status);

    int findTotalrecords(Integer categoryId);

    /**
     * 通过商品id查询商品
     * @param gid
     * @return
     */
    Goods findGoodsById(Integer gid);

    int addGoods(Goods goods);
    int delGoods(Integer gid);
    int editGoods(Goods goods);
}
