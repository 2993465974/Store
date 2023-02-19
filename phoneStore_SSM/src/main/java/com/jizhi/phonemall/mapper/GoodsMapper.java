package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GoodsMapper {


    List<Goods> findByName(String gname);

//    List<Goods> find(@Param("startIndex")int startIndex,
//                     @Param("pageSize")int pageSize);

    List<Goods> find(@Param("categoryId") Integer categoryId,@Param("status")Integer status);
//    @Select("select count(*) from goods")
//    int findTotal();
//    @Select("select count(*) from goods where categoryId = #{categoryId}")
    int findTotal(Integer categoryId);
    @Select("select * from goods where gid = #{gid}")
    Goods findById(Integer gid);

    int add(Goods goods);

    int del(Integer gid);

    int update(Goods goods);
}
