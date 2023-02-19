package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.OrderItem;
import com.jizhi.phonemall.entity.OrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemMapper {
    long countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
    @Select("select * from orderItem where orderId=#{orderId}")
    List<OrderItem> finAllByOrderId(int orderId);
    @Select("delete from orderItem where goodsId=#{gid}")
    Integer delOrderItemByGid(Integer gid);

    /**
     * 通过商品id查询订单项
     * @param gid
     * @return
     */
    @Select("select * from orderItem where goodsId=#{gid}")
    List<OrderItem> findByGid(Integer gid);
}