package com.hps.july.rts.task.service.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.SystemException;

import java.util.List;
import java.util.Collections;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.06.2006
 * Time: 13:14:14
 */
public class RTSTaskDAOHibernate extends HibernateDaoSupport implements RTSTaskDAO {

    public void saveAllRTSTask(List tasks) throws SystemException {
        try {
            getHibernateTemplate().saveOrUpdateAll(tasks);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new SystemException("Cannot save tasks collection ["+tasks+"], cause: " + e.toString());
        }
    }

    public void saveRTSTask(RTSTask task) throws SystemException {
        try {
            getHibernateTemplate().saveOrUpdate(task);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new SystemException("Cannot save task ["+task.getId()+"], cause: " + e.toString());
        }
    }

    public void removeRTSTask(RTSTask task) throws SystemException {
        try {
            getHibernateTemplate().delete(task);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new SystemException("Cannot delete task ["+task.getId()+"], cause: " + e.toString());
        }
    }

    public RTSTask findTaskById(Integer id) throws SystemException {
        try {
            List list = getHibernateTemplate().find("FROM RTSTask WHERE id = ?", new Object[] { id } );
            return (!list.isEmpty())?(RTSTask)list.get(0):null;
        } catch (RuntimeException e) {
            System.out.println("Error while finding task by id [" + id + "], cause: " + e.toString() );
            e.printStackTrace();
        }
        return null;
    }

    public List findTaskByStatus(String status) throws SystemException {
        List result = Collections.EMPTY_LIST;
        try {
            result = getHibernateTemplate().find("FROM RTSTask WHERE status = ?", new Object[] { status } );
        } catch (RuntimeException e) {
            System.out.println("Error while finding task by status [" + status + "], cause: " + e.toString() );
            e.printStackTrace();
        }
        return result;
    }
}
