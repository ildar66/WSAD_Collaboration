package com.hps.april.auth.service.dao;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.april.auth.object.JulyRole;
import com.hps.april.auth.object.Role;

/**
 * @author Dimitry Krivenko 
 * 26.02.2006
 */
public class RoleDAOHibernate extends HibernateDaoSupport implements RoleDAO {

	// Длина поля role  в базе данных
	private static int ROLE_NAME_LENGTH = 20;

    private List roles = null;

    public JulyRole load(String roleName) {
        if(roles == null) loadRoles();
        try {
        	roleName = expandRoleName(roleName);
            for(int i = 0; i < roles.size(); i++) {
                Role role = (Role)roles.get(i);
                if(role.getName().equals(roleName)) return (JulyRole)role;
            }
            return (JulyRole)getHibernateTemplate().load(JulyRole.class, roleName);
        } catch (DataAccessException e){
            logger.warn(e);
            return null;
        }
    }

	protected String expandRoleName(String roleName){
		// Заполнение строки пробелами до размера ROLE_NAME_LENGTH
		return StringUtils.rightPad(roleName, ROLE_NAME_LENGTH);
	}


    public Collection loadRoles(Integer operatorId) {
        throw new IllegalArgumentException("loadRoles not implemented");
    }

    private void loadRoles() {
        try {
            roles = getHibernateTemplate().find("FROM JulyRole");
        } catch(DataAccessException e) {
            logger.warn(e);
        }
    }

	public List getRoleList() {
		return getHibernateTemplate().find("from JulyRole role order by role.name");
	}

	public List getRoleList(String taskName) {
		return getHibernateTemplate().find("from JulyRole role where role.taskName=? or role.taskName=? order by role.name ", 
				new Object[] {taskName, "все"});
	}

}
