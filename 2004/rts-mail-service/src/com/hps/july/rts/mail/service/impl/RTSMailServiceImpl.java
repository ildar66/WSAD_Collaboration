/*
 *  $Id: RTSMailServiceImpl.java,v 1.12 2007/06/23 15:11:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
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
            //для тестовых целей письма высылаются только
            //на след адреса
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
        //всегда посылаем письма админам
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
     *  Инициатор: отправка Заявки инициатора на рассмотрение КМ.
     *  Оповещение: КМ, пользователей из списка оповещения
     */
	public void sendInitiatorRequestConsiderationMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        //список оповещаемых из списка оповещения для данной завяки
		Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
        //список всех КМ
        Collection persons = operatorService.findPersonByRole(RTSRole.TOP_MANAGER, null, null);
		persons.addAll(recipients);
		//оповещение Ключевых менеджеров
		sendRTSMail(authInfo, new InitiatorRequestOnConsideration2KMTemplate(initiatorRequest, persons, host));
	}

	/**
	 * RFC 060727
	 * КМ: отправка Заявки инициатора на рассмотрение РМ.
	 * Оповещение: РМ, пользователей из списка оповещения.
	 */
	public void sendInitiatorRequestConsiderationForRMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
		//список оповещаемых из списка оповещения для данной завяки
		Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
		//оповещение региональных менеджеров
		sendRTSMail(authInfo, new InitiatorRequestOnConsideration2RMTemplate(initiatorRequest, recipients, host));
	}

	/**
	 * RFC 060727
	 * КМ: отправка Консолидированной Заявки на отказ РМ-у.
	 * Оповещение: РМ, пользователей из списка оповещения.
	 */
	public void sendConsolidatedRequestRefuseForRMMail(AuthInfo authInfo, ConsolidatedRequest consRequest, String host) {
        //оповещение регионального менеджера
        ArrayList personList = new ArrayList();
        personList.add(consRequest.getRegMan());
		sendRTSMail(authInfo, new ConsolidatedRequestOnRefuse2RMTemplate(consRequest, personList, host));
	}

    /**
     * RFC 060727
     * КМ: отправляет заявку инициатора на доработку.
     * Оповещение: Инициатор
     */
    public void sendInitiatorRequestOnRevisionFromKMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        sendRTSMail(authInfo, new InitiatorRequestRevisionFromKMTemplate(initiatorRequest, null, host));
    }

    /**
     * @deprecated
     * На рассмотрении PM (Конс. заявка)
     *  1  Ключевому менеджеру (КМ) (конс. заявка)
     *  2. РМ 1-го уровня (конс. заявка)
     */
    public void sendConsolidatedRequestConsiderationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection recipients = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //оповещение КМ , подтверждение ! а надо ли ?
        //sendRTSMail(authInfo, new ConsolidatedRequestConsiderationMail2KMTemplate(consolidatedRequest, recipients, host));
        //оповещение РМ
        sendRTSMail(authInfo, new ConsolidatedRequestConsiderationMail2RMTemplate(consolidatedRequest, recipients, host));
    }

    /**
     * RFC 060727
     * РМ: оповещеня все исполнителей об отправке элементарных заявок на визирование.
     * Оповещение: исполнителей, пользователей из списка оповещения.
     */
    public void sendConsolidatedRequestOnVisaMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        //Collection recipients = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //руководителю - не надо
        //sendRTSMail(authInfo, new ConsolidatedRequestOnVisaTemplate(consolidatedRequest, recipients, host));
        //KM - не надо
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
     * РМ: отправка Элементарной заявоки на визирование в случае смены исполнителя !
     * Оповещение: исполнителя, пользователей из списка оповещения.
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
     * Исполнители: визирование элементарных заявок.
     * После визирования последней – оповещение: РМ, пользователей из списка оповещения.
     *
     * ранее ,теперь не используется
     * Завизирована (Конс. заявка)
     * получатели
     * 1. Ключевому менеджеру (КМ) (конс. заявка)
     * 2. Руководителю(конс. заявка) ???
     * 3. Региональному менеджеру
     * 4. Участники визирования
     */
    public void sendConsolidatedRequestVisedMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //руководитель
        //sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2Head(consolidatedRequest, visaPersonList, host));
        //КМ
        //sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2KM(consolidatedRequest, visaPersonList, host));
        //РМ
        sendRTSMail(authInfo, new ConsolidatedRequestVisedTemplate2RM(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727
     * РМ: отправка Консолидированной заявки на утверждение.
     * Оповещение: Утверждающего, пользователей из списка оповещения.
     */
    public void sendConsolidatedRequestOnRatificationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //утверждающий
        sendRTSMail(authInfo, new ConsolidatedRequestOnRatificationTemplate2Assertor(consolidatedRequest, visaPersonList, host));
        //КМ - это было ранее
        //sendRTSMail(authInfo, new ConsolidatedRequestOnRatificationTemplate2KM(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727
     * Утверждающий: утверждение Консолидированной заявки.
     * перевод в состоянии На исполнении
     * Оповещение: РМ, пользователей из списка оповещения и исполнителей !
     * Объединяем два ментода sendConsolidatedRequestOnPerformanceMail
     * и sendElementaryRequestOnPerformanceMail
     *
     */
    public void sendConsolidatedRequestOnPerformanceMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        boolean rmInExecutorRole = false;
        Person regManager = consolidatedRequest.getRegMan();
        //все исполнители
        Collection elemReqs = consolidatedRequest.getElementaryRequests();
        for(Iterator it = elemReqs.iterator(); it.hasNext();){
            ElementaryRequest elemReq = (ElementaryRequest) it.next();
			Collection visaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
			sendRTSMail(authInfo, new ElementaryRequestOnPerformanceTemplate(elemReq, visaPersonList, host));
			if(elemReq.getExecutor().equals(regManager)) {
				rmInExecutorRole = true;
			}
        }
        //Ecли пользователь являсь регионльным менеджером
        // ен является исполнителем одной из заявок
        // то высылаем опопвещение и ему как менеджеру, иначе не опопвещаем,
        // для уменьшения потока писем
        if(!rmInExecutorRole) {
            Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
            //РМ
            sendRTSMail(authInfo, new ConsolidatedRequestOnPerformanceTemplate2RM(consolidatedRequest, visaPersonList, host));
        }

    }


    /**
     * @deprecated
     * RFC 060727
     * РМ: оповещение об отправке Элементарных заявок на исполнение.
     * Оповещение: исполнителей, пользователей из списка оповещения.
     * объединен с методом sendConsolidatedRequestOnPerformanceMail
     *
     * метод оповещает по одной ЭЗ, выполянть должен после утверждения КЗ
     *
     * ранее
     * На исполнении (Элем. заявка)
     * 1. Исполнитель
     * 2. все в списке оповещения
     */
//    public void sendElementaryRequestOnPerformanceMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
//        Collection visaPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
//        sendRTSMail(authInfo, new ElementaryRequestOnPerformanceTemplate(elementaryRequest, visaPersonList, host));
//    }

    /**
     * RFC 060727
     * Исполнитель: исполнение элементарной заявки.
     * Все входящие элементарные заявки перешли в статус "Исполнена" или "Гтовы к тестированию"
     * После исполнения последней – оповещение: РМ, пользователей из списка оповещения
     */
    public void sendConsolidatedRequestReadyToTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        sendRTSMail(authInfo, new ConsolidatedRequestReadyToTestingTemplate(consolidatedRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     * РМ изменил статус заявки "на тестировании" и отправил ее тестирующему
     * РМ: отправка Консолидированной заявки на тестирование
     * Оповещение: Тестировщика, пользователей из списка оповещения
     */
    public void sendConsolidatedRequestOnTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        if(consolidatedRequest.getTestedBy()!=null)
            mailPersonList.add(consolidatedRequest.getTestedBy());
        else 
			mailPersonList.add(consolidatedRequest.getHead());
        // Не Тестирующий, а Руководитель! (Изменено в соответствии с RFC. Байков)
        sendRTSMail(authInfo, new ConsolidatedRequestOnTestingTemplate(consolidatedRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     * Тестировщик: отправка Консолидированной заявки на доработку.
     * Оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения
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
            //eсли РМ не одно и тоже ли что что и Руководитель
            // то отправляем оповещение руководителю
            // Ключевому
            //так как по этому спискку рассылки уже были посланы сообщения, то очищаем его mailPersonList = Collections.EMPTY_LIST;
            sendRTSMail(authInfo, new ConsolidatedRequestOnRevisionTemplate2KM(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
        if(!isRMInRoleHead) {
            //eсли РМ не одно и тоже ли что что и Руководитель
            // то отправляем оповещение руководителю
            // Руководителю
            //так как по этому спискку рассылки уже были посланы сообщения, то очищаем его mailPersonList = Collections.EMPTY_LIST;
            sendRTSMail(authInfo, new ConsolidatedRequestOnRevisionTemplate2Head(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
    }

    /**
     * RFC 060727
     * Тестировщик: отметка тестирования Консолидированной заявки.
     * Оповещение: РМ, Инициатора, пользователей из списка оповещения
     */
    public void sendConsolidatedRequestPerformMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection mailPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //KM
        //sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2KM(consolidatedRequest, mailPersonList, host));
        //руководитель
        //sendRTSMail(authInfo, new ConsolidatedRequestPerformTemplate2Head(consolidatedRequest, mailPersonList, host));
        //утверждающему
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
     * Тестировщик: отметка тестирования Консолидированной заявки.
     * Заявка исполнена (заявка инициатора)
     * Оповещение по завяке инициатора
     */
    public void sendInitiatorRequestPerformMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host) {
        Collection recipients = findVisaPersonList(authInfo, initiatorRequest.getNumber());
        //оповещение Инициатора
        sendRTSMail(authInfo, new InitiatorRequestPerformTemplate(initiatorRequest, recipients, host));
    }

    /**
     * RFC 060727
     * @deprecated
     * Исполнена (Эл. заявка)
     * Оповещение по элементарной заявке
     */
    public void sendElementaryRequestPerformMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host) {
//        Collection mailPersonList = findVisaPersonList(authInfo, elementaryRequest.getNumber());
//        sendRTSMail(authInfo, new ElementaryRequestPerformTemplate(elementaryRequest, mailPersonList, host));
    }

    /**
     * RFC 060727
     *
     * Инициатор: принятие в работу Заявки инициатора (Консолидированной и всех элементарных).
     * Оповещение: КМ, РМ, исполнителей, пользователей из списка оповещения.
     *
     * @param authInfo
     * @param consolidatedRequest
     */
    public void sendConsolidatedRequestOnAcceptToWorkMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host) {
        Collection visaPersonList = findVisaPersonList(authInfo, consolidatedRequest.getNumber());
        //РМ
        sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2RM(consolidatedRequest, visaPersonList, host));
        Person regManager = consolidatedRequest.getRegMan();
        Person keyManager = consolidatedRequest.getKeyManager();
        if(!regManager.equals(keyManager)) {
            //eсли КМ не одно и тоже ли что что и РМ
            // то отправляем оповещение КМ
            //KM
            sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2KM(consolidatedRequest, Collections.EMPTY_LIST, host));
        }
        Collection elemRequests = consolidatedRequest.getElementaryRequests();
        for (Iterator i = elemRequests.iterator(); i.hasNext();) {
            ElementaryRequest curReq = (ElementaryRequest) i.next();
            Person executor = curReq.getExecutor();
            if(!(executor.equals(regManager) || executor.equals(keyManager))) {
                //eсли исполнитель не одно и тоже ли что что и РМ или КМ
                //оповещаем исполнителя
                sendElementaryRequestOnAcceptToWorkMail(authInfo, curReq, host);
            }
        }
        //руководителю
        //sendRTSMail(authInfo, new ConsolidatedRequestOnAcceptToWorkTemplate2Head(consolidatedRequest, visaPersonList, host));
    }

    /**
     * RFC 060727

     * отправка письма по принятию в работу элементарной заявки
     * 1. Исполнитель
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
     * отправка письма по принятию в работу заявки инициатора
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
     * отправка писем при изменении планируемой даты
     * Получатели все участвующие в процессе
     * @param authInfo
     * @param elementaryRequest
     */
//    public void sendOnChangePlanningDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest) {
//
//        ConsolidatedRequest cRequest = elementaryRequest.getConsolidatedRequest();
//        //во избежании лишенго прохода по всем заявкам выбор получателей и списокв оповещения
//        //проводим в одно цикле
//        ArrayList visaPersonList = new ArrayList();
//        Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
//        //добавить список оповещения конс заявки
//        visaPersonList.addAll(cVisaPersonList);
//        //добавить в список всех кому необходимо отправить оповещенеи КМ РМ утверждающий, руководитель
//        visaPersonList.add(cRequest.getKeyManager());
//        visaPersonList.add(cRequest.getRegMan());
//        visaPersonList.add(cRequest.getConfirmativeMan());
//        visaPersonList.add(cRequest.getHead());
//        Collection initReqs = cRequest.getInitiatorRequests();
//        for(Iterator it = initReqs.iterator(); it.hasNext();){ // Устанавливаем у всех ЗИ статус "На доработке"
//            InitiatorRequest initReq = (InitiatorRequest) it.next();
//            //добавить список оповещения заявки инициатора
//            Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
//            visaPersonList.addAll(iVisaPersonList);
//            //добавляем инициатроа
//            visaPersonList.add(initReq.getInitiatorPerson());
//        }
//        Collection elemReqs = cRequest.getElementaryRequests();
//        for(Iterator it = elemReqs.iterator(); it.hasNext();){ // Устанавливаем у всех ЭЗ статус "На доработке"
//            ElementaryRequest elemReq = (ElementaryRequest) it.next();
//            //добавить список оповещения элем заявки
//            Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
//            visaPersonList.addAll(eVisaPersonList);
//            //добавляем исполнителя
//            visaPersonList.add(elemReq.getExecutor());
//        }
//        sendRTSMail(authInfo, new OnChangePlanningDateTemplate(cRequest, elementaryRequest, visaPersonList));
//    }

    /**
     * RFC 060727
     *
     * отправка писем при изменении планируемой даты, которая наступит позже
     * требуемой даты, указанной инициатором в заявке инициатора
     *
     * ! Необходимо информаировать всех участников процесса о превышении планируемой даты над требуемой
     * Не путать с предыдущим методом - sendOnChangePlanningDateMail
     * оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения
     *
     * ранее терпеь нет, после удалить комментарии
     * Получатели все участвующие в процессе
     * @param authInfo
     * @param elementaryRequest
     */
    public void sendOnChangePlanningDateAfterRequiredDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest){

            ConsolidatedRequest cRequest = elementaryRequest.getConsolidatedRequest();
            //во избежании лишенго прохода по всем заявкам выбор получателей и списокв оповещения
            //проводим в одно цикле
            ArrayList visaPersonList = new ArrayList();
            ArrayList personList = new ArrayList();
            Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
            //добавить список оповещения конс заявки
            visaPersonList.addAll(cVisaPersonList);
            //добавить в список всех кому необходимо отправить оповещенеи КМ РМ утверждающий, руководитель
            //и проверяем на включение в список
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
                //добавить список оповещения заявки инициатора
                Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
                for(Iterator ivp = iVisaPersonList.iterator(); ivp.hasNext();) {
                    Person person = (Person)ivp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //добавляем инициатроа
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
                // Вычисляем Заявку иницитаора, требуемая дата которой (самая дальняя)
                // была перекрыта планируемой датой, проставленной исполнителем
                if(completeDate == null || initReq.getCompleteDate().after(completeDate)) {
                    completeDate = initReq.getCompleteDate();
                    initiatorRequest = initReq;
                }
            }

            Collection elemReqs = cRequest.getElementaryRequests();
            for(Iterator it = elemReqs.iterator(); it.hasNext();){ // Устанавливаем у всех ЭЗ статус "На доработке"
                ElementaryRequest elemReq = (ElementaryRequest) it.next();
                //добавить список оповещения элем заявки
                Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
                for(Iterator evp = eVisaPersonList.iterator(); evp.hasNext();) {
                    Person person = (Person)evp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //добавляем исполнителя
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
            }

            sendRTSMail(authInfo, new OnChangePlanningDateAfterRequiredDateTemplate(cRequest, elementaryRequest, initiatorRequest, personList, visaPersonList));
        }

    /**
     * RFC 060727
     * При указании Исполнителем фактической даты, превышающей требуемую дату (указанную инициатором)
     * проверка происходит после исполнения всех заявок !!!!!
     * оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения.
     *
     * @param authInfo
     * @param cRequest
     */
    public void sendOnFactCompletedDateAfterRequiredDateMail(AuthInfo authInfo, ConsolidatedRequest cRequest) {

            //во избежании лишенго прохода по всем заявкам выбор получателей и списокв оповещения
            //проводим в одно цикле
            ArrayList visaPersonList = new ArrayList();
            ArrayList personList = new ArrayList();
            Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
            //добавить список оповещения конс заявки
            visaPersonList.addAll(cVisaPersonList);
            //добавить в список всех кому необходимо отправить оповещенеи КМ РМ
            //и проверяем на включение в список
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
                //добавить список оповещения заявки инициатора
                Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
                for(Iterator ivp = iVisaPersonList.iterator(); ivp.hasNext();) {
                    Person person = (Person)ivp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //добавляем инициатроа
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
                // Вычисляем Заявку иницитаора, требуемая дата которой (самая дальняя)
                // была перекрыта планируемой датой, проставленной исполнителем
                if(completeDate == null || initReq.getCompleteDate().after(completeDate)) {
                    completeDate = initReq.getCompleteDate();
                    initiatorRequest = initReq;
                }
            }

            Collection elemReqs = cRequest.getElementaryRequests();
            for(Iterator it = elemReqs.iterator(); it.hasNext();) {
                ElementaryRequest elemReq = (ElementaryRequest) it.next();
                //добавить список оповещения элем заявки
                Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
                for(Iterator evp = eVisaPersonList.iterator(); evp.hasNext();) {
                    Person person = (Person)evp.next();
                    if(!visaPersonList.contains(person)) {
                        visaPersonList.add(person);
                    }
                }
                //добавляем исполнителя
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
            }

            sendRTSMail(authInfo, new OnFactCompletedDateAfterRequiredDateTemplate(cRequest, initiatorRequest, personList, visaPersonList));
        }

//    /**
//  	Перенесено в RTSREglamentMAilSErvice
//     * отправка писем в случае если просрочено время рассмотрения указанное в регламенте
//     * Получатели все КМ и инициатор
//     * @param authInfo
//     * @param request
//     */
//    public void sendMailOnConsiderInitiatorRequestByOrder(AuthInfo authInfo, InitiatorRequest request, Date firstTime) {
//        Collection persons = operatorService.findPersonByRole(RTSRole.TOP_MANAGER, null, null);
//        //оповещение Ключевых менеджеров
//
//        //обновим заявку из за потери сесси
//
//        sendRTSMail(authInfo, new ConsiderInitialRequestByKMTemplate(request, persons, firstTime ,null));
//    }

	/**
     * RFC 060727
     * Инициатор: отклонение/аннулирование Заявки инициатора.
     * Оповещение: КМ, РМ, Руководителя, Утверждающего, Тестировщика, пользователей из списка оповещения.
	 * @param authInfo
	 * @param request
	 */
	public void sendInitiatorRequestOnCancelingByInitiator(AuthInfo authInfo, InitiatorRequest request){
		
		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		//во избежании лишенго прохода по всем заявкам выбор получателей и списокв оповещения
		//проводим в одно цикле
		ArrayList visaPersonList = new ArrayList();
		ArrayList personList = new ArrayList();
		if(cRequest == null) {
			personList.add(request.getInitiatorPerson());
		} else {
			Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
			//добавить список оповещения конс заявки
			visaPersonList.addAll(cVisaPersonList);
			//добавить в список всех кому необходимо отправить оповещенеи КМ РМ утверждающий, руководитель
            //и проверяем на включение в список
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
        	//Пока не открываем - инициаторы добавляются сами по заявке инициатора

			Collection initReqs = cRequest.getInitiatorRequests();
			for(Iterator it = initReqs.iterator(); it.hasNext();){
				InitiatorRequest initReq = (InitiatorRequest) it.next();
				//добавить список оповещения заявки инициатора
				Collection iVisaPersonList = findVisaPersonList(authInfo, initReq.getNumber());
				visaPersonList.addAll(iVisaPersonList);
				//добавляем инициатроа
                if(!personList.contains(initReq.getInitiatorPerson())) {
                    personList.add(initReq.getInitiatorPerson());
                }
			}
			Collection elemReqs = cRequest.getElementaryRequests();
			for(Iterator it = elemReqs.iterator(); it.hasNext();){ // Устанавливаем у всех ЭЗ статус "На доработке"
				ElementaryRequest elemReq = (ElementaryRequest) it.next();
				//добавить список оповещения элем заявки
				Collection eVisaPersonList = findVisaPersonList(authInfo, elemReq.getNumber());
				visaPersonList.addAll(eVisaPersonList);
				//добавляем исполнителя
                if(!personList.contains(elemReq.getExecutor())) {
                    personList.add(elemReq.getExecutor());
                }
			}
		}
		sendRTSMail(authInfo, new InitiatorRequestOnCancelingByInitiatorTemplate(request, personList, visaPersonList));
	}

	/**
	 * RFC 060727
	 * инициатор изменил статус заявки "на тестировании" с "исполнена"
	 * и отправил ее тестирующему для повторного тестирования, по какой нить ошибке
	 * РМ: отправка Консолидированной заявки на тестирование
	 * Оповещение: Тестировщика, PM, KM, пользователей из списка оповещения
	 * и других инициаторов если в консолидированной заявке несколько заявок инициаторов
	 */
	public void sendInitiatorRequestOnTestingMailAfterInitiatorRevision
			(AuthInfo authInfo, InitiatorRequest request, String host) {

		ConsolidatedRequest cRequest = request.getConsolidatedRequest();
		ArrayList visaPersonList = new ArrayList();
		ArrayList personList4ConsReq = new ArrayList();
		if(cRequest != null) {
			Collection cVisaPersonList = findVisaPersonList(authInfo, cRequest.getNumber());
			//добавить список оповещения конс заявки
			visaPersonList.addAll(cVisaPersonList);
			//добавить в список всех кому необходимо отправить оповещенеи КМ РМ утверждающий, руководитель
            //и проверяем на включение в список
            if(!personList4ConsReq.contains(cRequest.getKeyManager())) {
                personList4ConsReq.add(cRequest.getKeyManager());
				sendRTSMail(authInfo, new ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2KM(request, cRequest, visaPersonList, host));
            }
            if(!personList4ConsReq.contains(cRequest.getRegMan())) {
				sendRTSMail(authInfo, new ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2RM(request, cRequest, visaPersonList, host));
            }
        	//Пока не открываем - инициаторы добавляются сами по заявке инициатора

			Collection initReqs = cRequest.getInitiatorRequests();
			for(Iterator it = initReqs.iterator(); it.hasNext();) {
				InitiatorRequest initReq = (InitiatorRequest) it.next();
				//проверка на наличие других заявок инициаторов в переданной консолидированной
				//их так же надо оповесить
				if(initReq.getRequestId().intValue() != request.getRequestId().intValue()) {
					//добавить список оповещения заявки инициатора
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
