package com.hps.july.rts.service;

import com.hps.july.rts.object.channelinterfacetype.ChannelInterfaceType;
import com.hps.april.common.Service;
import com.hps.april.common.CreateObjectException;
import com.hps.april.common.RemoveObjectException;
import com.hps.april.common.StoreObjectException;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 19:58:01
 */
public interface ChannelInterfaceTypeService extends Service {

	String SERVICE_NAME = "july.rts.service.channelInterfaceTypeService";
	
    public Collection findAllChannelInterfaceTypes();

    public Collection findChannelInterfaceTypesByInterfaceId(Integer iId);

    public ChannelInterfaceType findChannelInterfaceType(Integer ident);

    public void saveChannelInterfaceTypes(ChannelInterfaceType type) throws CreateObjectException;

    public void updateChannelInterfaceTypes(ChannelInterfaceType type) throws StoreObjectException;

    public void removeChannelInterfaceTypes(ChannelInterfaceType type) throws RemoveObjectException;

    public void removeChannelInterfaceTypes(Integer ident)  throws RemoveObjectException;
}
