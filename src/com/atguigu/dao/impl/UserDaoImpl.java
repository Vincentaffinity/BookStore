package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

/**
 * @Author Chunsheng Zhang   Cat  is-a  Pet    接口    fdm   has-a  lock
 * @Date 2020/8/8             b  kb  mb  gb tb  pb  xb  zb
 * @Time 10:26
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    /**
     * 获取用户
     * @param user
     * @return
     */
    @Override
    public User getUser(User user) {
        String sql = "SELECT id,username,`password`,email FROM user" +
                " WHERE username=? AND `password`=?";
        return this.getBean(sql,user.getUsername(),user.getPassword());
    }

    /**
     * 确保用户存在
     * @param username
     * @return
     */
    @Override
    public User checkUserName(String username) {
        String sql = "SELECT id,username,`password`,email FROM user WHERE username=?";
        return this.getBean(sql,username);
    }

    /**
     * 保存客户信息
     * @param user
     */
    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO user(username,`password`,email) VALUES(?,?,?);";
        this.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

}
