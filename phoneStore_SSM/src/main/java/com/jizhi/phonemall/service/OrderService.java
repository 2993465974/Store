package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.entity.Orders;
import com.jizhi.phonemall.entity.Users;

import java.util.List;

public interface OrderService {
    /**
     * 根据条件查询所有订单
     * @param status 订单状态，为null代表查询全部
     * @return
     */
    List<Orders> findAllOrders(Integer status);

    /**
     * 通过id查询某一订单
     * @param oid
     * @return
     */
    Orders findOrdersById(Integer oid);

    /**
     * 获取某一用户的所有订单
     * @param uid
     * @return
     */
    List<Orders> findOrdersByUid(Integer uid);

//    Orders addOrder(Goods goods);

//    Orders find(int number);

    int saveOrders(Orders orders);

    Orders findOrdersByUidAndPaytype(Integer uid, int paytype);

    /**
     * 通过用户和商品创建购物项
     * @param user
     * @param goods
     * @return
     */
    Orders creatOrder(Users user,Goods goods);

    /**
     * 更新购物总项中的所有信息
     * @param orders
     * @param goods
     * @param type
     * @return
     */
    Orders updateAllInfo(Orders orders, Goods goods,Integer type);

    /**
     * 更新信息内容(商品数量等)
     * @param order
     * @return
     */
    int updateInfo(Orders order);
}
