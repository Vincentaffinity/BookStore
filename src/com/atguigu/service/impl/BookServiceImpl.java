package com.atguigu.service.impl;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDaoImpl bookDao = new BookDaoImpl();

    /**
     * 返回书籍列表
     * @return
     */
    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    /**
     * 保存书籍
     * @param book
     */
    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    /**
     * 删除书籍
     * @param id
     */
    @Override
    public void delBook(int id) {
        bookDao.delBook(id);
    }

    /**
     * 以id获取书籍
     * @param id
     * @return
     */
    @Override
    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    /**
     * 更新书籍信息
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 分页取得书籍列表
     * @param pageNo
     * @return
     */
    @Override
    public Page<Book> getBooksByPage(String pageNo) {
        Page<Book> page = new Page<>();
        int pNo = 1;
        try{
            pNo = Integer.parseInt(pageNo);
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }
        page.setPageNo(pNo);
        page = bookDao.getBooksByPage(page);
        return page;
    }

    /**
     * 以价格区间得到书籍列表
     * @param pageNo
     * @param min
     * @param max
     * @return
     */
    @Override
    public Page<Book> getBooksByRange(String pageNo, String min, String max) {
        Page<Book> page = new Page<>();
        int pNo = 1;
        try{
            pNo = Integer.parseInt(pageNo);
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }
        Double minNum = 0.0;
        Double maxNum = Double.MAX_VALUE;
        try {
            minNum = Double.parseDouble(min);
            maxNum = Double.parseDouble(max);
            if(minNum > maxNum){
                Double temp = minNum;
                minNum = maxNum;
                maxNum = temp;
            }
        }catch (Exception e){
//            System.out.println(e.getMessage());
        }
        page.setPageNo(pNo);
        page = bookDao.getBooksByRange(page, minNum, maxNum);
        return page;
    }
}
