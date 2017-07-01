package com.hps.july.sync.session;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.sql.DataSource;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.Page;
import com.hps.july.sync.model.dao.*;
import com.hps.july.sync.servicelocator.ServiceLocatorException;
import com.hps.july.sync.servicelocator.ejb.ServiceLocator;
import com.hps.july.sync.util.tracer.Debug;
import com.hps.july.sync.session.dao.JNDINames;
/**
 * Bean implementation class for Enterprise Bean: GenericFinder
 */
public class GenericFinderBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	private GenericDAO dao;
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
		try {
			dao = GenericDAOFactory.getDAO();
		} catch (ReferencesDAOSysException se) {
			Debug.println("Exception getting dao " + se);
			throw new EJBException(se.getMessage());
		}

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

	//бизнес методы:
	/**
	 * @param xml_get_pagem
	 * @param parameterValues
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return Page
	 */
	public Page findPage(String xml_get_pagem, String[] parameterValues, int start, int count, String orderBy) {
		try {
			Connection con = getDatasource().getConnection();
			return dao.executeSelect(con, xml_get_pagem, parameterValues, start, count, orderBy);
		} catch (ServiceLocatorException sle) {
			throw new EJBException(sle.getMessage());
		} catch (SQLException e) {
			throw new EJBException(e.getMessage());
		}
	}

	/**
	 * 
	 * @return DataSource
	 * @throws ServiceLocatorException
	 */
	private DataSource getDatasource() throws ServiceLocatorException {
		ServiceLocator sl = new ServiceLocator();
		return (DataSource) sl.getDataSource(JNDINames.INFORMIX_DATASOURCE);
	}
}
