package com.smbea.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Postgresql 连接器
 * @author zhaojiafu
 * Test upload
 * 2016年1月5日 上午11:55:30
 */
public class PostgresqlConnection {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = null;
		String user = null;
		String password = null;
		String sql = null;
		
		try {
			Class.forName("org.postgresql.Driver"); // 加载mysq驱动
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载错误");
			e.printStackTrace();// 打印出错详细信息
		}
		try {
			url = "jdbc:postgresql://123.56.176.252:5432/paidao";
			user = "postgres";
			password = "123456";
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("数据库链接错误");
			e.printStackTrace();
		}
		try {
			stmt = conn.createStatement();
			sql = "select * from pd_user_profile";	
			rs = stmt.executeQuery(sql);// 执行sql语句
			while (rs.next()) {
				System.out.print(rs.getString("nickname") + "   ");
				System.out.print(rs.getString("username") + "   ");
				System.out.println(rs.getString("password") + "   ");
			}
		} catch (SQLException e) {
			System.out.println("数据操作错误");
			e.printStackTrace();
		}
		// 关闭数据库
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			System.out.println("数据库关闭错误");
			e.printStackTrace();
		}
	}
}