/*
 *  $Id: RTSTaskEventType3Handler.java,v 1.6 2007/06/09 08:20:44 Abaykov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
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
 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_3
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.6 $ $Date: 2007/06/09 08:20:44 $
 */
public class RTSTaskEventType3Handler extends RTSTaskEventType1Handler {

	/**
	 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_3
	 * ���������� ��, �� ��������� � ������������� ��������
	 * ������������ ������ �� ������������ ������������
	 * (������� � �������� �������� ��� ����� ������� ������)
	 * @param task
	 * @param processInfo <String, String> ��� ������� (I-info,E-error), �������� �������
	 * @return ��������� �������
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
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] �������� ������ ����� ������� ?"));
			return RTSTask.STATUS_READY;
		}
		//��������� ������������ �� ��� ����� ? ��� ������ ������� RFC 060727 ������������� �������� ����� 3a , 3b
		if(request.getArendaType() == null || (request.getArendaType() != null && request.getArendaType().intValue() == 1)) {
			//���� ����
			//���� ������ 3 ���o� �� ��� ������ ����� �� ��������� ���������� �� �����
			processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �� ���� ����, �������� ������ �� 3 ���"));
			if((createdTime + (3L * DAY)) <= currentTime) {
				if(!checkSendingAtCurrentDay(task, processInfo)) {
					if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
						//��������� ������ ���������� ��� ������ �� �������
						// � ������ �������������� ������������
						getReglamentMailService().sendMailOnEventType3a(getAuthService().getSystemAuthInfo(),
								request, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ � ������������ ������������� ����������� ������������ ������ "));
						task.setLastSending(new Timestamp(System.currentTimeMillis()));
						return RTSTask.STATUS_READY;
					}
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �������� ������ '"+RTSStatus.status_4+"' �� '"+request.getRtsStatus().getName()+"', ������� ����������"));
					return RTSTask.STATUS_DELETED;
				} else {
					return RTSTask.STATUS_READY;
				}
			}

		} else {
			if(request.getArendaType() != null && request.getArendaType().intValue() == 2) {
				//������
				//���������� ����������� �� ������ ����������� � �
				// ������� ��������� 7 ������� ���� �� ���� � ������� ��������� �����
				//todo: ��������� ��� �� ��� �� ���� ���������, ���� ��� ���������� ����
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �� ������������ �����"));

				Date reqDate = request.getConsolidatedRequest().getRequiredDate();
				if(reqDate == null) {
					processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] ������ �� ������, ��������� ���� � �� ������ !"));
				}
				// �������� �������� �� 7 ��� ���� �� ����� ����
				//���� �������� ������ ����� ������, ���� ���� ��������� ���� ������
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �� ������������ �����, �������� �������� �� > 7 ����� ���������� ������ ���� ��� � ����"));
				if(checkLimitTimeToDate(7, reqDate)) {
					//��������� ����������� �� ������ � ���� ���� ?
					//  ��� ��� ���� ��c������� ������ ��� � �����,
					// � ������ Collaboration ����� ��������� �� �������� ��� � ��� !
					if(!checkSendingAtCurrentDay(task, processInfo)) {
						if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
							// �������� �������� �� 3 ��� ���� �� ����� ����
							//��� ���� ����� ������� ��� KM � �� !!!!
							boolean kmRKMNotify = false;
							if(checkLimitTimeToDate(3, reqDate)) {
								kmRKMNotify = true;
							}
							//��������� ������ ���������� ��� ������ �� �������
							// � ������ �������������� ������������ ������� �� ������!
							getReglamentMailService().sendMailOnEventType3b(getAuthService().getSystemAuthInfo(),
									request, createdTime, task.getHost(), kmRKMNotify);
							processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ � ������������ ������������� ����������� ������������ ������ ��� ��������� ���������� �� ���������"));
							task.setLastSending(new Timestamp(System.currentTimeMillis()));

							return RTSTask.STATUS_READY;
						}
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �������� ������ '"+RTSStatus.status_4+"' �� '"+request.getRtsStatus().getName()+"', ������� ����������"));
						return RTSTask.STATUS_DELETED;
					}
				} else {
					// �������� ������ 7 �������  ���� �� ����� ����
					// ��������� ����� ������������ ��������� ������,
					// ���� ���������� ��� � ������ !!!!
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �� ������������ �����, < 7 ����, ��������� ������ ���� ��� � 7 ���� "));
					if(checkSendingAtDay(task, processInfo, 7)) {
						// ���� ������ ���� ���� �� ��� ���������� ����������� ������ �������� ������� �������� ������
						// ��� ��� ������ Collaboration ����� ��������� �� �������� ��� � ��� !
						//�������� ������� ����, ����� ������ �����
						if(!checkSendingAtCurrentDay(task, processInfo)) {
							if(request.getRtsStatus().getId().intValue() == RTSStatus.statusId_4) {
								//��������� ������ ���������� ��� ������ �� �������
								// � ������ �������������� ������������ ������� �� ������!
								boolean kmRKMNotify = false;
								if(checkLimitTimeToDate(3, reqDate)) {
									kmRKMNotify = true;
									processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �� ������������ �����, ��������, ����� 3����, �� ���� ����, ��������� ��"));
								}
								getReglamentMailService().sendMailOnEventType3b(getAuthService().getSystemAuthInfo(),
										request, createdTime, task.getHost(), kmRKMNotify);
								processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ � ������������ ������������� ����������� ������������ ������ ��� ��������� ���������� �� ���������"));
								task.setLastSending(new Timestamp(System.currentTimeMillis()));
								return RTSTask.STATUS_READY;
							}
							processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������ �������� ������ '"+RTSStatus.status_4+"' �� '"+request.getRtsStatus().getName()+"', ������� ����������"));
							return RTSTask.STATUS_DELETED;
						}


					}

				}
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ����� ��������� ������� �� ���������"));
		return RTSTask.STATUS_READY;
	}

	/**
	 * �������� �� ��������� ������� ���� � ������� �������� ���� ��
	 * ���������
	 * @param countDay
	 * @param limit
	 * @return boolean
	 */

	protected boolean checkLimitTimeToDate(int countDay, Date limit) {
		if(System.currentTimeMillis() > limit.getTime()) {
			//������� ���� ��������� ����� ������ ����� ��������, ����� ����� �������� ������
			return true;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(limit);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		int i = 0;
		//��������� ������ ������� ����
		//�� ������ ���, �� ������� ����������
		while(i < countDay) {
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
			//��������� ���� �� �������� �� ����������� ���� ����� ����������
			if(!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
					|| calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
				i++;
			}
		}
		if(System.currentTimeMillis() > calendar.getTime().getTime()) {
			//������� ���� ��������� (�������� ���� ����� ���������� ������� ����)
			// ������ ������� ���� ��������� � ���������, ����� �������� ������
			return true;
		}
		//������� (�������� ���� ����� ���������� ������� ����)
		//�� ����������
		return false;

	}


}
