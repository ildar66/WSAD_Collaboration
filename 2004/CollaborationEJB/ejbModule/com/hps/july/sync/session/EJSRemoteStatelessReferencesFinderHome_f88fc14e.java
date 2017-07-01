package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessReferencesFinderHome_f88fc14e
 */
public class EJSRemoteStatelessReferencesFinderHome_f88fc14e extends EJSWrapper implements com.hps.july.sync.session.ReferencesFinderHome {
	/**
	 * EJSRemoteStatelessReferencesFinderHome_f88fc14e
	 */
	public EJSRemoteStatelessReferencesFinderHome_f88fc14e() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.hps.july.sync.session.ReferencesFinder create() throws javax.ejb.CreateException, java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		com.hps.july.sync.session.ReferencesFinder _EJS_result = null;
		try {
			com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = _EJS_beanRef.create();
		}
		catch (javax.ejb.CreateException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
	 * getEJBMetaData
	 */
	public javax.ejb.EJBMetaData getEJBMetaData() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		javax.ejb.EJBMetaData _EJS_result = null;
		try {
			com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e)container.preInvoke(this, 1, _EJS_s);
			_EJS_result = _EJS_beanRef.getEJBMetaData();
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
	 * getHomeHandle
	 */
	public javax.ejb.HomeHandle getHomeHandle() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		javax.ejb.HomeHandle _EJS_result = null;
		try {
			com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e)container.preInvoke(this, 2, _EJS_s);
			_EJS_result = _EJS_beanRef.getHomeHandle();
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
	 * remove
	 */
	public void remove(java.lang.Object arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		
		try {
			com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e)container.preInvoke(this, 3, _EJS_s);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
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
		return ;
	}
	/**
	 * remove
	 */
	public void remove(javax.ejb.Handle arg0) throws java.rmi.RemoteException, javax.ejb.RemoveException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		
		try {
			com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessReferencesFinderHomeBean_f88fc14e)container.preInvoke(this, 4, _EJS_s);
			_EJS_beanRef.remove(arg0);
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try {
				container.postInvoke(this, 4, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
