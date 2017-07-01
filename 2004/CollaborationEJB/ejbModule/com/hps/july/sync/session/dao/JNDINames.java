package com.hps.july.sync.session.dao;

/**
 * This class is the central location to store the internal
 * JNDI names of various entities. Any change here should
 * also be reflected in the deployment descriptors.
 */

public class JNDINames {
	// prevent instanciation
	private JNDINames() {
	}
	/**
	// JNDI Names of data sources.
	*/
	public static final String INFORMIX_DATASOURCE = "java:comp/env/jdbc/informix";

	public static final String ORACLE_DATASOURCE = "java:comp/env/jdbc/oracle";

	//
	// JNDI names of EJB home objects
	//
	public static final String References_EJBHOME = "java:comp/env/ejb/ReferencesFinder";

	public static final String Generic_EJBHOME = "java:comp/env/ejb/GenericFinderLocalHome";
	/**	
	    //
	    // JNDI Names of application properties
	    //
	    public static final String USE_CATALOG_EJB =
	        "java:comp/env/ejb/catalog/useCatalogEJB";
	
	    public static final String CATALOG_DAO_CLASS =
	        "java:comp/env/param/CatalogDAOClass";
	
	    public static final String CATALOG_DAO_SQL_URL =
	        "java:comp/env/url/CatalogDAOSQLURL";
	
	    public static final String CATALOG_DAO_DATABASE =
	        "java:comp/env/param/CatalogDAODatabase";
	*/

}
