package com.hps.july.rts.service.impl.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.april.common.utils.StringUtils;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.impl.dao.ConsolidatedRequestDAO;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 15.02.2006
 * Time: 19:03:57
 */
public class ConsolidatedRequestDAOHibernate extends HibernateDaoSupport  implements ConsolidatedRequestDAO {

    protected Logger logger = Logger.getLogger(this.getClass());

    RTSOperatorService operatorService;

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }


    public Collection findAllConsolidatedRequests(Person keyManager) throws SystemException {
        return null;
    }

    public ConsolidatedRequest findConsolidatedRequest(Integer id, AuthInfo currentAuthInfo) throws SystemException {
        String sql = "FROM ConsolidatedRequest WHERE requestId = ?";
        List list = getHibernateTemplate().find(sql, new Object [] { id });
        return (!list.isEmpty())?(ConsolidatedRequest)list.get(0):null;
    }

    public Collection findConsolidatedRequestsBySQL(String sqlQuery, Object [] params, Type [] types) throws SystemException {
        //TODO: type ?!?!?
        return getHibernateTemplate().find(sqlQuery, params);
    }

    public void saveConsolidatedRequest(ConsolidatedRequest request) throws SystemException {
        getHibernateTemplate().save(request);
//        getSession().flush();
    }

    public void updateConsolidatedRequest(final ConsolidatedRequest request) throws SystemException {
        logger.debug("UPDATE: " + request);
        getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                // TODO Временное решение - очистка кеша для этого объекта,
                // чтобы проверка версии отработала правильно в случае
                // сохранения объекта после его загрузки в той же сессии.
                session.evict(request);

                session.update(request);
                return null;
            }});



        getHibernateTemplate().update(request);
//        getSession().flush();
    }

    public void removeConsolidatedRequest(ConsolidatedRequest request) throws SystemException {
        getHibernateTemplate().delete(request);
        getSession().flush();
    }

    public Collection findConsolidatedRequests(Boolean isSerching,
                                               Boolean isNumber, String number,
                                               Boolean isDateType, String dateTypeSelect,
                                               Date dateType, Boolean isRtsRequestType, String rtsRequestType,
                                               Boolean isRtsStatus, String rtsStatus,
                                               Boolean isChannelSetting, String channelSetting,
                                               Boolean isChannelType, String channelType,
                                               String priority,
                                               Boolean isInitiator, String initiator,
                                               Boolean isRegMan, Integer regMan,
                                               Boolean isExecutor, Integer executor,
                                               Boolean isSearchAddrA, String searchAddrA,
                                               Boolean isSearchAddrB, String searchAddrB,
                                               AuthInfo currentAuthInfo, Boolean isAdmin) {

        String query = "FROM ConsolidatedRequest WHERE 1=1 ";
        List params = new ArrayList();

        //if admin !!! Это условие не нужно!!! КМ-ы должны видеть все!!! конс. заявки
/*        if(!(isAdmin.booleanValue())) {
            if(currentAuthInfo != null) {
                query += " AND keyManager.id = ? ";
                params.add(currentAuthInfo.getPersonId());
            }
        } */

        if( isNumber.booleanValue() ) {
          query += " AND UPPER(number) LIKE ? ";
          params.add(StringUtils.prepareFindString(number));
        }

        if( isDateType.booleanValue() ) {
          if(dateTypeSelect.equalsIgnoreCase("s")) {
              query += " AND created = ? ";
          } else if(dateTypeSelect.equalsIgnoreCase("e")) {
              query += " AND inWorkDate = ? ";
          }
          params.add(dateType);
        }

        if(isRtsRequestType.booleanValue()) {
          query += " AND rtsRequestTypeId = ? ";
          params.add(new Integer(rtsRequestType));
        }

        if( isRtsStatus.booleanValue() ) {
          query += " AND statusId = ? ";
          params.add(new Integer(rtsStatus));
        }

        if( isChannelSetting.booleanValue() ) {
          query += " AND channelSetting.id = ? ";
          params.add(new Integer(channelSetting));
        }

        if( isChannelType.booleanValue() ) {
            query += " AND channelType.id = ? ";
            params.add(new Integer(channelType));
        }

        getLogger().info(isSearchAddrA + ":" + searchAddrA);
        getLogger().info(isSearchAddrB + ":" + searchAddrB);

        if (isSearchAddrA.booleanValue() && isSearchAddrB.booleanValue()){
            query += " AND ( " +
                    "(UPPER(equipmentA.position.address) like ? AND UPPER(equipmentB.position.address) like ?) OR " +
                    "(UPPER(equipmentA.position.address) like ? AND UPPER(equipmentB.position.address) like ?)" +
                ") ";

            String positionAddressA = StringUtils.prepareFindString(searchAddrA);
            String positionAddressB = StringUtils.prepareFindString(searchAddrB);

            params.add(positionAddressA);
            params.add(positionAddressB);
            params.add(positionAddressB);
            params.add(positionAddressA);

        } else

        if (isSearchAddrA.booleanValue()){
            query += " AND UPPER(equipmentA.position.address) like ? ";
            params.add(StringUtils.prepareFindString(searchAddrA));
        } else

        if (isSearchAddrB.booleanValue()){
            query += " AND UPPER(equipmentB.position.address) like ? ";
            params.add(StringUtils.prepareFindString(searchAddrB));
        }

        query += " ORDER BY created DESC ";

        getLogger().info("Query: " + query + " --- " + params);
        return getHibernateTemplate().find(query, params.toArray());
    }


    public ConsolidatedRequest findConsolidatedRequest(Integer consolidatedRequestId) {
        List result = getHibernateTemplate().find("FROM ConsolidatedRequest WHERE requestId = ?",
                new Object[] { consolidatedRequestId });

        if (!result.isEmpty()) return (ConsolidatedRequest) result.get(0);
        return null;
    }

    private Logger getLogger() {
        return logger;
    }

}
