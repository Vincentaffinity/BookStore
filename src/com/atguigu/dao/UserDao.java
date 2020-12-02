package com.atguigu.dao;

import com.atguigu.bean.User;

import java.util.List;


public interface UserDao {

    /**
     * 通过用户名和密码查询用户信息
     * SELECT * FROM users WHERE username=? AND `password`=?
     *
     */
    public User getUser(User user);

    /**
     * 检查用户名是否存在
     * SELECT id,username,`password`,email FROM users WHERE username=?
     */
    public User checkUserName(String username);

    /**
     * 保存用户信息
     * INSERT INTO users(username,`password`,email) VALUES(?,?,?);
     */
    public void saveUser(User user);

}
