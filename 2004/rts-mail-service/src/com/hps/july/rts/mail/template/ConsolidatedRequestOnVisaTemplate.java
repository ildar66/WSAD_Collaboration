package com.hps.july.rts.mail.template;

import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * На визировании (Конс. заявка) руководителю
 * Subject: Заявка ТС  на визирование
 * Body: 
 * 		1. номер Конс.
 * 		2. дата создания
 * To: 	
 * 		1. Ключевому менеджеру (КМ) (конс. заявка)
 * 		2. Руководителю(конс. заявка)
 * 		3. Участники визирования 
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public class ConsolidatedRequestOnVisaTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnVisaTemplate(ConsolidatedRequest request, Collection visaPersonList) {
        super(request, visaPersonList);
    }

    public ConsolidatedRequestOnVisaTemplate(ConsolidatedRequest request, Collection visaPersonList, String host) {
        this(request, visaPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowConsolidatedRequestsForHead.do?navclear=1&itemMenu=item1_17_5_12&isNumber=on&number=").append(request.getNumber());
    }

    public void validateProperties() {
        super.validateProperties();

        Person person = request.getHead();
        if(person == null) throw new IllegalArgumentException("visa Head Manager must be defined");
        person = request.getRegMan();
        if(person == null) throw new IllegalArgumentException("visa Regional Manager must be defined");
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //Head manager - руководитель!
        Person person = request.getHead();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        for (Iterator iter = mailPersonList.iterator(); iter.hasNext();) {
            person = (Person)iter.next();
            if(person != null && person.getEmail() != null
                    && !recipientEmails.contains(person.getEmail()))
                            recipientEmails.add(person.getEmail());
        }
        return recipientEmails;
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" отправлена на визирование. ");
        return msg.toString();
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

    public List getAttachmentList() {
        return null;
    }

}
