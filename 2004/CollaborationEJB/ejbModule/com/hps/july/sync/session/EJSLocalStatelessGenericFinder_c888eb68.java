package com.hps.july.sync.session;

import com.ibm.ejs.container.*;
import java.rmi.RemoteException;

/**
 * EJSLocalStatelessGenericFinder_c888eb68
 */
public class EJSLocalStatelessGenericFinder_c888eb68 extends EJSLocalWrapper implements com.hps.july.sync.session.GenericFinderLocal {
	/**
	 * EJSLocalStatelessGenericFinder_c888eb68
	 */
	public EJSLocalStatelessGenericFinder_c888eb68() {
		super();	}
	/**
	 * findPage
	 */
	public com.hps.july.sync.model.Page findPage(java.lang.String xml_get_pagem, java.lang.String[] parameterValues, int start, int count, java.lang.String orderBy) {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		com.hps.july.sync.model.Page _EJS_result = null;
		try {
			com.hps.july.sync.session.GenericFinderBean beanRef = (com.hps.july.sync.session.GenericFinderBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_result = beanRef.findPage(xml_get_pagem, parameterValues, start, count, orderBy);
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
}
