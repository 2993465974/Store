package com.jizhi.phonemall.service;




import com.jizhi.phonemall.entity.Admin;

import java.util.List;

public interface AdminService {
    /**
     * 验证管理员账户名、密码是否在数据库中存在
     * @param username
     * @param password
     * @return
     */
    boolean checkAdmin(String username,String password);

    /**
     * 用户是否存在
     * @param username
     * @return
     */
    boolean isExist(String username);

    /**
     * 添加用户
     * @param admin
     * @return
     */
    int addAdmin(Admin admin);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delAdmin(Integer id);

    /**
     * 修改用户
     * @param admin
     * @return
     */
    int editAdmin(Admin admin);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    Admin findAdminById(Integer id);

    /**
     * 查询所有用户(分页)
     * @return
     */
    List<Admin> findAllAdmins();




}
