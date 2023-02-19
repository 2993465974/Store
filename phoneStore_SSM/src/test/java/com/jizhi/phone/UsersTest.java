package com.jizhi.phone;

import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.service.CategoryService;
import com.jizhi.phonemall.service.UsersService;
import com.jizhi.phonemall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UsersTest {
    @Autowired
    private UsersService usersService;

    @Test
    public void String(){
//        String testString = "G:\\In school code\\phoneStore_SSM\\target\\phoneStore_SSM-1.0-SNAPSHOT\\pic\\20200605024025430.jpeg";
//        testString= testString.substring(0,testString.lastIndexOf("\\target"));
//        testString = testString.substring(testString.lastIndexOf("\\"),testString.length());
//        System.out.println(testString);
        Integer a = 1;
        System.out.println(a.toString());

    }

    @Test
    public void testMD5(){
        System.out.println(MD5Util.encode("123456"));
        System.out.println(MD5Util.encode("123"));
        System.out.println(MD5Util.encode("654321"));
        System.out.println(MD5Util.encode("password"));
        System.out.println(!StringUtils.isNotEmpty("realname".trim()));
    }

    @Test
    public void testFind(){
        List<Users> allUsers = usersService.findAllUsers(0, 2);
        System.out.println(allUsers);
    }
}
