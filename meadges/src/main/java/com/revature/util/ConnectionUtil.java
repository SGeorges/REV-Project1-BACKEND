package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	/* No Idea what this is for */
	public static boolean testMode = false;
	public static Connection getConnection() {
		try {
			String url = System.getenv("ERS_DB_URL");
			
			Connection conn = DriverManager.getConnection(
								url,
								System.getenv("ERS_ROLE"),
								System.getenv("ERS_PASS"));
			
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to the databse.");
			return null;
		}
	}

}
