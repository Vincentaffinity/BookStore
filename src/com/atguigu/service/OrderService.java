package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;

import java.util.List;

public interface OrderService {

    /**
     * 去结账
     *      1. 生成订单                   【OrderDao】
     *      2. 生成订单详情               【OrderItemDao】
     *      3. 更改购物项库存和销量        【BookDao】
     *      4. 清空购物车                 【Cart】
     */

    /**
     * 结账
     * @param cart
     * @param user
     * @return
     */
    public String createOrder(Cart cart, User user);

    /**
     * 获取订单列表
     * @param userId
     * @return
     */
    public List<Order> getOrderList(String userId);
}
