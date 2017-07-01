/*
 *  $Id: InitiatorRequestPerformTemplate.java,v 1.2 2007/04/15 16:54:50 vglushkov Exp $
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
 * Исполнена (заявка инициатора)
 * Subject: Заявка ТС от инициатора
 * Body:
 * 		1. номер заявки
 * 		2. дата создания
 * 		3. инициатор
 * 		4. представитель  инициатора
 * To: 	инициатор
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/15 16:54:50 $
 */
public class InitiatorRequestPerformTemplate extends InitiatorRequestTemplate {

    public InitiatorRequestPerformTemplate(InitiatorRequest request, Collection recipents, String host) {
        super(request, recipents);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/RequestsListAction.do?navclear=1&itemMenu=item1_17_5_8&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        ArrayList emails = new ArrayList();
        Person person = request.getInitiatorPerson();
        if(person != null && person.getEmail() != null) {
            emails.add(person.getEmail());
        }
        return emails;
    }

    public List getCC() {
        ArrayList emails = new ArrayList();
        Person person = null;
        for (Iterator iterator = mailPersonList.iterator(); iterator.hasNext();) {
            person = (Person) iterator.next();
            if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
        }
        return emails;
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" исполнена. ");
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
