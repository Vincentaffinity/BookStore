package com.atguigu.dao;

import com.atguigu.bean.Book;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Page;

import java.util.List;

public interface BookDao {

    /**
     * 返回书籍列表
     * @return
     */
    public List<Book> getBookList();

    /**
     * 保存书籍信息
     * @param book
     */
    public void saveBook(Book book);

    /**
     * 用id查找并删除书籍
     * @param id
     */
    public void delBook(int id);

    /**
     * 用id查找书籍
     * @param id
     * @return
     */
    public Book getBookById(int id);

    /**
     * 更新书籍信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 分页查找到书籍列表
     * @param page
     * @return
     */
    public Page<Book> getBooksByPage(Page<Book> page);

    /**
     * 根据价格范围查询
     * @param page
     * @param min
     * @param max
     * @return
     */
    public Page<Book> getBooksByRange(Page<Book> page, Double min, Double max);

    /**
     * 更改购物项库存和销量
     * @param book
     * @param cartItem
     */
    public void updateBookState(Book book, CartItem cartItem);

    /**
     * 批处理更改书籍信息
     * @param params
     */
    public void batchUpdateBook(Object[][] params);

}
