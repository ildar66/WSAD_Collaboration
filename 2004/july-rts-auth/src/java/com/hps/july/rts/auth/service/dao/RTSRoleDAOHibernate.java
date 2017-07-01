/*
 *  $Id: RTSRoleDAOHibernate.java,v 1.6 2007/02/28 14:21:47 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.auth.service.dao;

import java.util.List;
import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.dao.DataAccessException;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

import com.hps.july.rts.auth.object.RTSRole;

import com.hps.april.common.FinderException;

import com.hps.april.auth.object.Person;

public class RTSRoleDAOHibernate extends HibernateDaoSupport implements RTSRoleDAO {

    private RTSRoleAliases roleAliases;
	private List roles = null;


    public RTSRoleAliases getRoleAliases() {
        return roleAliases;
    }

    public void setRoleAliases(RTSRoleAliases roleAliases) {
        this.roleAliases = roleAliases;
    }

    public RTSRole load(Integer roleId) throws FinderException {
    	if(roles == null) loadRoles();
        try {
			for(int i = 0; i < roles.size(); i++) {
				RTSRole role = (RTSRole)roles.get(i);
				if(role.getId().equals(roleId)) return (RTSRole)role;
			}
            return (RTSRole)getHibernateTemplate().load(RTSRole.class, roleId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new FinderException("Cannot find role [" + roleId + "]", e);
        }
    }

    public RTSRole load(String roleName) throws FinderException {
        List roleList = getHibernateTemplate().find(
                "From RTSRole as role Where role.name = ?",
                new Object[] { roleName });

        if (!roleList.isEmpty()) {
            if (roleList.size() > 1) logger.warn("Found > 1 RTSRole with role.name=" + roleName + ".");
            return (RTSRole) roleList.get(0);
        }

        logger.warn("Not found role with name: " + roleName);
        return null;
    }

    public RTSRole loadByAlias(String roleAlias) throws FinderException {
        Integer roleId = roleAliases.getRoleId(roleAlias);
        //return load(roleId);
        return (roleId != null)?load(roleId):null;
    }

    public Collection findRoleByPerson(final Person person) throws FinderException {
        return (Collection)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT {rtsRole.*} FROM rts_roles rtsRole, rts_roles2people r2p WHERE r2p.roleid = rtsRole.roleid AND r2p.man = :personId ";
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                sqlQuery.addEntity("rtsRole", RTSRole.class);
                sqlQuery.setInteger("personId", person.getId().intValue());
                return sqlQuery.list();
            }
        });
    }
    
	private void loadRoles() {
		try {
			roles = getHibernateTemplate().find("FROM RTSRole");
		} catch(DataAccessException e) {
			logger.error(e, e);
		}
	}


}
