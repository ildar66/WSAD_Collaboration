package com.hps.april.common.object;

import java.io.Serializable;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Created on 23.12.2005
 */
public class ValueObject implements Serializable {

	private Integer id = null;
	private String name = null;

	public ValueObject() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object o) {
		if(o instanceof ValueObject) {
			ValueObject vo = (ValueObject)o;
			boolean result1 = false;
			boolean result2 = false;
			if(this.id != null && vo.getId() != null &&
					this.id.equals(vo.getId())) result1 = true;
			if(this.id == null && vo.getId() == null) result1 = true;
			if(this.name == null && vo.getName() == null) result2 = true;
			if(this.name != null && vo.getName() != null &&
					this.name.equals(vo.getName())) result2 = true;
			return (result1 && result2);
		}
		return false;
	}

	public int hashCode() {
		int result;
		result = ((this.id != null)?this.id.hashCode():0);
		result = 29 * result + ((this.name != null)?this.name.hashCode():0);
		return result;
	}
	
	public String toString(){
		if(name!=null) return name.trim();
		return null;
	}
}
