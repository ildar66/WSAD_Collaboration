package com.hps.april.common.utils.jdbc;

import java.util.Iterator;
import java.util.List;

/**
 * @author dimitry
 * @deprecated
 * Created on 06.12.2005
 */
public class UpdateParametrizeQuery extends ParametrizeQuery {

	private String whereStatement;
	private Object[] whereValues; 
	private int[] whereTypes;

	public UpdateParametrizeQuery(String tableName) {
		super(tableName);
	}

	protected String getSqlString(String tableName, List parameters) {
		String query = "update " + tableName + " ";
		
		if (!parameters.isEmpty()){
			query += "set ";

			Iterator itr = parameters.iterator();
			while (itr.hasNext()){
				String parameter = (String) itr.next();
				query += " "+ parameter + " = ?";
				if (itr.hasNext()){	query += ","; }
			}

			if (whereStatement != null){
				query += " where " + whereStatement; 
			}
		}
		return query;
	}

	protected void prepareProperties() {
		if (whereValues != null)
			addValues(whereValues, whereTypes);			
	}

	public void addWhere(String whereStatement, Object[] values, int[] types){
		this.whereStatement = whereStatement;
		this.whereValues = values;
		this.whereTypes = types;
	}

	public void addWhere(String whereStatement, Object[] values){
		addWhere(whereStatement, values, null);
	}


}
