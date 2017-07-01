package com.hps.july.rts.service;

import com.hps.july.rts.object.channelinterface.ChannelInterface;
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
public interface ChannelInterfaceService extends Service {

	String SERVICE_NAME = "july.rts.service.channelInterfaceService";
	
    public Collection findAllChannelInterface();

    public ChannelInterface findChannelInterface(Integer ident);

    public void saveChannelInterface(ChannelInterface channelInterface) throws CreateObjectException;

    public void updateChannelInterface(ChannelInterface channelInterface) throws StoreObjectException;

    public void removeChannelInterface(ChannelInterface channelInterface) throws RemoveObjectException;
}
