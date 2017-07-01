package com.hps.july.rts.auth.object;

import com.hps.april.auth.object.Role;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 14.12.2005
 * Time: 14:07:36
 * @author dkrivenko
 */
public class RTSRole implements Role {
    public static final String ADMINISTRATOR = "administrator";
    public static final String DEVELOPER = "developer";

    public static final Integer INITIATOR = new Integer(1);
    public static final Integer REGION_MANAGER = new Integer(2);
    public static final Integer PERFORMER = new Integer(3);
    public static final Integer TOP_MANAGER = new Integer(4);
    public static final Integer ASSERTOR = new Integer(5);
    public static final Integer TESTER = new Integer(6);
    public static final Integer SETS_RESPONSIBLE = new Integer(7);
    public static final Integer ARENDA_RESPONSIBLE = new Integer(8);
    public static final Integer ARENDA_PERFORMER = new Integer(9);
	public static final Integer MANAGER = new Integer(10);

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Полное имя роли
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Полное имя роли
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

    public boolean equals(Object obj) {
    	if (obj instanceof RTSRole){
    		RTSRole role = (RTSRole) obj;
    		if (role.getId() != null && getId() != null) {
    			if (role.getId().equals(getId())) return true;
    		} else if (role.getName() != null && getName() != null){
    			if (role.getName().equals(getName())) return true;
    		}
    	}
    	return false;
    }
    
	public int hashCode() {
		int result;
		result = ((this.id != null)?this.id.hashCode():0);
		result = 29 * result + ((this.name != null)?this.name.hashCode():0);
		return result;
	}
    
    
    public String toString() {
        return "RTSRole:[id="+getId()+",name="+getName()+"]";    
    }
   
	public String getDescription() {
		return null;
	}

}
