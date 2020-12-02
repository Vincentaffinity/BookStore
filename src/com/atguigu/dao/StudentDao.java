package com.atguigu.dao;

import com.atguigu.bean.Student;


public interface StudentDao {

    /**
     * 新增student信息
     * add|save|insert
     * sql:  insert into students(name) values(?)
     */
    public void saveStudent(Student student);

}
