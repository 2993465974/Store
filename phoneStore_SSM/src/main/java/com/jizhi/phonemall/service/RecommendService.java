package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.Recommend;
import com.sun.org.apache.regexp.internal.RE;

import java.util.List;

/**
 * 推荐服务
 * 条幅推荐(海报推荐、轮播图推荐)
 * 热销推荐
 * 性价比推荐
 */
public interface RecommendService {
    /**
     * 全部查询
     * @param type
     * @return
     */
    List<Recommend> findAll(Byte type);

    /**
     * 根据商品ID查询，可以有多个推荐
     * @param gId
     * @return
     */
    List<Recommend> findRecommendByGoodsId(Integer gId);

    /**
     * 根据ID查询
     * @param rid
     * @return
     */
    Recommend findRecommendById(Integer rid);

    /**
     * 通过Recommend对象向数据库推荐表中添加数据
     * @param recommend
     * @return
     */
    int addRecommend(Recommend recommend);

    int editRecommend(Recommend recommend);

    /**
     * 删除推荐
     * @param goodsId
     * @param type
     * @return
     */
    int delRecommend(Integer goodsId, Byte type);

    /**
     * 判断是否存在该推荐
     * @param goodsId
     * @param type
     * @return
     */
    boolean isExist(Integer goodsId, Byte type);

    List<Recommend> findRecommendByType(Integer type);
}
