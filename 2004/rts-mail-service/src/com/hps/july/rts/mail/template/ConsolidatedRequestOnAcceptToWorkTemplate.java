package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * Принята в работу (Конс. заявка) 
 * <li>Subject: Заявка ТС  принята в работу
 * <li>Body: номер заявки, дата создания, Инициатор, Представитель инициатора
 * <li>To: КМ, Руководителю, региональным менеджерам, Исполнителям
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestOnAcceptToWorkTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnAcceptToWorkTemplate(ConsolidatedRequest request, Collection mailPersonList) {
        super(request, mailPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" принята в работу. ");
        return msg.toString();
    }
}
