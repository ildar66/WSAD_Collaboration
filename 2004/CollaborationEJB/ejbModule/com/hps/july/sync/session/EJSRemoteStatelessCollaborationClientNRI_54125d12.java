package com.hps.july.sync.session;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessCollaborationClientNRI_54125d12
 */
public class EJSRemoteStatelessCollaborationClientNRI_54125d12 extends EJSWrapper implements CollaborationClientNRI {
	/**
	 * EJSRemoteStatelessCollaborationClientNRI_54125d12
	 */
	public EJSRemoteStatelessCollaborationClientNRI_54125d12() throws java.rmi.RemoteException {
		super();	}
	/**
	 * process
	 */
	public void process() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		
		try {
			com.hps.july.sync.session.CollaborationClientNRIBean _EJS_beanRef = (com.hps.july.sync.session.CollaborationClientNRIBean)container.preInvoke(this, 0, _EJS_s);
			_EJS_beanRef.process();
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
		return ;
	}
}
