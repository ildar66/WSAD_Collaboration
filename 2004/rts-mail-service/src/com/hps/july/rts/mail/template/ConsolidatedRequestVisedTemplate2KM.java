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
 * Завизирована (Конс. заявка)
 * Subject: Заявка ТС  завизирована
 * To: 	Ключевому менеждеру
 * @author Dimitry Krivenko
 * 10.03.2006
 */
public class ConsolidatedRequestVisedTemplate2KM extends ConsolidatedRequestVisedTemplate {

    public ConsolidatedRequestVisedTemplate2KM(ConsolidatedRequest request, Collection visaPersonList, String host) {
        super(request, visaPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=KM&itemMenu=item1_17_5_9&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //ключеовй менеджер
        Person person = request.getKeyManager();
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

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        formatter.addParameter("request", request);
        formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
        formatter.addParameter("reference", reference);
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
    }

}
