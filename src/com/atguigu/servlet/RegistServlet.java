package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;


public class RegistServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String vcode = request.getParameter("vcode");
        HttpSession sessionTest = request.getSession();
        String kaptcha_session_key = sessionTest.getAttribute("KAPTCHA_SESSION_KEY").toString();

        //2. 调用dao层
        User user = userService.checkUserName(username);
        //3. 判断【携带数据】
        if (vcode != null && vcode.equals(kaptcha_session_key)) {
            sessionTest.removeAttribute("KAPTCHA_SESSION_KEY");
            User userin = new User(null, username, password, email);
            if (user == null) {
                //用户名可用，调用saveUser()，重定向注册成功页面
                userService.saveUser(userin);
                request.getSession().setAttribute("user", userin);
                response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
            } else {
                //用户名已存在，转发注册页面
                request.setAttribute("msg", "用户名已存在，请重新输入！");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "验证码错误，请重新输入！");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }

    }

    protected void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        User user = userService.checkUserName(name);
        if(user != null){
            //不可用
            response.getWriter().print("false");
        }else{
            //可用
            response.getWriter().print("true");
        }
    }
}