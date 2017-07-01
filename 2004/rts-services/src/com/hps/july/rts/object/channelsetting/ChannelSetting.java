package com.hps.july.rts.object.channelsetting;

import com.hps.april.common.object.ExtensibleObject;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 19:54:46
 */
public class ChannelSetting extends ExtensibleObject {

    private Integer groupId;

    public ChannelSetting() {
        setId(null);
        setName(null);
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public String toString(){
    	return getName(); 
    }
}
