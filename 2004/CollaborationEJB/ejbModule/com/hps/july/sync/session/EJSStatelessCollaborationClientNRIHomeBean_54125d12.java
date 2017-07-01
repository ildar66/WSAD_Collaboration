package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessCollaborationClientNRIHomeBean_54125d12
 */
public class EJSStatelessCollaborationClientNRIHomeBean_54125d12 extends EJSHome {
	/**
	 * EJSStatelessCollaborationClientNRIHomeBean_54125d12
	 */
	public EJSStatelessCollaborationClientNRIHomeBean_54125d12() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.hps.july.sync.session.CollaborationClientNRI create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.hps.july.sync.session.CollaborationClientNRI result = null;
boolean createFailed = false;
try {
	result = (com.hps.july.sync.session.CollaborationClientNRI) super.createWrapper(new BeanId(this, null));
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
