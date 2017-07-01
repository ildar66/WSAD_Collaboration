package com.hps.july.rts.auth.service;

import java.util.Collection;
import java.util.List;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.service.AuthService;
import com.hps.april.auth.object.Person;
import com.hps.april.common.FinderException;

import com.hps.july.rts.auth.object.RTSRole;

/**
 * @author Dimitry Krivenko 
 * 14.02.2006
 */
public interface RTSAuthService extends AuthService {

	public String SERVICE_NAME = "july.rts.auth.RTSAuthService";

	/**
	 * 
	 * @param authInfo
	 * @param role
	 * @return
	 */
	public boolean isUserInRtsRole(AuthInfo authInfo, RTSRole role);
	
	//public boolean isUserInRole(AuthInfo authInfo, String role);

	public RTSRole getRTSRole(String roleName) throws FinderException; 
	
	public RTSRole getRTSRole(Integer roleId) throws FinderException;

	public RTSRole getRTSRoleByAlias(String roleAlias) throws FinderException;

	
	public AuthInfo getAuthInfo(Person person);
	public AuthInfo getCurrentPersonAuthInfo(Person person, String login);

	public Person getPerson(AuthInfo authInfo);
	public Person getPerson(Integer personId);
	
	public Collection getGroupList(Person person);
	
}
