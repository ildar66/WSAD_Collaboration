package com.hps.july.rts.service;

import com.hps.july.rts.object.channeltype.ChannelType;
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
public interface ChannelTypeService extends Service {

	String SERVICE_NAME = "july.rts.service.channelTypeService";
	
    public Collection findAllChannelTypes();

    public ChannelType findChannelType(Integer ident);

    public void saveChannelType(ChannelType type) throws CreateObjectException;

    public void updateChannelType(ChannelType type) throws StoreObjectException;

    public void removeChannelType(ChannelType type) throws RemoveObjectException;
}
