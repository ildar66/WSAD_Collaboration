/*
 *  $Id: RTSAuthPlugIn.java,v 1.4 2007/02/27 17:54:23 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.auth.service.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Person;
import com.hps.april.auth.object.Role;
import com.hps.april.auth.service.AuthPlugIn;
import com.hps.april.common.FinderException;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.auth.service.dao.RTSGroupDAO;
import com.hps.july.rts.auth.service.dao.RTSPersonDAO;
import com.hps.july.rts.auth.service.dao.RTSRoleAliases;
import com.hps.july.rts.auth.service.dao.RTSRoleDAO;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/02/27 17:54:23 $
 */
public class RTSAuthPlugIn implements AuthPlugIn {

	protected RTSRoleDAO rtsRoleDAO;
	protected RTSGroupDAO rtsGroupDAO;
	protected RTSPersonDAO personDAO;

	private RTSRoleAliases roleAliases;

	public RTSGroupDAO getRtsGroupDAO() {
		return rtsGroupDAO;
	}

	public void setRtsGroupDAO(RTSGroupDAO rtsGroupDAO) {
		this.rtsGroupDAO = rtsGroupDAO;
	}

	public RTSPersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(RTSPersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public RTSRoleDAO getRtsRoleDAO() {
		return rtsRoleDAO;
	}

	public void setRtsRoleDAO(RTSRoleDAO roleDAO) {
		this.rtsRoleDAO = roleDAO;
	}

	public RTSRoleAliases getRoleAliases() {
		return roleAliases;
	}

	public void setRoleAliases(RTSRoleAliases roleAliases) {
		this.roleAliases = roleAliases;
	}

	public Collection getRoles(Operator operator) {
		Person person = operator.getPerson();
		ArrayList roleList = new ArrayList();
		//по ролям RTS
		try {
			Collection list2 = rtsRoleDAO.findRoleByPerson(person);
			for (Iterator itr = list2.iterator(); itr.hasNext();) {
				RTSRole personRole = (RTSRole) itr.next();
				roleList.add(personRole);
//				  System.out.println("!>>>>>>>>!["+personRole.getId()+"]");

			}
		} catch(FinderException e) {
			e.printStackTrace();
		}
		//по группам
		try {
			Collection list2 = rtsGroupDAO.findGroupByPerson(person);
			for (Iterator itr = list2.iterator(); itr.hasNext();) {
				RTSGroup group = (RTSGroup) itr.next();
                RTSRole role = (group != null)?group.getRole():null;
                if(role != null) roleList.add(role);
//				  System.out.println("G!>>>>>>>>G!["+group.getRole().getId()+"]");
			}
		} catch(FinderException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	public Role getRole(String roleName) {
        try {
            return rtsRoleDAO.loadByAlias(roleName);
        } catch(FinderException e) {
            return null;
        }
    }

}
