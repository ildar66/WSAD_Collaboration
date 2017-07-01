/*
 *  $Id: InitiatorRequestRevisionFromKMTemplate.java,v 1.2 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;

/**
 * На редактировании (заявка инициатора)
 * Subject: Заявка ТС  на редактирование
 * Body: 
 * 		1. номер заявки
 * 		2. дата создания
 * To: 	Инициатор
 * @author Dimitry Krivenko 
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/15 16:54:50 $
 */
public class InitiatorRequestRevisionFromKMTemplate extends InitiatorRequestTemplate {

    public InitiatorRequestRevisionFromKMTemplate(InitiatorRequest request, Collection mailPersonList, String host) {
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

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" отправлена на доработку. ");
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
