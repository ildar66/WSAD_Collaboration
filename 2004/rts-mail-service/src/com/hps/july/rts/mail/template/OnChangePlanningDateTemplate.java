package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class OnChangePlanningDateTemplate extends RTSMailTemplate {

    protected ElementaryRequest sourceRequest;
    protected ConsolidatedRequest consolidatedRequest;
    protected Collection mailPersonList;

    public OnChangePlanningDateTemplate(ConsolidatedRequest cRequest, ElementaryRequest request, Collection mailPersonList) {
        this.sourceRequest = request;
        this.consolidatedRequest = cRequest;
        this.mailPersonList = mailPersonList;
    }

    public void validateProperties() {
        if (sourceRequest == null)
            throw new IllegalArgumentException("ElementaryRequest must be defined");
        if (consolidatedRequest == null)
            throw new IllegalArgumentException("ConsolidatedRequest must be defined");
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
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
