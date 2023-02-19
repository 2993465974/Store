package com.jizhi.phone;

import com.jizhi.phonemall.entity.Admin;

import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.service.AdminService;
import com.jizhi.phonemall.service.UsersService;
import com.jizhi.phonemall.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class AdminsTest {
    @Autowired
    private AdminService adminService;
    @Test
    public void testMD5(){
        System.out.println(MD5Util.encode("123456"));
        System.out.println(MD5Util.encode("654321"));
        System.out.println(MD5Util.encode("password"));
    }


    @Test
    public void testAdd(){
        Admin admins = new Admin();
        admins.setId(1);
        admins.setUsername("菜稽");
        admins.setPassword("123456");
       adminService.addAdmin(admins);

    }
    @Test
    public void testupdate(){
        Admin admins = new Admin();
        admins.setId(1);
        admins.setUsername("菜稽");
        admins.setPassword("123456");
       adminService.editAdmin(admins);

    }
    @Test
    public void testlogin(){

        System.out.println(adminService.checkAdmin("菜稽","123456"));

    }
}
