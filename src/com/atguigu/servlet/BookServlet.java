package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookServiceImpl bookService = new BookServiceImpl();

    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> list = bookService.getBookList();
        request.setAttribute("booklist", list);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

    protected void saveBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("book_name");
        String price = request.getParameter("book_price");
        String author = request.getParameter("book_author");
        String sales = request.getParameter("book_sales");
        String stock = request.getParameter("book_stock");

        bookService.saveBook(new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock),null));
        //response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooks");
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooksByPage");
    }

    protected void delBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("book_name");
        String price = request.getParameter("book_price");
        String author = request.getParameter("book_author");
        String sales = request.getParameter("book_sales");
        String stock = request.getParameter("book_stock");

        String bookId = request.getParameter("bookId");
        bookService.delBook(Integer.parseInt(bookId));
        //response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooks");
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooksByPage");
    }

    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");
        Book book = bookService.getBookById(Integer.parseInt(bookId));
        request.setAttribute("book", book);
        request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request,response);
    }

    protected void updateOrSaveBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");

        String title = request.getParameter("book_name");
        String price = request.getParameter("book_price");
        String author = request.getParameter("book_author");
        String sales = request.getParameter("book_sales");
        String stock = request.getParameter("book_stock");

        if(bookId == null || "".equals(bookId)){
            bookService.saveBook(new Book(null, title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock),null));
        }else{
            bookService.updateBook(new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock),null));
        }
        //response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooks");
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooksByPage");
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("bookId");

        String title = request.getParameter("book_name");
        String price = request.getParameter("book_price");
        String author = request.getParameter("book_author");
        String sales = request.getParameter("book_sales");
        String stock = request.getParameter("book_stock");

        bookService.updateBook(new Book(Integer.parseInt(bookId), title, author, Double.parseDouble(price), Integer.parseInt(sales), Integer.parseInt(stock),null));
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=getBooks");
    }


    protected void getBooksByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        Page<Book> page = bookService.getBooksByPage(pageNo);

        request.setAttribute("page", page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }

}
