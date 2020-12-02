package com.atguigu.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1007964047627654369L;
    private Integer pageNo;         //默认1
    private Integer totalPageNo;    //总数据除以每页显示数量+1
    private Integer totalRecord;    //总数据数量
    private static final Integer PAGE_SIZE = 4;
    private List<T> list;

    public Page(Integer pageNo, Integer totalPageNo, Integer totalRecord, List<T> list) {
        this.pageNo = pageNo;
        this.totalPageNo = totalPageNo;
        this.totalRecord = totalRecord;
        this.list = list;
    }

    public Page() {
    }

    public Integer getPageNo() {
        if(pageNo<1){
            return 1;
        }
        if(pageNo > getTotalPageNo()){
            return getTotalPageNo();
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotalPageNo() {
        int totalPageNo = this.totalRecord%PAGE_SIZE ==0? this.totalRecord/PAGE_SIZE : this.totalRecord/PAGE_SIZE+1;
        return totalPageNo;
    }

    public void setTotalPageNo(Integer totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPageNo=" + totalPageNo +
                ", totalRecord=" + totalRecord +
                ", list=" + list +
                '}';
    }
}
