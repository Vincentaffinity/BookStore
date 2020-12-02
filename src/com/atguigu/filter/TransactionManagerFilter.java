package com.atguigu.filter;

import com.atguigu.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerFilter extends HttpFilter {

    /**
     * 使用过滤器统一管理事物 异常
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //开启事务
        Connection connection = null;
        try{
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            filterChain.doFilter(request, response);
            //无异常 提交
            connection.commit();
        }catch (Exception e){
            //有异常
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            //显示友好的页面
            response.sendRedirect(request.getContextPath()+"/pages/error/transaction_error.jsp");
        }finally {
            JDBCUtils.releaseConnection();
        }
    }
}
