package com.untec.shared.infrastructure.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDatabase implements Database {
	private static MySQLDatabase instance;
	private Connection connection;
	
	private MySQLDatabase() throws SQLException, IOException {
		Properties props = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties");
		props.load(input);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL driver no encontrado", e);
		}

		this.connection = DriverManager.getConnection(
				props.getProperty("db.url"),
				props.getProperty("db.username"),
				props.getProperty("db.password")
			);
	}
	
	public static MySQLDatabase getInstance() throws SQLException, IOException {
		if (instance == null || instance.connection.isClosed()) {
			instance = new MySQLDatabase();
		}
		return instance;
	}
	
	@Override
	public Connection getConnection() {
		return this.connection;
	}
}
