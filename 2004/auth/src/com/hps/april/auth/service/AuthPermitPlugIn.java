package com.hps.april.auth.service;

import java.util.List;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.PermitAction;
import com.hps.april.auth.object.PermitObjectId;

/**
 * ������ ����������� � ������� �uthService. 
 * ��������� ������ � ������� ������������ �� �������� � �� �������  
 * @author dimitry
 * 07.12.2006
 */
public interface AuthPermitPlugIn extends AuthPlugIn {

	/**
	 * ���������� ������ �������������� ������� ������� PermitAction  
	 * @return Class[]
	 */
	Class[] getPermitActionSupportClasses();

	/**
	 * ���������� ������ �������������� ������� ������� PermitObjectId 
	 * @return Class[]
	 */
	Class[] getPermitObjectIdSupportClasses();
	
	/**
	 * ��������� �������� �������� �� ����� ��������
	 * @param actionKey
	 * @return PermitAction
	 */
	PermitAction getPermitAction(String actionKey);
	
	/**
	 * ��������� �������� �������������� ������� �� �����
	 * @param permitObjectKey
	 * @return PermitObjectId
	 */
	PermitObjectId getPermitObjectId(String permitObjectKey);
	
	/**
	 * ���������� ��������: ����� �� ������������ (authInfo) ��������� �������� action
	 * @param authInfo
	 * @param action
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action);

	/**
	 * ��������, ����� �� ������������ authInfo ��������� �������� action � �������� objectId
	 * @param authInfo
	 * @param action
	 * @param objectId
	 * @return boolean
	 */
	boolean checkPermission(AuthInfo authInfo, PermitAction action, PermitObjectId objectId);

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



	

}
