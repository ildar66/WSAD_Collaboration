/*
 *  $Id: RTSMailService.java,v 1.7 2007/04/29 13:04:41 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.rts.mail.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.rts.mail.template.RTSMailTemplate;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.InitiatorRequest;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.7 $ $Date: 2007/04/29 13:04:41 $
 */
public interface RTSMailService {

    String SERVICE_NAME = "july.rts.mail.mailService";

    /**
     * �������� ��������� �� �������
     * @param authInfo
     * @param mailTemplate
     */
    void sendRTSMail(AuthInfo authInfo, RTSMailTemplate mailTemplate);

    /**
     * @deprecated RFC 060727
     * �������� (��� ������� �� ������ ��� ��������� � ����. ������)
     * @param authInfo
     * @param request
     */
//    void sendConsolidatedRequestDraftMail(AuthInfo authInfo, ConsolidatedRequest request);

    /**
     *  ���������: �������� ������ ���������� �� ������������ ��.
     *  ����������: ��, ������������� �� ������ ����������
     */
	void sendInitiatorRequestConsiderationMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

	/**
	 * ��: �������� ������ ���������� �� ������������ ��.
	 * ����������: ��, ������������� �� ������ ����������.
	 */
	void sendInitiatorRequestConsiderationForRMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

	/**
	 * ���������: �������� ������ ���������� �� ����� �� ������,
	 * ���������������� � ������������, ��������� �������������, �� ������������ ��.
	 * ����������: ��, ������������� �� ������ ����������.
	 */
	void sendConsolidatedRequestRefuseForRMMail(AuthInfo authInfo, ConsolidatedRequest consRequestRequest, String host);

    /**
     * �� ��������� �� �� (������ ����������)
      */
    void sendInitiatorRequestOnRevisionFromKMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

    /**
     * RFC 060727
     * ������ ��������� (������ ����������)
     */
    public void sendInitiatorRequestPerformMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

    /**
     * @deprecated
     * �� ������������ (����. ������)
     */
    void sendConsolidatedRequestConsiderationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * ��: ��������� ��� ������������ �� �������� ������������ ������ �� �����������.
     * ����������: ������������, ������������� �� ������ ����������.
     */
    void sendConsolidatedRequestOnVisaMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * �� ����������� (��.������)
     */
    void sendElementaryRequestOnVisaMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * ������������ (����. ������)
     */
    void sendConsolidatedRequestVisedMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * �� ����������� (����. ������)
     */
    void sendConsolidatedRequestOnRatificationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * �� ���������� (����. ������)
     */
    void sendConsolidatedRequestOnPerformanceMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * @deprecated
     * RFC 060727
     * ��: ���������� �� �������� ������������ ������ �� ����������.
     * ����������: ������������, ������������� �� ������ ����������.
     * ��������� � ������� sendConsolidatedRequestOnPerformanceMail
	 * ������ ��� private
     *
     * ����� ��������� �� ����� ��, ��������� ������ ����� ����������� ��
     *
     * �����
     * �� ���������� (����. ������)
     * 1. �����������
     * 2. ��� � ������ ����������
     */
    //void sendElementaryRequestOnPerformanceMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * RFC 060727
     * @deprecated
     * ��������� (��. ������)
     * ���������� �� ������������ ������
     */
    void sendElementaryRequestPerformMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * RFC 060727
     * �����������: ���������� ������������ ������.
     * ��� �������� ������������ ������ ������� � ������ "���������" ��� "����� � ������������"
     * ����� ���������� ��������� � ����������: ��, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestReadyToTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * �� ������� ������ ������ "�� ������������" � �������� �� ������������
     * ��: �������� ����������������� ������ �� ������������
     * ����������: ������������, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestOnTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * ��������� ������� ������ ������ "�� ������������" � "���������"
	 * � �������� �� ������������ ��� ���������� ������������, �� ����� ���� ������
     * ��: �������� ����������������� ������ �� ������������
     * ����������: ������������, PM, KM, ������������� �� ������ ����������
     */
    public void sendInitiatorRequestOnTestingMailAfterInitiatorRevision(AuthInfo authInfo, InitiatorRequest request, String host);

    /**
     * ��������� (����. ������)
     */
    void sendConsolidatedRequestPerformMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * �����������: �������� ����������������� ������ �� ���������.
     * ����������: ��, ��, ����������, ������������� �� ������ ����������
     */
    void sendConsolidatedRequestOnRevisionMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * ������� � ������ (����. ������)
     */
    void sendConsolidatedRequestOnAcceptToWorkMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * �������� ������ �� �������� � ������ ������������ ������
     * 1. �����������
     * @param authInfo
     * @param elementaryRequest
     * @param host
     */
    public void sendElementaryRequestOnAcceptToWorkMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * @deprecated
     * �������� ������ �� �������� � ������ ������ ����������
     * @param authInfo
     * @param initiatorRequest
     * @param host
     */
    public void sendInitiatorRequestOnAcceptToWorkMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);


    /**
     * @deprecated
     * �������� ����� ��� ��������� ����������� ����
     * ���������� ��� ����������� � ��������
     * @param authInfo
     * @param elementaryRequest
     */
//    public void sendOnChangePlanningDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest);
    
	/**
	 * �������� ����� ��� ��������� ����������� ����, ������� �������� �����
	 * ��������� ����, ��������� ����������� � ������ ����������
	 * ! ���������� �������������� ���� ���������� �������� � ���������� ����������� ���� ��� ���������
	 * �� ������ � ���������� ������� - sendOnChangePlanningDateMail
	 * ���������� ��� ����������� � ��������
	 * @param authInfo
	 * @param elementaryRequest
	 */
	public void sendOnChangePlanningDateAfterRequiredDateMail(AuthInfo authInfo,  ElementaryRequest elementaryRequest);

    /**
     * RFC 060727
     * ��� �������� ������������ ����������� ����, ����������� ��������� ���� (��������� �����������)
     * �������� ���������� ����� ���������� ���� ������ !!!!!
     * ����������: ��, ��, ����������, ������������� �� ������ ����������.
     *
     * ����� ������ ���, ����� ������� �����������
     * ���������� ��� ����������� � ��������
     * @param authInfo
     * @param cRequest
     */
    public void sendOnFactCompletedDateAfterRequiredDateMail(AuthInfo authInfo, ConsolidatedRequest cRequest);


//    /**
//	 * �������� ����� � ������ ���� ���������� ����� ������������ ��������� � ����������
//	 * ���������� ��� �� � ���������
//	 * @param authInfo
//	 * @param request
//	 */
//	public void sendMailOnConsiderInitiatorRequestByOrder(AuthInfo authInfo, InitiatorRequest request, Date firstTime);

	/**
	 * �������� ����� ���� ���������� ����� ��� ������ �� ������ �����������
	 * ���������� ��� ��, ��, ���������, �����������, ������������, �����������, ������������
	 * @param authInfo
	 * @param request
	 */
	public void sendInitiatorRequestOnCancelingByInitiator(AuthInfo authInfo, InitiatorRequest request);


}
