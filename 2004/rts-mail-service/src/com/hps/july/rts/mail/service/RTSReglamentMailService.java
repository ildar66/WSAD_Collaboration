/*
 *  $Id: RTSReglamentMailService.java,v 1.4 2007/06/02 14:55:55 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.rts.mail.service;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.april.auth.object.AuthInfo;

/**
 * ������ ��������������� ������ �������� ��������� ���
 * ������ �������� ���������� ������� ������ ��� ��������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/06/02 14:55:55 $
 */
public interface RTSReglamentMailService {

	String SERVICE_NAME = "july.rts.mail.reglamentMailService";
	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_1
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType1(AuthInfo authInfo, InitiatorRequest request, long created, String host);
	/**
	 * RFC 060727
	 * �������� ��������� ��� ������� 1 RTSTaskEvent.AUTOMATIC_EVENT_2
	 * � ������������� �������� ������������ ������ �� ������������ ������������
	 * @param request ������
	 * @param created - ����� ������ �������
	 * @param host
	 */
	public void sendMailOnEventType2(AuthInfo authInfo, InitiatorRequest request, long created, String host);
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
	public void sendMailOnEventType3a(AuthInfo authInfo, ElementaryRequest request, long created, String host);
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
	public void sendMailOnEventType3b(AuthInfo authInfo, ElementaryRequest request, long created, String host, boolean kmRMNotify);


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
	public void sendMailOnEventType4(AuthInfo authInfo, ConsolidatedRequest request, long created, String host);

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
	public void sendMailOnEventType5(AuthInfo authInfo, ElementaryRequest request, long created, String host);

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
	public void sendMailOnEventType6(AuthInfo authInfo, ConsolidatedRequest request, long created, String host);

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
	public void sendMailOnEventType7(AuthInfo authInfo, InitiatorRequest request, long created, String host);
}
