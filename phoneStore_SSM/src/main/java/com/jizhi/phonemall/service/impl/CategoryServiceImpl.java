package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Category;
import com.jizhi.phonemall.mapper.CategoryMapper;
import com.jizhi.phonemall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("categoryService")//开启Service注解 便于 装配
@Transactional  //开启方法的注解
public class CategoryServiceImpl implements CategoryService {
    @Autowired //自动装配
    private CategoryMapper categoryMapper;
    /**
     * 全部查询
     *
     * @param cname 如果为空代表全部查询，有参数代表为模糊查询
     * @return
     */
    @Override
    public List<Category> findAllCategory(String cname) {
        List<Category> list = categoryMapper.findAll(cname);
        return list;
    }

    /**
     * 通过ID查询Category
     *
     * @param cid
     * @return
     */
    @Override
    public Category findCategoryById(Integer cid) {
        Category category = categoryMapper.findById(cid);
        return category;
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @Override
    public int addCategory(Category category) {
        int number = categoryMapper.add(category);
        return number;
    }

    /**
     * 修改
     *
     * @param category
     * @return
     */
    @Override
    public int editCategory(Category category) {
        int number = categoryMapper.update(category);
        return number;
    }

    /**
     * 删除
     *
     * @param cid
     * @return
     */
    @Override
    public int delCategory(Integer cid) {
        int number = categoryMapper.del(cid);
        return number;
    }

    @Override
    public List<Category> findCategoryByLikeName(String cname) {
        return categoryMapper.findByLinkName(cname);
    }
}
