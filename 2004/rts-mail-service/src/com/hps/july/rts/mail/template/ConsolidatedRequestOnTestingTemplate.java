/*
 *  $Id: ConsolidatedRequestOnTestingTemplate.java,v 1.3 2007/04/15 16:54:50 vglushkov Exp $
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
 * Темплейт письма, для отправки Тестирующему
 * когда консолидированная заявка отправлена на тестирование
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/04/15 16:54:50 $
 */
public class ConsolidatedRequestOnTestingTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnTestingTemplate() {
        super();
    }

    public ConsolidatedRequestOnTestingTemplate(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestOnTestingTemplate(ConsolidatedRequest request, Collection mailPersonList) {
        super(request, mailPersonList);
    }

    public ConsolidatedRequestOnTestingTemplate(ConsolidatedRequest request, Collection mailPersonList, String host) {
        this(request, mailPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowConsolidatedRequestsToTest.do?navclear=1&itemMenu=item1_17_5_14&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        //Теситрующий
        List recipientEmails = new ArrayList();
        //Тестирующий
        Person person = request.getTestedBy();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        return recipientEmails;
    }

    public List getCC() {
        // список оповещания
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
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" отправлена на тестироване. ");
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
