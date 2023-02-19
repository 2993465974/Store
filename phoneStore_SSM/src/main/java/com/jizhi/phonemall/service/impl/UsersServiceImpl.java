package com.jizhi.phonemall.service.impl;

import com.jizhi.phonemall.entity.Users;
import com.jizhi.phonemall.mapper.UsersMapper;
import com.jizhi.phonemall.service.UsersService;
import com.jizhi.phonemall.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {

    @Autowired
    @Qualifier("usersMapper")
    private UsersMapper usersMapper;


    /**
     * 验证用户名、密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean checkUser(String username, String password) {
        boolean res = usersMapper.findUserByUsernameAndPassword(username, MD5Util.encode(password)) != null;
        return res;
    }

    /**
     * 用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public boolean isExist(String username) {
        boolean res = usersMapper.findUsername(username) != null;
        return res;
    }

    /**
     * 添加用户
     *
     * @param users
     * @return
     */
    @Override
    public int addUser(Users users) {
        users.setPassword(MD5Util.encode(users.getPassword()));
        int number = usersMapper.insert(users);
        return number;
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    @Override
    public int delUser(Integer uid) {
        int number = usersMapper.deleteByPrimaryKey(uid);
        return number;
    }

    /**
     * 修改用户
     *
     * @param users
     * @return
     */
    @Override
    public int editUser(Users users) {
        if (users.getPassword() != null)
            users.setPassword(MD5Util.encode(users.getPassword()));
        int number = usersMapper.updateByPrimaryKeySelective(users);
        return number;
    }

    /**
     * 根据ID查询用户
     *
     * @param uid
     * @return
     */
    @Override
    public Users findUserById(Integer uid) {
        Users users = usersMapper.selectByPrimaryKey(uid);
        return users;
    }

    /**
     * 查询所有用户(分页)
     *
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Users> findAllUsers(int startIndex, int pageSize) {
        List<Users> all = usersMapper.findAll(startIndex, pageSize);
        return all;
    }

    /**
     * 模糊查询用户
     *
     * @param username
     * @return
     */
    @Override
    public List<Users> findUserByLikeName(String username) {
        List<Users> users = usersMapper.findUserByLikeUsername(username);
        return users;
    }

    /**
     * 查询用户的总条数
     *
     * @return
     */
    @Override
    public int findTotalItem() {
        Long num = usersMapper.countByExample(null);
        int number = num.intValue();
        return number;
    }
}
