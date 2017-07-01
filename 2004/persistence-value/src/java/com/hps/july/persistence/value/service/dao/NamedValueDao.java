/*
 *  $Id: NamedValueDao.java,v 1.1 2006/11/16 16:36:12 vglushkov Exp $
 *  Copyright (c) 2003 -2006 ОАО Вымпелком    
 */
package com.hps.july.persistence.value.service.dao;

import com.hps.july.persistence.value.object.NamedValue;
import com.hps.april.common.NoSuchObjectException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 *          15.09.2006 18:59:01
 */
public interface NamedValueDao {

    public NamedValue findById(String aName) throws NoSuchObjectException;
}
