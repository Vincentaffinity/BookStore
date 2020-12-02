package com.atguigu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.atguigu.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.xml.ws.handler.Handler;


public class BaseDao<T> {
	private QueryRunner queryRunner = new QueryRunner();
	//定义一个变量来接收泛型的类型
	private Class<T> type;
	// 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDao() {
		//获取子类的类型
		Class clazz = this.getClass();
		//获取父类的类型
		//getGenericSuperclass()用来获取当前类的父类的类型
		//ParameterizedType表示的是带泛型的类型
		ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
		//获取具体的泛型类型 getActualTypeArguments获取具体的泛型的类型
		//这个方法会返回一个Type的数组
		Type[] types = parameterizedType.getActualTypeArguments();
		//获取具体的泛型的类型·
		this.type = (Class<T>) types[0];
	}
	
	/**
	 * 通用的增删改操作
	 * 			sql: insert delete update
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql, Object... params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		int count = 0;
		try {
			count = queryRunner.update(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * 批处理
	 * @param sql
	 * @param params
	 * @return
	 */
	public int[] batchUpdate(String sql, Object[][] params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		int[] count;
		try {
			count = queryRunner.batch(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return count;
	}


	/**
	 * 获取一个对象
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql, Object... params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		T t = null;
		try {
			t = queryRunner.query(connection, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
		}
		return t;
	}

	/**
	 * 获取所有对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql, Object... params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		List<T> list = null;
		try {
			list = queryRunner.query(connection, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
		}
		return list;
	}

	//获取单个值
	public Object getSingleValue(String sql, Object... params) {
		// 获取连接
		Connection connection = JDBCUtils.getConnection();
		Object t = null;
		try {
			t = queryRunner.query(connection, sql, new ScalarHandler<>(),
					params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return t;
	}
}
