package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;


public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 获取用户对象
     * @param user
     * @return
     */
    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    /**
     * 检查客户是否已经存在
     * @param username
     * @return
     */
    @Override
    public User checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    /**
     * 保存客户
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }


}
