/*
 * Created on 29.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.collaboration.helper;

import java.rmi.RemoteException;

import javax.ejb.RemoveException;

import com.hps.july.sync.exceptions.ReferencesDAOSysException;
import com.hps.july.sync.model.Page;
import com.hps.july.sync.servicelocator.ServiceLocatorException;
import com.hps.july.sync.servicelocator.ejb.ServiceLocator;
import com.hps.july.sync.session.ReferencesFinder;
import com.hps.july.sync.session.ReferencesFinderHome;
import com.hps.july.sync.session.dao.JNDINames;
import com.hps.july.sync.session.dao.ReferencesFinderPersistence;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReferencesHelper {
	private ReferencesFinderPersistence finder;
	private boolean useFastLane = false;
	private int count = 2;
	private int start = 0;
	private String orderBy = null;

	public ReferencesHelper(boolean useFastLane) {
		this.useFastLane = useFastLane;
		finder = new ReferencesFinderPersistence();
	}

	public ReferencesHelper(boolean useFastLane, int typeDatabase, boolean isXML) {
		this.useFastLane = useFastLane;
		finder = new ReferencesFinderPersistence(typeDatabase, isXML);
	}

	/**
	 * @param i
	 */
	public void setCount(int i) {
		count = i;
	}

	/**
	 * @param i
	 */
	public void setStart(int i) {
		start = i;
	}

	public Page searchDbsPositions_Page(int start, int count, String orderBy) throws ReferencesException {
		return useFastLane ? searchDbsPositionsFromDAO(start, count, orderBy) : searchDbsPositionsFromEJB(start, count,orderBy);
	}
	public Page searchDbsPositions_Page() throws ReferencesException {
		return useFastLane ? searchDbsPositionsFromDAO(start, count, orderBy) : searchDbsPositionsFromEJB(start, count,orderBy);
	}

	private Page searchDbsPositionsFromEJB(int start, int count, String orderBy) throws ReferencesException {
		try {
			return getReferencesEJB().findDbsPositionsPage(orderBy, start, count);
		} catch (RemoteException re) {
			throw new ReferencesException("ReferencesHelper: failed to searchDbsPositionsFromEJB: caught " + re);
		}
	}

	private Page searchDbsPositionsFromDAO(int start, int count, String orderBy) throws ReferencesException {
		try {
			return finder.findDbsPositions_Page(orderBy, start, count);
		} catch (ReferencesDAOSysException re) {
			throw new ReferencesException(re.getMessage());
		}
	}
	/*
	 * Use the Service locator pattern to located the Catalog Home and use the home
	 * to create an instance of the ReferencesFinder.
	 */
	private ReferencesFinder getReferencesEJB() throws ReferencesException {
		try {
			ServiceLocator sl = new ServiceLocator();
			ReferencesFinderHome home = (ReferencesFinderHome) sl.getLocalHome(JNDINames.References_EJBHOME);
			return home.create();
		} catch (javax.ejb.CreateException cx) {
			throw new ReferencesException("ReferencesHelper: failed to create ReferencesFinder EJB: caught " + cx);
		} catch (RemoteException re) {
			throw new ReferencesException("ReferencesHelper: failed to create ReferencesFinder EJB: caught " + re);
		} catch (ServiceLocatorException slx) {
			throw new ReferencesException("ReferencesHelper: failed to look up ReferencesFinder Home: caught " + slx);
		}
	}
	/**
	 * @param string
	 */
	public void setOrderBy(String string) {
		orderBy = string;
	}

}