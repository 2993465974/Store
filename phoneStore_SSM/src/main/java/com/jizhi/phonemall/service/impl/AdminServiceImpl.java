package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Admin;
import com.jizhi.phonemall.mapper.AdminMapper;
import com.jizhi.phonemall.service.AdminService;
import com.jizhi.phonemall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("adminService")//开启Service注解 便于 装配
@Transactional  //开启方法的注解
public class AdminServiceImpl implements AdminService {

    @Autowired
    @Qualifier("adminMapper")
    private AdminMapper adminMapper;

    /**
     * 验证管理员账户名、密码是否在数据库中存在
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean checkAdmin(String username, String password) {
        return adminMapper.checkAdmin(username, MD5Util.encode(password)) != null;
    }

    /**
     * 用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean isExist(String username) {
        return adminMapper.findAdminByUsername(username) != null;
    }

    /**
     * 添加用户
     *
     * @param admin
     * @return
     */
    @Override
    public int addAdmin(Admin admin) {
        admin.setPassword(MD5Util.encode(admin.getPassword()));
        return adminMapper.add(admin);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public int delAdmin(Integer id) {
        return adminMapper.del(id);
    }

    /**
     * 修改用户
     *
     * @param admin
     * @return
     */
    @Override
    public int editAdmin(Admin admin) {
        admin.setPassword(MD5Util.encode(admin.getPassword()));
        return adminMapper.update(admin);
    }

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Admin findAdminById(Integer id) {
        return adminMapper.findAdminById(id);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Admin> findAllAdmins() {
        return adminMapper.finAll();
    }
}
