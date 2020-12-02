package com.atguigu.service;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

import java.util.List;

public interface BookService {

    /**
     * 得到书籍列表
     * @return
     */
    public List<Book> getBookList();

    /**
     * 保存新书籍
     * @param book
     */
    public void saveBook(Book book);

    /**
     * 删除指定书籍
     * @param id
     */
    public void delBook(int id);

    /**
     * 用id查询对应书籍
     * @param id
     * @return
     */
    public Book getBookById(int id);

    /**
     * 修改书籍信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 分页查询书籍
     * @param pageNo
     * @return
     */
    public Page<Book> getBooksByPage(String pageNo);

    /**
     * 根据价格区间查询书本
     * @param pageNo
     * @param min
     * @param max
     * @return
     */
    public Page<Book> getBooksByRange(String pageNo, String min, String max);
}
