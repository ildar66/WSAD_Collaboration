/*
 *  $Id: InitiatorRequestOnConsideration2RMTemplate.java,v 1.4 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;


/**
 * Subject: Заявка ТС от инициатора
 * Body: 
 * 		1. номер заявки
 * 		2. дата создания
 * 		3. инициатор
 * 		4. представитель  инициатора
 * To: Региональный менеджер
 * Оповещение: РМ, пользователей из списка оповещения.
 * @author abaykov
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/04/15 16:54:50 $
 */
public class InitiatorRequestOnConsideration2RMTemplate extends InitiatorRequestTemplate {

    public InitiatorRequestOnConsideration2RMTemplate(InitiatorRequest request, Collection recipents, String host) {
        super(request, recipents);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=RM&itemMenu=item1_17_5_9&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        ArrayList emails = new ArrayList();
        //add RM
        Person person  = request.getRegManager();
        if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
        return emails;
    }

    public List getCC() {
        ArrayList emails = new ArrayList();
        for (Iterator iterator = mailPersonList.iterator(); iterator.hasNext();) {
            Person person = (Person) iterator.next();
            if(person != null && person.getEmail() != null && !emails.contains(person.getEmail())) emails.add(person.getEmail());
        }
        return emails;
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка на расширение региональной ТС ").append(request.getNumber());
        msg.append(" отправлена на рассмотрение РМ. ");
        return msg.toString();
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
        //return formatter.format(new InputSource(this.getClass().getClassLoader().getResourceAsStream("com/hps/july/rts/mail/template/InitiatorRequestOnConsideration2KMTemplate.message")));
    }

}
