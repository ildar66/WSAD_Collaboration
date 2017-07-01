/*
 *  $Id: ReglamentEventType2Template2KM.java,v 1.1 2007/04/29 13:04:40 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.rts.object.request.InitiatorRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Темплейт письма для события 2
 * отправки Элементарных Заявок на рассмотрение исполнителям
 * (начиная с третьего рабочего дня после прихода заявки)
 * Subject: Заявка ТС  не разбита на ЭЗ и не отправлена на рассмотрение исполнителей в течении ...
 * To: 	КМ
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/29 13:04:40 $
 */
public class ReglamentEventType2Template2KM extends RTSMailTemplate {

	private long delay;
	protected InitiatorRequest request;
	protected StringBuffer reference;

	public ReglamentEventType2Template2KM(InitiatorRequest request, long createTime, String host) {
		this.request = request;
		delay = (int)((System.currentTimeMillis() - createTime)/(1000L * 60L * 60L * 24L));
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=KM&itemMenu=item1_17_5_8&isNumber=on&number=").append(request.getNumber());
	}

	public void validateProperties() {

	}

	public List getTo() {
		List recipients = new ArrayList();
		Person person = request.getKeyManager();
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
		msg.append(" не разбита на ЭЗ и не отправлена на рассмотрение исполнителей в течении ");
		msg.append(delay);
		msg.append(".");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("delayed", new Integer((int)(delay / (1000L * 60L * 60L * 24L))));
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
