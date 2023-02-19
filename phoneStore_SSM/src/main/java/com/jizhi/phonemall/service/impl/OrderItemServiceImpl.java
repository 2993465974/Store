package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.entity.OrderItem;

import com.jizhi.phonemall.mapper.OrderItemMapper;
import com.jizhi.phonemall.service.GoodsService;
import com.jizhi.phonemall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("orderItemService")
@Transactional
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    @Qualifier("orderItemMapper")
    private OrderItemMapper orderItemMapper;
    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;

    /**
     * 添加购物项
     * @param item
     * @return
     */
    @Override
    public int addOrderItem(OrderItem item) {
        int number = orderItemMapper.insertSelective(item);
        return number;
    }

    /**
     * 通过总项ID查找对应的子购物项
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> findOrdersItemByOrderId(int orderId) {
        List<OrderItem> items = orderItemMapper.finAllByOrderId(orderId);
        if(items != null) {
            //查询购物项对应的商品
            for (OrderItem item : items) {
                Goods goods = goodsService.findGoodsById(item.getGoodsid());
                item.setGoods(goods);
            }
        }
        return items;
    }

    @Override
    public int edit(OrderItem item) {
        int number = orderItemMapper.updateByPrimaryKey(item);
        return number;
    }

    @Override
    public int del(Integer id) {
        int number = orderItemMapper.deleteByPrimaryKey(id);
        return number;
    }

    @Override
    public Integer delByGid(Integer gid) {
        //先查该商品是不是不在购物项中
        List<OrderItem> orderItemList = orderItemMapper.findByGid(gid);
        if(orderItemList == null)
            return 0;
        return orderItemMapper.delOrderItemByGid(gid);
    }
}
