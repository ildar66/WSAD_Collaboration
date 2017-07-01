package com.hps.july.rts.service.impl.dao.impl;

import com.hps.july.rts.service.impl.dao.ElementaryRequestDAO;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.SystemException;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.type.Type;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 27.02.2006
 * Time: 10:23:14
 */
public class ElementaryRequestDAOHibernate extends HibernateDaoSupport
        implements ElementaryRequestDAO {

    RTSOperatorService operatorService;

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public ElementaryRequest findElementaryRequest(Integer id) throws SystemException {
        String sql = "FROM ElementaryRequest WHERE requestId = ?";
        List list = getHibernateTemplate().find(sql, new Object [] { id });
        return (!list.isEmpty())?(ElementaryRequest)list.get(0):null;
    }

    public void saveElementaryRequest(ElementaryRequest request) throws SystemException {
        getHibernateTemplate().save(request);
        getSession().flush();
    }

    public void updateElementaryRequest(final ElementaryRequest request) throws SystemException {
		logger.debug("UPDATE: " + request);
        getHibernateTemplate().saveOrUpdate(request);
        getSession().flush();
    }

    public void removeElementaryRequest(ElementaryRequest request) throws SystemException {
        getHibernateTemplate().delete(request);
        getSession().flush();
    }

    public Collection findElemenaryRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException {
        //TODO: type ?!?!?
        return getHibernateTemplate().find(sqlQuery, params);
    }
}
