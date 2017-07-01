package com.hps.july.rts.auth.object;

import com.hps.april.auth.object.Role;
import com.hps.april.common.object.ExtensibleObject;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 13.12.2005
 * Time: 14:45:59
 */
public class RTSGroup extends ExtensibleObject {

    private Integer channelTypeId;
    private Integer regionId;
    private Integer filialId;
    private Integer roleId;
    private Integer initiatorCode;
    private RTSRole role;

    public RTSGroup() {
    }

    public Integer getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(Integer channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public Integer getFilialId() {
        return filialId;
    }

    public void setFilialId(Integer filialId) {
        this.filialId = filialId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public RTSRole getRole() {
        return role;
    }

    public void setRole(RTSRole role) {
        this.role = role;
    }

    public Integer getInitiatorCode() {
        return initiatorCode;
    }

    public void setInitiatorCode(Integer initiatorCode) {
        this.initiatorCode = initiatorCode;
    }

    public String toString() {
        return "RTSGroup:[id="+getId()+",name="+getName()+"]";
    }

	public String getDescription() {
		return null;
	}

}
