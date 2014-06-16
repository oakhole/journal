package com.oakhole.common.util.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.oakhole.common.util.fileUtils.ReadConfig;

public class DBConnMysql {

	private static Connection conn;
	private static String DBDRIVER;
	private static String DBURL;
	private static String DBUSER;
	private static String DBPASS;

	static {
		try {
			DBDRIVER = ReadConfig.getValue("jdbc.driverClassName");
			Class.forName(DBDRIVER);
			DBURL = ReadConfig.getValue("jdbc.url");
			DBUSER = ReadConfig.getValue("jdbc.username");
			DBPASS = ReadConfig.getValue("jdbc.password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection(String dburl, String dbuser,
			String dbpass) {
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = DBConnMysql.getConnection();
		System.out.println(conn);
		DBConnMysql.close();
	}
}
