/*
 *  $Id: InitiatorRequestOnTestingMailAfterInitiatorRevisionTemplate.java,v 1.1 2007/04/28 17:42:16 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * инициатор изменил статус заявки "на тестировании" с "исполнена"
 * и отправил ее тестирующему для повторного тестирования, по какой нить ошибке
 * РМ: отправка Консолидированной заявки на тестирование
 * Оповещение: Тестировщика, PM, KM, пользователей из списка оповещения
 * и других инициаторов если в консолидированной заявке несколько заявок инициаторов

 * Subject: Заявка ТС  направлена на дополнительное тестирование
 * To: 	Инициатор + список оповещения
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/28 17:42:16 $
 */
public class InitiatorRequestOnTestingMailAfterInitiatorRevisionTemplate extends InitiatorRequestTemplate {

	public InitiatorRequestOnTestingMailAfterInitiatorRevisionTemplate(InitiatorRequest request, Collection mailPersonList, String host) {
		super(request, mailPersonList);
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/RequestsListAction.do?navclear=1&itemMenu=item1_17_5_8&isNumber=on&number=").append(request.getNumber());
	}

	public List getTo() {
		List recipients = new ArrayList();
		//Инициатору
		Person person = request.getInitiatorPerson();
		if(person != null && person.getEmail() != null) {
			recipients.add(person.getEmail());
		}
		return recipients;
	}

	public List getCC() {
		List recipients = new ArrayList();
		for(Iterator i = mailPersonList.iterator(); i.hasNext();) {
			Person person = (Person)i.next();
			if(person != null && person.getEmail() != null) {
				recipients.add(person.getEmail());
			}
		}
		return recipients;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("Заявка ТС ").append(request.getNumber());
		msg.append(" направлена на дополнительное тестирование. ");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("reason", request.getRevisionComment());
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
