package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	
	public static boolean testMode = false;
	public static Connection getConnection() {
		
		// First time we will use a literal user and password
		// and we will refactor to use environment variables for safety
		// in practice you should NEVER use literals
		
		// Note: JDBC url has a specific format
		//  jdbc:database-type://network-location:port/internal-database
		String url = "jdbc:postgresql://rev-project1-db.cjoxac3tcnj8.us-east-2.rds.amazonaws.com:5432/postgres";
		try {
			Connection conn = DriverManager.getConnection(url, 
					System.getenv("P1U"), 
					System.getenv("P1P"));
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to connect to database. Sad :(");
			return null;
		}
	}
	
}