package com.atguigu.dao.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Page;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    /**
     * 获取书籍列表
     * @return
     */
    @Override
    public List<Book> getBookList() {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from books";
        return this.getBeanList(sql);
    }

    /**
     * 保存书籍
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        String sql = "insert into books(title,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        this.update(sql,book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    /**
     * 删除书籍
     * @param id
     */
    @Override
    public void delBook(int id) {
        String sql = "delete from books where id=?";
        this.update(sql, id);
    }

    /**
     * 以id获取书籍信息
     * @param id
     * @return
     */
    @Override
    public Book getBookById(int id) {
        String sql = "select id,title,author,price,sales,stock,img_path as imgPath from books where id=?";
        return getBean(sql, id);
    }

    /**
     * 更新书籍信息
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        String sql = "update books set title=?,author=?,price=?,sales=?,stock=? where id=?";
        this.update(sql, book.getTitle(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
    }

    /**
     * 分页查找书籍列表
     * @param page
     * @return
     */
    @Override
    public Page<Book> getBooksByPage(Page<Book> page) {
        String sql = "select count(*) from books";
        Integer totalRecord = Integer.parseInt(this.getSingleValue(sql)+"");
        page.setTotalRecord(totalRecord);

        //获取list 当前页显示数据的集合
        String sqlGet = "select id, title, author, price, sales, stock, img_path imgPath from books limit ?,?";
        List<Book> bookList = this.getBeanList(sqlGet, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
        page.setList(bookList);
        return page;
    }

    /**
     * 以价格区间分页获取书籍信息
     * @param page
     * @param min
     * @param max
     * @return
     */
    @Override
    public Page<Book> getBooksByRange(Page<Book> page, Double min, Double max) {
        String sql = "select count(*) from books where books.price>=? and books.price<=?";
        Integer totalRecord = Integer.parseInt(this.getSingleValue(sql, min, max)+"");
        page.setTotalRecord(totalRecord);

        //获取list 当前页显示数据的集合
        String sqlGet = "select id, title, author, price, sales, stock, img_path imgPath from books"
        +" where books.price>=? and books.price<=? limit ?,?";
        List<Book> bookList = this.getBeanList(sqlGet, min, max, (page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
        page.setList(bookList);
        return page;
    }

    /**
     * 更改购物项库存和销量
     * @param book
     * @param cartItem
     */
    @Override
    public void updateBookState(Book book, CartItem cartItem) {
        String sql = "update books set stock=?, sales=? where id=?";
        this.update(sql, book.getStock()-cartItem.getCount(), book.getSales()+cartItem.getCount(),
                book.getId());
    }

    /**
     * 批处理更新书籍信息
     * @param params
     */
    @Override
    public void batchUpdateBook(Object[][] params) {
        String sql = "update books set stock=?, sales=? where id=?";
        this.batchUpdate(sql, params);
    }
}
