package com.atguigu.dao;

import com.atguigu.bean.OrderItem;

public interface OrderItemDao {

    /**
     * 生成订单详情
     * @param orderItem
     */
    public void createOrderItem(OrderItem orderItem);

    /**
     * 批处理订单
     * @param params
     */
    public void batchOrderItem(Object[][] params);
}
