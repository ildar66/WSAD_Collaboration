/*
 *  $Id: RTSAuthServiceImpl.java,v 1.11 2007/02/28 14:21:47 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.auth.service.impl;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.AuthInfoImpl;
import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Role;
import com.hps.april.auth.service.impl.AuthServiceImpl;
import com.hps.april.auth.exception.AuthenticationException;
import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.auth.service.dao.RTSPersonDAO;
import com.hps.july.rts.auth.service.dao.RTSRoleDAO;
import com.hps.july.rts.auth.service.dao.RTSRoleAliases;
import com.hps.july.rts.auth.service.dao.RTSGroupDAO;
import com.hps.july.rts.auth.object.RTSGroup;
import com.hps.july.rts.auth.object.RTSRole;

/**
 * @author Dimitry Krivenko 
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.11 $ $Date: 2007/02/28 14:21:47 $
 */
public class RTSAuthServiceImpl extends AuthServiceImpl implements RTSAuthService  {

    protected RTSRoleDAO rtsRoleDAO;
    protected RTSGroupDAO rtsGroupDAO;
    protected RTSPersonDAO personDAO;

    private RTSRoleAliases roleAliases;

    public RTSAuthServiceImpl() {
        this.logger = LogFactory.getLog(getClass());
    }
    
	public void afterPropertiesSet() throws Exception {
	}

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

	public boolean isUserInRole(AuthInfo authInfo, String roleName) {
		if (authInfo == null)
			throw new IllegalArgumentException("AuthInfo can not be null");

		if (roleName == null){
			logger.warn("Role not found: null");
			return false;
		}
		Role role = getRole(roleName);
		if (role == null){
			logger.warn("Role object not found: null");
			return false;
		}
		//проверяем роль а вернее супер роль
		return authInfo.isUserInRole(role);
	}

    public boolean isUserInRtsRole(AuthInfo authInfo, RTSRole role) {
        if (authInfo == null)
            throw new IllegalArgumentException("AuthInfo can not be null");

        if (role == null){
            logger.warn("Role not found: null");
            return false;
        }
		return authInfo.isUserInRole(role);
        //алгоритм постоянно обращается к базе
    }

    /*
     * (non-Javadoc)
     * @see com.hps.july.auth.service.AuthService#getAuthInfo(com.hps.july.auth.object.Operator)
     */
//    public AuthInfo getAuthInfo(Operator operator) {
//
//        if (operator == null)
//           throw new AuthenticationException("Operator: (null) not found ");
//
//		AuthInfoImpl authInfo = new AuthInfoImpl(operator.getLogin());
//        authInfo.setOperatorId(operator.getId());
//        Person person = operator.getPerson();
//        authInfo.setPersonId(person.getId());
//
//        //add user NRI roles
//        Collection roles = operator.getRoleList();
//        for (Iterator i = roles.iterator(); i.hasNext();) {
//            Role role = (Role)i.next();
//            String roleId = role.getName();
//            if(roleId != null) authInfo.addRole(roleId.trim());
//			authInfo.addRole(role);
//        }
//        //add specific RTS role aliasses
//        //по ролям
//        try {
//            Collection list2 = rtsRoleDAO.findRoleByPerson(person);
//            for (Iterator itr = list2.iterator(); itr.hasNext();) {
//                RTSRole personRole = (RTSRole) itr.next();
//				String alias = roleAliases.getRoleAlias(personRole.getId());
//				authInfo.addRole(alias);
//				authInfo.addRole(personRole);
////                System.out.println("!>>>>>>>>!["+personRole.getId()+"]");
//            }
//        } catch(FinderException e) {
//            e.printStackTrace();
//        }
//        //по группам
//        try {
//            Collection list2 = rtsGroupDAO.findGroupByPerson(person);
//            for (Iterator itr = list2.iterator(); itr.hasNext();) {
//                RTSGroup group = (RTSGroup) itr.next();
//				String alias = roleAliases.getRoleAlias(group.getRole().getId());
//				authInfo.addRole(alias);
//				authInfo.addRole(group.getRole());
////                System.out.println("G!>>>>>>>>G!["+group.getRole().getId()+"]");
//            }
//        } catch(FinderException e) {
//            e.printStackTrace();
//        }
//        logger.warn(authInfo.toString());
//        return authInfo;
//    }
    public AuthInfo getAuthInfo(Person person) {
        AuthInfo authInfo = null;
        Collection operators = person.getOperators();
        if(operators == null || operators.size() == 0) return null;
        Operator operator;
        for(Iterator it=operators.iterator();it.hasNext();) {
            operator = (Operator)it.next();
            authInfo = getAuthInfo(operator);
        }
        return authInfo;
    }


    public Person getPerson(AuthInfo authInfo){
        Integer personId = authInfo.getPersonId();
        return getPerson(personId);
    }

    public RTSRole getRTSRole(String roleName) throws FinderException {
        return rtsRoleDAO.load(roleName);
    }

    public RTSRole getRTSRole(Integer roleId) throws FinderException {
        return rtsRoleDAO.load(roleId);
    }

    public RTSRole getRTSRoleByAlias(String roleAlias) throws FinderException {
        return rtsRoleDAO.loadByAlias(roleAlias);
    }

    public Person getPerson(Integer personId) {
        return personDAO.load(personId);
    }

    public AuthInfo getCurrentPersonAuthInfo(Person person, String login){
        if(login==null) return getAuthInfo(person);
        AuthInfo authInfo = null;
        Collection operators = person.getOperators();
        if(operators == null || operators.size() == 0) return authInfo;
        Operator operator;
        for(Iterator it=operators.iterator();it.hasNext();) {
            operator = (Operator)it.next();
            if(operator.getLogin().trim().equalsIgnoreCase(login.trim())) {
                authInfo = getAuthInfo(operator);
            }
        }
        return authInfo;
    }

    public void refreshRoles(AuthInfo authInfo) throws AuthenticationException {
		if(authInfo == null) throw new IllegalArgumentException("AuthInfo can not be null");
		if(authInfo.getLogin() == null) throw new AuthenticationException("User is not authentificated");
		if(authInfo.getOperatorId() == null) throw new AuthenticationException("User is not authentificated");
		if(authInfo.getPersonId() == null) throw new AuthenticationException("User is not authentificated");
		Operator operator = getOperator(authInfo.getOperatorId());
		//add user roles
		Collection roles = operator.getRoleList();
		for (Iterator i = roles.iterator(); i.hasNext();) {
			Role role = (Role)i.next();
			String roleId = role.getName();
			authInfo.addRole((roleId != null)?roleId.trim():null);
		}
		Person person = getPerson(authInfo.getPersonId());
		try {
			Collection list2 = rtsRoleDAO.findRoleByPerson(person);
			for (Iterator itr = list2.iterator(); itr.hasNext();) {
				RTSRole personRole = (RTSRole) itr.next();
			}
		} catch(FinderException e) {
			e.printStackTrace();
		}
		//по группам
		try {
			Collection list2 = rtsGroupDAO.findGroupByPerson(person);
			for (Iterator itr = list2.iterator(); itr.hasNext();) {
				RTSGroup group = (RTSGroup) itr.next();
			}
		} catch(FinderException e) {
			e.printStackTrace();
		}
    }
    
	public Collection getGroupList(Person person) {
		try {
			return rtsGroupDAO.findGroupByPerson(person);
		} catch(FinderException e) { 
			return null;
		}
	}

}
