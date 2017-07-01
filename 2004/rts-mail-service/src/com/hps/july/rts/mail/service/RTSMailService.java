/*
 *  $Id: RTSMailService.java,v 1.7 2007/04/29 13:04:41 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
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
     * Отправка сообщения по шаблону
     * @param authInfo
     * @param mailTemplate
     */
    void sendRTSMail(AuthInfo authInfo, RTSMailTemplate mailTemplate);

    /**
     * @deprecated RFC 060727
     * Черновик (при нажатии КМ кнопки «На доработку» в Конс. заявке»)
     * @param authInfo
     * @param request
     */
//    void sendConsolidatedRequestDraftMail(AuthInfo authInfo, ConsolidatedRequest request);

    /**
     *  Инициатор: отправка Заявки инициатора на рассмотрение КМ.
     *  Оповещение: КМ, пользователей из списка оповещения
     */
	void sendInitiatorRequestConsiderationMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

	/**
	 * КМ: отправка Заявки инициатора на рассмотрение РМ.
	 * Оповещение: РМ, пользователей из списка оповещения.
	 */
	void sendInitiatorRequestConsiderationForRMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

	/**
	 * Инициатор: отправка Заявки инициатора на отказ от канала,
	 * Консолидированой и Элементарных, созданных автоматически, на рассмотрение РМ.
	 * Оповещение: РМ, пользователей из списка оповещения.
	 */
	void sendConsolidatedRequestRefuseForRMMail(AuthInfo authInfo, ConsolidatedRequest consRequestRequest, String host);

    /**
     * На доработку от КМ (заявка инициатора)
      */
    void sendInitiatorRequestOnRevisionFromKMMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

    /**
     * RFC 060727
     * Заявка исполнена (заявка инициатора)
     */
    public void sendInitiatorRequestPerformMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);

    /**
     * @deprecated
     * На рассмотрении (Конс. заявка)
     */
    void sendConsolidatedRequestConsiderationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * РМ: оповещеня все исполнителей об отправке элементарных заявок на визирование.
     * Оповещение: исполнителей, пользователей из списка оповещения.
     */
    void sendConsolidatedRequestOnVisaMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * На визировании (Эл.заявка)
     */
    void sendElementaryRequestOnVisaMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * Завизирована (Конс. заявка)
     */
    void sendConsolidatedRequestVisedMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * На утверждении (Конс. заявка)
     */
    void sendConsolidatedRequestOnRatificationMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * На исполнении (Конс. заявка)
     */
    void sendConsolidatedRequestOnPerformanceMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * @deprecated
     * RFC 060727
     * РМ: оповещение об отправке Элементарных заявок на исполнение.
     * Оповещение: исполнителей, пользователей из списка оповещения.
     * объединен с методом sendConsolidatedRequestOnPerformanceMail
	 * сделан как private
     *
     * метод оповещает по одной ЭЗ, выполянть должен после утверждения КЗ
     *
     * ранее
     * На исполнении (Элем. заявка)
     * 1. Исполнитель
     * 2. все в списке оповещения
     */
    //void sendElementaryRequestOnPerformanceMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * RFC 060727
     * @deprecated
     * Исполнена (Эл. заявка)
     * Оповещение по элементарной заявке
     */
    void sendElementaryRequestPerformMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * RFC 060727
     * Исполнитель: исполнение элементарной заявки.
     * Все входящие элементарные заявки перешли в статус "Исполнена" или "Гтовы к тестированию"
     * После исполнения последней – оповещение: РМ, пользователей из списка оповещения
     */
    public void sendConsolidatedRequestReadyToTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * РМ изменил статус заявки "на тестировании" и отправил ее тестирующему
     * РМ: отправка Консолидированной заявки на тестирование
     * Оповещение: Тестировщика, пользователей из списка оповещения
     */
    public void sendConsolidatedRequestOnTestingMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * инициатор изменил статус заявки "на тестировании" с "исполнена"
	 * и отправил ее тестирующему для повторного тестирования, по какой нить ошибке
     * РМ: отправка Консолидированной заявки на тестирование
     * Оповещение: Тестировщика, PM, KM, пользователей из списка оповещения
     */
    public void sendInitiatorRequestOnTestingMailAfterInitiatorRevision(AuthInfo authInfo, InitiatorRequest request, String host);

    /**
     * Исполнена (Конс. заявка)
     */
    void sendConsolidatedRequestPerformMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * RFC 060727
     * Тестировщик: отправка Консолидированной заявки на доработку.
     * Оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения
     */
    void sendConsolidatedRequestOnRevisionMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * Принята в работу (Конс. заявка)
     */
    void sendConsolidatedRequestOnAcceptToWorkMail(AuthInfo authInfo, ConsolidatedRequest consolidatedRequest, String host);

    /**
     * отправка письма по принятию в работу элементарной заявки
     * 1. Исполнитель
     * @param authInfo
     * @param elementaryRequest
     * @param host
     */
    public void sendElementaryRequestOnAcceptToWorkMail(AuthInfo authInfo, ElementaryRequest elementaryRequest, String host);

    /**
     * @deprecated
     * отправка письма по принятию в работу заявки инициатора
     * @param authInfo
     * @param initiatorRequest
     * @param host
     */
    public void sendInitiatorRequestOnAcceptToWorkMail(AuthInfo authInfo, InitiatorRequest initiatorRequest, String host);


    /**
     * @deprecated
     * отправка писем при изменении планируемой даты
     * Получатели все участвующие в процессе
     * @param authInfo
     * @param elementaryRequest
     */
//    public void sendOnChangePlanningDateMail(AuthInfo authInfo, ElementaryRequest elementaryRequest);
    
	/**
	 * отправка писем при изменении планируемой даты, которая наступит позже
	 * требуемой даты, указанной инициатором в заявке инициатора
	 * ! Необходимо информаировать всех участников процесса о превышении планируемой даты над требуемой
	 * Не путать с предыдущим методом - sendOnChangePlanningDateMail
	 * Получатели все участвующие в процессе
	 * @param authInfo
	 * @param elementaryRequest
	 */
	public void sendOnChangePlanningDateAfterRequiredDateMail(AuthInfo authInfo,  ElementaryRequest elementaryRequest);

    /**
     * RFC 060727
     * При указании Исполнителем фактической даты, превышающей требуемую дату (указанную инициатором)
     * проверка происходит после исполнения всех заявок !!!!!
     * оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения.
     *
     * ранее терпеь нет, после удалить комментарии
     * Получатели все участвующие в процессе
     * @param authInfo
     * @param cRequest
     */
    public void sendOnFactCompletedDateAfterRequiredDateMail(AuthInfo authInfo, ConsolidatedRequest cRequest);


//    /**
//	 * отправка писем в случае если просрочено время рассмотрения указанное в регламенте
//	 * Получатели все КМ и инициатор
//	 * @param authInfo
//	 * @param request
//	 */
//	public void sendMailOnConsiderInitiatorRequestByOrder(AuthInfo authInfo, InitiatorRequest request, Date firstTime);

	/**
	 * отправка писем всем участникам цикла при отказе от канала инициатором
	 * Получатели все КМ, РМ, инициатор, исполнители, Руководитель, Тестировщик, Утверждающий
	 * @param authInfo
	 * @param request
	 */
	public void sendInitiatorRequestOnCancelingByInitiator(AuthInfo authInfo, InitiatorRequest request);


}
