/*
 * Created on 19.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.Page;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
//public class ImpGenericDAO extends GenericDAO implements DbsPositionDAO_Page, DbsAntennaPlaceDAO, ...
public class ImpGenericDAO extends GenericDAO implements DbsPositionDAO_Page {
	//перечисление всех названий запросов (xml)
	private static final String XML_GET_DbsPositions = "GET_DbsPositions";
	/**
	 * @param daoSQLFileName
	 * @param database
	 * @throws ReferencesDAOSysException
	 */
	public ImpGenericDAO(String daoSQLFileName, String database) throws ReferencesDAOSysException {
		super(daoSQLFileName, database);
	}

	/**
	 * @throws ReferencesDAOSysException
	 */
	public ImpGenericDAO() throws ReferencesDAOSysException {
		super();
	}

	/* (non-Javadoc)
	 * @see com.hps.july.sync.model.dao.DbsPositionDAO_Page#findDbsPositionsPage(java.sql.Connection, int, int, java.lang.String)
	 */
	public Page findDbsPositionsPage(Connection con, int start, int count, String orderBy) throws ReferencesDAOSysException {
		//String[] parameterValues = new String[] { locale.toString()};
		//формируем из "Object searchCriteria" переданного как параметр	другого findDbsPositionsPage:	
		String[] parameterValues = null;
		return executeSelect(con, XML_GET_DbsPositions, parameterValues, start, count, orderBy);
	}

	public static void main(String[] args) {

		if (args.length <= 2) {
			try {
/**
				GenericDAO catalogDAO = new GenericDAO(args[0], args[1]);
				//String[] parameterValues = new String[] { "parameter1", "parameter2, ..." };
				String[] parameterValues = new String[] {}; 
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_DbsPositions, parameterValues);
*/				
/**				
				String[] parameterValues = new String[] { "FR_fr", "Chien" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_CATEGORY, parameterValues);
				parameterValues = new String[] { "FR_fr" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_CATEGORIES, parameterValues);
				parameterValues = new String[] { "FR_fr", "Caniche" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_PRODUCT, parameterValues);
				parameterValues = new String[] { "FR_fr", "Chien" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_PRODUCTS, parameterValues);
				parameterValues = new String[] { "FR_fr", "Medor" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_ITEM, parameterValues);
				parameterValues = new String[] { "FR_fr", "Caniche" };
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_GET_ITEMS, parameterValues);
				String[] keywords = { "Chien", "Chat", "Poisson" };
				parameterValues = new String[1 + (keywords.length * 2)];
				parameterValues[0] = "FR_fr";
				for (int i = 0; i < keywords.length; i++) {
					parameterValues[(i * 2) + 1] = keywords[i];
					parameterValues[(i * 2) + 2] = keywords[i];
				}
				catalogDAO.printSQLStatement(catalogDAO.sqlStatements, XML_SEARCH_ITEMS, parameterValues);
*/				
				System.exit(0);
			} catch (Exception exception) {
				exception.printStackTrace(System.err);
				System.err.println(exception);
				System.exit(2);
			}
		}
		System.err.println("Usage: " + GenericDAO.class.getName() + " [file-name] [databas-type]");
		System.exit(1);

	}

}
