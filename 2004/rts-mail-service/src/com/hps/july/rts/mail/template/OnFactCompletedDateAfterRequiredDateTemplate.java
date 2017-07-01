/*
 *  $Id: OnFactCompletedDateAfterRequiredDateTemplate.java,v 1.1 2007/04/15 16:54:51 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
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
 * RFC 060727
 * При указании Исполнителем фактической даты исполнения, превышающей требуемую дату (инициатора)
 * проверка только при исполнеии всех заявок
 * оповещение:  оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/15 16:54:51 $
 *
 */
public class OnFactCompletedDateAfterRequiredDateTemplate extends RTSMailTemplate {

    protected ConsolidatedRequest consolidatedRequest;
    protected InitiatorRequest initiatorRequest;
    protected Collection mailPersonList;
    protected Collection visaMailPersonList;

    public OnFactCompletedDateAfterRequiredDateTemplate(ConsolidatedRequest cRequest, InitiatorRequest initiatorRequest, Collection mailPersonList, Collection visaPersonList) {
        this.consolidatedRequest = cRequest;
        this.initiatorRequest = initiatorRequest;
        this.mailPersonList = mailPersonList;
        this.visaMailPersonList = visaPersonList;
    }

    public void validateProperties() {
        if (consolidatedRequest == null)
            throw new IllegalArgumentException("ConsolidatedRequest must be defined");
        if (initiatorRequest == null)
            throw new IllegalArgumentException("InitiatorRequest must be defined");
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //список оповещения
        Person person = null;
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

    public List getCC() {
        List recipientEmails = new ArrayList();
        //список оповещения
        Person person = null;
        if(visaMailPersonList != null) {
            for (Iterator iter = visaMailPersonList.iterator(); iter.hasNext();) {
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
        msg.append("Внимание: Изменение планируемой даты исполнения заявки ");
        msg.append(consolidatedRequest.getNumber());
        return msg.toString();
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", consolidatedRequest);
        formatter.addParameter("created", FormatUtils.formatDate(consolidatedRequest.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("requiredDate", FormatUtils.formatDate(initiatorRequest.getCompleteDate(), "dd.MM.yyyy"));
        formatter.addParameter("initiator", initiatorRequest.getInitiator().getName());
        formatter.addParameter("initiatorPerson", initiatorRequest.getInitiatorPerson().getName());
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
