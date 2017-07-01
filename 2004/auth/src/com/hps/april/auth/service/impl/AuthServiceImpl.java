/*
 *  $Id: AuthServiceImpl.java,v 1.16 2007/05/21 16:58:53 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.hps.april.auth.exception.AuthenticationException;
import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.AuthInfoImpl;
import com.hps.april.auth.object.JulyRole;
import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.PermitAction;
import com.hps.april.auth.object.PermitObjectId;
import com.hps.april.auth.object.Person;
import com.hps.april.auth.object.Role;
import com.hps.april.auth.service.AuthPermitPlugIn;
import com.hps.april.auth.service.AuthPlugIn;
import com.hps.april.auth.service.AuthService;
import com.hps.april.auth.service.dao.OperatorDAO;
import com.hps.april.auth.service.dao.PersonDao;
import com.hps.april.auth.service.dao.RoleDAO;
import com.hps.april.auth.service.plugin.JulyAuthPlugIn;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.16 $ $Date: 2007/05/21 16:58:53 $
 */
public class AuthServiceImpl implements AuthService, InitializingBean {

	private static Integer SYSTEM_OPERATOR_ID = new Integer(100000);
//	private static Role ADMINISTRATOR_ROLE = new GlobalRole("administrator");
	
	private static final String USER_LOGIN_PARAMETER = "user";
	private static final String AUTH_INFO_SESSION_KEY = "com.hps.auth.AuthInfo_key#09758366";
	
	protected Log logger = LogFactory.getLog(getClass());
	
	protected RoleDAO roleDAO;
	protected OperatorDAO operatorDAO;
	protected PersonDao personDao;
	
	public void afterPropertiesSet() throws Exception {
		// set default check to Nri July roles
		pluginList.add(new JulyAuthPlugIn(roleDAO));
	}
	
	private List pluginList = new ArrayList();

	private boolean useRequestUserParameter = true;
		
	public List getPluginList() {
		return pluginList;
	}
	public void setPluginList(List pluginList) {
		this.pluginList = pluginList;
	}
	public boolean isUseRequestUserParameter() {
		return useRequestUserParameter;
	}
	public void setUseRequestUserParameter(boolean useRequestUserParameter) {
		this.useRequestUserParameter = useRequestUserParameter;
	}
	public OperatorDAO getOperatorDAO() {
		return operatorDAO;
	}
	public void setOperatorDAO(OperatorDAO operatorDAO) {
		this.operatorDAO = operatorDAO;
	}
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public AuthInfo getAuthInfo(HttpServletRequest request) {
		AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute(AUTH_INFO_SESSION_KEY);
		if (authInfo == null){
			// search remote user
			String operatorLogin = request.getRemoteUser();
			// search request parameter
			if (operatorLogin == null) operatorLogin = request.getParameter(USER_LOGIN_PARAMETER);

			authInfo = getAuthInfo(operatorLogin);
		
			if (authInfo == null)
				throw new AuthenticationException("User :" + operatorLogin + " not found");

			request.getSession().setAttribute(AUTH_INFO_SESSION_KEY, authInfo);
		} 
		return authInfo;
	}

    public void invalidateAuthInfo(HttpServletRequest request) {
        request.getSession().removeAttribute(AUTH_INFO_SESSION_KEY);
    }

    public AuthInfo getAuthInfo(Integer operatorId) {
		Operator operator = getOperator(operatorId);
		if (operator == null)
			throw new AuthenticationException("Operator: (id=" + operatorId + ") not found");

		return getAuthInfo(operator);
	}
	
	public AuthInfo getAuthInfo(String operatorLogin) {
		Operator operator = getOperator(operatorLogin);
		if (operator == null)
			throw new AuthenticationException("Operator: (login=" + operatorLogin + ") not found");

		return getAuthInfo(operator);
	}
	
	public AuthInfo getAuthInfo(Operator operator) {
		if (operator == null)
			throw new AuthenticationException("Operator: (null) not found ");
		
		// create and set authInfo properties
		AuthInfoImpl authInfo = new AuthInfoImpl(operator.getLogin());
		authInfo.setOperatorId(operator.getId());
		authInfo.setPersonId(operator.getPerson().getId());
		authInfo.setPersonName(operator.getPerson().getName());

		//add user roles
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPlugIn plugIn = getAuthPlugIn(pluginList.get(i));			
				if (plugIn != null) {
					Collection plugInRoles = plugIn.getRoles(operator);
					if (plugInRoles != null)
						authInfo.addRoles(plugInRoles);
				}
			}
		}
		
        return authInfo;
	}
	
	public boolean isUserInRole(AuthInfo authInfo, Role role) {
		if (authInfo == null) 
			throw new IllegalArgumentException("AuthInfo can not be null");
		
		if (role == null){
			logger.warn("Role not found: null");
			return false;
		}
		
		//проверяем роли
		return authInfo.isUserInRole(role);

		//алгоритм постоянно обращается к базе
//		Operator operator = getOperator(authInfo);
		
//		if (operator == null){
//			logger.warn("Operator not found: null");
//			return false;
//		}
		
//		Collection roleList = operator.getRoleList();
//		for (Iterator itr = roleList.iterator(); itr.hasNext();) {
//			Role operatorRole = (Role) itr.next();
			
//			if (operatorRole.equals(ADMINISTRATOR_ROLE)) return true;
// 			if (operatorRole.equals(role)) return true;
//		}
	
		// TODO find in groups?
//		return false;
	}
	
	public boolean isUserInRole(AuthInfo authInfo, String roleName) {
        if (authInfo == null)
            throw new IllegalArgumentException("AuthInfo can not be null");
        if (roleName == null){
            logger.warn("Role not found: null");
            return false;
        }
		
        Role role = getRole(roleName);
		return authInfo.isUserInRole(role);
	}
	
	public Role getRole(String roleName) {

		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPlugIn plugIn = getAuthPlugIn(pluginList.get(i));			
				if (plugIn != null){
					Role plugInRole = plugIn.getRole(roleName);
					if (plugInRole != null)
						return plugInRole;
				}
			}
		}
		return null;
	}

	public Operator getOperator(Integer operatorId) {
		return operatorDAO.load(operatorId);
	}
	
	public Operator getOperator(String operatorLogin) {
		if (operatorLogin == null) return null;
		return operatorDAO.load(operatorLogin);
	}
	
	public Operator getOperator(AuthInfo authInfo) {
		if (authInfo == null)
			throw new IllegalArgumentException("AuthInfo can not be null");
		
		Operator operator = getOperator(authInfo.getOperatorId());
		return operator;
	}

	public AuthInfo getSystemAuthInfo() {
		return getAuthInfo(SYSTEM_OPERATOR_ID);
	}
	
	protected boolean instanceOf(Object object, Class[] classes){
		if (object == null || classes == null) return false;
		
		for (int i=0; i<classes.length; i++){
			if (classes[i].equals(object.getClass()))
					return true;
		}
		
		return false;
	}

	protected AuthPermitPlugIn getAuthPermitPlugIn(Object object){
		if (object instanceof AuthPermitPlugIn)
			return (AuthPermitPlugIn)object;
		
		return null;
	}
	
	protected AuthPlugIn getAuthPlugIn(Object object){
		if (object instanceof AuthPlugIn)
			return (AuthPlugIn)object;
		
		return null;
	}
	
	
	public boolean checkPermission(AuthInfo authInfo, PermitAction action) {
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				if (plugIn != null && instanceOf(action, plugIn.getPermitActionSupportClasses())){
					if (plugIn.checkPermission(authInfo, action))
						return true;					
				} else {
					logger.warn("Check permission in PlugIn " + (plugIn != null ? plugIn.getClass().getName() : "null") + " failed: " + (action != null ? action.getClass().getName() : "null") + " not supported class for this PlugIn" );
				}
			}
		}

		return false;
	}
	
	public boolean checkPermission(AuthInfo authInfo, String actionKey) {
		PermitAction action = getPermitAction(actionKey);
		return checkPermission(authInfo, action);
	}
	
	public PermitAction getPermitAction(String actionKey) {
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				if (plugIn != null){
					PermitAction action = plugIn.getPermitAction(actionKey);
					if (action != null)
						return action;
				}
			}
		}

		return null;
	}
	
	public boolean checkPermission(AuthInfo authInfo, PermitAction action, PermitObjectId objectId) {
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				
				if (plugIn != null && instanceOf(action, plugIn.getPermitActionSupportClasses()) &&
						instanceOf(objectId, plugIn.getPermitObjectIdSupportClasses())){
					if (plugIn.checkPermission(authInfo, action, objectId))
						return true;					
				} else {
					logger.warn("Check permission in PlugIn " + (plugIn != null ? plugIn.getClass().getName() : "null") + " failed: " + (action != null ? action.getClass().getName() : "null") + " or " + (objectId != null ? objectId.getClass().getName() : "null") + " not supported class for this PlugIn" );
				}
			}
		}

		return false;
	}

	public boolean checkPermission(AuthInfo authInfo, String actionKey, String objectIdKey) {
		PermitAction action = getPermitAction(actionKey);
		PermitObjectId objectId = getPermitObjectId(objectIdKey);
		return checkPermission(authInfo, action, objectId);
	}

	
	public PermitObjectId getPermitObjectId(String permitObjectKey) {
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				if (plugIn != null){
					PermitObjectId objectId = plugIn.getPermitObjectId(permitObjectKey);
					if (objectId != null)
						return objectId;
				}
			}
		}

		return null;
	}
	
	
	public Person getPerson(Integer personId) {
		return operatorDAO.loadPerson(personId);
	}
	
	public List getPermission(AuthInfo authInfo) {
		List result = new ArrayList();
		
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				if (plugIn != null){
					List permissionList = plugIn.getPermission(authInfo);
					result.addAll(permissionList);
				}
			}
		}

		return result;
	}
	
	public List getPermission(AuthInfo authInfo, PermitObjectId permitObjectId) {
		List result = new ArrayList();
		
		if (!pluginList.isEmpty()){
			for (int i=0; i<pluginList.size(); i++){
				AuthPermitPlugIn plugIn = getAuthPermitPlugIn(pluginList.get(i));
				if (plugIn != null){
					List permissionList = plugIn.getPermission(authInfo, permitObjectId);
					result.addAll(permissionList);
				}
			}
		}

		return result;
	}
	public JulyRole getJulyRole(String roleName) {
		return roleDAO.load(roleName);
	}
	public List getPersonBySername(String sername) {
		return personDao.getPersonBySername(sername);
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	
//	public void refreshRoles(AuthInfo authInfo) throws AuthenticationException {
//		if(authInfo == null) throw new IllegalArgumentException("AuthInfo can not be null");
//		if(authInfo.getLogin() == null) throw new AuthenticationException("User is not authentificated");
//		if(authInfo.getOperatorId() == null) throw new AuthenticationException("User is not authentificated");
//		if(authInfo.getPersonId() == null) throw new AuthenticationException("User is not authentificated");
//		Operator operator = getOperator(authInfo.getOperatorId());
//		//add user roles
//		Collection roles = operator.getRoleList();
//		for (Iterator i = roles.iterator(); i.hasNext();) {
//			Role role = (Role)i.next();
//			String roleId = role.getName();
//			authInfo.addRole((roleId != null)?roleId.trim():null);
//		}
//	}
	
	
}
