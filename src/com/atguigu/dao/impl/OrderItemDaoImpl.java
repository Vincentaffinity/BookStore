package com.atguigu.dao.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    /**
     * 生成订单详情
     * @param orderItem
     */
    @Override
    public void createOrderItem(OrderItem orderItem) {
        String sql = "insert into order_items(count, amount, title, author, price, img_path, orderid) values(?,?,?,?,?,?,?)";
        this.update(sql, orderItem.getCount(), orderItem.getAmount(), orderItem.getTitle(),
                orderItem.getAuthor(), orderItem.getPrice(),
                orderItem.getImgPath(), orderItem.getOrderId());
    }

    /**
     * 批处理加入订单详情
     * @param params
     */
    @Override
    public void batchOrderItem(Object[][] params) {
        String sql = "insert into order_items(count, amount, title, author, price, img_path, orderid) values(?,?,?,?,?,?,?)";
        this.batchUpdate(sql, params);
    }
}
