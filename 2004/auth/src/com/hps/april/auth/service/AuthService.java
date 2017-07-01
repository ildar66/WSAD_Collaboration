/*
 *  $Id: AuthService.java,v 1.9 2007/05/21 16:58:53 mmorev Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
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
	 * Получение аутентификационной информации для 
	 * текущего пользователя из request
	 * @param request
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(HttpServletRequest request);

	/**
     * Удаление аутентификационной информации для
     * текущего пользователя из request
	 * @param request
	 */
	public void invalidateAuthInfo(HttpServletRequest request);

	/**
	 * Получение аутентификационной информации о пользователе 
	 * по идентификатору оператора operatorId
	 * @param operatorId
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(Integer operatorId);

	/**
	 * Получение аутентификационной информации о пользователе 
	 * по логину оператора
	 * @param operatorLogin
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(String operatorLogin);
	
	/**
 	 * Получение аутентификационной информации о пользователе 
	 * по объекту оператор
	 * @param operator
	 * @return AuthInfo
	 */
	AuthInfo getAuthInfo(Operator operator);

	/**
	 * Проверяет, есть ли у пользователя authInfo роль role 
	 * @param authInfo
	 * @param role
	 * @return boolean
	 */
	boolean isUserInRole(AuthInfo authInfo, Role role);
	
	/**
	 * Проверяет, есть ли у пользователя authInfo роль, 
	 * определяемая roleName
	 * @param authInfo
	 * @param roleName
	 * @return
	 */
	boolean isUserInRole(AuthInfo authInfo, String roleName);
	
	/**
	 * Получение роли по roleName
	 * @param roleName
	 * @return Role
	 */
	Role getRole(String roleName);
	
	/**
	 * Получение оператора по его id. Если оператор не найден возвращается null
	 * @param operatorId
	 * @return Operator
	 */
	Operator getOperator(Integer operatorId);
	
	/**
	 * Получение оператора по логину. Если оператор не найден возвращает null
	 * @param operatorLogin
	 * @return Operator
	 */
	Operator getOperator(String operatorLogin);

	/**
	 * Получение оператора по authInfo. Если не найден возвращается null
	 * @param authInfo
	 * @return Operator
	 * @throws IllegalArgumentException, если authInfo == null
	 */
	Operator getOperator(AuthInfo authInfo);

	/**
	 * Возвращает аутентификационную информацию системного пользователя. 
	 * @return AuthInfo
	 */
	AuthInfo getSystemAuthInfo();
	
	/**
	 * Проверяет, есть ли у пользователя право на действие action
	 * @param action
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action);
	
	/**
	 * Проверяет, есть ли у пользователя право на действие, 
	 * определенное ключем actionKey
	 * @param actionKey
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, String actionKey);
		
	/**
	 * Получение описания действия, определенное ключем actionKey
	 * @param permissionKey
	 * @return Permission
	 */
	PermitAction getPermitAction(String actionKey);
	
	/**
	 * Проверяет, есть ли у пользователя authInfo право на действие action с объектом,
	 * определяемым идентификатором objectId
	 * @param authInfo
	 * @param action
	 * @param objectId
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action, PermitObjectId objectId);
	
	/**
	 * Проверяет, есть ли у пользователя authInfo право на действие action с объектом,
	 * определяемым идентификатором objectId
	 * @param authInfo
	 * @param actionKey
	 * @param objectIdKey
	 * @return
	 */
	boolean checkPermission(AuthInfo authInfo, String actionKey, String objectIdKey);
	
	/**
	 * Получение описания идентификатора объекта по ключу
	 * @param permitObjectKey
	 * @return PermitObjectId
	 */
	PermitObjectId getPermitObjectId(String permitObjectKey);

	/**
	 * Получение Person по Id
	 * @param personId
	 * @return Person
	 */
	Person getPerson(Integer personId);
	
	/**
	 * Получение списка прав, не привязанных к объектам, для данного пользователя 
	 * @param authInfo
	 * @return List of Permission
	 */
	List getPermission(AuthInfo authInfo);
	
	/**
	 * Получение списка прав, привязанных к объектам, для данного пользователя
	 * @param authInfo
	 * @param permitObjectId
	 * @return List of Permission
	 */
	List getPermission(AuthInfo authInfo, PermitObjectId permitObjectId);
	
	JulyRole getJulyRole(String roleName);
	
	
	
	
	
//	/**
//	 * Обновляет информацию о ролях данного пользователя
//	 * @param authInfo
//	 * @throws AuthenticationException
//	 */
//	public void refreshRoles(AuthInfo authInfo) throws AuthenticationException;
//	
//	/**
//	 * Возвращает дополнительные/внешние сервисы аутентификации
//	 * Используется для проверки в других системах
//	 * Сервисы должны удовлетворять интерфейсу @see com.hps.april.auth.service.AuthService  
//	 * @return
//	 */
//	public Collection getExternalAuthServices();
//	
//	/**
//	 * Позволяет добавить дополнительный/внешний сервис аутентификации 
//	 * @param service
//	 */
//	public void addExternalAuthService(AuthService service);
	
	List getPersonBySername(String sername);
	
}
