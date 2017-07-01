/*
 *  $Id: ConsolidatedRequestVisedTemplate2RM.java,v 1.4 2007/04/15 16:54:50 vglushkov Exp $
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
 * RFC 060727
 * Завизирована (Конс. заявка)
 * Subject: Заявка ТС  завизирована
 * Body:
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * Исполнители: визирование элементарных заявок.
 * оповещение: РМ, пользователей из списка оповещения.
 *
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/04/15 16:54:50 $
 */
public class ConsolidatedRequestVisedTemplate2RM extends ConsolidatedRequestVisedTemplate {

    public ConsolidatedRequestVisedTemplate2RM(ConsolidatedRequest request, Collection visaPersonList, String host) {
        super(request, visaPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=RM&itemMenu=item1_17_5_10&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //региональный менеджер!
        Person person = request.getRegMan();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        return recipientEmails;
    }

    public List getCC() {
        List recipientEmails = new ArrayList();
        Person person = null;
        for (Iterator iter = mailPersonList.iterator(); iter.hasNext();) {
            person = (Person)iter.next();
            if(person != null && person.getEmail() != null
                    && !recipientEmails.contains(person.getEmail()))
                            recipientEmails.add(person.getEmail());
        }
        return recipientEmails;
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
