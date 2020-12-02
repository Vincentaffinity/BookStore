package com.atguigu.dao;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;

import java.util.List;

public interface OrderDao {

    /**
     * 创建新订单
     * @param order
     */
    public void creatOrder(Order order);

    /**
     * 取得订单列表
     * @param userId
     * @return
     */
    public List<Order> getOrder(String userId);
}
