package com.hps.july.rts.object.channelinterfacetype;

import com.hps.april.common.object.ExtensibleObject;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 19:57:02
 */
public class ChannelInterfaceType extends ExtensibleObject {

    private Integer interfaceId;
    public ChannelInterfaceType() {
        setId(null);
        setName(null);
        this.interfaceId = null;
    }

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }
    
	public String toString(){
		return getName(); 
	}
    
}
