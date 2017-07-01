/*
 *  $Id: ConsolidatedRequestOnRatificationTemplate.java,v 1.2 2007/04/14 16:41:42 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * На утверждении (Конс. заявка)
 * Subject: Заявка ТС  на утверждение
 * Body: 
 * 		1. номер Конс. заявки
 * 		2. дата создания
 * To: 	
 * 		1. Утверждающему
 * 		2. КМ
 * @author Dimitry Krivenko 
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/14 16:41:42 $
 */
public abstract class ConsolidatedRequestOnRatificationTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnRatificationTemplate() {
        super();
    }

    public ConsolidatedRequestOnRatificationTemplate(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestOnRatificationTemplate(ConsolidatedRequest request, Collection visaPersonList) {
        super(request, visaPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("Заявка ТС ").append(request.getNumber());
        msg.append(" отправлена на утверждение. ");
        return msg.toString();
    }

}
