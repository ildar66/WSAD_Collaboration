package com.hps.july.sync.model.dao;

// Informix concrete DAO Factory implementation
import java.sql.*;

public class ReferencesDAOFactory extends DAOFactory {
	String database = null;
	String daoSQLFileName = null;
	public ReferencesDAOFactory(String databese) {
		this.database = databese;
		/**			
		 *нужна загрузка из настроек: 
		 *пока так:
		 *daoSQLFileName = C:\workspases\WSAD_Collaboration\Collaboration\DAOSQL.xml
		 */
		this.daoSQLFileName = "C:\\workspases\\WSAD_Collaboration\\Collaboration\\DAOSQL.xml";		
	}
	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsAntennaPlaceDAO()
	 */
	public DbsAntennaPlaceDAO getDbsAntennaPlaceDAO() {
		return null;
	}

	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsPositionDAO()
	 */
	public DbsPositionDAO getDbsPositionDAO() {
		return null;
	}

	/**
	 * @see com.hps.july.sync.model.dao.DAOFactory#getDbsPositionDAO_Page()
	 */
	public DbsPositionDAO_Page getDbsPositionDAO_Page() {
		return new ImpGenericDAO(daoSQLFileName, database);
	}
}
