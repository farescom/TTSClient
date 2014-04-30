
package com.learnprogrammingnow.ttsclient.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteHelper {

	private static String databaseFile;
	private static SQLiteHelper instance = null;
	
	private SQLiteHelper(String databaseFile) {
		SQLiteHelper.databaseFile = databaseFile;
	}

	static public synchronized SQLiteHelper getDB(String databaseFile) {
		if (instance == null) {
			instance = new SQLiteHelper(databaseFile);
		}
		if (prepareDB()) {
			return instance;
		} else {
			return null;
		}
	}
	

	/**
	 * Prepares DB. First checks if DB exists with all tables, and if not it creates 
	 * 
	 * @return Connection instance or null (if not able to connect to DB)
	 * */
	private static boolean prepareDB() {
		if (testDB()) {
			return true;
		} else {
			return createDB();
		}
	}

	/**
	 * Creates connection to DB
	 * 
	 * @return Connection instance or null (if not able to connect to DB)
	 * */
	private static Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseFile);
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("Failed to create DB connection");
			return null;
		}
		System.out.println("DB Connection created successfully");
		return c;
	}

	/**
	 * Checks if DB exists and includes needed tables
	 * 
	 * @return true - if DB exists and includes needed tables, false - otherwise
	 * */
	private static boolean testDB() {
		Connection c = getConnection();
		if (c != null) {
			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = c.createStatement();
				rs = stmt.executeQuery("SELECT * FROM CONFIG;");
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false;
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (c != null)
						c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Creates DB (a DB file)
	 * 
	 * @return true - if succeeded in creating DB file, false - otherwise
	 * */
	private static boolean createDB() {
		try {
			File file = new File(databaseFile);
			file.delete();
			if (!file.createNewFile()) {
				return false;
			}
		} catch (IOException e) {
			System.out.println("Failed to create DB file: " + e.getMessage());
			return false;
		}
		return createTables();
	}

	/**
	 * Creates DB tables
	 * 
	 * @return true - if succeeded, false - otherwise
	 * */
	private static boolean createTables() {
		Connection c = getConnection();
		if (c != null) {
			Statement stmt = null;
			try {
				stmt = c.createStatement();
				String sql = "CREATE TABLE CONFIG "
						+ "(ID 			INTEGER 		PRIMARY KEY AUTOINCREMENT,"
						+ " LANGUAGE       CHAR(5)     	NOT NULL, "
						+ " SPEED          TINYINT     	NOT NULL, "
						+ " CODING        	CHAR(20) 		NOT NULL, "
						+ " TEXT         	VARCHAR(1000)	NOT NULL)";
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				return false;
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (c != null) {
						c.commit();
						c.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
