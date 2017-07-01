/*
 *  $Id: RTSTaskEventType5Handler.java,v 1.2 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_5
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType5Handler extends RTSTaskEventType3Handler {

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_5
	 * оповещения Исполнителей в течение последних 3-х
	 * дней до истечения планируемого срока о необходимости
	 * исполнения или переноса срока исполнения Элементарной заявки
	 * @param task
	 * @param processInfo <String, String> тип заметки (I-info,E-error), значение заметки
	 * @return Состояние задания
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		ElementaryRequest request = null;
		try {
			request = getElementaryRequestService().findElementaryRequest(getAuthService().getSystemAuthInfo(), task.getRequestId());
		} catch(SystemException e) {
			e.printStackTrace();
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] возможно заявку можно удалить ?"));
			return RTSTask.STATUS_READY;
		}
		//todo: проверить та ли дата ?
		Date planDate = request.getPlanComplDate();
		if(planDate == null) {
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] заявка , планируемая дата окончания исполнения пустая!"));
		}

		// прверяем осталось ли менее 3 раб дней до план  даты окончания
		//если осталось меньше посыл письмо, если дата превышена посл письмо
		if(checkLimitTimeToDate(3, planDate)) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				if(request.getRtsStatus().getId().intValue() < RTSStatus.statusId_8) {
					//выполняем задачу оповещения так заявка не перешла
					// в статус "готова к теситрованию"!!
					getReglamentMailService().sendMailOnEventType5(getAuthService().getSystemAuthInfo(), request, createdTime, task.getHost());
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с  просбой утвердить КЗ "));
					task.setLastSending(new Timestamp(System.currentTimeMillis()));
				}
				return RTSTask.STATUS_READY;
			} else {
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] сегодня отправка письма с  просбой утвердить КЗ уже происходила"));
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}


}
