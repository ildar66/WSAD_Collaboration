package ru.trailcom.rchs2nri.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplate {
	
	Connection connection;
	
	public JdbcTemplate(Connection aConnection){
		this.connection = aConnection;
	}
	
	protected Log logger = LogFactory.getLog(getClass());
	
	
	public Object execute(final JdbcCallback callback) {
		Object res = null;
		try {
			res = callback.doWithConnection(this.connection); 
		}catch (SQLException e) {
			logger.error("Ошибка в SQL ", e);
            logger.error("SQLCode = " + e.getErrorCode());
            logger.error("SQLState = " + e.getSQLState());
            logger.error("Message = " + e.getMessage());
            throw new RuntimeException(e);
        }
		return res;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
