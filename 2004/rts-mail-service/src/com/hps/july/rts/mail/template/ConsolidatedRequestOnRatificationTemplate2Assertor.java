/*
 *  $Id: ConsolidatedRequestOnRatificationTemplate2Assertor.java,v 1.3 2007/04/15 16:54:51 vglushkov Exp $
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
 * РМ: отправка Консолидированной заявки на утверждение
 * Subject: Заявка ТС  на утверждение
 * Body:
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * To:
 * 		Оповещение: Утверждающего, пользователей из списка оповещения
 *
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/04/15 16:54:51 $
 */
public class ConsolidatedRequestOnRatificationTemplate2Assertor extends ConsolidatedRequestOnRatificationTemplate {

    public ConsolidatedRequestOnRatificationTemplate2Assertor() {
        super();
    }

    public ConsolidatedRequestOnRatificationTemplate2Assertor(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestOnRatificationTemplate2Assertor(ConsolidatedRequest request, Collection visaPersonList, String host) {
        super(request, visaPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowConsolidatedRequestsToConfirm.do?navclear=1&itemMenu=item1_17_5_13&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //Утверждающий
        Person person = request.getConfirmativeMan();
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

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
