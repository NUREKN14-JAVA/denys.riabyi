package com.nixsolutions.usermanagement.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String url;
	private String user;
	private String password;
	private String driver;

	public ConnectionFactoryImpl(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ConnectionFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public ConnectionFactoryImpl(Properties properties) {
		user = properties.getProperty("connection.user");
		password = properties.getProperty("connection.password");
		url = properties.getProperty("connection.url");
		driver = properties.getProperty("connection.driver");
	}

	@Override
	public Connection createConnection() throws DatabaseException {
		String url = "jdbc:hsqldb:file:db/usermanagement";
		String user = "sa";
		String password = "";
		String driver = "org.hsqldb.jdbcDriver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public UserDao getUserDao() {
		UserDao result = null;

		return result;
	}

}
