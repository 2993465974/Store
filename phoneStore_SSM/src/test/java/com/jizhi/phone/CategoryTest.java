package com.jizhi.phone;

import com.jizhi.phonemall.entity.Category;
import com.jizhi.phonemall.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class CategoryTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testfindAll(){
        String cname = null;
        System.out.println("全部查询：");
        List<Category> allCategory = categoryService.findAllCategory(cname);
        System.out.println(allCategory);
        System.out.println("模糊查询");
        cname = "机";
        allCategory = categoryService.findAllCategory(cname);
        System.out.println(allCategory);
    }
}
