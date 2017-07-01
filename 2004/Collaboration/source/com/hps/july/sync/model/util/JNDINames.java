/*
 * Created on 19.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.model.util;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * This class is the central location to store the internal
 * JNDI names of various entities. Any change here should
 * also be reflected in the deployment descriptors.
 */

public class JNDINames {
	private JNDINames() {
	} // prevent instanciation

	//
	// JNDI names of EJB home objects
	//
	public static final String CATALOG_EJBHOME = "java:comp/env/ejb/Catalog";

	//
	// JNDI Names of data sources.
	//

	public static final String CATALOG_DATASOURCE = "java:comp/env/jdbc/CatalogDB";

	//
	// JNDI Names of application properties
	//
	public static final String USE_CATALOG_EJB = "java:comp/env/ejb/catalog/useCatalogEJB";

	public static final String CATALOG_DAO_CLASS = "java:comp/env/param/CatalogDAOClass";

	public static final String CATALOG_DAO_SQL_URL = "java:comp/env/url/CatalogDAOSQLURL";

	public static final String CATALOG_DAO_DATABASE = "java:comp/env/param/CatalogDAODatabase";
	
	public static final String DAO_SQL_URL = "java:comp/env/url/DAOSQLURL";

	public static final String DAO_DATABASE = "java:comp/env/param/DAODatabase";
	
	public static final String GENERIC_DAO_CLASS = "java:comp/env/param/GenericDAOClass";
	

}
