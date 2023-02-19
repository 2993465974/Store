package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.OrderItem;
import com.jizhi.phonemall.entity.Orders;
import com.jizhi.phonemall.entity.OrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> findAllByStatus(Integer status);

    @Select("select * from orders where userId=#{uid}")
    List<Orders> getOrdersByUid(Integer uid);

//    void add(OrderItem item);

    @Select("select * from orders where userId=#{uid} and paytype=#{paytype}")
    Orders findAllByUidAndPaytype(@Param("uid") Integer uid,@Param("paytype") int paytype);
}