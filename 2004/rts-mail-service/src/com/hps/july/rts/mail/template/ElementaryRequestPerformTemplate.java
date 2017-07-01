package com.hps.july.rts.mail.template;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.common.utils.FormatUtils;
import com.hps.april.auth.object.Person;

/**
 * Исполнена (Эл. заявка)
 * Subject: Элементарная заявка ТС  исполнена
 * Body: 
 * 			1. номер Эл. заявки
 * 			2. дата создания
 * To: исполнителю
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public class ElementaryRequestPerformTemplate extends ElementaryRequestTemplate {

    public ElementaryRequestPerformTemplate() {
        super();
    }
    public ElementaryRequestPerformTemplate(ElementaryRequest request) {
        super(request);
    }

    public ElementaryRequestPerformTemplate(ElementaryRequest request, Collection mailPersonList, String host) {
        super(request, mailPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowElementaryRequestsForExecutor.do?navclear=1&itemMenu=item1_17_5_11&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //исполнителю
        Person person = request.getExecutor();
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

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Элементарная заявка ТС ").append(request.getNumber());
        msg.append(" исполнена. ");
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
