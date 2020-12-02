package com.atguigu.servlet;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    /**
     * 购物车添加图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addBookToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        Book book = bookService.getBookById(Integer.parseInt(bookId));
        Cart cart = (Cart)session.getAttribute("cart");

        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.addBookToCart(book);
        Integer stock = cart.getMap().get(bookId).getBook().getStock();
        Integer count = cart.getMap().get(bookId).getCount();
        if(count > stock){
            session.setAttribute("bookCountMsg", book.getTitle() + " " + "库存不足");
            cart.getMap().get(bookId).setCount(stock);
        }else{
            session.setAttribute("bookTitle", book.getTitle());
        }
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    /**
     * 删除购物车图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        Cart cart = (Cart) session.getAttribute("cart");
        cart.delBookById(bookId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.emptyCart();
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    /**
     * 更改购物车物品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void changeCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String bookId = request.getParameter("bookId");
        String count = request.getParameter("count");
        Cart cart = (Cart) session.getAttribute("cart");
        cart.changeCount(bookId, count);

        Double bookAmount = cart.getMap().get(bookId).getAmount();
        Integer totalCount = cart.getTotalCount();
        Double totalAmount = cart.getTotalAmount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("bookAmount", bookAmount);
        map.put("totalCount", totalCount);
        map.put("totalAmount", totalAmount);

        //利用异步加载的方式
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        response.getWriter().write(jsonStr);
        //response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
    }
    
}
