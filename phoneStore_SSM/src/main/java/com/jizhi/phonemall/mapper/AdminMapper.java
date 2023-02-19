package com.jizhi.phonemall.mapper;

import com.jizhi.phonemall.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminMapper {
    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    Admin checkAdmin(@Param("username") String username,@Param("password") String password);

    Admin findAdminByUsername(String username);

    int add(Admin admin);

    int del(Integer id);

    int update(Admin admin);
    @Select("select * from admin where id = #{id}")
    Admin findAdminById(Integer id);
    @Select("select * from admin")
    List<Admin> finAll();
}
