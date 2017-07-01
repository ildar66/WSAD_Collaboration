package com.hps.july.sync.session.dao;

import javax.sql.*;
import javax.naming.*;
import java.sql.*;
import java.util.*;

import com.hps.july.sync.model.Page;
import com.hps.july.sync.model.dao.*;
import com.hps.july.sync.model.valueobject.*;

import com.hps.july.sync.servicelocator.ejb.*;
/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ReferencesFinderPersistence {
	// sql datasource cached
	private DataSource ds = null;
	private DAOFactory daoFactory = null;

	// environment variable key and value - must exist in bean environment 
	//private final String DB_DATASOURCE_KEY = "session/dbdatasource";

	/**
	 * Constructor for ReferencesFinderPersister.
	 */
	public ReferencesFinderPersistence() {
		initializeResources();
	}

	public ReferencesFinderPersistence(int typeDatabase, boolean isXML) {
		initializeResources(typeDatabase, isXML);
	}

	private void initializeResources() {
		try {
			/**			
			InitialContext ctx = new InitialContext();
			String dbDatasource = (String) ctx.lookup("java:comp/env/" + DB_DATASOURCE_KEY);
			System.out.println("ReferencesFinderPersister datasource name: " + dbDatasource);
			ds = (DataSource) ctx.lookup(dbDatasource);
			// ds = (DataSource) ctx.lookup("jdbc/informix");
			System.out.println("ReferencesFinderPersister datasource OK");
			*/
			ServiceLocator sl = new ServiceLocator();
			//нужно грузить из настроек (пока так):
			ds = (DataSource) sl.getDataSource(JNDINames.INFORMIX_DATASOURCE);
			int typeDatabase = DAOFactory.INFORMIX;
			boolean isXML = false;
			daoFactory = DAOFactory.getDAOFactory(typeDatabase, isXML);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initializeResources(int typeDatabase, boolean isXML) {
		try {
			ServiceLocator sl = new ServiceLocator();
			if (typeDatabase == DAOFactory.INFORMIX)
				ds = (DataSource) sl.getDataSource(JNDINames.INFORMIX_DATASOURCE);
			else if (typeDatabase == DAOFactory.ORACLE)
				ds = (DataSource) sl.getDataSource(JNDINames.ORACLE_DATASOURCE);
			daoFactory = DAOFactory.getDAOFactory(typeDatabase, isXML);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private DataSource getDatasource() throws SQLException {
		if (ds == null)
			throw new SQLException("Data source is null");
		else
			return ds;
	}

	public void freeResources() {
		System.out.println("ReferencesFinderPersister free resources");
		ds = null;
		daoFactory = null;
	}

	public Collection findDbsAntennaPlaces(String orderBy) {
		Connection conn = null;
		try {
			conn = getDatasource().getConnection();
			DbsAntennaPlaceDAO dbsAntennaPlaceDAO = daoFactory.getDbsAntennaPlaceDAO();
			return dbsAntennaPlaceDAO.findDbsAntennaPlaces(conn, orderBy);
		} catch (SQLException e) {
			System.out.println("SQLException while querying findDbsAntennaPlaces(): CODE=" + e.getErrorCode());
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	public Collection findDbsPositions(String orderBy, int startAtRow, int howManyRows) {
		Connection conn = null;
		try {
			conn = getDatasource().getConnection();
			DbsPositionDAO dbsPositionDAO = daoFactory.getDbsPositionDAO();
			return dbsPositionDAO.findDbsPositions(conn, startAtRow, howManyRows, orderBy);
		} catch (SQLException e) {
			System.out.println("SQLException while querying findDbsPositions(): CODE=" + e.getErrorCode());
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	public Page findDbsPositions_Page(String orderBy, int startAtRow, int howManyRows) {
		Connection conn = null;
		try {
			conn = getDatasource().getConnection();
			DbsPositionDAO_Page dbsPositionDAO = daoFactory.getDbsPositionDAO_Page();
			return dbsPositionDAO.findDbsPositionsPage(conn, startAtRow, howManyRows, orderBy);
		} catch (SQLException e) {
			System.out.println("SQLException while querying findDbsPositions(): CODE=" + e.getErrorCode());
			return null;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
}
