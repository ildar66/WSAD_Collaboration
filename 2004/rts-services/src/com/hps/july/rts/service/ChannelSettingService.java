package com.hps.july.rts.service;

import com.hps.july.rts.object.channelsetting.ChannelSetting;
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
public interface ChannelSettingService extends Service {

    String SERVICE_NAME = "july.rts.service.channelSettingService";

	public Collection findAllChannelSettings();

    public ChannelSetting findChannelSetting(Integer ident);

    public void saveChannelSetting(ChannelSetting channelSetting) throws CreateObjectException;

    public void updateChannelSetting(ChannelSetting channelSetting) throws StoreObjectException;

    public void removeChannelSetting(ChannelSetting channelSetting) throws RemoveObjectException;

    public void removeChannelSetting(Integer ident) throws RemoveObjectException;
}
