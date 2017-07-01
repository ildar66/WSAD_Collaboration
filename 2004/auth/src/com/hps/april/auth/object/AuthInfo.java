/*
 *  $Id: AuthInfo.java,v 1.7 2007/01/10 18:00:10 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.object;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.7 $ $Date: 2007/01/10 18:00:10 $
 */
public interface AuthInfo extends Principal, Serializable {

    Integer getOperatorId();

    Integer getPersonId();

    String getOperatorLogin();

    String getLogin();

    public String getPersonName();

    void addRole(Role role);
	void addRole(String role);

    void addRoles(Collection roles);

    boolean isUserInRole(Role role);
    
	boolean isUserInRole(String role);
//    void addPermission(Permission permission);
//    
//    void addPermissions(Collection permissions);
//    
//    boolean checkPermission(Permission permission);
    

}
