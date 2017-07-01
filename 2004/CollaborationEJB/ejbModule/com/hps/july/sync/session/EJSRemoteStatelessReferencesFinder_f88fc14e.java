package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessReferencesFinder_f88fc14e
 */
public class EJSRemoteStatelessReferencesFinder_f88fc14e extends EJSWrapper implements ReferencesFinder {
	/**
	 * EJSRemoteStatelessReferencesFinder_f88fc14e
	 */
	public EJSRemoteStatelessReferencesFinder_f88fc14e() throws java.rmi.RemoteException {
		super();	}
	/**
	 * findDbsPositionsPage
	 */
	public com.hps.july.sync.model.Page findDbsPositionsPage(java.lang.String orderBy, int startAtRow, int howManyRows) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		com.hps.july.sync.model.Page _EJS_result = null;
		try {
			com.hps.july.sync.session.ReferencesFinderBean _EJS_beanRef = (com.hps.july.sync.session.ReferencesFinderBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = _EJS_beanRef.findDbsPositionsPage(orderBy, startAtRow, howManyRows);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * findDbsAntennaPlaces
	 */
	public java.util.Collection findDbsAntennaPlaces(java.lang.String orderBy) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		java.util.Collection _EJS_result = null;
		try {
			com.hps.july.sync.session.ReferencesFinderBean _EJS_beanRef = (com.hps.july.sync.session.ReferencesFinderBean)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = _EJS_beanRef.findDbsAntennaPlaces(orderBy);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * findDbsPositions
	 */
	public java.util.Collection findDbsPositions(java.lang.String orderBy) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		java.util.Collection _EJS_result = null;
		try {
			com.hps.july.sync.session.ReferencesFinderBean _EJS_beanRef = (com.hps.july.sync.session.ReferencesFinderBean)container.preInvoke(this, 2, _EJS_s);
			_EJS_result = _EJS_beanRef.findDbsPositions(orderBy);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 2, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * findDbsPositions
	 */
	public java.util.Collection findDbsPositions(java.lang.String orderBy, int startAtRow, int howManyRows) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		java.util.Collection _EJS_result = null;
		try {
			com.hps.july.sync.session.ReferencesFinderBean _EJS_beanRef = (com.hps.july.sync.session.ReferencesFinderBean)container.preInvoke(this, 3, _EJS_s);
			_EJS_result = _EJS_beanRef.findDbsPositions(orderBy, startAtRow, howManyRows);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 3, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
