/*
 *  $Id: RTSTaskEventType7Handler.java,v 1.1 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_7
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType7Handler extends RTSTaskEventType1Handler{

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_7
	 * оповещения Инициатора ежедневно о необходимости
	 * принятия в работу Заявки инициатора
	 * (начиная со следующего рабочего дня после прихода заявки)
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//если прошли сутки со дня подачи завки то проверяем принята ли заявка инициатора в работу
		if((createdTime + DAY) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					InitiatorRequest initiatorRequest = getInitiatorRequestService().findInitiatorRequest(task.getRequestId());
					if(initiatorRequest.getRtsStatus().getId().intValue() == RTSStatus.statusId_12) {
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ЭЗ принята в работу, задание отменяется"));
						return RTSTask.STATUS_DELETED;
					} else {
						//заявка не принята в работу -> посылаем оповещение
						getReglamentMailService().sendMailOnEventType7(getAuthService().getSystemAuthInfo(), initiatorRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями приянть в работу"));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
						return RTSTask.STATUS_READY;
					}
				} catch(Exception e) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
					return RTSTask.STATUS_READY;
				}
			} else {
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] письма"));
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}


}
