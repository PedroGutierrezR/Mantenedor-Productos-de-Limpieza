package com.desafiolatam.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection = null;
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "productos_limpieza";
	private static final String	PASSWORD = "1234";
	
	private void createConnection() {
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public Connection getConnection() {
		if(connection == null) {
			createConnection();
		}
		return connection;
	}
	
}
