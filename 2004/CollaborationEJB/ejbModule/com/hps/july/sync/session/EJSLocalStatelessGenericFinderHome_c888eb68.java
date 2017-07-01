package com.hps.july.sync.session;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessGenericFinderHome_c888eb68
 */
public class EJSLocalStatelessGenericFinderHome_c888eb68 extends EJSLocalWrapper implements com.hps.july.sync.session.GenericFinderLocalHome {
	/**
	 * EJSLocalStatelessGenericFinderHome_c888eb68
	 */
	public EJSLocalStatelessGenericFinderHome_c888eb68() {
		super();	}
	/**
	 * create
	 */
	public com.hps.july.sync.session.GenericFinderLocal create() throws javax.ejb.CreateException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		com.hps.july.sync.session.GenericFinderLocal _EJS_result = null;
		try {
			com.hps.july.sync.session.EJSStatelessGenericFinderHomeBean_c888eb68 _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessGenericFinderHomeBean_c888eb68)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = _EJS_beanRef.create_Local();
		}
		catch (javax.ejb.CreateException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 0, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * remove
	 */
	public void remove(java.lang.Object arg0) throws javax.ejb.RemoveException, javax.ejb.EJBException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		
		try {
			com.hps.july.sync.session.EJSStatelessGenericFinderHomeBean_c888eb68 _EJS_beanRef = (com.hps.july.sync.session.EJSStatelessGenericFinderHomeBean_c888eb68)container.preInvoke(this, 1, _EJS_s);
			_EJS_beanRef.remove(arg0);
		}
		catch (javax.ejb.RemoveException ex) {
			_EJS_s.setCheckedException(ex);
			throw ex;
		}
		catch (javax.ejb.EJBException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (java.rmi.RemoteException ex) {
		 	_EJS_s.setUncheckedLocalException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedLocalException(ex);
		}

		finally {
			try {
				container.postInvoke(this, 1, _EJS_s);
			} catch ( RemoteException ex ) {
				_EJS_s.setUncheckedLocalException(ex);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return ;
	}
}
