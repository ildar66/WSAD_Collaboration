/*
 *  $Id: RTSReglamentMailServiceImpl.java,v 1.3 2007/05/02 08:25:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.mail.service.impl;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.july.rts.mail.service.RTSReglamentMailService;
import com.hps.july.rts.mail.template.ReglamentEventType1ByKMTemplate;
import com.hps.july.rts.mail.template.ReglamentEventType2Template2KM;
import com.hps.july.rts.mail.template.ReglamentEventType2Template2RM;
import com.hps.july.rts.mail.template.ReglamentEventType3aTemplate2RM;
import com.hps.july.rts.mail.template.ReglamentEventType3bTemplate2Executor;
import com.hps.july.rts.mail.template.ReglamentEventType3bTemplate2KM;
import com.hps.july.rts.mail.template.ReglamentEventType3bTemplate2RM;
import com.hps.july.rts.mail.template.ReglamentEventType4Template2KM;
import com.hps.july.rts.mail.template.ReglamentEventType4Template2Assertor;
import com.hps.july.rts.mail.template.ReglamentEventType5Template2Executor;
import com.hps.july.rts.mail.template.ReglamentEventType5Template2KM;
import com.hps.july.rts.mail.template.ReglamentEventType6Template2Tester;
import com.hps.july.rts.mail.template.ReglamentEventType6Template2Head;
import com.hps.july.rts.mail.template.ReglamentEventType6Template2KM;
import com.hps.july.rts.mail.template.ReglamentEventType7Template2Initiator;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.InitiatorRequest;

import java.util.Collection;

/**
 * ������ ��������������� ������ �������� ��������� ���
 * ������ �������� ���������� ������� ������ ��� ��������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/05/02 08:25:50 $
 */
public class RTSReglamentMailServiceImpl extends RTSMailServiceImpl
		implements RTSReglamentMailService {

	/**
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_1
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType1(AuthInfo authInfo, InitiatorRequest request, long created, String host) {
		//������ ���� ��
		Collection persons = operatorService.findPersonByRole(RTSRole.TOP_MANAGER, null, null);
		sendRTSMail(authInfo, new ReglamentEventType1ByKMTemplate(request, persons, created, host));
	}

	/**
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_2
	 * � ������������� �������� ������������ ������ �� ������������ ������������
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType2(AuthInfo authInfo, InitiatorRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType2Template2RM(request, created, host));
		Person keyManager = request.getKeyManager();
		Person regManager = request.getRegManager();
		//���� PM � �� ������ ������������, �� ��������� ��� ������ ������
		if(!keyManager.equals(regManager)) {
			sendRTSMail(authInfo, new ReglamentEventType2Template2KM(request, created, host));
		}
	}


	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_3
	 * ��� ������� ������
	 * ���������� ����������� ����������� ����������
	 * �����������, �� � ������� 3 ������� ���� � �������������
	 * ����������� ������������ ������
	 * (������� � �������� �������� ��� ����� ������� ������)
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType3a(AuthInfo authInfo, ElementaryRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType3bTemplate2Executor(request, created, host));
		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		Person keyManager = cRequest.getKeyManager();
		Person regManager = cRequest.getRegManager();
		//��������� �� ���� �� ��� ������������ !!
		//��� �� �� �������
		if(!request.getExecutor().equals(cRequest.getKeyManager())) {
			sendRTSMail(authInfo, new ReglamentEventType3bTemplate2KM(request, cRequest, created, host));
			if(!keyManager.equals(regManager)) {
				sendRTSMail(authInfo, new ReglamentEventType3aTemplate2RM(request, cRequest, created, host));
			}
		} else {
			if(!request.getExecutor().equals(regManager)) {
				sendRTSMail(authInfo, new ReglamentEventType3aTemplate2RM(request, cRequest, created, host));
			}
		}
	}
	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_3
	 * ��� ������������ �����
	 * ���������� ����������� ����������� ����������
	 * ����������� �� ������ ����������� � �
	 * ������� ��������� 7 ������� ���� �� ���� � ������� ��������� �����
	 * ��, �� � ������� 3 ������� ���� �� ���� ����
	 * (������� � �������� �������� ��� ����� ������� ������)
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType3b(AuthInfo authInfo, ElementaryRequest request, long created, String host, boolean kmRMNotify) {
		sendRTSMail(authInfo, new ReglamentEventType3bTemplate2Executor(request, created, host));
		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		Person keyManager = cRequest.getKeyManager();
		Person regManager = cRequest.getRegManager();
		//��������� �� ���� �� ��� ������������ !!
		//��� �� �� �������
		if(kmRMNotify) {
			if(!request.getExecutor().equals(cRequest.getKeyManager())) {
				sendRTSMail(authInfo, new ReglamentEventType3bTemplate2KM(request, cRequest, created, host));
				if(!keyManager.equals(regManager)) {
					sendRTSMail(authInfo, new ReglamentEventType3bTemplate2RM(request, cRequest, created, host));
				}
			} else {
				if(!request.getExecutor().equals(regManager)) {
					sendRTSMail(authInfo, new ReglamentEventType3bTemplate2RM(request, cRequest, created, host));
				}
			}
		}
	}

	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 4 RTSTaskEvent.AUTOMATIC_EVENT_4
	 * ���������� �������������, �� ���������
	 * � ������������� ����������� ����������������� ������
	 * (������� �� ���������� �������� ��� ����� ������� ������)
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType4(AuthInfo authInfo, ConsolidatedRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType4Template2Assertor(request, created, host));
		Person assertor = request.getConfirmativeMan();
		Person regManager = request.getRegManager();
		//���� ������������ � �� ������ ������������, �� ��������� ��� ������ ������
		if(!assertor.equals(regManager)) {
			sendRTSMail(authInfo, new ReglamentEventType4Template2KM(request, created, host));
		}

	}

	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 5 RTSTaskEvent.AUTOMATIC_EVENT_5
	 * ���������� ������������ � ������� ��������� 3-� ����
	 * �� ��������� ������������ ����� � �������������
	 * ���������� ��� �������� ����� ���������� ������������ ������
	 * * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType5(AuthInfo authInfo, ElementaryRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType5Template2Executor(request, created, host));
		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		Person keyManager = cRequest.getKeyManager();
		if(!request.getExecutor().equals(keyManager)) {
			sendRTSMail(authInfo, new ReglamentEventType5Template2KM(request, cRequest, created, host));
		}
	}

	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 6 RTSTaskEvent.AUTOMATIC_EVENT_6
	 * ������������, ������������, �� ��������� �
	 * ������������� ������������ ����������������� ������
	 * (������� � �������� �������� ��� ����� ������� ������)
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType6(AuthInfo authInfo, ConsolidatedRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType6Template2Tester(request, created, host));
		Person tester = request.getTestedBy();
		boolean headSend = true;
		boolean kmSend = true;
		Person head = request.getHead();
		Person km = request.getKeyManager();
		if(head.equals(tester) && head.equals(km)) {
			headSend = false;
			kmSend = false;
		} else if(head.equals(km)) {
			kmSend = false;
		}
		if(headSend) {
			sendRTSMail(authInfo, new ReglamentEventType6Template2Head(request, created, host));
		}
		if(kmSend) {
			sendRTSMail(authInfo, new ReglamentEventType6Template2KM(request, created, host));
		}
	}

	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 7 RTSTaskEvent.AUTOMATIC_EVENT_7
	 * ���������� ���������� ��������� � ������������� �������� � ������
	 * ������ ����������
	 * (������� �� ���������� �������� ��� ����� ������� ������)
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType7(AuthInfo authInfo, InitiatorRequest request, long created, String host) {
		sendRTSMail(authInfo, new ReglamentEventType7Template2Initiator(request, created, host));

	}


}
