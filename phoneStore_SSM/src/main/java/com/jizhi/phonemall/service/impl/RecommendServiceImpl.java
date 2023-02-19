package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.entity.Recommend;
import com.jizhi.phonemall.mapper.RecommendMapper;
import com.jizhi.phonemall.service.GoodsService;
import com.jizhi.phonemall.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("recommendService")
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    @Qualifier("recommendMapper")
    private RecommendMapper recommendMapper;

    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;

    /**
     * 全部查询
     *
     * @return
     */
    @Override
    public List<Recommend> findAll(Byte type) {
        List<Recommend> list = recommendMapper.findAll(type);
        for (Recommend item : list) {
            item.setGoods(goodsService.findGoodsById(item.getGoodsId()));
        }
        return list;
    }

    /**
     * 根据商品ID查询，可以有多个推荐
     *
     * @param gId
     * @return
     */
    @Override
    public List<Recommend> findRecommendByGoodsId(Integer gId) {
        List<Recommend> list = recommendMapper.findByGoodsId(gId);
        return list;
    }

    /**
     * 根据ID查询
     *
     * @param rid
     * @return
     */
    @Override
    public Recommend findRecommendById(Integer rid) {
        Recommend re = recommendMapper.findById(rid);
        return re;
    }

    @Override
    public int addRecommend(Recommend recommend) {
        Goods goods = goodsService.findGoodsById(recommend.getGoodsId());
        if(recommend.getType() == 1){
            goods.setTopScroll(true);
        }else if(recommend.getType() == 2){
            goods.setTopLarge(true);
        }else{
            goods.setTopSmall(true);
        }

        goodsService.editGoods(goods);
        int number = recommendMapper.insert(recommend);
        return number;
    }

    @Override
    public int delRecommend(Integer goodsId, Byte type) {
        if(goodsId ==null || type == null)
            return 0;
        int number = recommendMapper.del(goodsId,type);
        return number;
    }

    @Override
    public boolean isExist(Integer goodsId, Byte type) {
        Recommend res = recommendMapper.isExist(goodsId,type);
        if(res == null)
            return false;
        else
            return true;
    }

    @Override
    public List<Recommend> findRecommendByType(Integer type) {

        return recommendMapper.findByType(type);
    }

    @Override
    public int editRecommend(Recommend recommend) {
        int number = recommendMapper.update(recommend);
        return number;
    }
}
