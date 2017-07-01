package com.hps.april.auth.service.interceptor;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import com.hps.april.auth.object.AuthInfo;

/**
 * @author dimitry
 * Created on 01.12.2005
 */
public class PermissionInterceptor implements MethodBeforeAdvice {
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	private PermissionAttributeSource permissionAttributeSource;
	private PermissionManager permissionManager;
	
	public void before(Method method, Object[] args, Object target)
		throws Throwable {

		AuthInfo authInfo = foundAuthInfo(args);
		if (authInfo != null){
			PermissionAttribute permissionAttribute = 
				permissionAttributeSource.getPermissionAttribute(method, target.getClass());
			
			if (permissionAttribute != null){
				if (permissionManager.checkPermission(authInfo, permissionAttribute)){
					logger.debug("Allow access to method " + target.getClass() + "#" + method.getName() + "() to user " + authInfo.getLogin());
					return;
				}
		
				throw new PermissionDeniedException("Permission denied for user " + authInfo.getLogin() + " on method " + method.getName());
			} else {
				logger.debug("SecurityAttributes definition not found for class " + target.getClass() + "#" + target.getClass() + ". Permission check disabled.");
			}
		} else {
			logger.debug("AuthInfo not found in " + target.getClass() + "#" + method.getName() + " method's arguments. Permission check disabled.");	
		}
	}

	protected AuthInfo foundAuthInfo(Object[] args) {
		if (args != null) 
			for (int i=0; i< args.length; i++){
				if (args[i] instanceof AuthInfo) return (AuthInfo) args[i];
			}

		return null;
	}

	public PermissionAttributeSource getPermissionAttributeSource() {
		return permissionAttributeSource;
	}
	public void setPermissionAttributeSource(
			PermissionAttributeSource permissionAttributeSource) {
		this.permissionAttributeSource = permissionAttributeSource;
	}
	public PermissionManager getPermissionManager() {
		return permissionManager;
	}
	public void setPermissionManager(PermissionManager permissionManager) {
		this.permissionManager = permissionManager;
	}
	
}
