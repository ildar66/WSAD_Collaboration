/*
 *  $Id: Role.java,v 1.5 2006/12/04 10:50:59 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.april.auth.object;

import java.io.Serializable;

/**
 * 
 * @author dimitry
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.5 $ $Date: 2006/12/04 10:50:59 $
 * 29.11.2006
 */
public interface Role extends Serializable {

	String getDescription();
	
	//void setDescription(String description);
	
	String getName();
	
	//void setName(String name);

}
