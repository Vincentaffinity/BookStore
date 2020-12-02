package com.atguigu.dao.impl;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

import java.util.List;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    /**
     * 生成新订单
     * @param order
     */
    @Override
    public void creatOrder(Order order) {
        String sql = "insert into orders(id, order_time, total_count, total_amount, state, user_id) values(?,?,?,?,?,?)";
        this.update(sql, order.getId(), order.getOrderTime(), order.getTotalCount(), order.getTotalAmount(), order.getState(), order.getUserId());
    }

    /**
     * 取得订单列表
     * @param userId
     * @return
     */
    @Override
    public List<Order> getOrder(String userId) {
        //String id, Date orderTime, int totalCount, double totalAmount, int state, int userId
        String sql = "select id, order_time orderTime, total_count totalCount, total_amount totalAmount, state, user_id userId from orders where user_id=?";
        List<Order> orderList = this.getBeanList(sql, userId);
        return orderList;
    }
}
