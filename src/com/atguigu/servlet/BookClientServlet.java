package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookClientServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        Page<Book> page = bookService.getBooksByPage(pageNo);

        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);
    }

    protected void getBooksByRange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        Page<Book> page = bookService.getBooksByRange(pageNo, min, max);

        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);
    }

}
