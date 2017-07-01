/*
 * Created on 17.12.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.model.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.util.JNDINames;
import com.hps.july.sync.util.tracer.Debug;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GenericDAOFactory {

	/**
	 * This method instantiates a particular subclass implementing
	 * the DAO methods based on the information obtained from the
	 * deployment descriptor
	 */
	public static GenericDAO getDAO() throws ReferencesDAOSysException{

		GenericDAO catDao = null;
		try {
			InitialContext ic = new InitialContext();
			String className = (String) ic.lookup(JNDINames.GENERIC_DAO_CLASS);
			Debug.println("GENERIC_DAO_CLASS className=" + className);
			catDao = (GenericDAO) Class.forName(className).newInstance();
		} catch (NamingException ne) {
			throw new ReferencesDAOSysException("GenericDAOFactory.getDAO:  NamingException while getting DAO type : \n" + ne.getMessage());
		} catch (Exception se) {
			throw new ReferencesDAOSysException("GenericDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
		}
		return catDao;
	}


}
