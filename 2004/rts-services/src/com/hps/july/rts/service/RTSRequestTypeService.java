package com.hps.july.rts.service;

import com.hps.july.rts.object.RTSRequestType;
import com.hps.april.common.Service;

import java.util.Collection;

/**
 * RTS ("Заявки на расширение региональной ТС")
 * 
 *  @author abaykov
 *  Created on 18.01.2006
 */
public interface RTSRequestTypeService extends Service {

	String SERVICE_NAME = "july.rts.service.requestTypeService";
	
    public Collection findAllRTSRequestTypes();

    public RTSRequestType findRTSRequestType(Integer ident);

//    public void saveRTSRequestType(RTSRequestType RTSRequestType) throws CreateObjectException;

//    public void updateRTSRequestType(RTSRequestType RTSRequestType) throws StoreObjectException;

//    public void removeRTSRequestType(RTSRequestType RTSRequestType)) throws RemoveObjectException;

}
