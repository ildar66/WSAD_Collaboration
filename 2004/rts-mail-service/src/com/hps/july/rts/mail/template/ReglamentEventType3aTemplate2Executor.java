/*
 *  $Id: ReglamentEventType3aTemplate2Executor.java,v 1.1 2007/04/29 17:22:04 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * RFC 060727
 * Темплейт письма для события 3 b
 * Необходимо реализовать возможность оповещения
 * исполнителя по аренде еженедельно и в
 * течение последних 7 рабочих дней до даты
 * к которой необходим поток
 * * Subject: Заявка ТС  не завизирована исполнителем  в течении ...
 * To: 	КМ
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/29 17:22:04 $
 */
public class ReglamentEventType3aTemplate2Executor extends RTSMailTemplate {

	private long delay;
	protected ElementaryRequest request;
	protected StringBuffer reference;

	public ReglamentEventType3aTemplate2Executor(ElementaryRequest request, long createTime, String host) {
		this.request = request;
		delay = (int)((System.currentTimeMillis() - createTime)/(1000L * 60L * 60L * 24L));
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ShowElementaryRequestsForExecutor.do?navclear=1&currentRole=RM&itemMenu=item1_17_5_11&isNumber=on&number=").append(request.getNumber());
	}

	public void validateProperties() {

	}

	public List getTo() {
		List recipients = new ArrayList();
		Person person = request.getExecutor();
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
		msg.append(" не завизирована исполнителем в течении ");
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
