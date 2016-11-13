package com.nixsolutions.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private static final String USER_DAO = "dao.com.nixsolutions.usermanagement.db.UserDao";
	private Properties properties;

	private final static DaoFactory INSTANCE = new DaoFactory();

	public final static DaoFactory getInstance() {
		return INSTANCE;
	}

	private DaoFactory() {
		properties = new Properties();

		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void init(Properties properties){
		this.properties = properties;
	}

	private ConnectionFactory getConnectionFactory() {
		return new ConnectionFactoryImpl(properties);
	}

	public UserDao getUserDao() {
		UserDao result = null;
		try {
			Class clazz = Class.forName(properties.getProperty(USER_DAO));
			result = (UserDao) clazz.newInstance();
			result.setConnectionFactory(getConnectionFactory());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
