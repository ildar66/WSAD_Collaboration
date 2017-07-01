package com.hps.april.auth.service;

import java.util.List;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.PermitAction;
import com.hps.april.auth.object.PermitObjectId;

/**
 * Модуль подключения к сервису АuthService. 
 * Расширяет работу с правами пользователя на действия и на объекты  
 * @author dimitry
 * 07.12.2006
 */
public interface AuthPermitPlugIn extends AuthPlugIn {

	/**
	 * Возвращает список поддерживаемых модулем классов PermitAction  
	 * @return Class[]
	 */
	Class[] getPermitActionSupportClasses();

	/**
	 * Возвращает список поддерживаемых модулем классов PermitObjectId 
	 * @return Class[]
	 */
	Class[] getPermitObjectIdSupportClasses();
	
	/**
	 * Получение описания действия по ключу действия
	 * @param actionKey
	 * @return PermitAction
	 */
	PermitAction getPermitAction(String actionKey);
	
	/**
	 * Получение описания идентификатора объекта по ключу
	 * @param permitObjectKey
	 * @return PermitObjectId
	 */
	PermitObjectId getPermitObjectId(String permitObjectKey);
	
	/**
	 * Выполнение проверки: может ли пользователь (authInfo) выполнить действие action
	 * @param authInfo
	 * @param action
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action);

	/**
	 * Проверка, может ли пользователь authInfo выполнить действие action с объектом objectId
	 * @param authInfo
	 * @param action
	 * @param objectId
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action, PermitObjectId objectId);

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



	

}
