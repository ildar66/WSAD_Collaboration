/*
 *  $Id: RTSTaskEventType2Handler.java,v 1.3 2007/05/22 10:03:53 vglushkov Exp $
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
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_2
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/05/22 10:03:53 $
 */
public class RTSTaskEventType2Handler extends RTSTaskEventType1Handler {

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_2
	 * оповещения РМ, КМ ежедневно о необходимости отправки
	 * Элементарных Заявок на рассмотрение исполнителям
	 * (начиная с третьего рабочего дня после прихода заявки)
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//если прошли 3 сутoк со дня подачи завки то проверяем направлена ли завка
		if((createdTime + (3L * DAY)) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					InitiatorRequest initiatorRequest = getInitiatorRequestService().findInitiatorRequest(task.getRequestId());
					if(initiatorRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_4) {
						//выполняем задачу оповещения так заявка не перешла
						// в статус на визировании исполнителей
						getReglamentMailService().sendMailOnEventType1(getAuthService().getSystemAuthInfo(), initiatorRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями необходимости отправки Элементарных Заявок на рассмотрение исполнителям "));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
						return RTSTask.STATUS_READY;
					} else {
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] статус больше 4, задачу закрываем  "));
						return RTSTask.STATUS_DELETED;
					}
				} catch(SystemException e) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
					return RTSTask.STATUS_READY;
				}
			} else {
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}


}
