/*
 *  $Id: AuthInfoImpl.java,v 1.7 2007/02/28 14:17:03 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.april.auth.object;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.7 $ $Date: 2007/02/28 14:17:03 $
 */
public class AuthInfoImpl implements AuthInfo {
    private static final long serialVersionUID = 10L;

    private Integer operatorId;
    private String operatorLogin;

    private Integer personId;
    private String personName;

    private Set roles;
//    private Set permissions;

    public AuthInfoImpl(String login) {
        this.operatorLogin = login;
        this.operatorId = null;
        this.personId = null;
        this.roles = new HashSet();
//        this.permissions = new HashSet();
    }

    public String getName() {
        return operatorLogin;
    }

    public Integer getOperatorId() {
        return operatorId;
    }
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }
    public Integer getPersonId() {
        return personId;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getOperatorLogin() {
        return operatorLogin;
    }
    public void setOperatorLogin(String operatorLogin) {
        this.operatorLogin = operatorLogin;
    }
    public String getLogin(){
        return getOperatorLogin();
    }

    public void addRole(Role role) {
        if(role != null) this.roles.add(role);
    }

    /**
     * @deprecated используете AuthInfoImpl.addRole(Role role)
     */
    public void addRole(String role) {
        if(role != null) this.roles.add(role);
    }

    public void addRoles(Collection roles) {
        if(roles != null) this.roles.addAll(roles);
    }

    public boolean isUserInRole(Role role) {
        if(role != null) return this.roles.contains(role);
        return false;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * @deprecated этот метод проверяет только String роли. Рекомендуется использовать AuthInfoImpl.isUserInRole(Role role)
     */
    public boolean isUserInRole(String role) {
        if(role != null) return this.roles.contains(role);
        return false;
    }

    public String toString() {
        return "AuthInfo[operatorId=" + getOperatorId() + ",operatorLogin="+getOperatorLogin()+",personId=" + getPersonId() + "] ROLES ["+roles+"]";
    }

//	public void addPermission(Permission permission) {
//		if (permission != null)
//			permissions.add(permission);
//	}
//
//	public void addPermissions(Collection permissions) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	public boolean checkPermission(Permission permission) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
