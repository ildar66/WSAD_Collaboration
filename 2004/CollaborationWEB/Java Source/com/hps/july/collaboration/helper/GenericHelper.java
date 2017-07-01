/*
 * Created on 29.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.collaboration.helper;

import java.rmi.*;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.*;
import com.hps.july.sync.model.dao.*;
import com.hps.july.sync.servicelocator.*;
import com.hps.july.sync.servicelocator.ejb.*;
import com.hps.july.sync.session.*;
import com.hps.july.sync.session.dao.JNDINames;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GenericHelper {
	private GenericDAO dao;
	private boolean useFastLane = false;

	public GenericHelper(boolean useFastLane) {
		this.useFastLane = useFastLane;
	}

	public Page searchPage(String xml_get_sql, int start, int count, String orderBy) throws ReferencesException {
		return searchPage(xml_get_sql, null, start, count, orderBy);
	}

	public Page searchPage(String xml_get_sql, String[] parameterValies, int start, int count, String orderBy) throws ReferencesException {
		return useFastLane ? searchPageFromDAO(xml_get_sql, parameterValies, start, count, orderBy) : searchPageFromEJB(xml_get_sql, parameterValies, start, count, orderBy);
	}

	private Page searchPageFromEJB(String xml_get_sql, String[] parameterValies, int start, int count, String orderBy) throws ReferencesException {
		return getGenericEJB().findPage(xml_get_sql, parameterValies, start, count, orderBy);
	}

	private Page searchPageFromDAO(String xml_get_sql, String[] parameterValies, int start, int count, String orderBy) throws ReferencesException {
		try {
			if (dao == null)
				dao = GenericDAOFactory.getDAO();
			Connection conn = getDatasource().getConnection();
			return dao.executeSelect(conn, xml_get_sql, parameterValies, start, count, orderBy);
		} catch (SQLException e) {
			throw new ReferencesException(e.getMessage());
		} catch (ReferencesDAOSysException re) {
			throw new ReferencesException(re.getMessage());
		}
	}
	/*
	 * Use the Service locator pattern to located the Catalog Home and use the home
	 * to create an instance of the ReferencesFinder.
	 */
	private GenericFinderLocal getGenericEJB() throws ReferencesException {
		try {
			ServiceLocator sl = new ServiceLocator();
			GenericFinderLocalHome home = (GenericFinderLocalHome) sl.getLocalHome(JNDINames.Generic_EJBHOME);
			return home.create();
		} catch (javax.ejb.CreateException cx) {
			throw new ReferencesException("GenericHelper: failed to create ReferencesFinder EJB: caught " + cx);
		} catch (ServiceLocatorException slx) {
			throw new ReferencesException("GenericHelper: failed to look up ReferencesFinder Home: caught " + slx);
		}
	}

	private DataSource getDatasource() throws ReferencesException {
		try {
			//нужно грузить из настроек (пока так):
			ServiceLocator sl = new ServiceLocator();
			return (DataSource) sl.getDataSource(JNDINames.INFORMIX_DATASOURCE);
		} catch (ServiceLocatorException slx) {
			throw new ReferencesException("GenericHelper: failed to look up DataSource: caught " + slx);
		}
	}
}