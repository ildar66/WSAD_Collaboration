/*
 *  $Id: ConsolidatedRequestConsiderationMail2RMTemplate.java,v 1.3 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Шаблон письма, о изменение статуса консолидированной заявки "На рассмотрении (Конс. заявка)"
 * для отправки Региональному менеджеру
 * Subject: Заявка ТС  на рассмотрение
 * Body:
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * To: 	РМ 1-го уровня
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/04/15 16:54:50 $
 */
public class ConsolidatedRequestConsiderationMail2RMTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestConsiderationMail2RMTemplate() {
        super();
    }

    public ConsolidatedRequestConsiderationMail2RMTemplate(ConsolidatedRequest request, Collection recipents, String host) {
        super(request, recipents);
        this.reference = new StringBuffer();
        this.reference.append(host).append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=RM&itemMenu=item1_17_5_10&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        ArrayList emails = new ArrayList();
        Person regMan = request.getRegMan();
        if(regMan != null && regMan.getEmail() != null) emails.add(regMan.getEmail());
        return emails;
    }

    public List getCC() {
        ArrayList emails = new ArrayList();
        if(mailPersonList != null) {
            for (Iterator iterator = mailPersonList.iterator(); iterator.hasNext();) {
                Person person = (Person) iterator.next();
                if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
            }
        }
        return emails;
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer("Заявка ТС ");
        msg.append(request.getNumber());
        msg.append(" отправлена на рассмотрение.");
        return msg.toString();
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
        //return formatter.format(new InputSource(this.getClass().getClassLoader().getResourceAsStream("com/hps/july/rts/mail/template/ConsolidatedRequestConsiderationMail2RMTemplate.message")));
    }

    public List getAttachmentList() {
        return null;
    }

}
