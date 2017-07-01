package com.hps.july.rts.service;

import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.object.RTSStatus;
import com.hps.april.common.Service;

import java.util.Collection;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * 
 *  @author abaykov
 *  Created on 18.01.2006
 */
public interface RTSStatusService extends Service {

	String SERVICE_NAME = "july.rts.statusService";
	
	public Collection findAllRTSStatuses();

	public Collection findAllRTSStatusesForRole(RTSRole role);

    public RTSStatus findRTSStatus(Integer ident);

    public String getListOfStatuses(Collection statuses);

//    public void saveRTSStatus(RTSStatus RTSStatus) throws CreateObjectException;

//    public void updateRTSStatus(RTSStatus RTSStatus) throws StoreObjectException;

//    public void removeRTSStatus(RTSStatus RTSStatus) throws RemoveObjectException;

}
