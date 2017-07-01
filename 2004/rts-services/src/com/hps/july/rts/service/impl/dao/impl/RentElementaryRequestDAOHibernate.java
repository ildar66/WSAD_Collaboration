package com.hps.july.rts.service.impl.dao.impl;

import com.hps.july.rts.service.impl.dao.RentElementaryRequestDAO;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.object.request.RentElementaryRequest;
import com.hps.july.rts.SystemException;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.type.Type;

/**
 * DAO
 *
 * Ёлементарна€ ѕќƒза€вка (по аренде)
 * »сполнитель может разбивать Ёл. за€вку на подза€вки
 * Ќа один арендуемый канал может оформл€тьс€ более одного
 * доп. соглашени€
 *
 *
 * @author ABaykov
 * Created on 04.09.2006
 */
public class RentElementaryRequestDAOHibernate extends HibernateDaoSupport
        implements RentElementaryRequestDAO {

    RTSOperatorService operatorService;

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public RentElementaryRequest findRentElementaryRequest(Integer id) throws SystemException {
        String sql = "FROM RentElementaryRequest WHERE requestId = ?";
        List list = getHibernateTemplate().find(sql, new Object [] { id });
        return (!list.isEmpty())?(RentElementaryRequest)list.get(0):null;
    }

    public void saveRentElementaryRequest(RentElementaryRequest request) throws SystemException {
        getHibernateTemplate().save(request);
        getSession().flush();
    }

    public void updateRentElementaryRequest(final RentElementaryRequest request) throws SystemException {
		logger.debug("UPDATE: " + request);
        getHibernateTemplate().saveOrUpdate(request);
        getSession().flush();
    }

    public void removeRentElementaryRequest(RentElementaryRequest request) throws SystemException {
        getHibernateTemplate().delete(request);
        getSession().flush();
    }

    public Collection findRentElemenaryRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException {
        //TODO: type ?!?!?
        return getHibernateTemplate().find(sqlQuery, params);
    }
}
