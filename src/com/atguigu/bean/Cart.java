package com.atguigu.bean;

import java.io.Serializable;
import java.util.*;

public class Cart implements Serializable {

    private static final long serialVersionUID = -6742829628716421070L;
    private Map<String, CartItem> map = new LinkedHashMap<>();
    private Integer totalCount;
    private Double totalAmount;

    public Cart(Map<String, CartItem> map, Integer totalCount, Double totalAmount) {
        this.map = map;
        this.totalCount = totalCount;
        this.totalAmount = totalAmount;
    }

    public Cart() {
    }

    /**
     * 添加书到购物车
     * @param book
     */
    public void addBookToCart(Book book){
        String id = book.getId() + "";
        CartItem cartItem = map.get(id);
        if(cartItem == null){
            cartItem = new CartItem();
            //设置默认一本书
            cartItem.setCount(1);
            cartItem.setBook(book);
            map.put(id, cartItem);
            return;
        }
        cartItem.setCount(cartItem.getCount()+1);
        return;
    }

    /**
     * 用id删除购物车中书籍
     * @param id
     */
    public void delBookById(String id){
        map.remove(id);
    }

    /**
     * 清空购物车
     */
    public void emptyCart(){
        map.clear();
    }

    /**
     * 更改书籍数量
     * @param bookId
     * @param newCount
     */
    public void changeCount(String bookId, String newCount){
        CartItem cartItem = map.get(bookId);
        try{
            cartItem.setCount(Integer.parseInt(newCount));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取所有购物项集合
     * @return
     */
    public List<CartItem> getCartItems(){
        return new ArrayList<>(map.values());
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    /**
     * 计算购物车总数量
     * @return
     */
    public Integer getTotalCount() {
        int totalCount = 0;
        for(CartItem item: getCartItems()){
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 计算总金额
     * @return
     */
    public Double getTotalAmount() {
        double totalAmount = 0;
        for(CartItem item: getCartItems()){
            totalAmount += item.getAmount();
        }
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "map=" + map +
                ", totalCount=" + totalCount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
