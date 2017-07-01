/*
 *  $Id: RTSMailServiceImpl.java,v 1.12 2007/06/23 15:11:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.rts.mail.service.impl;

import java.util.*;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.service.MailService;
import com.hps.july.mail.template.NRIMailTemplate;
import com.hps.july.mail.MailException;
import com.hps.april.auth.object.Person;
import com.hps.july.persistence.value.service.NamedValueService;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.mail.service.RTSMailService;
import com.hps.july.rts.mail.template.*;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.service.NotificationService;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.auth.object.RTSRole;

/**
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.12 $ $Date: 2007/06/23 15:11:50 $
 */
public class RTSMailServiceImpl implements RTSMailService {

    protected MailService mailService;
    protected NotificationService notificationService;
    protected RTSAuthService authService;
    protected RTSOperatorService operatorService;
    protected NamedValueService namedValueService;

    private HashMap accessedEmails;

    private boolean isTest = false;

    public RTSMailServiceImpl() {
		String isTestStr = null;
       	try {
			isTestStr = namedValueService.getNamedValueString("MAIL_TESTING");
    	} catch (Exception e) {
		}
        isTest = (isTestStr != null && !isTestStr.equals(""));
        System.out.println("RTS_TESTING ["+isTestStr +"] ["+isTest +"] ");

        accessedEmails = new HashMap();
        accessedEmails.put("vglushkov@partners.beeline.ru", "1");
        accessedEmails.put("abaykov@beeline.ru", "1");
        accessedEmails.put("sdrudenko@beeline.ru", "1");
    }

    public RTSAuthService getAuthService() {
        return authService;
    }
    public void setAuthService(RTSAuthService authService) {
        this.authService = authService;
    }
    public MailService getMailService() {
        return mailService;
    }
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    public NotificationService getNotificationService() {
        return notificationService;
    }
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public NamedValueService getNamedValueService() {
        return namedValueService;
    }

    public void setNamedValueService(NamedValueService namedValueService) {
        this.namedValueService = namedValueService;
    }

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public void sendRTSMail(AuthInfo authInfo, RTSMailTemplate mailTemplate) {
        MailMessage mailMessage = mailTemplate.createMailMessage();
        if(isTest) {
            mailMessage.setRecipients(null);
            mailMessage.setRecipientsCC(null);
            mailMessage.setRecipientsBCC(null);
            //��� �������� ����� ������ ���������� ������
            //�� ���� ������
            //ATushin@beeline.ru, ATikhomirov@beeline.ru,
            //vglushkov@partners.beeline.ru, DDneprov@partners.beeline.ru,
            //abaykov@beeline.ru
            Set keys = accessedEmails.keySet();
            for(Iterator i = keys.iterator(); i.hasNext();) {
                String mail = (String)i.next();
                mailMessage.addRecipient(mail);
            }
            mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST);
        } else {
            mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS);
        }
        //������ �������� ������ �������
        mailMessage.addRecipientBCC("vglushkov@partners.beeline.ru");
        mailMessage.addRecipientBCC("abaykov@beeline.ru");

        try {
//			JulyMailService service = JulyMailServiceFactory.createMailService(false);
//			System.out.println("-------------------------------!! "); 
//			service.sendRTSMail(authInfo, RTSMailService.SERVICE_NAME, mailMessage);
//			System.out.println("!!!-------------------------------!! ");
            mailService.sendMail(authInfo, RTSMailService.SERVICE_NAME, mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    /**
     * @deprecated RFC 060727
     * @param authInfo
     * @param request
     */
//    public void sendConsolidatedRequestDraftMail(AuthInfo authInfo, ConsolidatedRequest request){
//        sendRTSMail(authInfo, new ConsolidatedRequestDraftTemplate(request));
//    }

	/**
     *  RFC 060727
     *  ���������: �������� ������ ���������� �� ������������ ��.
     *  ����������: ��, ������������� �� ������ ����������
     */
	public void sendInitiatorRequestConsiderationMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        //������ ����������� �� ������ ���������� ��� ������ ������
		Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
        //������ ���� ��
        Collection persons = operatorService.findPersonByRole(RTSRole.TOP_MANAGER, null, null);
		persons.addAll(recipients);
		//���������� �������� ����������
		sendRTSMail(authInfo, new InitiatorRequestOnConsideration2KMTemplate(initiatorRequest, persons, host));
	}

	/**
	 * RFC 060727
	 * ��: �������� ������ ���������� �� ������������ ��.
	 * ����������: ��, ������������� �� ������ ����������.
	 */
	public void sendInitiatorRequestConsiderationForRMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
		//������ ����������� �� ������ ���������� ��� ������ ������
		Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
		//���������� ������������ ����������
		sendRTSMail(authInfo, new InitiatorRequestOnConsideration2RMTemplate(initiatorRequest, recipients, host));
	}

	/**
	 * RFC 060727
	 * ��: �������� ����������������� ������ �� ����� ��-�.
	 * ����������: ��, ������������� �� ������ ����������.
	 */
	public void sendConsolidatedRequestRefuseForRMMail(AuthInfo authInfo, ConsolidatedRequest consRequest, String host) {
        //���������� ������������� ���������
        ArrayList personList = new ArrayList();
        personList.add(consRequest.getRegMan());
		sendRTSMail(authInfo, new ConsolidatedRequestOnRefuse2RMTemplate(consRequest, personList, host));
	}

    /**
     * RFC 060727
     * ��: ���������� ������ ���������� �� ���������.
     * ����������: ���������
     */
    public void sendInitiatorRequestOnRevisionFromKMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        sendRTSMail(authInfo, new InitiatorRequestRevisionFromKMTemplate(initiatorRequest, null, host));
    }

    /**
     * @deprecated
     * �� ������������ PM (����. ������)
     *  1  ��������� ��������� (��) (����. ������)
     *  2. �� 1-�� ������ (����. ������)
     */
    public void sendConsolidatedRequestConsiderationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection recipients = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //���������� �� , ������������� ! � ���� �� ?
        //sendRTSMail(authInfo, new ConsolidatedRequestConsiderationMail2KMTemplate(consolidatedRequest, recipients, host));
        //���������� ��
        sendRTSMail(authInfo, new ConsolidatedRequestConsiderationMail2RMTemplate(consolidatedRequest, recipients, host));
    }

    /**
     * RFC 060727
     * ��: ��������� ��� ������������ �� �������� ������������ ������ �� �����������.
     * ����������: ������������, ������������� �� ������ ����������.
     */
    public void sendConsolidatedRequestOnVisaMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        //Collection recipients = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //������������ - �� ����
        //sendRTSMail(authInfo, new ConsolidatedRequestOnVisaTemplate(consolidatedRequest, recipients, host));
        //KM - �� ����
        //sendRTSMail(authInfo, new ConsolidatedRequestOnVisaTemplate2KM(consolidatedRequest, recipients, host));
        Collection elemRequests = consolidatedRequest.getElementaryRequests();
        if(!elemRequests.isEmpty()) {
            for(Iterator it = elemRequests.iterator(); it.hasNext();) {
                ElementaryRequest elemRequest = (ElementaryRequest) it.next();
                sendElementaryRequestOnVisaMail(authInfo, elemRequest, host);
            }
        }
    }

    /**
     * RFC 060727
     * ��: �������� ������������ ������� �� ����������� � ������ ����� ����������� !
     * ����������: �����������, ������������� �� ������ ����������.
     */
    public void sendElementaryRequestOnVisaMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
		if(elementaryRequest.getArendaType() == null || (elementaryRequest.getArendaType() != null && elementaryRequest.getArendaType().intValue() == 1)) {
			Collection personList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
			sendRTSMail(authInfo, new ElementaryRequestOnVisaTemplate(elementaryRequest, personList, host));
		} else {
			if(elementaryRequest.getArendaType() != null && elementaryRequest.getArendaType().intValue() == 2) {
				Collection visaPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
				sendRTSMail(authInfo, new ElementaryRequestOnVisaTemplate2Arenda(elementaryRequest, visaPersonList, host));
			}
		}

    }

    /**
     * RFC 060727
     * �����������: ����������� ������������ ������.
     * ����� ����������� ��������� � ����������: ��, ������������� �� ������ ����������.
     *
     * ����� ,������ �� ������������
     * ������������ (����. ������)
     * ����������
     * 1. ��������� ��������� (��) (����. ������)
     * 2. ������������(����. ������) ???
     * 3. ������������� ���������
     * 4. ��������� �����������
     */
    public void sendConsolidatedRequestVisedMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //������������
        //sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2Head(consolidatedRequest, visaPersonList, host));
        //��
        //sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2KM(consolidatedRequest, visaPersonList, host));
        //��
        sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2RM(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727
     * ��: �������� ����������������� ������ �� �����������.
     * ����������: �������������, ������������� �� ������ ����������.
     */
    public void sendConsolidatedRequestOnRatificationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //������������
        sendRTSMail(authInfo, new ConsolidatedRequestOnRatificationTemplate2Assertor(consolidatedRequest, visaPersonList, host));
        //�� - ��� ���� �����
        //sendRTSMail(authInfo, new ConsolidatedRequestOnRatificationTemplate2KM(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727
     * ������������: ����������� ����������������� ������.
     * ������� � ��������� �� ����������
     * ����������: ��, ������������� �� ������ ���������� � ������������ !
     * ���������� ��� ������� sendConsolidatedRequestOnPerformanceMail
     * � sendElementaryRequestOnPerformanceMail
     *
     */
    public void sendConsolidatedRequestOnPerformanceMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        boolean rmInExecutorRole = false;
        Person regManager = consolidatedRequest.getRegMan();
        //��� �����������
        Collection elemReqs = consolidatedRequest.getElementaryRequests();
        for(Iterator it = elemReqs.iterator(); it.hasNext();){
            ElementaryRequest elemReq = (ElementaryRequest) it.next();
			Collection visaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
			sendRTSMail(authInfo, new ElementaryRequestOnPerformanceTemplate(elemReq, visaPersonList, host));
			if(elemReq.getExecutor().equals(regManager)) {
				rmInExecutorRole = true;
			}
        }
        //Ec�� ������������ ������ ����������� ����������
        // �� �������� ������������ ����� �� ������
        // �� �������� ����������� � ��� ��� ���������, ����� �� ����������,
        // ��� ���������� ������ �����
        if(!rmInExecutorRole) {
            Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
            //��
            sendRTSMail(authInfo, new ConsolidatedRequestOnPerformanceTemplate2RM(consolidatedRequest, visaPersonList, host));
        }

    }


    /**
     * @deprecated
     * RFC 060727
     * ��: ���������� �� �������� ������������ ������ �� ����������.
     * ����������: ������������, ������������� �� ������ ����������.
     * ��������� � ������� sendConsolidatedRequestOnPerformanceMail
     *
     * ����� ��������� �� ����� ��, ��������� ������ ����� ����������� ��
     *
     * �����
     * �� ���������� (����. ������)
     * 1. �����������
     * 2. ��� � ������ ����������
     */
//    public void sendElementaryRequestOnPerformanceMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
//        Collection visaPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
//        sendRTSMail(authInfo, new ElementaryRequestOnPerformanceTemplate(elementaryRequest, visaPersonList, host));
//    }

    /**
     * RFC 060727
     * �����������: ���������� ������������ ������.
     * ��� �������� ������������ ������ ������� � ������ "���������" ��� "����� � ������������"
     * ����� ���������� ��������� � ����������: ��, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestReadyToTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        sendRTSMail(authInfo, new ConsolidatedRequestReadyToTestingTemplate(consolidatedRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     * �� ������� ������ ������ "�� ������������" � �������� �� ������������
     * ��: �������� ����������������� ������ �� ������������
     * ����������: ������������, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestOnTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        if(consolidatedRequest.getTestedBy()!=null)
            mailPersonList.add(consolidatedRequest.getTestedBy());
        else 
			mailPersonList.add(consolidatedRequest.getHead());
        // �� �����������, � ������������! (�������� � ������������ � RFC. ������)
        sendRTSMail(authInfo, new ConsolidatedRequestOnTestingTemplate(consolidatedRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     * �����������: �������� ����������������� ������ �� ���������.
     * ����������: ��, ��, ����������, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestOnRevisionMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        // PM
        sendRTSMail(authInfo, new ConsolidatedRequestOnRevisionTemplate2RM(consolidatedRequest, mailPersonList, host));
        boolean isRMInRoleHead = false;
        boolean isRMInRoleKM = false;

        if(consolidatedRequest.getRegMan().equals(consolidatedRequest.getHead())) {
            isRMInRoleHead = true;
        }
        if(consolidatedRequest.getRegMan().equals(consolidatedRequest.getKeyManager())) {
            isRMInRoleKM = true;
        }
        if(!isRMInRoleKM) {
            //e��� �� �� ���� � ���� �� ��� ��� � ������������
            // �� ���������� ���������� ������������
            // ���������
            //��� ��� �� ����� ������� �������� ��� ���� ������� ���������, �� ������� ��� mailPersonList = Collections.EMPTY_LIST;
            sendRTSMail(authInfo, new ConsolidatedRequestOnRevisionTemplate2KM(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
        if(!isRMInRoleHead) {
            //e��� �� �� ���� � ���� �� ��� ��� � ������������
            // �� ���������� ���������� ������������
            // ������������
            //��� ��� �� ����� ������� �������� ��� ���� ������� ���������, �� ������� ��� mailPersonList = Collections.EMPTY_LIST;
            sendRTSMail(authInfo, new ConsolidatedRequestOnRevisionTemplate2Head(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
    }

    /**
     * RFC 060727
     * �����������: ������� ������������ ����������������� ������.
     * ����������: ��, ����������, ������������� �� ������ ����������
     */
    public void sendConsolidatedRequestPerformMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //KM
        //sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2KM(consolidatedRequest, mailPersonList, host));
        //������������
        //sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2Head(consolidatedRequest, mailPersonList, host));
        //�������������
        //sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2Confirm(consolidatedRequest, mailPersonList, host));
        //RM
        sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2RM(consolidatedRequest, mailPersonList, host));

        Collection initRequests = consolidatedRequest.getInitiatorRequests();
        for(Iterator i = initRequests.iterator(); i.hasNext();) {
            InitiatorRequest request = (InitiatorRequest)i.next();
            sendInitiatorRequestPerformMail(authInfo, request, host);
        }

    }

    /**
     * RFC 060727
     *
     * �����������: ������� ������������ ����������������� ������.
     * ������ ��������� (������ ����������)
     * ���������� �� ������ ����������
     */
    public void sendInitiatorRequestPerformMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
        //���������� ����������
        sendRTSMail(authInfo, new InitiatorRequestPerformTemplate(initiatorRequest, recipients, host));
    }

    /**
     * RFC 060727
     * @deprecated
     * ��������� (��. ������)
     * ���������� �� ������������ ������
     */
    public void sendElementaryRequestPerformMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
//        Collection mailPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
//        sendRTSMail(authInfo, new ElementaryRequestPerformTemplate(elementaryRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     *
     * ���������: �������� � ������ ������ ���������� (����������������� � ���� ������������).
     * ����������: ��, ��, ������������, ������������� �� ������ ����������.
     *
     * @param authInfo
     * @param consolidatedRequest
     */
    public void sendConsolidatedRequestOnAcceptToWorkMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //��
        sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2RM(consolidatedRequest, visaPersonList, host));
        Person regManager = consolidatedRequest.getRegMan();
        Person keyManager = consolidatedRequest.getKeyManager();
        if(!regManager.equals(keyManager)) {
            //e��� �� �� ���� � ���� �� ��� ��� � ��
            // �� ���������� ���������� ��
            //KM
            sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2KM(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
        Collection elemRequests = consolidatedRequest.getElementaryRequests();
        for (Iterator i = elemRequests.iterator(); i.hasNext();) {
            ElementaryRequest curReq = (ElementaryRequest) i.next();
            Person executor = curReq.getExecutor();
            if(!(executor.equals(regManager) || executor.equals(keyManager))) {
                //e��� ����������� �� ���� � ���� �� ��� ��� � �� ��� ��
                //��������� �����������
                sendElementaryRequestOnAcceptToWorkMail(authInfo, curReq, host);
            }
        }
        //������������
        //sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2Head(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727

     * �������� ������ �� �������� � ������ ������������ ������
     * 1. �����������
     * @param authInfo
     * @param elementaryRequest
     * @param host
     */
    public void sendElementaryRequestOnAcceptToWorkMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
        sendRTSMail(authInfo, new ElementaryRequestOnAcceptToWorkTemplate(elementaryRequest, visaPersonList, host));
    }

    /**
     * @deprecated
     * �������� ������ �� �������� � ������ ������ ����������
     * @param authInfo
     * @param initiatorRequest
     * @param host
     */
    public void sendInitiatorRequestOnAcceptToWorkMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, initiatorRequest.getNumber());
        sendRTSMail(authInfo, new InitiatorRequestOnAcceptToWorkTemplate(initiatorRequest, visaPersonList, host));
    }




    /**
     * @deprecated
     *
     * �������� ����� ��� ��������� ����������� ����
     * ���������� ��� ����������� � ��������
     * @param authInfo
     * @param elementaryRequest
     */
//    public void sendOnChangePlanningDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest) {
//
//        ConsolidatedRequest cRequest = elementaryRequest.getConsolidatedRequest();
//        //�� ��������� ������� ������� �� ���� ������� ����� ����������� � ������� ����������
//        //�������� � ���� �����
//        ArrayList visaPersonList = new ArrayList();
//        Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
//        //�������� ������ ���������� ���� ������
//        visaPersonList.addAll(cVisaPersonList);
//        //�������� � ������ ���� ���� ���������� ��������� ���������� �� �� ������������, ������������
//        visaPersonList.add(cRequest.getKeyManager());
//        visaPersonList.add(cRequest.getRegMan());
//        visaPersonList.add(cRequest.getConfirmativeMan());
//        visaPersonList.add(cRequest.getHead());
//        Collection initReqs = cRequest.getInitiatorRequests();
//        for(Iterator it = initReqs.iterator(); it.hasNext();){ // ������������� � ���� �� ������ "�� ���������"
//            InitiatorRequest initReq = (InitiatorRequest) it.next();
//            //�������� ������ ���������� ������ ����������
//            Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
//            visaPersonList.addAll(iVisaPersonList);
//            //��������� ����������
//            visaPersonList.add(initReq.getInitiatorPerson());
//        }
//        Collection elemReqs = cRequest.getElementaryRequests();
//        for(Iterator it = elemReqs.iterator(); it.hasNext();){ // ������������� � ���� �� ������ "�� ���������"
//            ElementaryRequest elemReq = (ElementaryRequest) it.next();
//            //�������� ������ ���������� ���� ������
//            Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
//            visaPersonList.addAll(eVisaPersonList);
//            //��������� �����������
//            visaPersonList.add(elemReq.getExecutor());
//        }
//        sendRTSMail(authInfo, new OnChangePlanningDateTemplate(cRequest, elementaryRequest, visaPersonList));
//    }

    /**
     * RFC 060727
     *
     * �������� ����� ��� ��������� ����������� ����, ������� �������� �����
     * ��������� ����, ��������� ����������� � ������ ����������
     *
     * ! ���������� �������������� ���� ���������� �������� � ���������� ����������� ���� ��� ���������
     * �� ������ � ���������� ������� - sendOnChangePlanningDateMail
     * ����������: ��, ��, ����������, ������������� �� ������ ����������
     *
     * ����� ������ ���, ����� ������� �����������
     * ���������� ��� ����������� � ��������
     * @param authInfo
     * @param elementaryRequest
     */
    public void sendOnChangePlanningDateAfterRequiredDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest){

            ConsolidatedRequest cRequest = elementaryRequest.getConsolidatedRequest();
            //�� ��������� ������� ������� �� ���� ������� ����� ����������� � ������� ����������
            //�������� � ���� �����
            ArrayList visaPersonList = new ArrayList();
            ArrayList personList = new ArrayList();
            Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
            //�������� ������ ���������� ���� ������
            visaPersonList.addAll(cVisaPersonList);
            //�������� � ������ ���� ���� ���������� ��������� ���������� �� �� ������������, ������������
            //� ��������� �� ��������� � ������
            if(!personList.contains(cRequest.getKeyManager())) {
                personList.add(cRequest.getKeyManager());
            }
            if(!personList.contains(cRequest.getRegMan())) {
                personList.add(cRequest.getRegMan());
            }
            Collection initReqs = cRequest.getInitiatorRequests();

            Date completeDate = null;
            InitiatorRequest initiatorRequest = null;
            for(Iterator it = initReqs.iterator(); it.hasNext();){
                InitiatorRequest initReq = (InitiatorRequest) it.next();
                //�������� ������ ���������� ������ ����������
                Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
                for(Iterator ivp = iVisaPersonList.iterator(); ivp.hasNext();) {
                    Person person = (Person)ivp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //��������� ����������
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
                // ��������� ������ ����������, ��������� ���� ������� (����� �������)
                // ���� ��������� ����������� �����, ������������� ������������
                if(completeDate == null || initReq.getCompleteDate().after(completeDate)) {
                    completeDate = initReq.getCompleteDate();
                    initiatorRequest = initReq;
                }
            }

            Collection elemReqs = cRequest.getElementaryRequests();
            for(Iterator it = elemReqs.iterator(); it.hasNext();){ // ������������� � ���� �� ������ "�� ���������"
                ElementaryRequest elemReq = (ElementaryRequest) it.next();
                //�������� ������ ���������� ���� ������
                Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
                for(Iterator evp = eVisaPersonList.iterator(); evp.hasNext();) {
                    Person person = (Person)evp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //��������� �����������
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
            }

            sendRTSMail(authInfo, new OnChangePlanningDateAfterRequiredDateTemplate(cRequest, elementaryRequest, initiatorRequest, personList, visaPersonList));
        }

    /**
     * RFC 060727
     * ��� �������� ������������ ����������� ����, ����������� ��������� ���� (��������� �����������)
     * �������� ���������� ����� ���������� ���� ������ !!!!!
     * ����������: ��, ��, ����������, ������������� �� ������ ����������.
     *
     * @param authInfo
     * @param cRequest
     */
    public void sendOnFactCompletedDateAfterRequiredDateMail(AuthInfo authInfo, ConsolidatedRequest cRequest) {

            //�� ��������� ������� ������� �� ���� ������� ����� ����������� � ������� ����������
            //�������� � ���� �����
            ArrayList visaPersonList = new ArrayList();
            ArrayList personList = new ArrayList();
            Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
            //�������� ������ ���������� ���� ������
            visaPersonList.addAll(cVisaPersonList);
            //�������� � ������ ���� ���� ���������� ��������� ���������� �� ��
            //� ��������� �� ��������� � ������
            if(!personList.contains(cRequest.getKeyManager())) {
                personList.add(cRequest.getKeyManager());
            }
            if(!personList.contains(cRequest.getRegMan())) {
                personList.add(cRequest.getRegMan());
            }
            Collection initReqs = cRequest.getInitiatorRequests();

            Date completeDate = null;
            InitiatorRequest initiatorRequest = null;
            for(Iterator it = initReqs.iterator(); it.hasNext();){
                InitiatorRequest initReq = (InitiatorRequest) it.next();
                //�������� ������ ���������� ������ ����������
                Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
                for(Iterator ivp = iVisaPersonList.iterator(); ivp.hasNext();) {
                    Person person = (Person)ivp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //��������� ����������
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
                // ��������� ������ ����������, ��������� ���� ������� (����� �������)
                // ���� ��������� ����������� �����, ������������� ������������
                if(completeDate == null || initReq.getCompleteDate().after(completeDate)) {
                    completeDate = initReq.getCompleteDate();
                    initiatorRequest = initReq;
                }
            }

            Collection elemReqs = cRequest.getElementaryRequests();
            for(Iterator it = elemReqs.iterator(); it.hasNext();) {
                ElementaryRequest elemReq = (ElementaryRequest) it.next();
                //�������� ������ ���������� ���� ������
                Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
                for(Iterator evp = eVisaPersonList.iterator(); evp.hasNext();) {
                    Person person = (Person)evp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //��������� �����������
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
            }

            sendRTSMail(authInfo, new OnFactCompletedDateAfterRequiredDateTemplate(cRequest, initiatorRequest, personList, visaPersonList));
        }

//    /**
//  	���������� � RTSREglamentMAilSErvice
//     * �������� ����� � ������ ���� ���������� ����� ������������ ��������� � ����������
//     * ���������� ��� �� � ���������
//     * @param authInfo
//     * @param request
//     */
//    public void sendMailOnConsiderInitiatorRequestByOrder(AuthInfo authInfo, InitiatorRequest request, Date firstTime) {
//        Collection persons = operatorService.findPersonByRole(RTSRole.TOP_MANAGER, null, null);
//        //���������� �������� ����������
//
//        //������� ������ �� �� ������ �����
//
//        sendRTSMail(authInfo, new ConsiderInitialRequestByKMTemplate(request, persons, firstTime ,null));
//    }

	/**
     * RFC 060727
     * ���������: ����������/������������� ������ ����������.
     * ����������: ��, ��, ������������, �������������, ������������, ������������� �� ������ ����������.
	 * @param authInfo
	 * @param request
	 */
	public void sendInitiatorRequestOnCancelingByInitiator(AuthInfo authInfo, InitiatorRequest request){
		
		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		//�� ��������� ������� ������� �� ���� ������� ����� ����������� � ������� ����������
		//�������� � ���� �����
		ArrayList visaPersonList = new ArrayList();
		ArrayList personList = new ArrayList();
		if(cRequest == null) {
			personList.add(request.getInitiatorPerson());
		} else {
			Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
			//�������� ������ ���������� ���� ������
			visaPersonList.addAll(cVisaPersonList);
			//�������� � ������ ���� ���� ���������� ��������� ���������� �� �� ������������, ������������
            //� ��������� �� ��������� � ������
            if(!personList.contains(cRequest.getKeyManager())) {
                personList.add(cRequest.getKeyManager());
            }
            if(!personList.contains(cRequest.getRegMan())) {
                personList.add(cRequest.getRegMan());
            }
            if(!personList.contains(cRequest.getConfirmativeMan())) {
                personList.add(cRequest.getConfirmativeMan());
            }
            if(!personList.contains(cRequest.getHead())) {
                personList.add(cRequest.getHead());
            }
            if(!personList.contains(cRequest.getTestedBy())) {
                personList.add(cRequest.getTestedBy());
            }
        	//���� �� ��������� - ���������� ����������� ���� �� ������ ����������

			Collection initReqs = cRequest.getInitiatorRequests();
			for(Iterator it = initReqs.iterator(); it.hasNext();){
				InitiatorRequest initReq = (InitiatorRequest) it.next();
				//�������� ������ ���������� ������ ����������
				Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
				visaPersonList.addAll(iVisaPersonList);
				//��������� ����������
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
			}
			Collection elemReqs = cRequest.getElementaryRequests();
			for(Iterator it = elemReqs.iterator(); it.hasNext();){ // ������������� � ���� �� ������ "�� ���������"
				ElementaryRequest elemReq = (ElementaryRequest) it.next();
				//�������� ������ ���������� ���� ������
				Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
				visaPersonList.addAll(eVisaPersonList);
				//��������� �����������
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
			}
		}
		sendRTSMail(authInfo, new InitiatorRequestOnCancelingByInitiatorTemplate(request, personList, visaPersonList));
	}

	/**
	 * RFC 060727
	 * ��������� ������� ������ ������ "�� ������������" � "���������"
	 * � �������� �� ������������ ��� ���������� ������������, �� ����� ���� ������
	 * ��: �������� ����������������� ������ �� ������������
	 * ����������: ������������, PM, KM, ������������� �� ������ ����������
	 * � ������ ����������� ���� � ����������������� ������ ��������� ������ �����������
	 */
	public void sendInitiatorRequestOnTestingMailAfterInitiatorRevision
			(AuthInfo authInfo, InitiatorRequest request, String host) {

		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		ArrayList visaPersonList = new ArrayList();
		ArrayList personList4ConsReq = new ArrayList();
		if(cRequest != null) {
			Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
			//�������� ������ ���������� ���� ������
			visaPersonList.addAll(cVisaPersonList);
			//�������� � ������ ���� ���� ���������� ��������� ���������� �� �� ������������, ������������
            //� ��������� �� ��������� � ������
            if(!personList4ConsReq.contains(cRequest.getKeyManager())) {
                personList4ConsReq.add(cRequest.getKeyManager());
				sendRTSMail(authInfo, new ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2KM(request, cRequest, visaPersonList, host));
            }
            if(!personList4ConsReq.contains(cRequest.getRegMan())) {
				sendRTSMail(authInfo, new ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2RM(request, cRequest, visaPersonList, host));
            }
        	//���� �� ��������� - ���������� ����������� ���� �� ������ ����������

			Collection initReqs = cRequest.getInitiatorRequests();
			for(Iterator it = initReqs.iterator(); it.hasNext();) {
				InitiatorRequest initReq = (InitiatorRequest) it.next();
				//�������� �� ������� ������ ������ ����������� � ���������� �����������������
				//�� ��� �� ���� ���������
				if(initReq.getRequestId().intValue() != request.getRequestId().intValue()) {
					//�������� ������ ���������� ������ ����������
					Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
					sendRTSMail(authInfo, new InitiatorRequestOnTestingMailAfterInitiatorRevisionTemplate(request, iVisaPersonList, host));
				}
			}
		}

	}

	//-- private methods
    private Collection findVisaPersonList(AuthInfo authInfo, String requestNumber) {
        Person currentPerson = authService.getPerson(authInfo);
        return notificationService.findNotificationList(requestNumber, currentPerson);
    }
}
