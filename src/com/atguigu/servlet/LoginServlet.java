package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkbox = request.getParameter("checkbox");
        //调用dao中getUser()
        User user = userService.getUser(new User(null, username, password, null));
        //判断登录成功|失败(路径跳转)【存在登录成功，重定向到登录成功页面，不存在登录失败，转发到登录页面】
        if(user ==  null || "".equals(user)){
            //不存在登录失败，转发到登录页面
            request.setAttribute("msg", "登录失败，请重新输入用户名和密码！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            //存在登录成功，重定向到登录成功页面
            if(checkbox != null){
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie: cookies){
                    if("JSESSIONID".equals(cookie.getName())){
                        cookie.setMaxAge(3600*24*7);
                        response.addCookie(cookie);
                        break;
                    }
                }
                request.getSession().setMaxInactiveInterval(3600*24*7);
                request.getSession().setAttribute("userSession", new User(null, username, password, null));
            }
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }
    }

}
