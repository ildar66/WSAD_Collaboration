package com.hps.july.sync.session;

import java.util.Collection;

import com.hps.july.sync.model.Page;
import com.hps.july.sync.session.dao.ReferencesFinderPersistence;

/**
 * Bean implementation class for Enterprise Bean: ReferencesFinder
 */
public class ReferencesFinderBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
	// persister class
	private ReferencesFinderPersistence persister = null;

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
		if (persister == null)
			persister = new ReferencesFinderPersistence();
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
		/**if (persister == null)
			persister = new ReferencesFinderPersistence();
		*/
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
		/**if (persister != null)
			persister.freeResources();
		persister = null;
		*/
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	
	//бизнес методы:
	public Collection findDbsAntennaPlaces(String orderBy) {
		return persister.findDbsAntennaPlaces(orderBy);
	}
	
	public Collection findDbsPositions(String orderBy, int startAtRow, int howManyRows) {
		return persister.findDbsPositions(orderBy, startAtRow, howManyRows);
	}
	
	public Collection findDbsPositions(String orderBy) {
		return findDbsPositions(orderBy, 0, 0);
	}
	
	public Page findDbsPositionsPage(String orderBy, int startAtRow, int howManyRows) {
		return persister.findDbsPositions_Page(orderBy, startAtRow, howManyRows);
	}		
}
