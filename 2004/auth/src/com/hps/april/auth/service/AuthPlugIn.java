/*
 *  $Id: AuthPlugIn.java,v 1.3 2006/12/13 16:10:51 dkrivenko Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.service;

import java.util.Collection;

import com.hps.april.auth.object.Operator;
import com.hps.april.auth.object.Role;

/**
 * Модуль подключения к сервису АuthService. 
 * Расширяет работу с ролями
 * @author dimitry
 * 29.11.2006
 */
public interface AuthPlugIn {

	/**
	 * Получение коллекции ролей для заданного оператора
	 * @param operator
	 * @return Collection of Role
	 */
	Collection getRoles(Operator operator);
	
	/**
	 * Получение роли по имени
	 * @param roleName
	 * @return Role
	 */
	Role getRole(String roleName);

}
