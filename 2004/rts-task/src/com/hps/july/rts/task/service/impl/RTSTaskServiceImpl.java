package com.hps.july.rts.task.service.impl;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.object.request.Request;
import com.hps.july.rts.service.CommentService;
import com.hps.july.rts.service.ConsolidatedRequestService;
import com.hps.july.rts.service.ElementaryRequestService;
import com.hps.july.rts.service.InitiatorRequestService;
import com.hps.july.rts.service.NotificationService;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.RTSStatusService;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.service.RTSTaskService;
import com.hps.july.rts.task.service.dao.RTSTaskDAO;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.06.2006
 * Time: 13:12:23
 */
public class RTSTaskServiceImpl implements RTSTaskService {

	private RTSTaskDAO dao;
	private RTSAuthService authService;
	//private RTSMailService mailService;
	private RTSOperatorService operatorService;
	private ConsolidatedRequestService consolidatedRequestService;
	private InitiatorRequestService initiatorRequestService;
	private RTSStatusService statusService;
	private NotificationService notificationService;
	private CommentService commentService;
	private ElementaryRequestService elementaryRequestService;
	private Logger logger = Logger.getLogger(getClass());

	public RTSTaskServiceImpl() {
	}

	public RTSTaskDAO getDao() {
		return dao;
	}

	public void setDao(RTSTaskDAO dao) {
		this.dao = dao;
	}

	public RTSAuthService getAuthService() {
		return authService;
	}

	public void setAuthService(RTSAuthService authService) {
		this.authService = authService;
	}

//    public RTSMailService getMailService() {
//        return mailService;
//    }
//
//    public void setMailService(RTSMailService mailService) {
//        this.mailService = mailService;
//    }

	public RTSOperatorService getOperatorService() {
		return operatorService;
	}

	public void setOperatorService(RTSOperatorService operatorService) {
		this.operatorService = operatorService;
	}

	public ConsolidatedRequestService getConsolidatedRequestService() {
		return consolidatedRequestService;
	}

	public void setConsolidatedRequestService(ConsolidatedRequestService consolidatedRequestService) {
		this.consolidatedRequestService = consolidatedRequestService;
	}

	public InitiatorRequestService getInitiatorRequestService() {
		return initiatorRequestService;
	}

	public void setInitiatorRequestService(InitiatorRequestService initiatorRequestService) {
		this.initiatorRequestService = initiatorRequestService;
	}

	public RTSStatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(RTSStatusService statusService) {
		this.statusService = statusService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public ElementaryRequestService getElementaryRequestService() {
		return elementaryRequestService;
	}

	public void setElementaryRequestService(ElementaryRequestService elementaryRequestService) {
		this.elementaryRequestService = elementaryRequestService;
	}

	public RTSTask findTaskById(Integer id) throws SystemException {
		return this.dao.findTaskById(id);
	}

	public List findReadyTasks() throws SystemException {
		return this.dao.findTaskByStatus(RTSTask.STATUS_READY);
	}

	public List findUsedTasks() throws SystemException {
		return this.dao.findTaskByStatus(RTSTask.STATUS_USED);
	}

	public void saveRTSTask(AuthInfo authInfo, RTSTask task) throws SystemException {
		if(task == null) throw new SystemException("Cannot save RTS task, cause null");
		this.dao.saveRTSTask(task);
	}

	public void saveAllRTSTask(AuthInfo authInfo, List tasks) throws SystemException {
		this.dao.saveAllRTSTask(tasks);
	}

	public void removeRTSTask(AuthInfo authInfo, RTSTask task) throws SystemException {
		//TODO: check user permissions ! only admin
		if(task == null) throw new SystemException("Cannot remove RTS task, cause null");
		this.dao.removeRTSTask(task);
	}

//    /**
//     *  ћетод осуществл€ет проверку времени работы  ћ с зав€кой инициатора,
//     *  отпеделенной регламентом процесса.
//     *
//     * @param authInfo
//     * @param requestId
//     * @param firstTime
//     * @return true - отмена задани€; false - задание продолжает рабоатть
//     * @throws SystemException
//     */
//    public boolean executeTaskConsiderInitiatorRequestByKM(AuthInfo authInfo, Integer requestId, Date firstTime) throws SystemException {
//        InitiatorRequest request = null;
//        try {
//            if(requestId != null) {
//                //получаем за€вку
//                request = getInitiatorRequestService().findInitiatorRequest(requestId);
//                //String name = request.getInitiatorPerson().getName();
//            } else {
//                //можно найти по номеру !
//                // или по номеру и типу
//            }
//            if(request != null) {
//                //статус не изменилс€ отправить собщение !
//                //а если статуc измени€с€ несколько раз, никак не удалю сообщение из очереди, блин
//                //TODO:проверить наличие этой же за€вки с таким же типом задачи ! этим исправим баг с возможностью по€влени€ лишних писем
//                if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_2) {
//
//                    getMailService().sendMailOnConsiderInitiatorRequestByOrder(authInfo, request, firstTime);
//                    return false;
//                } else {
//                    //cancel task;
//                    return true;
//                }
//            }
//        } catch(RuntimeException e) {
//            logger.error("Error while execute 'executeTaskConsiderInitiatorRequestByKM' task [requestId="+requestId+"]", e);
//        } catch(SystemException e) {
//            logger.error("Error while execute 'executeTaskConsiderInitiatorRequestByKM' task [requestId="+requestId+"]", e);
//        }
//        return false;
//    }

	public RTSTask createTask(int taskEvent, Request request, String host) {
		RTSTask task = new RTSTask();
		task.setStatus(RTSTask.STATUS_READY);
		task.setRequestId(request.getRequestId());
		task.setRequestType(request.getRequestType());
		task.setRequestNumber(request.getNumber());
		task.setTaskType(new Integer(taskEvent));
		task.setCreated(new Timestamp(System.currentTimeMillis()));
		task.setHost(host);
		return task;
	}
}
