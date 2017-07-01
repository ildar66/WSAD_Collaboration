package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * Исполнена (Конс. заявка)
 * <li>Subject: Заявка ТС  исполнена
 * <li>Body: номер Конс. заявки, дата создания
 * <li>To: всем участникам процесса, включая Инициатора 
 * 		и сотрудникам из списка оповещения, указанном в  конс. заявке
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestPerformTemplate extends ConsolidatedRequestTemplate {

    protected StringBuffer reference;

    public ConsolidatedRequestPerformTemplate() {
        super();
    }
    public ConsolidatedRequestPerformTemplate(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestPerformTemplate(ConsolidatedRequest request, Collection mailPersonList) {
        super(request, mailPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" исполнена. ");
        return msg.toString();
    }

    public String getBody() {
        return "Заявка ТС N " + request.getNumber() + ", " +
                "созданная " + request.getCreated() + ", исполнена.";
    }

}
