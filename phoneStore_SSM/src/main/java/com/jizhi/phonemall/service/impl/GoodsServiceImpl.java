package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Category;
import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.mapper.CategoryMapper;
import com.jizhi.phonemall.mapper.GoodsMapper;
import com.jizhi.phonemall.service.CategoryService;
import com.jizhi.phonemall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("goodsService")//开启Service注解 便于 装配
@Transactional  //开启方法的注解
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    @Qualifier("goodsMapper")
    private GoodsMapper goodsMapper;
    @Autowired
    @Qualifier("categoryService")
    private CategoryService categoryService;


    @Override
    public List<Goods> findGoodsByLikeName(String gname) {
        List<Goods> goodsList = goodsMapper.findByName(gname);
        return goodsList;
    }

    @Override
    public List<Goods> findAllGoods(Integer categoryId,Integer status) {
        List<Goods> goodsList = null;
            goodsList = goodsMapper.find(categoryId,status);
        return goodsList;
    }

    @Override
    public int findTotalrecords(Integer categoryId) {
        int totalItem = 0;
            totalItem = goodsMapper.findTotal(categoryId);
        return totalItem;
    }

    /**
     * 通过商品id查询商品
     * @param gid
     * @return
     */
    @Override
    public Goods findGoodsById(Integer gid) {
        //查找到商品信息(此时商品Category内容为null.需要单独查询)
        Goods goods = goodsMapper.findById(gid);
        Integer categoryId = goods.getCategoryId();
        Category category = categoryService.findCategoryById(categoryId);
        goods.setCategory(category);
        return goods;
    }

    @Override
    public int addGoods(Goods goods) {
        int number = goodsMapper.add(goods);
        return number;
    }

    @Override
    public int delGoods(Integer gid) {
        int number = goodsMapper.del(gid);
        return number;
    }

    @Override
    public int editGoods(Goods goods) {
        int number = goodsMapper.update(goods);
        return number;
    }
}
