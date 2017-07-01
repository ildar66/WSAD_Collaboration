/*
 *  $Id: ReglamentEventType3aTemplate2KM.java,v 1.1 2007/04/29 17:22:04 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Темплейт письма для события 3
 * Необходимо реализовать возможность оповещения
 * Исполнителя, КМ  в течение 3 рабочих дней о необходимости
 * визирования Элементарной заявки
 * Subject: Заявка ТС  не завизирована  исполнителем в течении ...
 * To: 	КМ
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/29 17:22:04 $
 */
public class ReglamentEventType3aTemplate2KM extends RTSMailTemplate {

	private long delay;
	protected ElementaryRequest request;
	protected StringBuffer reference;
	private ConsolidatedRequest cRequest;

	public ReglamentEventType3aTemplate2KM(ElementaryRequest request, ConsolidatedRequest cRequest, long createTime, String host) {
		this.request = request;
		this.cRequest = cRequest;
		delay = (int)((System.currentTimeMillis() - createTime)/(1000L * 60L * 60L * 24L));
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=KM&itemMenu=item1_17_5_8&isNumber=on&number=").append(cRequest.getNumber());
	}

	public void validateProperties() {

	}

	public List getTo() {
		List recipients = new ArrayList();
		Person person = cRequest.getKeyManager();
		if(person != null && person.getEmail() != null
				&& !recipients.contains(person.getEmail()))
						recipients.add(person.getEmail());
		return recipients;
	}

	public List getCC() {
		List recipients = Collections.EMPTY_LIST;
		return recipients;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("Заявка ТС ").append(request.getNumber());
		msg.append(" не завизирована  исполнителем  в течении ");
		msg.append(delay);
		msg.append(".");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("cRequest", cRequest);
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("delayed", new Integer((int)(delay / (1000L * 60L * 60L * 24L))));
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
