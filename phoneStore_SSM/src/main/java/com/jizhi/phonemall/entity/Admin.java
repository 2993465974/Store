package com.jizhi.phonemall.entity;

import java.io.Serializable;

/**
 * admins
 * @author 
 */
public class Admin implements Serializable {
    /**
     * 管理员编号
     */
    private Integer id;

    /**
     * 管理员
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
    }
}