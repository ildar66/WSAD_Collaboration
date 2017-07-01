package com.hps.july.rts.object.channelinterface;

import com.hps.april.common.object.ExtensibleObject;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 07.12.2005
 * Time: 19:56:34
 */
public class ChannelInterface extends ExtensibleObject {

    private Collection interfaceTypes;

    public ChannelInterface() {
        setId(null);
        setName(null);
    }

    public Collection getInterfaceTypes() {
        return interfaceTypes;
    }

    public void setInterfaceTypes(Collection interfaceTypes) {
        this.interfaceTypes = interfaceTypes;
    }
    
	public String toString(){
		return getName(); 
	}
}
