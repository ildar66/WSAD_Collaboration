package com.hps.july.sync.session;
/**
 * Home interface for Enterprise Bean: ReferencesFinder
 */
public interface ReferencesFinderHome extends javax.ejb.EJBHome {
	/**
	 * Creates a default instance of Session Bean: ReferencesFinder
	 */
	public com.hps.july.sync.session.ReferencesFinder create()
		throws javax.ejb.CreateException, java.rmi.RemoteException; 
	//public com.hps.july.sync.session.ReferencesFinder create(int typeDatabase, boolean isXML) throws javax.ejb.CreateException, java.rmi.RemoteException;
}
