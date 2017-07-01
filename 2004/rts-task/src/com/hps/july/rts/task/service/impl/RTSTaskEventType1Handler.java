/*
 *  $Id: RTSTaskEventType1Handler.java,v 1.4 2007/05/13 15:08:25 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_1
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/05/13 15:08:25 $
 */
public class RTSTaskEventType1Handler extends AbstractRTSTaskEventHandler{


	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_1
	 * реализовать возможность оповещения КМ ежедневно о необходимости отправки
	 * Заявки инициатора на рассмотрение РМ-у
	 * (начиная со следующего рабочего дня после прихода заявки)
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//если прошли сутки со дня подачи завки то проверяем направлена ли завка
		if((createdTime + DAY) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					InitiatorRequest initiatorRequest = getInitiatorRequestService().findInitiatorRequest(task.getRequestId());
					if(initiatorRequest.getRtsStatus().getId().intValue() == RTSStatus.statusId_11
							&& initiatorRequest.getRevisionComment() != null) {
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] on status на доработке от КМ, задание отменяется"));
						return RTSTask.STATUS_DELETED;
					}
					if(initiatorRequest.getRegManager() != null) {
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] regManager был назначен, задание отменяется"));
						return RTSTask.STATUS_DELETED;
					} else {
						//выполняем задачу оповещения так рег манагер не назначения
						getReglamentMailService().sendMailOnEventType1(getAuthService().getSystemAuthInfo(), initiatorRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями отпарвить на рассмотрение РМ"));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
					}
				} catch(SystemException e) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
					return RTSTask.STATUS_READY;
				}
				return RTSTask.STATUS_READY;
			} else {
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}


}
