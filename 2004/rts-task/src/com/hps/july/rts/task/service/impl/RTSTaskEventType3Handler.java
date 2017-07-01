/*
 *  $Id: RTSTaskEventType3Handler.java,v 1.6 2007/06/09 08:20:44 Abaykov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.SystemException;
import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_3
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.6 $ $Date: 2007/06/09 08:20:44 $
 */
public class RTSTaskEventType3Handler extends RTSTaskEventType1Handler {

	/**
	 * Обработчик задания для типа RTSTaskEvent.AUTOMATIC_EVENT_3
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
		ElementaryRequest request = null;
		try {
			request = getElementaryRequestService().findElementaryRequest(getAuthService().getSystemAuthInfo(), task.getRequestId());
		} catch(SystemException e) {
			e.printStackTrace();
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] stoped cause, " + e.toString()));
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] возможно заявку можно удалить ?"));
			return RTSTask.STATUS_READY;
		}
		//проверяем адренованная ли это часть ? так зарные задания RFC 060727 автоматичские рассылки части 3a , 3b
		if(request.getArendaType() == null || (request.getArendaType() != null && request.getArendaType().intValue() == 1)) {
			//своя сеть
			//если прошли 3 сутoк со дня подачи завки то проверяем направлена ли завка
			processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка на свою сеть, проверям прошли ли 3 дня"));
			if((createdTime + (3L * DAY)) <= currentTime) {
				if(!checkSendingAtCurrentDay(task, processInfo)) {
					if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
						//выполняем задачу оповещения так заявка не перешла
						// в статус завизированной исполнителем
						getReglamentMailService().sendMailOnEventType3a(getAuthService().getSystemAuthInfo(),
								request, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями необходимости визирования Элементарной заявки "));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
						return RTSTask.STATUS_READY;
					}
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка изменила статус '"+RTSStatus.status_4+"' на '"+request.getRtsStatus().getName()+"', задание отменяется"));
					return RTSTask.STATUS_DELETED;
				} else {
					return RTSTask.STATUS_READY;
				}
			}

		} else {
			if(request.getArendaType() != null && request.getArendaType().intValue() == 2) {
				//аренда
				//оповещения исполнителя по аренде еженедельно и в
				// течение последних 7 рабочих дней до даты к которой необходим поток
				//todo: првоерить что же это за дата требуемая, пока что используем эжту
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка на арендованную часть"));

				Date reqDate = request.getConsolidatedRequest().getRequiredDate();
				if(reqDate == null) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] заявка на аренду, требуемая дата в КЗ пустая !"));
				}
				// прверяем осталось ли 7 раб дней до требю даты
				//если осталось меньше посыл письмо, если дата превышена посл письмо
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка на арендованную часть, проверям осталось ли > 7 днейб оповещение должно быть раз в день"));
				if(checkLimitTimeToDate(7, reqDate)) {
					//проверяем послылалось ли письмр в этот день ?
					//  так как надо поcылпатсь письмо раз в сутки,
					// а сисмеа Collaboration будет настроена на проверку раз в час !
					if(!checkSendingAtCurrentDay(task, processInfo)) {
						if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
							// прверяем осталось ли 3 раб дней до требю даты
							//для того чтобы послать еще KM и РМ !!!!
							boolean kmRKMNotify = false;
							if(checkLimitTimeToDate(3, reqDate)) {
								kmRKMNotify = true;
							}
							//выполняем задачу оповещения так заявка не перешла
							// в статус завизированной исполнителем ответст за аренду!
							getReglamentMailService().sendMailOnEventType3b(getAuthService().getSystemAuthInfo(),
									request, createdTime, task.getHost(), kmRKMNotify);
							processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями необходимости визирования Элементарной заявки или получения информации от оператора"));
							task.setLastSending(new Timestamp(System.currentTimeMillis()));

							return RTSTask.STATUS_READY;
						}
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка изменила статус '"+RTSStatus.status_4+"' на '"+request.getRtsStatus().getName()+"', задание отменяется"));
						return RTSTask.STATUS_DELETED;
					}
				} else {
					// осталось больше 7 рабочих  дней до требю даты
					// проверяем когда отпарвлялось последнее письмо,
					// надо отправлять раз в неделю !!!!
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка на арендованную часть, < 7 дней, оповещеие должно быть раз в 7 дней "));
					if(checkSendingAtDay(task, processInfo, 7)) {
						// если прошло семь дней со дня последнего отпарвления письма провеяем условие отпарвки письма
						// так как сисмеа Collaboration будет настроена на проверку раз в час !
						//проверям текущий день, чтобы небыло спама
						if(!checkSendingAtCurrentDay(task, processInfo)) {
							if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
								//выполняем задачу оповещения так заявка не перешла
								// в статус завизированной исполнителем ответст за аренду!
								boolean kmRKMNotify = false;
								if(checkLimitTimeToDate(3, reqDate)) {
									kmRKMNotify = true;
									processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка на арендованную часть, осталось, менее 3дней, до план даты, оповещаем КМ"));
								}
								getReglamentMailService().sendMailOnEventType3b(getAuthService().getSystemAuthInfo(),
										request, createdTime, task.getHost(), kmRKMNotify);
								processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] отправка письма с требованиями необходимости визирования Элементарной заявки или получения информации от оператора"));
								task.setLastSending(new Timestamp(System.currentTimeMillis()));
								return RTSTask.STATUS_READY;
							}
							processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] заявка изменила статус '"+RTSStatus.status_4+"' на '"+request.getRtsStatus().getName()+"', задание отменяется"));
							return RTSTask.STATUS_DELETED;
						}


					}

				}
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] время обработки запроса не наступило"));
		return RTSTask.STATUS_READY;
	}

	/**
	 * Прверяем на вхождение текущей даты в границы раброчих дней до
	 * требуемой
	 * @param countDay
	 * @param limit
	 * @return boolean
	 */

	protected boolean checkLimitTimeToDate(int countDay, Date limit) {
		if(System.currentTimeMillis() > limit.getTime()) {
			//текущая дата первышает лимит значит лимит исчерпан, можно сразу посылать письмо
			return true;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(limit);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		int i = 0;
		//учитываем только рабочие дени
		//по одному дню, до нужного количества
		while(i < countDay) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
			//Проверяем если не выходные то увеличиваем счет иначе пропускаем
			if(!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
				i++;
			}
		}
		if(System.currentTimeMillis() > calendar.getTime().getTime()) {
			//текущая дата первышает (требумая дата минус количество рабочих дней)
			// значит текущая дата находится в интервале, можно посылать письмо
			return true;
		}
		//граница (требумая дата минус количество рабочих дней)
		//не достигнута
		return false;

	}


}
