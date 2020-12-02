package com.atguigu.service.impl;

import com.atguigu.bean.*;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    /**
     * 去结账
     *      1. 生成订单                   【OrderDao】
     *      2. 生成订单详情               【OrderItemDao】
     *      3. 更改购物项库存和销量        【BookDao】
     *      4. 清空购物车                 【Cart】
     */
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    /**
     * 结账
     * @param cart
     * @param user
     * @return
     */
    @Override
    public String createOrder(Cart cart, User user) {
        String orderid = System.currentTimeMillis() + "" + user.getId();
        //1. 生成订单 String id, Date orderTime, int totalCount, double totalAmount, int state, int userId
        Order order = new Order(orderid, new Date(), cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
        orderDao.creatOrder(order);
        List<CartItem> cartItems = cart.getCartItems();
        Object[][] paramsOrderItem = new Object[cartItems.size()][];
        Object[][] paramsBook = new Object[cartItems.size()][];

        //2. 生成订单详情 Integer id, int count, double amount, String title, String author, double price, String imgPath, String orderId
        for(int i = 0; i < cartItems.size(); i++){
            CartItem cartItem = cartItems.get(i);
            Book book = cartItem.getBook();
            //获取批处理参数
            paramsOrderItem[i] = new Object[]{cartItem.getCount(), cartItem.getAmount(), cartItem.getBook().getTitle(), cartItem.getBook().getAuthor(), cartItem.getAmount(),
                    cartItem.getBook().getImgPath(), orderid};
            paramsBook[i] = new Object[]{book.getStock()-cartItem.getCount(), book.getSales()+cartItem.getCount(),
                    book.getId()};
        }
        //批处理
        orderItemDao.batchOrderItem(paramsOrderItem);
        bookDao.batchUpdateBook(paramsBook);
        cart.emptyCart();
        return orderid;
    }

    /**
     * 获取订单列表
     * @param userId
     * @return
     */
    @Override
    public List<Order> getOrderList(String userId) {
        List<Order> orders = orderDao.getOrder(userId);
        return orders;
    }

}
