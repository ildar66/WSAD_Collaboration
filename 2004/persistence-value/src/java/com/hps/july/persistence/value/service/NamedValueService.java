/*
 *  $Id: NamedValueService.java,v 1.1 2006/11/16 16:36:12 vglushkov Exp $
 *  Copyright (c) 2003-2006 ОАО Вымпелком
 */
package com.hps.july.persistence.value.service;

import com.hps.april.common.NoSuchObjectException;
import com.hps.april.common.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 * Date: 15.09.2006
 * Time: 17:17:17
 */
public interface NamedValueService extends Service {

    public static final String SERVICE_NAME = "july.persistence.value.NamedValueService";

    public Integer getNamedValueInt(String aName) throws NoSuchObjectException;

    public String getNamedValueString(String aName) throws NoSuchObjectException;

    public BigDecimal getNamedValueDecimal(String aName) throws NoSuchObjectException;

    public Timestamp getNamedValueDate(String aName) throws NoSuchObjectException;

}
