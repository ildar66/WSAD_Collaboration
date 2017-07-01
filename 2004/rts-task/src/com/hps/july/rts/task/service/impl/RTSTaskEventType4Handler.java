/*
 *  $Id: RTSTaskEventType4Handler.java,v 1.2 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.object.RTSStatus;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;

import java.sql.Timestamp;
import java.util.List;

/**
 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_4
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType4Handler extends RTSTaskEventType1Handler {

	/**
	 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_4
	 * ���������� �������������, �� ���������
	 * � ������������� ����������� ����������������� ������
	 * (������� �� ���������� �������� ��� ����� ������� ������)
	 * @param task
	 * @param processInfo <String, String> ��� ������� (I-info,E-error), �������� �������
	 * @return ��������� �������
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//���� ������ 1 ���o� �� ��� ������ ����� �� ��������� ���������� �� �����
		if((createdTime + DAY) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					ConsolidatedRequest consolidatedRequest =
							getConsolidatedRequestService().findConsolidatedRequest
									(getAuthService().getSystemAuthInfo(), task.getRequestId());
					if(consolidatedRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_7) {
						//��������� ������ ���������� ��� ������ �� �������
						// � ������ ���������� !!
						getReglamentMailService().sendMailOnEventType4(getAuthService().getSystemAuthInfo(), consolidatedRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ �  ������� ��������� �� "));
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
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ����� ��������� ������� �� ���������"));
		return RTSTask.STATUS_READY;
	}


}
