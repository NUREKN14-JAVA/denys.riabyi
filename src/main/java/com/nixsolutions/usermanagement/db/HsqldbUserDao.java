package com.nixsolutions.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import com.nixsolutions.usermanagement.User;

public class HsqldbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	final private String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	final private String SELECT_QUERY = "SELECT * FROM users WHERE id = ?";
	final private String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDao() {

	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}

	@Override
	public void update(User user) throws DatabaseException {
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(this.UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			statement.setLong(4, new Long(user.getId()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of updated rows: " + n);
			}
		} catch (DatabaseException de) {
			throw de;
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle);
		}
	}

	@Override
	public void delete(User user) throws DatabaseException {
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(this.DELETE_QUERY);
			statement.setLong(1, new Long(user.getId()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of deleted rows: " + n);
			}
		} catch (DatabaseException de) {
			throw de;
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle);
		}
	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user = new User();
		try {
			Connection connection = this.connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(this.SELECT_QUERY);
			statement.setLong(1, new Long(id));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
			}
		} catch (DatabaseException de) {
			throw de;
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle);
		}
		return user;
	}

	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();

		try {
			Connection conneciton = connectionFactory.createConnection();
			Statement statement = conneciton.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setFirstName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException();
		}

		return result;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
