package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.entity.OrderItem;
import com.jizhi.phonemall.entity.Orders;
import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.mapper.OrdersMapper;
import com.jizhi.phonemall.service.GoodsService;
import com.jizhi.phonemall.service.OrderItemService;
import com.jizhi.phonemall.service.OrderService;
import com.jizhi.phonemall.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO  这里问题大大滴，细整
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    @Qualifier("ordersMapper")
    private OrdersMapper ordersMapper;
    @Autowired
    @Qualifier("goodsService")
    private GoodsService goodsService;
    @Autowired
    @Qualifier("usersService")
    private UsersService usersService;
    @Autowired
    @Qualifier("orderItemService")
    private OrderItemService orderItemService;

    /**
     * 查询所有订单(以管理员身份)
     *
     * @param status
     * @return
     */
    @Override
    public List<Orders> findAllOrders(Integer status) {
        List<Orders> list = ordersMapper.findAllByStatus(status);
        if (list != null) {
            //查询订单对应的购物项
            for (Orders orders : list) {
                findOrderItemAndAdd(orders);
            }
        }
        return list;
    }

    /**
     * 通过id查询某一订单
     *
     * @param oid
     * @return
     */
    @Override
    public Orders findOrdersById(Integer oid) {
        if(oid == null)
            return null;
        Orders orders = ordersMapper.selectByPrimaryKey(oid);
        if (orders != null) {
            findOrderItemAndAdd(orders);
        }
        return orders;
    }



    /**
     * 获取某一用户的所有订单
     *
     * @param uid
     * @return
     */
    @Override
    public List<Orders> findOrdersByUid(Integer uid) {

        List<Orders> ordersList = ordersMapper.getOrdersByUid(uid);

        if (ordersList != null) {
            for (Orders orders : ordersList) {
                findOrderItemAndAdd(orders);
            }
        }
        return ordersList;
    }

    /**
     * 根据订单id查询购物项并添加到对应的订单对象中
     * @param orders
     */
    private void findOrderItemAndAdd(Orders orders){
        List<OrderItem> orderItemList = orderItemService.findOrdersItemByOrderId(orders.getOid());
        if (orderItemList != null) {
            orders.setOrderItems(orderItemList);
            orders.setUser(usersService.findUserById(orders.getUserid()));
        }
    }

//
//    /**
//     * @param goods
//     * @return
//     */
//    @Override
//    public Orders addOrder(Goods goods) {
//        ArrayList<OrderItem> orderItems = new ArrayList<>();
//        OrderItem order = addItem(goods);
//        orderItems.add(order);
//        Orders orders = new Orders();
//        orders.setOrderItems(orderItems);
//        return orders;
//
//    }

//    @Override
//    public Orders find(int number) {
//        return null;
//    }

    @Override
    public int saveOrders(Orders orders) {
        orders.setStatus(Orders.STATUS_UNPAY);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        orders.setSystime(timeStamp);
        //未能返回自增id
        ordersMapper.insertSelective(orders);
        int oid = ordersMapper.findAllByUidAndPaytype(orders.getUserid(),orders.getPaytype()).getOid();

        return oid;
    }

    /**
     * 通过用户ID和支付类型进行查找
     *
     * @param uid
     * @param paytype
     * @return
     */
    @Override
    public Orders findOrdersByUidAndPaytype(Integer uid, int paytype) {
        Orders orders = ordersMapper.findAllByUidAndPaytype(uid, paytype);
        //通过orders的ID查找对应的子购物项
        if (orders == null) {
            return orders;
        }
        List<OrderItem> items = orderItemService.findOrdersItemByOrderId(orders.getOid());
        if (items != null)
            orders.setOrderItems(items);
        return orders;
    }


    /**
     * 通过用户和商品创建购物项
     *
     * @param user
     * @param goods
     * @return
     */
    @Override
    public Orders creatOrder(Users user, Goods goods) {
        Orders orders = new Orders();
        orders.setTotal(goods.getPrice());//设置商品价格
        orders.setAmount(1);//当前仅一件商品传入
        orders.setStatus(Orders.STATUS_UNPAY);//未支付状态，只有未支付才能出现在购物车中
        orders.setPaytype(Orders.PAYTYPE_NOPAY);
        orders.setRealname(user.getRealname());
        orders.setPhone(user.getPhone());
        orders.setAddress(user.getAddress());
        orders.setUserid(user.getUid());
        int orderId = saveOrders(orders);
        OrderItem item = new OrderItem();//创建购物项
        item.setOrderid(orderId);
        item.setGoodsid(goods.getGid());
        item.setGoods(goods);
        item.setPrice(goods.getPrice());
        item.setAmount(1);
        item.setTotal();
        orderItemService.addOrderItem(item);//向数据库中添加购物项
        orders.getOrderItems().add(item);//向总项的集合中添加购物项
        return orders;
    }

    /**
     * 更新购物总项中的所有信息
     *
     * @param orders
     * @param goods
     * @param type   1代表增加 2代表减少 3代表删除
     * @return
     */
    @Override
    public Orders updateAllInfo(Orders orders, Goods goods, Integer type) {
        //首先查找对比goods是否存在于orders的orderItems中
        int temp = -1;//orders中没有goods
        List<OrderItem> orderItems = orders.getOrderItems();
        OrderItem tOI = null;
        for (OrderItem item : orderItems) {
            //当商品已存在时
            if (item.getGoodsid() == goods.getGid() && type == 1) {
                item.setAmount(item.getAmount() + 1);//数量+1
                item.setTotal();//购物项总价格增加
                orderItemService.edit(item);
                //库存减1
                goods.setStock(goods.getStock() - 1);
                temp = 0;
            }
            //当数量为0时，无法再减少
            if (item.getGoodsid() == goods.getGid() && type == 2 && item.getAmount() >= 1) {
                item.setAmount(item.getAmount() - 1);//数量-1
                item.setTotal();//购物项总价格增加
                orderItemService.edit(item);
                goods.setStock(goods.getStock() + 1);
                temp = 0;
            }
            //删除商品，先暂时记录该商品
            if (item.getGoodsid() == goods.getGid() && type == 3) {
                goods.setStock(item.getAmount() + goods.getStock());
                tOI = item;
            }
        }
        goodsService.editGoods(goods);
        if (tOI != null) {
            orders.getOrderItems().remove(tOI);
            orderItemService.del(tOI.getId());
            orders.setAmount(orders.getAmount() - 1);
            temp = 0;
        }
        if (temp == -1) {//购物车中没有该商品
            OrderItem orderItem = new OrderItem();
            orderItem.setGoods(goods);
            orderItem.setGoodsid(goods.getGid());
            orderItem.setAmount(1);
            orderItem.setPrice(goods.getPrice());
            orderItem.setOrderid(orders.getOid());
            orderItem.setTotal();
            orderItemService.addOrderItem(orderItem);
            orders.getOrderItems().add(orderItem);
            orders.setAmount(orders.getAmount() + 1);
        }
        orders.calTotal();
        ordersMapper.updateByPrimaryKeySelective(orders);
        return orders;
    }

    @Override
    public int updateInfo(Orders order) {

        List<OrderItem> itemList = orderItemService.findOrdersItemByOrderId(order.getOid());
        List<OrderItem> newItemList = new ArrayList<>();
        for (OrderItem item : itemList) {
            //剔除商品数量为0的购物项
            if(item.getAmount() > 0){
                newItemList.add(item);
            }else{
                orderItemService.del(item.getId());
            }
        }
        order.setOrderItems(newItemList);
        order.setAmount(newItemList.size());
        int number = ordersMapper.updateByPrimaryKeySelective(order);
        return number;
    }

    public OrderItem addItem(Goods goods) {
        OrderItem item = new OrderItem();
        item.setGoodsid(goods.getGid());
        item.setAmount(1);
        item.setPrice(goods.getPrice());
        item.setTotal();
        Goods good = goodsService.findGoodsById(goods.getGid());
        item.setGoods(good);
        return item;
    }

    public List<OrderItem> findAllItem(int orderId) {
        List<OrderItem> items = orderItemService.findOrdersItemByOrderId(orderId);
        return items;
    }
}
