package com.hps.april.common.utils.jdbc;

import java.util.Iterator;
import java.util.List;

/**
 * @author dimitry
 * @deprecated
 * Created on 06.12.2005
 */
public class InsertParametrizeQuery extends ParametrizeQuery {

	public InsertParametrizeQuery(String tableName) {
		super(tableName);
	}

	protected String getSqlString(String tableName, List parameters) {
		String fields = "", values = "";

		Iterator itr = parameters.iterator();
		while (itr.hasNext()){
			String parameter = (String) itr.next();
			fields += " "+ parameter + ""; values += " ?";
			if (itr.hasNext()){	fields += ","; values += ",";}
		}
		
		return "insert into " + tableName + " (" + fields + ") values (" + values + ")";
	}

}
