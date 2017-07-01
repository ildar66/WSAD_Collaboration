/*
 *  $Id: InitiatorRequestOnCancelingByInitiatorTemplate.java,v 1.3 2007/06/09 07:36:34 Abaykov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * RTS ("Заявки на ТС")
 *
 * @author ABaykov
 * Created on 18.07.2006
 */
public class InitiatorRequestOnCancelingByInitiatorTemplate extends InitiatorRequestTemplate {

    private Collection visaPersonList;
    public InitiatorRequestOnCancelingByInitiatorTemplate(InitiatorRequest request, Collection recipents, Collection visaPersonList) {
		super(request, recipents);
        this.visaPersonList = visaPersonList;
    }

	public List getTo() {
		ArrayList emails = new ArrayList();
		Person person = null;
		for (Iterator iterator = mailPersonList.iterator(); iterator.hasNext();) {
			person = (Person) iterator.next();
			if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
		}
		return emails;
	}

	public List getCC() {
		ArrayList emails = new ArrayList();
		for (Iterator iterator = visaPersonList.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
		}
		return emails;
	}

    public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("Заявка ТС ").append(request.getNumber());
		msg.append(" на аннулирование заявки. ");
		
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
