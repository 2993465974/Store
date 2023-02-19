package com.jizhi.phonemall.service;

import com.jizhi.phonemall.entity.Users;

import java.util.List;

public interface UsersService {
    /**
     * 验证用户名、密码
     * @param username
     * @param password
     * @return
     */
    boolean checkUser(String username,String password);

    /**
     * 用户是否存在
     * @param username
     * @return
     */
    boolean isExist(String username);

    /**
     * 添加用户
     * @param users
     * @return
     */
    int addUser(Users users);

    /**
     * 删除用户
     * @param uid
     * @return
     */
    int delUser(Integer uid);

    /**
     * 修改用户
     * @param users
     * @return
     */
    int editUser(Users users);

    /**
     * 根据ID查询用户
     * @param uid
     * @return
     */
    Users findUserById(Integer uid);

    /**
     * 查询所有用户(分页)
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Users> findAllUsers(int startIndex,int pageSize);

    /**
     * 模糊查询用户
     * @param username
     * @return
     */
    List<Users> findUserByLikeName(String username);

    /**
     * 查询用户的总条数
     * @return
     */
    int findTotalItem();
}
