package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessReferencesFinderHomeBean_f88fc14e
 */
public class EJSStatelessReferencesFinderHomeBean_f88fc14e extends EJSHome {
	/**
	 * EJSStatelessReferencesFinderHomeBean_f88fc14e
	 */
	public EJSStatelessReferencesFinderHomeBean_f88fc14e() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.hps.july.sync.session.ReferencesFinder create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.hps.july.sync.session.ReferencesFinder result = null;
boolean createFailed = false;
try {
	result = (com.hps.july.sync.session.ReferencesFinder) super.createWrapper(new BeanId(this, null));
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
