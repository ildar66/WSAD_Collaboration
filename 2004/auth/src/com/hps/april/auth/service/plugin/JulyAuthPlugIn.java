/*
 *  $Id: JulyAuthPlugIn.java,v 1.3 2006/12/04 11:29:25 dkrivenko Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.service.plugin;

import java.util.Collection;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Role;
import com.hps.april.auth.service.AuthPlugIn;
import com.hps.april.auth.service.dao.RoleDAO;

public class JulyAuthPlugIn implements AuthPlugIn {

	private RoleDAO roleDAO;
	
	public JulyAuthPlugIn() {}
	
	public JulyAuthPlugIn(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public Collection getRoles(Operator operator) {
		if (operator != null){
			return operator.getRoleList();
		}

		return null;
	}

	public Role getRole(String roleName) {
		return roleDAO.load(roleName);
	}

	
	
	
}
