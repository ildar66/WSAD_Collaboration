package com.hps.july.rts.task.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.common.Service;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.request.Request;
import com.hps.july.rts.task.object.RTSTask;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.06.2006
 * Time: 13:11:54
 */
public interface RTSTaskService extends Service {

    public static final String SERVICE_NAME = "july.rts.service.task";

    public RTSTask findTaskById(Integer id) throws SystemException;

    public List findReadyTasks() throws SystemException;

    public List findUsedTasks() throws SystemException;

    public void saveAllRTSTask(AuthInfo authInfo, List tasks) throws SystemException;

    public void saveRTSTask(AuthInfo authInfo, RTSTask task) throws SystemException;

    public void removeRTSTask(AuthInfo authInfo, RTSTask task) throws SystemException;

    /**
     * создание обхекта события на задания
     * @param taskEvent
     * @param request
     * @return событие
     */
    public RTSTask createTask(int taskEvent, Request request, String host);

//    /**
//     *  Метод осуществляет проверку времени работы КМ с завякой инициатора,
//     *  отпеделенной регламентом процесса.
//     *
//     * @param authInfo
//     * @param requestId
//     * @param firstTime
//     * @return true - отмена задания; false - задание продолжает рабоатть
//     * @throws SystemException
//     */
//    public boolean executeTaskConsiderInitiatorRequestByKM(AuthInfo authInfo, Integer requestId, Date firstTime) throws SystemException;
}
