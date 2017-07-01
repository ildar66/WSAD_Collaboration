/*
 *  $Id: RTSTaskEventType4Handler.java,v 1.2 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_4
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType4Handler extends RTSTaskEventType1Handler {

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_4
	 * оповещения Утверждающего, КМ ежедневно
	 * о необходимости утверждения Консолидированной заявки
	 * (начиная со следующего рабочего дня после прихода заявки)
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//если прошли 1 сутoк со дня подачи завки то проверяем направлена ли завка
		if((createdTime + DAY) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					ConsolidatedRequest consolidatedRequest =
							getConsolidatedRequestService().findConsolidatedRequest
									(getAuthService().getSystemAuthInfo(), task.getRequestId());
					if(consolidatedRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_7) {
						//выполняем задачу оповещения так заявка не перешла
						// в статус утверждена !!
						getReglamentMailService().sendMailOnEventType4(getAuthService().getSystemAuthInfo(), consolidatedRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с  просбой утвердить КЗ "));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
					}
				} catch(Exception e) {
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
