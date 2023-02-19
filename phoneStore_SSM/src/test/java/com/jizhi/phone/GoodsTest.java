package com.jizhi.phone;

import com.jizhi.phonemall.entity.Category;
import com.jizhi.phonemall.entity.Goods;
import com.jizhi.phonemall.service.CategoryService;
import com.jizhi.phonemall.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class GoodsTest {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsService goodsService;

    @Test
    public void testfindAll(){
        String cname = null;
        System.out.println("全部查询：");
        List<Goods> allCategory = goodsService.findAllGoods(1,0);
        for (Goods goods : allCategory) {
            System.out.println(goods);
        }

    }
    @Test
    public void testfindtotal(){
        String cname = null;
        System.out.println("查询条数：");
        int number = goodsService.findTotalrecords(1);
        System.out.println(number);
    }
    @Test
    public void testadd(){
        String cname = null;
        System.out.println("添加：");
        Goods goods = new Goods();
        goods.setGname("荣耀62");
        goods.setPrice(8999.0);
        goods.setStock(15);
        goods.setIntro("麒麟986");
        goods.setCategoryId(1);
        goods.setImages1("20200661.png");
        goods.setImages2("20200662.png");
        System.out.println(goods);
        goodsService.addGoods(goods);

    }
}
