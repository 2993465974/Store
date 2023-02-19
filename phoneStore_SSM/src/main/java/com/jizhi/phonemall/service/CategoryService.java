package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 全部查询
     * @param cname 如果为空代表全部查询，有参数代表为模糊查询
     * @return
     */
    List<Category> findAllCategory(String cname);

    /**
     * 通过ID查询Category
     * @param cid
     * @return
     */
    Category findCategoryById(Integer cid);

    /**
     * 添加分类
     * @param category
     * @return
     */
    int addCategory(Category category);

    /**
     * 修改
     * @param category
     * @return
     */
    int editCategory(Category category);

    /**
     * 删除
     * @param cid
     * @return
     */
    int delCategory(Integer cid);


    List<Category> findCategoryByLikeName(String cname);
}
