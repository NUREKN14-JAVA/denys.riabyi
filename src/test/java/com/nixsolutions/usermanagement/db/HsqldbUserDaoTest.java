package com.nixsolutions.usermanagement.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {

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

	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull(collection);
			assertEquals(2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}

	}

	@Test
	public void testFind() {
		try {
			User user = dao.find(new Long(1000));
			assertNotNull("No such user", user.getId());
			assertEquals("Wrong id", new Long(1000), user.getId());
			assertEquals("Wrong full name", "Gates, Bill", user.getFullName());
		} catch (DatabaseException de) {
			de.printStackTrace();
			fail(de.toString());
		}
	}

	@Test
	public void testDelete() {
		try {
			User user = dao.find(new Long(1000));
			dao.delete(user);
			user = dao.find(new Long(1000));
			assertNull("User was not deleted", user.getId());
		} catch (DatabaseException de) {
			de.printStackTrace();
			fail(de.toString());
		}
	}

	@Test
	public void testUpdate() {
		try {
			User user = new User();
			user.setFirstName("Not");
			user.setLastName("Sure");
			Calendar calendar = Calendar.getInstance();
			calendar.set(1000, Calendar.MARCH, 1);
			user.setDateOfBirth(calendar.getTime());
			user.setId(new Long(1000));
			dao.update(user);
			User userUpd = dao.find(new Long(1000));
			Calendar calendarUpd = Calendar.getInstance();
			calendarUpd.setTime(new Date(userUpd.getDateOfBirth().getTime()));
			assertEquals("Wrong full name", user.getFullName(), userUpd.getFullName());
			assertEquals("Wrong year", calendar.get(Calendar.YEAR), calendarUpd.get(Calendar.YEAR));
			assertEquals("Wrong month", calendar.get(Calendar.MONTH), calendarUpd.get(Calendar.MONTH));
			assertEquals("Wrong day", calendar.get(Calendar.DAY_OF_MONTH), calendarUpd.get(Calendar.DAY_OF_MONTH));
		} catch (DatabaseException de) {
			de.printStackTrace();
			fail(de.toString());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connecitonFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connecitonFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
