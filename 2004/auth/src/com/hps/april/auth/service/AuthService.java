/*
 *  $Id: AuthService.java,v 1.9 2007/05/21 16:58:53 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.april.auth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.JulyRole;
import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.PermitAction;
import com.hps.april.auth.object.PermitObjectId;
import com.hps.april.auth.object.Person;
import com.hps.april.auth.object.Role;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.9 $ $Date: 2007/05/21 16:58:53 $
 * 16.02.2006
 */
public interface AuthService {
	
	String SERVICE_NAME = "april.auth.authService";

	/**
	 * ��������� ������������������ ���������� ��� 
	 * �������� ������������ �� request
	 * @param request
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(HttpServletRequest request);

	/**
     * �������� ������������������ ���������� ���
     * �������� ������������ �� request
	 * @param request
	 */
	public void invalidateAuthInfo(HttpServletRequest request);

	/**
	 * ��������� ������������������ ���������� � ������������ 
	 * �� �������������� ��������� operatorId
	 * @param operatorId
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(Integer operatorId);

	/**
	 * ��������� ������������������ ���������� � ������������ 
	 * �� ������ ���������
	 * @param operatorLogin
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(String operatorLogin);
	
	/**
 	 * ��������� ������������������ ���������� � ������������ 
	 * �� ������� ��������
	 * @param operator
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(Operator operator);

	/**
	 * ���������, ���� �� � ������������ authInfo ���� role 
	 * @param authInfo
	 * @param role
	 * @return boolean
	 */
	boolean isUserInRole(AuthInfo authInfo, Role role);
	
	/**
	 * ���������, ���� �� � ������������ authInfo ����, 
	 * ������������ roleName
	 * @param authInfo
	 * @param roleName
	 * @return
	 */
	boolean isUserInRole(AuthInfo authInfo, String roleName);
	
	/**
	 * ��������� ���� �� roleName
	 * @param roleName
	 * @return Role
	 */
	Role getRole(String roleName);
	
	/**
	 * ��������� ��������� �� ��� id. ���� �������� �� ������ ������������ null
	 * @param operatorId
	 * @return Operator
	 */
	Operator getOperator(Integer operatorId);
	
	/**
	 * ��������� ��������� �� ������. ���� �������� �� ������ ���������� null
	 * @param operatorLogin
	 * @return Operator
	 */
	Operator getOperator(String operatorLogin);

	/**
	 * ��������� ��������� �� authInfo. ���� �� ������ ������������ null
	 * @param authInfo
	 * @return Operator
	 * @throws IllegalArgumentException, ���� authInfo == null
	 */
	Operator getOperator(AuthInfo authInfo);

	/**
	 * ���������� ������������������ ���������� ���������� ������������. 
	 * @return AuthInfo
	 */
	AuthInfo getSystemAuthInfo();
	
	/**
	 * ���������, ���� �� � ������������ ����� �� �������� action
	 * @param action
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action);
	
	/**
	 * ���������, ���� �� � ������������ ����� �� ��������, 
	 * ������������ ������ actionKey
	 * @param actionKey
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, String actionKey);
		
	/**
	 * ��������� �������� ��������, ������������ ������ actionKey
	 * @param permissionKey
	 * @return Permission
	 */
	PermitAction getPermitAction(String actionKey);
	
	/**
	 * ���������, ���� �� � ������������ authInfo ����� �� �������� action � ��������,
	 * ������������ ��������������� objectId
	 * @param authInfo
	 * @param action
	 * @param objectId
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action, PermitObjectId objectId);
	
	/**
	 * ���������, ���� �� � ������������ authInfo ����� �� �������� action � ��������,
	 * ������������ ��������������� objectId
	 * @param authInfo
	 * @param actionKey
	 * @param objectIdKey
	 * @return
	 */
	boolean checkPermission(AuthInfo authInfo, String actionKey, String objectIdKey);
	
	/**
	 * ��������� �������� �������������� ������� �� �����
	 * @param permitObjectKey
	 * @return PermitObjectId
	 */
	PermitObjectId getPermitObjectId(String permitObjectKey);

	/**
	 * ��������� Person �� Id
	 * @param personId
	 * @return Person
	 */
	Person getPerson(Integer personId);
	
	/**
	 * ��������� ������ ����, �� ����������� � ��������, ��� ������� ������������ 
	 * @param authInfo
	 * @return List of Permission
	 */
	List getPermission(AuthInfo authInfo);
	
	/**
	 * ��������� ������ ����, ����������� � ��������, ��� ������� ������������
	 * @param authInfo
	 * @param permitObjectId
	 * @return List of Permission
	 */
	List getPermission(AuthInfo authInfo, PermitObjectId permitObjectId);
	
	JulyRole getJulyRole(String roleName);
	
	
	
	
	
//	/**
//	 * ��������� ���������� � ����� ������� ������������
//	 * @param authInfo
//	 * @throws AuthenticationException
//	 */
//	public void refreshRoles(AuthInfo authInfo) throws AuthenticationException;
//	
//	/**
//	 * ���������� ��������������/������� ������� ��������������
//	 * ������������ ��� �������� � ������ ��������
//	 * ������� ������ ������������� ���������� @see com.hps.april.auth.service.AuthService  
//	 * @return
//	 */
//	public Collection getExternalAuthServices();
//	
//	/**
//	 * ��������� �������� ��������������/������� ������ �������������� 
//	 * @param service
//	 */
//	public void addExternalAuthService(AuthService service);
	
	List getPersonBySername(String sername);
	
}
