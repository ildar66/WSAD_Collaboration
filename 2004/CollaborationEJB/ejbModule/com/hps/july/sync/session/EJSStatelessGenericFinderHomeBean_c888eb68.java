package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessGenericFinderHomeBean_c888eb68
 */
public class EJSStatelessGenericFinderHomeBean_c888eb68 extends EJSHome {
	/**
	 * EJSStatelessGenericFinderHomeBean_c888eb68
	 */
	public EJSStatelessGenericFinderHomeBean_c888eb68() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create_Local
	 */
	public com.hps.july.sync.session.GenericFinderLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.hps.july.sync.session.GenericFinderLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (com.hps.july.sync.session.GenericFinderLocal) super.createWrapper_Local(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
