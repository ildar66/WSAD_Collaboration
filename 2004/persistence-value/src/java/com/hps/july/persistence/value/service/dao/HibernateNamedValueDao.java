/*
 *  $Id: HibernateNamedValueDao.java,v 1.1 2006/11/16 16:36:12 vglushkov Exp $
 *  Copyright (c) 2003-2006 ОАО Вымпелком
 */
package com.hps.july.persistence.value.service.dao;

import com.hps.july.persistence.value.object.NamedValue;
import com.hps.april.common.NoSuchObjectException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $
 *          15.09.2006 18:55:52
 */
public class HibernateNamedValueDao extends HibernateDaoSupport implements NamedValueDao {

    public NamedValue findById(String aName) throws NoSuchObjectException {
        try {
            String sql = "FROM NamedValue WHERE id = ?";
            List list = getHibernateTemplate().find(sql, new Object [] { aName });
            if(!list.isEmpty()) return (NamedValue)list.get(0);
            throw new NoSuchObjectException("Cannot find 'NamedValue' with id ["+aName+"]"); 
        } catch(RuntimeException e) {
            throw new NoSuchObjectException("Cannot find 'NamedValue' with id ["+aName+"], cause " + e.getMessage());
        }
    }
}
