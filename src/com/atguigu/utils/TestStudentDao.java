package com.atguigu.utils;

import com.atguigu.bean.Student;
import com.atguigu.dao.StudentDao;
import com.atguigu.dao.impl.StudentDaoImpl;


public class TestStudentDao {

    public static void main(String[] args) {

        StudentDao studentDao = new StudentDaoImpl();

        studentDao.saveStudent(new Student(null,"学生名"));

    }

}
