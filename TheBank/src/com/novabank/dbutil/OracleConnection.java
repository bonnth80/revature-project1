package com.novabank.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.novabank.exception.BusinessException;

public class OracleConnection {
	private static Connection connection;

	private static String connectionURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String username = "bonnth";
	private static String password = "clandestine";
	
	// Mutators and Accessors
	public static void setConnectionURL(String connectionURL) {
		Logger log = Logger.getLogger(OracleConnection.class);
		try {
			if (!connectionURL.matches("^jdbc:oracle:")) {
				throw new BusinessException("Connection url must start with : jdbc:oracle:");
			}
		}catch (BusinessException e) {
			log.error(e);
		}
		OracleConnection.connectionURL = connectionURL;
	}

	public static void setUsername(String username) {
		OracleConnection.username = username;
	}

	public static void setPassword(String password) {
		OracleConnection.password = password;
	}
	
	// Methods
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		connection=DriverManager.getConnection(connectionURL,username,password);
		return connection;
	}
}
