package com.hps.april.auth.service.interceptor;

import java.lang.reflect.Method;

public interface PermissionAttributeSource {

	/**
	 * @param method
	 * @param targetClass
	 * @return
	 */
	PermissionAttribute getPermissionAttribute(Method method, Class targetClass);
	
}
