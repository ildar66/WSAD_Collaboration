/*
 *  $Id: ElementaryRequestOnVisaTemplate2Arenda.java,v 1.1 2007/04/29 17:22:05 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * На визировании (Елементарная заявка)
 * Subject: Консолидированная заявка утверждена - каскадно все
 * эл. заявки получают статус
 * Body:
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * To: 	всем участникам процесса
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/29 17:22:05 $
 */
public class ElementaryRequestOnVisaTemplate2Arenda extends ElementaryRequestTemplate {

	public ElementaryRequestOnVisaTemplate2Arenda(ElementaryRequest request, Collection mailPersonList, String host) {
		super(request, mailPersonList);
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ShowElementaryRequestsForExecutor.do?navclear=1&itemMenu=item1_17_5_11&isNumber=on&number=").append(request.getNumber());
	}

	public List getTo() {
		List recipientEmails = new ArrayList();
		//Исполнитель
		Person person = request.getExecutor();
		if(person != null && person.getEmail() != null) {
			recipientEmails.add(person.getEmail());
		}
		return recipientEmails;
	}

	public List getCC() {
		List recipientEmails = new ArrayList();
		Person person = null;
		//список оповещения
		if(mailPersonList != null) {
			for (Iterator iter = mailPersonList.iterator(); iter.hasNext();) {
				person = (Person)iter.next();
				if(person != null && person.getEmail() != null
						&& !recipientEmails.contains(person.getEmail()))
								recipientEmails.add(person.getEmail());
			}
		}
		return recipientEmails;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("Заявку ТС ").append(request.getNumber());
		msg.append(" необходимо отправить на запрос оператору.");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
