/*
 *  $Id: RTSReglamentMailService.java,v 1.4 2007/06/02 14:55:55 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.service;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.april.auth.object.AuthInfo;

/**
 * Сервис предоставляющий методы отправки сообщения для
 * модуля проверки регламента времени работы над заявками
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/06/02 14:55:55 $
 */
public interface RTSReglamentMailService {

	String SERVICE_NAME = "july.rts.mail.reglamentMailService";
	/**
	 * RFC 060727
	 * Отправка сообщения для событию 1 RTSTaskEvent.AUTOMATIC_EVENT_1
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType1(AuthInfo authInfo, InitiatorRequest request, long created, String host);
	/**
	 * RFC 060727
	 * Отправка сообщения для событию 1 RTSTaskEvent.AUTOMATIC_EVENT_2
	 * о необходимости отправки Элементарных Заявок на рассмотрение исполнителям
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType2(AuthInfo authInfo, InitiatorRequest request, long created, String host);
	/**
	 * RFC 060727
	 * Отправка сообщения для событию 1 RTSTaskEvent.AUTOMATIC_EVENT_3
	 * для простой заявки
	 * Необходимо реализовать возможность оповещения
	 * Исполнителя, КМ в течение 3 рабочих дней о необходимости
	 * визирования Элементарной заявки
	 * (начиная с третьего рабочего дня после прихода заявки)
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType3a(AuthInfo authInfo, ElementaryRequest request, long created, String host);
	/**
	 * RFC 060727
	 * Отправка сообщения для событию 1 RTSTaskEvent.AUTOMATIC_EVENT_3
	 * для арендованной части 
	 * Необходимо реализовать возможность оповещения
	 * исполнителя по аренде еженедельно и в
	 * течение последних 7 рабочих дней до даты к которой необходим поток
	 * РМ, КМ в течение 3 рабочих дней до треб даты
	 * (начиная с третьего рабочего дня после прихода заявки)
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType3b(AuthInfo authInfo, ElementaryRequest request, long created, String host, boolean kmRMNotify);


	/**
	 * RFC 060727
	 * Отправка сообщения для событию 4 RTSTaskEvent.AUTOMATIC_EVENT_4
	 * оповещения Утверждающего, КМ ежедневно
	 * о необходимости утверждения Консолидированной заявки
	 * (начиная со следующего рабочего дня после прихода заявки)
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType4(AuthInfo authInfo, ConsolidatedRequest request, long created, String host);

	/**
	 * RFC 060727
	 * Отправка сообщения для событию 5 RTSTaskEvent.AUTOMATIC_EVENT_5
	 * оповещения Исполнителей в течение последних 3-х дней
	 * до истечения планируемого срока о необходимости
	 * исполнения или переноса срока исполнения Элементарной заявки
	 * * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType5(AuthInfo authInfo, ElementaryRequest request, long created, String host);

	/**
	 * RFC 060727
	 * Отправка сообщения для событию 6 RTSTaskEvent.AUTOMATIC_EVENT_6
	 * Тестирующего, Руководителя, КМ ежедневно о
	 * необходимости тестирования Консолидированной заявки
	 * (начиная с третьего рабочего дня после прихода заявки)
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType6(AuthInfo authInfo, ConsolidatedRequest request, long created, String host);

	/**
	 * RFC 060727
	 * Отправка сообщения для событию 7 RTSTaskEvent.AUTOMATIC_EVENT_7
	 * оповещения Инициатора ежедневно о необходимости принятия в работу
	 * Заявки инициатора
	 * (начиная со следующего рабочего дня после прихода заявки)
	 * @param request заявка
	 * @param created - время начала задания
	 * @param host
	 */
	public void sendMailOnEventType7(AuthInfo authInfo, InitiatorRequest request, long created, String host);
}
