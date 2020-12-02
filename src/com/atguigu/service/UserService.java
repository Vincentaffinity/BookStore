package com.atguigu.service;

import com.atguigu.bean.User;


public interface UserService {

    /**
     * 获取用户对象
     * @param user
     * @return
     */
    public User getUser(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    public User checkUserName(String username);

    /**
     * 保存客户
     * @param user
     */
    public void saveUser(User user);

}
