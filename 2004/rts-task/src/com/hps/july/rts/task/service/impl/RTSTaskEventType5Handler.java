/*
 *  $Id: RTSTaskEventType5Handler.java,v 1.2 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
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
 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_5
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType5Handler extends RTSTaskEventType3Handler {

	/**
	 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_5
	 * ���������� ������������ � ������� ��������� 3-�
	 * ���� �� ��������� ������������ ����� � �������������
	 * ���������� ��� �������� ����� ���������� ������������ ������
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
		//todo: ��������� �� �� ���� ?
		Date planDate = request.getPlanComplDate();
		if(planDate == null) {
			processInfo.add(new RTSTaskInfo(ERROR, "task ["+task.getId()+"] ������ , ����������� ���� ��������� ���������� ������!"));
		}

		// �������� �������� �� ����� 3 ��� ���� �� ����  ���� ���������
		//���� �������� ������ ����� ������, ���� ���� ��������� ���� ������
		if(checkLimitTimeToDate(3, planDate)) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				if(request.getRtsStatus().getId().intValue() < RTSStatus.statusId_8) {
					//��������� ������ ���������� ��� ������ �� �������
					// � ������ "������ � ������������"!!
					getReglamentMailService().sendMailOnEventType5(getAuthService().getSystemAuthInfo(), request, createdTime, task.getHost());
					processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ �  ������� ��������� �� "));
					task.setLastSending(new Timestamp(System.currentTimeMillis()));
				}
				return RTSTask.STATUS_READY;
			} else {
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ������� �������� ������ �  ������� ��������� �� ��� �����������"));
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ����� ��������� ������� �� ���������"));
		return RTSTask.STATUS_READY;
	}


}
