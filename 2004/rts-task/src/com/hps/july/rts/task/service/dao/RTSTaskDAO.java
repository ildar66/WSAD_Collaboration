package com.hps.july.rts.task.service.dao;

import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.SystemException;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.06.2006
 * Time: 13:13:49
 */
public interface RTSTaskDAO {

    public List findTaskByStatus(String status) throws SystemException;

    public RTSTask findTaskById(Integer id) throws SystemException;

    public void saveRTSTask(RTSTask task) throws SystemException;

    public void saveAllRTSTask(List tasks) throws SystemException;

    public void removeRTSTask(RTSTask task) throws SystemException;

}
