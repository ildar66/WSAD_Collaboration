package ru.trailcom.rchs2nri.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class JdbcDaoSupport {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private JdbcTemplate jdbcTemplate;


    public JdbcDaoSupport(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public JdbcDaoSupport() {
    }

    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int getNextId(Connection connection, String table) throws SQLException {
        String getSeq = "execute procedure getsequence(?) "; 
        logger.info("execute procedure getsequence('tables." + table + "')");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(getSeq);
            preparedStatement.setString(1, "tables." + table);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                logger.info("id="+id);
                return id;
            } else {
                throw new RuntimeException("strange getsequence result");
            }
        } finally{
            closeResultSet(resultSet);
            closePreparedStatement(preparedStatement);
        }
	}

    protected void closeResultSet(ResultSet resultSetFull) {
        if(resultSetFull!=null){
            try {
                resultSetFull.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void closePreparedStatement(Statement preparedStatementLast) {
        if(preparedStatementLast!=null){
            try {
                preparedStatementLast.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
