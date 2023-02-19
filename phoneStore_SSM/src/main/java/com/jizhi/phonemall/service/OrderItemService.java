package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    int addOrderItem(OrderItem item);

    /**
     * 通过订单ID查询购物项
     * @param orderId
     * @return
     */
    List<OrderItem> findOrdersItemByOrderId(int orderId);

    int edit(OrderItem item);

    int del(Integer id);

    /**
     * 通过商品ID删除购物项
     * @param gid
     * @return
     */
    Integer delByGid(Integer gid);
}
