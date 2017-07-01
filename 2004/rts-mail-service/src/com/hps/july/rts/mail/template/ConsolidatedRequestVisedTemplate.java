package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;

/**
 * Завизирована (Конс. заявка)
 * Subject: Заявка ТС  завизирована
 * Body: 
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * To: 	РМ 1-го уровня
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestVisedTemplate extends ConsolidatedRequestOnVisaTemplate {

    public ConsolidatedRequestVisedTemplate(ConsolidatedRequest request, Collection visaPersonList) {
        super(request, visaPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" завизирована. ");
        return msg.toString();
    }

}
