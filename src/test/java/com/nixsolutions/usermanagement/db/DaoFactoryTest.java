package com.nixsolutions.usermanagement.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaoFactoryTest {

	@Test
	public void testGetUserDao() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("Dao Factory is null", daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("User Dao is null", userDao);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
