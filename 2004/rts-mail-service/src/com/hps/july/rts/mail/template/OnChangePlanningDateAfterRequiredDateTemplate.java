/*
 *  $Id: OnChangePlanningDateAfterRequiredDateTemplate.java,v 1.3 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
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
 * RFC 060727
 * При указании Исполнителем планируемой даты, превышающей требуемую дату
 * оповещение: РМ, КМ, Инициатора, пользователей из списка оповещения

 * @author Albert Baykov
 * @version $Revision: 1.3 $ $Date: 2007/04/15 16:54:50 $
 *
 */
public class OnChangePlanningDateAfterRequiredDateTemplate extends RTSMailTemplate {

    protected ElementaryRequest sourceRequest;
	protected ConsolidatedRequest consolidatedRequest;
	protected InitiatorRequest initiatorRequest;
    protected Collection mailPersonList;
    protected Collection visaMailPersonList;

    public OnChangePlanningDateAfterRequiredDateTemplate(ConsolidatedRequest cRequest, ElementaryRequest request, InitiatorRequest initiatorRequest, Collection mailPersonList, Collection visaPersonList) {
        this.sourceRequest = request;
        this.consolidatedRequest = cRequest;
        this.initiatorRequest = initiatorRequest;
        this.mailPersonList = mailPersonList;
        this.visaMailPersonList = visaPersonList;
    }

    public void validateProperties() {
        if (sourceRequest == null)
            throw new IllegalArgumentException("ElementaryRequest must be defined");
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
        formatter.addParameter("panningDate", FormatUtils.formatDate(sourceRequest.getPlanComplDate(), "dd.MM.yyyy"));
		formatter.addParameter("elemRequest", sourceRequest);
        formatter.addParameter("requiredDate", FormatUtils.formatDate(initiatorRequest.getCompleteDate(), "dd.MM.yyyy"));
        formatter.addParameter("initiator", initiatorRequest.getInitiator().getName());
        formatter.addParameter("initiatorPerson", initiatorRequest.getInitiatorPerson().getName());
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
