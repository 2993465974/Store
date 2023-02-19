package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.Recommend;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendMapper {
    @Select("select * from recommend where type = #{type} order by rid")
    List<Recommend> findAll(Byte type);
    @Select("select * from recommend where goodsId = #{goodsId}")
    List<Recommend> findByGoodsId(Integer goodsId);
    @Select("select * from recommend where rid = #{rid}")
    Recommend findById(Integer rid);

    int insert(Recommend recommend);

    int del(@Param("goodsId") Integer goodsId,@Param("type") Byte type);

    int update(Recommend recommend);
    @Select("select * from recommend where goodsId = #{goodsId} and type = #{type}")
    Recommend isExist(@Param("goodsId") Integer goodsId,@Param("type") Byte type);
    @Select("select * from recommend where type = #{type}")
    List<Recommend> findByType(Integer type);
}
