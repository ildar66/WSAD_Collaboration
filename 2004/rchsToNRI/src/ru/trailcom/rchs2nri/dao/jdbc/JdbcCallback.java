package ru.trailcom.rchs2nri.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface JdbcCallback {
	
	Object doWithConnection(Connection connection) throws SQLException;

}
