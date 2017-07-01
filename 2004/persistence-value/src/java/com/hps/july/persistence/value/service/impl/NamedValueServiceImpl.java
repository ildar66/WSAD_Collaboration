/*
 *  $Id: NamedValueServiceImpl.java,v 1.1 2006/11/16 16:36:12 vglushkov Exp $
 *  Copyright (c) 2003-2006 ОАО Вымпелком
 */
package com.hps.july.persistence.value.service.impl;

import com.hps.july.persistence.value.service.NamedValueService;
import com.hps.july.persistence.value.service.dao.NamedValueDao;
import com.hps.july.persistence.value.object.NamedValue;

import com.hps.april.common.NoSuchObjectException;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 * Date: 15.09.2006
 * Time: 17:18:23
 */
public class NamedValueServiceImpl implements NamedValueService {

    private NamedValueDao dao;

    public NamedValueServiceImpl() {
        this.dao = null;
    }

    public Integer getNamedValueInt(String aName) throws NoSuchObjectException {
        NamedValue value = findById(aName);
        return value.getIntValue();
    }

    public String getNamedValueString(String aName) throws NoSuchObjectException {
        NamedValue value = findById(aName);
        return value.getStringValue();
    }

    public BigDecimal getNamedValueDecimal(String aName) throws NoSuchObjectException {
        NamedValue value = findById(aName);
        return value.getDecimalValue();
    }

    public Timestamp getNamedValueDate(String aName) throws NoSuchObjectException {
        NamedValue value = findById(aName);
        return value.getDateValue();
    }

    public NamedValue findById(String aName) throws NoSuchObjectException {
        return dao.findById(aName);
    }

    public NamedValueDao getDao() {
        return dao;
    }

    public void setDao(NamedValueDao dao) {
        this.dao = dao;
    }
}
