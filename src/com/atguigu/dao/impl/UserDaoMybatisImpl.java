package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;


public class UserDaoMybatisImpl extends BaseDao<User> implements UserDao {
    @Override
    public User getUser(User user) {

        return null;
    }

    @Override
    public User checkUserName(String username) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }
}
