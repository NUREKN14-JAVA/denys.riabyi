package com.nixsolutions.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConneciton() throws DatabaseException;
}
