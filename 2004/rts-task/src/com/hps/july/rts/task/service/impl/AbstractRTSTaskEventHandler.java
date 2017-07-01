/*
 *  $Id: AbstractRTSTaskEventHandler.java,v 1.5 2007/05/22 10:03:53 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.task.service.impl;

import com.hps.april.auth.service.AuthService;
import com.hps.july.rts.mail.service.RTSReglamentMailService;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;
import com.hps.july.rts.task.service.RTSTaskEventHandler;
import com.hps.july.rts.service.InitiatorRequestService;
import com.hps.july.rts.service.ConsolidatedRequestService;
import com.hps.july.rts.service.ElementaryRequestService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.5 $ $Date: 2007/05/22 10:03:53 $
 */
abstract class AbstractRTSTaskEventHandler implements RTSTaskEventHandler {

	protected long DAY = 1000L * 60L * 60L * 24L;

	private AuthService authService;
	private RTSReglamentMailService reglamentMailService;
	private InitiatorRequestService initiatorRequestService;
	private ConsolidatedRequestService consolidatedRequestService;
	private ElementaryRequestService elementaryRequestService;

	public ElementaryRequestService getElementaryRequestService() {
		return elementaryRequestService;
	}

	public void setElementaryRequestService(ElementaryRequestService elementaryRequestService) {
		this.elementaryRequestService = elementaryRequestService;
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


	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	public RTSReglamentMailService getReglamentMailService() {
		return reglamentMailService;
	}

	public void setReglamentMailService(RTSReglamentMailService reglamentMailService) {
		this.reglamentMailService = reglamentMailService;
	}

	protected SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	/**
	 *  проверка на послыку письма некскольок дней назад
	 * @param task
	 * @param processInfo
	 * @param countDay
	 * @return boolean
	 */
	protected boolean checkSendingAtDay(RTSTask task, List processInfo, int countDay) {
		String lastSendingTime = (task.getLastSending() != null) ? sdf.format(task.getLastSending()) : "еще не отправлялось";
		if(task.getLastSending() != null &&
				checkSendingAtDay(task.getLastSending(), countDay)) {
			//processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] предыдущее время отправления было ["+lastSendingTime+"], сегодня ["+sdf.format(new Date())+"] письмо должно уходить раз в "+countDay+" день"));
			return true;
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] предыдущее время отправления было ["+lastSendingTime+"], сегодня ["+sdf.format(new Date())+"]письмо должно уходить раз в "+countDay+" день"));
		return false;
	}

	/**
	 *  проверка на послыку письма в тот же день
	 * @param task
	 * @param processInfo
	 * @return boolean
	 */
	protected boolean checkSendingAtCurrentDay(RTSTask task, List processInfo) {
		String lastSendingTime = (task.getLastSending() != null) ? sdf.format(task.getLastSending()) : "еще не отправлялось";
		if(task.getLastSending() != null &&
				checkSendingAtDay(task.getLastSending(), 0)) {
			processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] предыдущее время отправления было ["+lastSendingTime+"], сегодня ["+sdf.format(new Date())+"] письмо должно уходить раз в день"));
			return true;
		}
		return false;
	}

	private boolean checkSendingAtDay(Date lastSendingTime, int countDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(lastSendingTime);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		GregorianCalendar current = new GregorianCalendar();
		current.setTime(new Date());
		if(countDay != 0) {
			current.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) - countDay);
		}
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.SECOND, 0);
		current.set(Calendar.MILLISECOND, 0);

		return current.equals(calendar);
	}
}
