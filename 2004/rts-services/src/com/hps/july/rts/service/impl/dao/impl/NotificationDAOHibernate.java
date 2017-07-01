package com.hps.july.rts.service.impl.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.hps.july.rts.service.impl.dao.NotificationDAO;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.object.notificationlist.NotificationItem;
import com.hps.april.auth.object.Person;

import java.util.Collection;
import java.util.List;
import java.sql.SQLException;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 02.03.2006
 * Time: 16:23:11
 */
public class NotificationDAOHibernate extends HibernateDaoSupport
    implements NotificationDAO {

    private RTSOperatorService operatorService;

    public NotificationDAOHibernate() {
    }

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public Collection findNotificationList(final String requestNumber, final Person currentPerson) {
        return (List) getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String sql = " FROM NotificationItem WHERE requestId = ? AND ownerId = ? ";
                Query query = session.createQuery(sql);
                query.setString(0, requestNumber);
                query.setInteger(1, currentPerson.getId().intValue());

                return query.list();
            }});
/*
        String sql = " FROM NotificationItem WHERE requestId = ? AND ownerId = ? ";
        System.out.println("["+requestNumber+"] ["+currentPerson.getId()+"] ");
        List list = getHibernateTemplate().find(sql, new Object [] { requestNumber, currentPerson.getId() });
        System.out.println("-->["+list.size()+"]");
        return list;
*/
    }

    public void saveNotificationItem(NotificationItem item) {
        Integer serial = (Integer)getHibernateTemplate().save(item);
        item.setId(serial);
        getHibernateTemplate().flush();
        getHibernateTemplate().refresh(item);
    }

    public void removeNotificationItem(NotificationItem item) {
        getHibernateTemplate().delete(item);
        getHibernateTemplate().flush();
    }

    public void removeNotificationList(Collection items) {
        getHibernateTemplate().deleteAll(items);
        getHibernateTemplate().flush();
    }
}
