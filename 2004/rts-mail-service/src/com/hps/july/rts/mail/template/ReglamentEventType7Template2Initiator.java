/*
 *  $Id: ReglamentEventType7Template2Initiator.java,v 1.1 2007/05/02 08:25:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.rts.object.request.InitiatorRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Темплейт письма для события 1
 * реализовать возможность оповещения
 * КМ ежедневно о необходимости отправки
 * Заявки инициатора на рассмотрение РМ-у
 * (начиная со следующего рабочего дня после прихода заявки)
 * Subject: Заявка ТС  не принята в работу в течении ...
 * To: 	КМ
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/05/02 08:25:50 $
 */
public class ReglamentEventType7Template2Initiator extends RTSMailTemplate {

	private long delay;
	protected InitiatorRequest request;
	protected StringBuffer reference;

	public ReglamentEventType7Template2Initiator(InitiatorRequest request, long createTime, String host) {
		this.request = request;
		delay = (int)((System.currentTimeMillis() - createTime)/(1000L * 60L * 60L * 24L));
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/RequestsListAction.do?navclear=1&itemMenu=item1_17_5_8&isNumber=on&number=").append(request.getNumber());
	}

	public void validateProperties() {

	}

	public List getTo() {
		List recipients = new ArrayList();
		Person person = request.getInitiatorPerson();
		if(person != null && person.getEmail() != null
				&& !recipients.contains(person.getEmail()))
						recipients.add(person.getEmail());
		return recipients;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("Заявка ТС ").append(request.getNumber());
		msg.append(" не принята в работу в течении ");
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
