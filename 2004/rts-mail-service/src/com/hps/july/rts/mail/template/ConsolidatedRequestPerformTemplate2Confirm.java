/*
 *  $Id: ConsolidatedRequestPerformTemplate2Confirm.java,v 1.2 2007/04/14 16:41:43 vglushkov Exp $
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
 * Исполнена (Конс. заявка)
 * <li>Subject: Заявка ТС  исполнена
 * <li>Body: номер Конс. заявки, дата создания
 * <li>To: утверждающему
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/14 16:41:43 $
 */
public class ConsolidatedRequestPerformTemplate2Confirm extends ConsolidatedRequestPerformTemplate {

    protected StringBuffer reference;

    public ConsolidatedRequestPerformTemplate2Confirm() {
        super();
    }
    public ConsolidatedRequestPerformTemplate2Confirm(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestPerformTemplate2Confirm(ConsolidatedRequest request, Collection mailPersonList) {
        super(request, mailPersonList);
    }

    public ConsolidatedRequestPerformTemplate2Confirm(ConsolidatedRequest request, Collection mailPersonList, String host) {
        this(request, mailPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowConsolidatedRequestsToConfirm.do?navclear=1&itemMenu=item1_17_5_13&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //утверждающему
        Person person = request.getConfirmativeMan();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
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
