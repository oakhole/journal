package com.oakhole.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.oakhole.utils.ReadConfig;

public class DBConnMySql {

	public static String dburl = ReadConfig.getValue("dburl");
	public static String dbuser = ReadConfig.getValue("dbuser");
	public static String dbpass = ReadConfig.getValue("dbpass");

	public static Connection getConnection(String url, String user,
			String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		System.out.println(DBConnMySql.getConnection());
	}

}
