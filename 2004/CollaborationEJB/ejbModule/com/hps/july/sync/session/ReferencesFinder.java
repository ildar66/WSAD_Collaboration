package com.hps.july.sync.session;
import java.util.Collection;
import com.hps.july.sync.model.Page;
/**
 * Remote interface for Enterprise Bean: ReferencesFinder
 */
public interface ReferencesFinder extends javax.ejb.EJBObject {
	public Collection findDbsAntennaPlaces(String orderBy) throws java.rmi.RemoteException;
	public Collection findDbsPositions(String orderBy) throws java.rmi.RemoteException;
	public Collection findDbsPositions(String orderBy, int startAtRow, int howManyRows)
		throws java.rmi.RemoteException;
	public Page findDbsPositionsPage(String orderBy, int startAtRow, int howManyRows) throws java.rmi.RemoteException;
}
