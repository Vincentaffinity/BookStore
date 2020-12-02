package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	private static DataSource dataSource;

//	private static Connection connection;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>(); //线程安全
	
	static {
		try {
			//1、读取druip.properties文件
			Properties pro = new Properties();
			pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
			
			//2、连接连接池
			dataSource = DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获取连接
	public static Connection getConnection() {
		Connection connection = threadLocal.get();
		try {
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	//释放连接
	public static void releaseConnection() {
		Connection connection = threadLocal.get();
		if(connection != null) {
			try {
				connection.close();
				//将connection对象移除 保证只有一个connection
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
