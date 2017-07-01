/*
 *  $Id: RTSTaskEventType6Handler.java,v 1.1 2007/05/02 08:25:52 vglushkov Exp $
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
 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_6
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/05/02 08:25:52 $
 */
public class RTSTaskEventType6Handler extends RTSTaskEventType4Handler {

	/**
	 * ���������� ������� ��� ���� RTSTaskEvent.AUTOMATIC_EVENT_6
	 * ���������� ������������, ������������, ��
	 * ��������� � ������������� ������������
	 * ����������������� ������
	 * (������� � �������� �������� ��� ����� ������� ������)
	 * @param task
	 * @param processInfo <String, String> ��� ������� (I-info,E-error), �������� �������
	 * @return ��������� �������
	 */
	public String handleTask(RTSTask task, List processInfo) {
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] type ["+task.getTaskType()+"] started"));
		long createdTime = task.getCreated().getTime();
		long currentTime = System.currentTimeMillis();
		//���� ������ 3 ���o� �� ��� ������ ����� �� ��������� �������������� �� ����� ?
		if((createdTime + (3L * DAY)) <= currentTime) {
			if(!checkSendingAtCurrentDay(task, processInfo)) {
				try {
					ConsolidatedRequest consolidatedRequest =
							getConsolidatedRequestService().findConsolidatedRequest
									(getAuthService().getSystemAuthInfo(), task.getRequestId());
					//�������������� �� ����� ? ������ ������ ��� �� ������������
					//���� ���� ���� �� ��������� ������ 11
					if(consolidatedRequest.getRtsStatus().getId().intValue() < RTSStatus.statusId_9) {
						//��������� ������ ���������� ��� ������ �� �������
						// � ������ ��������� ��� �� ���������!!
						getReglamentMailService().sendMailOnEventType4(getAuthService().getSystemAuthInfo(), consolidatedRequest, createdTime, task.getHost());
						processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] �������� ������ �  ������� ��������� �� "));
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
				processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ����������� � ������������ ��� ������������ �������"));
				return RTSTask.STATUS_READY;
			}
		}
		processInfo.add(new RTSTaskInfo(INFO, "task ["+task.getId()+"] ����� ��������� ������� �� ���������"));
		return RTSTask.STATUS_READY;
	}


}
