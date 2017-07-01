package com.hps.april.auth.service.interceptor;

import com.hps.april.auth.object.AuthInfo;

public interface PermissionManager {

	boolean checkPermission(AuthInfo authInfo, PermissionAttribute permissionAttribute);
	
}
