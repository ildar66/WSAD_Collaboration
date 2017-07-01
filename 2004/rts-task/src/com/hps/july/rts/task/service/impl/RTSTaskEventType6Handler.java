/*
 *  $Id: RTSTaskEventType6Handler.java,v 1.1 2007/05/02 08:25:52 vglushkov Exp $
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
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_6
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType6Handler extends RTSTaskEventType4Handler {

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_6
	 * оповещения Тестирующего, Руководителя, КМ
	 * ежедневно о необходимости тестирования
	 * Консолидированной заявки
	 * (начиная с третьего рабочего дня после прихода заявки)
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//если прошли 3 сутoк со дня подачи завки то проверяем протестирована ли завка ?
		if((createdTime + (3L * DAY)) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					ConsolidatedRequest consolidatedRequest =
							getConsolidatedRequestService().findConsolidatedRequest
									(getAuthService().getSystemAuthInfo(), task.getRequestId());
					//протестирована ли завка ? статус больше чем на тестировании
					//даже если ушла на доработку статус 11
					if(consolidatedRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_9) {
						//выполняем задачу оповещения так заявка не перешла
						// в статус исполнена или на доработку!!
						getReglamentMailService().sendMailOnEventType4(getAuthService().getSystemAuthInfo(), consolidatedRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с  просбой утвердить КЗ "));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
					} else {
						if(consolidatedRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_9) {

						}
					}
				} catch(Exception e) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
					return RTSTask.STATUS_READY;
				}
				return RTSTask.STATUS_READY;
			} else {
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] Напоминание о тестировании уже отправлялось сегодня"));
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}


}
