package com.hps.april.common.utils.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

/**
 * @author dimitry
 * @deprecated
 * Created on 07.12.2005
 */
public abstract class ParametrizeQuery implements PreparedStatementCreator {
	private Log logger = LogFactory.getLog(this.getClass());
	
	private List parameterList = new ArrayList();	
	private List valueList = new ArrayList();
	private List typeList = new ArrayList();

	private String tableName;
	
	public ParametrizeQuery(String tableName){
		this.tableName = tableName;
	}

	public void add(String parameter, Object value){
		add(parameter, value, SqlTypeValue.TYPE_UNKNOWN);
	}

	public void add(String parameter, Object value, int type){
		if (value != null) {
			parameterList.add(parameter);
			addValue(value, type);
		}
	}

	public void add(String[] parameters, Object[] values, int[] types){
		if (parameters == null || values == null || parameters.length != values.length || 
			(types != null && types.length != values.length))
			throw new IllegalArgumentException("incorrect parameters");
			
		for(int i=0;i<parameters.length;i++){
			if (types != null)
				add(parameters[i], values[i], types[i]);
			else 
				add(parameters[i], values[i]);
		}
	}

	public void add(String[] parameters, Object[] values){
		add(parameters, values, null);
	}

	protected void addValue(Object value){
		addValue(value, SqlTypeValue.TYPE_UNKNOWN);
	}

	protected void addValue(Object value, int type){
		valueList.add(value);
		typeList.add(new Integer(type));
	}
	
	protected void addValues(Object[] values, int[] types){
		if (values == null || (types != null && types.length != values.length))
			throw new IllegalArgumentException("incorrect parameters");
			
		for(int i=0;i<values.length;i++){
			if (types != null)
				addValue(values[i], types[i]);
			else 
				addValue(values[i]);
		}
	}


	/**
	 * @param parameters
	 * @return
	 */
	protected abstract String getSqlString(String tableName, List parameters);

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		String query = getSqlString(tableName, parameterList);
			
		prepareProperties();

		logger.debug("PreparedStatement: " +			"Query: [" + query + "] " +			"Values: " + valueList + " " +			"Types: " + typeList);

		PreparedStatement preparedStatement = con.prepareStatement(query);
		
		for (int i = 0; i < valueList.size(); i++) {
			Integer sqlType = (Integer) typeList.get(i);
			StatementCreatorUtils.setParameterValue(preparedStatement, i + 1, sqlType.intValue(), null, valueList.get(i));
		}

		return preparedStatement;
	}

	protected void prepareProperties() {
		
	}

}
