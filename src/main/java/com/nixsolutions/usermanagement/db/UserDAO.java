package com.nixsolutions.usermanagement.db;

import java.util.Collection;

import com.nixsolutions.usermanagement.User;

import org.dbunit.DatabaseTestCase;

public interface UserDAO {
	public User create(User user) throws DatabaseException;
	public void delete(User user) throws DatabaseException;
	public void update(User user) throws DatabaseException;
	public User find(Long id) throws DatabaseException;
	public Collection findAll() throws DatabaseException;
	
	public void setConnectionFactory(ConnectionFactory connectionFactory);
}
