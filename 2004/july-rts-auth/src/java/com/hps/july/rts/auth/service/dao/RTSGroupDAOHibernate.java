/*
 *  $Id: RTSGroupDAOHibernate.java,v 1.2 2006/11/17 09:48:00 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.auth.service.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;

import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;

import com.hps.july.rts.auth.object.RTSGroup;

import com.hps.april.common.FinderException;

import com.hps.april.auth.object.Person;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2006/11/17 09:48:00 $
 */

public class RTSGroupDAOHibernate extends HibernateDaoSupport implements RTSGroupDAO {

    public Collection findGroupByPerson(final Person person) throws FinderException {
        return (Collection)getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT {rtsGroup.*} FROM rts_groups rtsGroup, rts_groups2people r2g WHERE r2g.groupid = rtsGroup.groupid AND r2g.man = :personId ";
                SQLQuery sqlQuery = session.createSQLQuery(sql);
                sqlQuery.addEntity("rtsGroup", RTSGroup.class);
                sqlQuery.setInteger("personId", person.getId().intValue());
                return sqlQuery.list();
            }
        });
    }

}
