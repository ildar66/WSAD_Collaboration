package com.hps.july.sync.session;

import java.sql.*;
import java.util.Properties;
/**
 * Bean implementation class for Enterprise Bean: CollaborationClientNRI
 */
public class CollaborationClientNRIBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	public void process() {
		Connection con = null;
		System.out.println("Getting Connection to msAccess");
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (Exception e) {
			System.out.println("Cannot load jdbc driver, class = sun.jdbc.odbc.JdbcOdbcDriver");
			e.printStackTrace(System.out);
		}
		try {
			con = DriverManager.getConnection("jdbc:odbc:DBdelibash", "gerasimov", "max13");
			if (con != null) {
				System.out.println(" Connection OK !" + con.getMetaData().getDriverName());
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Cannot get connection");
			e.printStackTrace(System.out);
		}
	}
}
