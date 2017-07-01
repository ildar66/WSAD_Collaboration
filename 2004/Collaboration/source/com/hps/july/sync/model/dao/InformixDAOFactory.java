package com.hps.july.sync.model.dao;

// Informix concrete DAO Factory implementation
import java.sql.*;

public class InformixDAOFactory extends DAOFactory {
	/**	
	  public static final String DRIVER=
	    "COM.cloudscape.core.RmiJdbcDriver";
	  public static final String DBURL=
	    "jdbc:cloudscape:rmi://localhost:1099/CoreJ2EEDB";
	
	  // method to create Informix connections
	  public static Connection createConnection() {
	    // Use DRIVER and DBURL to create a connection
	    // Recommend connection pool implementation/usage
	  }
	*/
	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsAntennaPlaceDAO()
	 */
	public DbsAntennaPlaceDAO getDbsAntennaPlaceDAO() {
		return new InformixDbsAntennaPlaceDAO();
	}

	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsPositionDAO()
	 */
	public DbsPositionDAO getDbsPositionDAO() {
		return new InformixDbsPositionDAO();
	}

	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsPositionDAO_Page()
	 */
	public DbsPositionDAO_Page getDbsPositionDAO_Page() {
		return new InformixDbsPositionDAO_Page();
	}

}
