package com.atguigu.servlet;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();

    /**
     * 完成订单
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //获取cart 和 user 用于订单生成
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        String order = orderService.createOrder(cart, user);
        session.setAttribute("order", order);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void getOrderList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) response.sendRedirect(request.getContextPath() + "/pages/manager/order_manager.jsp");
        List<Order> orderList = orderService.getOrderList(user.getId().toString());
        session.setAttribute("orderList", orderList);
        response.sendRedirect(request.getContextPath() + "/pages/manager/order_manager.jsp");
    }
}