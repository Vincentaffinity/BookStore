package com.atguigu.filter;

import com.atguigu.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter extends HttpFilter {

    /**
     * 购物时的登录验证
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            request.setAttribute("loginmsg", "请先登录后再结账");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }else{
            filterChain.doFilter(request, response);
        }
    }
}
