package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {

    List<Category> findAll(String cname);

    /**
     * 通过ID查询分类
     * @param cid
     * @return
     */
    Category findById(Integer cid);

    /**
     * 通过Category对象添加分类到数据库分类表
     * @param category
     * @return
     */
    int add(Category category);

    int update(Category category);

    int del(Integer cid);

    @Select("select * from category where cname like concat('%',#{cname},'%') order by cid desc")
    List<Category> findByLinkName(String cname);
}
