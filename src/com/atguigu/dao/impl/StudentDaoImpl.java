package com.atguigu.dao.impl;

import com.atguigu.bean.Student;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.StudentDao;

/**
 * @Author Chunsheng Zhang
 * @Date 2020/8/7
 * @Time 17:14
 */
public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {

    @Override
    public void saveStudent(Student student) {
        String sql = "INSERT INTO students(`name`) VALUES(?)";
        update(sql,student.getName());
    }


}
