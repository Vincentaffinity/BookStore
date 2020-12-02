package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class CartItem implements Serializable {

    private static final long serialVersionUID = 1134152162889708435L;
    private Book book;
    private Integer count;
    private Double amount; //购物项金额

    public CartItem() {
    }

    public CartItem(Book book, Integer count, Double amount) {
        this.book = book;
        this.count = count;
        this.amount = amount;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        BigDecimal price = new BigDecimal(book.getPrice()+"");
        BigDecimal counter = new BigDecimal(count+"");
        amount = price.multiply(counter).doubleValue();
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }
}
