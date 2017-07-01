/*
 * Created on 19.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.exceptions;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
/**
 * CatalogDAOSysException is an exception that extends the standard
 * RunTimeException Exception. This is thrown by the DAOs of the catalog
 * component when there is some irrecoverable error (like SQLException)
 */

public class ReferencesDAOSysException extends RuntimeException {

	/**
	 * 
	 */
	public ReferencesDAOSysException() {
		super();
	}

	/**
	 * @param s
	 */
	public ReferencesDAOSysException(String s) {
		super(s);
	}

}
