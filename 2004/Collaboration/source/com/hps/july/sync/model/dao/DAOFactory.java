package com.hps.july.sync.model.dao;

// Abstract class DAO Factory
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int INFORMIX = 1;
	public static final int ORACLE = 2;

	// There will be a method for each DAO that can be 
	// created. The concrete factories will have to 
	// implement these methods.
	public abstract DbsAntennaPlaceDAO getDbsAntennaPlaceDAO();
	public abstract DbsPositionDAO getDbsPositionDAO();
	public abstract DbsPositionDAO_Page getDbsPositionDAO_Page();

	public static DAOFactory getDAOFactory(int whichFactory, boolean isXML) {
		switch (whichFactory) {
			case INFORMIX :
				if (!isXML)
					return new InformixDAOFactory();
				else
					return new ReferencesDAOFactory("informix");
				/**    
				      case ORACLE    : 
						if (!isXML)				      
				          return new OracleDAOFactory();
						else
						  return new GenericDAOFactory("oracle");
				*/
			default :
				return null;
		}
	}
}
