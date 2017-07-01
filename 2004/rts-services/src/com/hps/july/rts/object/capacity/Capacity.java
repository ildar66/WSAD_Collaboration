package com.hps.july.rts.object.capacity;

import com.hps.april.common.object.ExtensibleObject;

import java.util.Collection;

/**
 * RTS ("Заявки на расширение региональной ТС")
 *
 * @author ABaykov
 * Created on 26.04.2006
 */
public class Capacity extends ExtensibleObject {

	private Collection capacities;
    private String indexCap;

    public Capacity() {
		setId(null);
		setName(null);
	}

	public Capacity(Integer id, String name) {
		setId(id);
		setName(name);
	}

	public Collection getCapacities() {
		return capacities;
	}

	public void setСapacities(Collection capacities) {
		this.capacities = capacities;
	}
    
	public String toString(){
		return getName(); 
	}

    public String getIndexCap() {
        return indexCap;
    }

    public void setIndexCap(String index) {
        this.indexCap = index;
    }

}
