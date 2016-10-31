package com.nixsolutions.usermanagement.db;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends TestCase {

	private HsqldbUserDao dao;
	private ConnectionFactory connecitonFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		connecitonFactory = new ConnectionFactoryImpl();
		dao = new HsqldbUserDao(connecitonFactory);
	}

	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}

}
