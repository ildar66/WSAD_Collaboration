package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;

/**
 * ������������ (����. ������)
 * Subject: ������ ��  ������������
 * Body: 
 * 		1. ����� ����. ������
 * 		2. ���� ��������
 * To: 	�� 1-�� ������
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestVisedTemplate extends ConsolidatedRequestOnVisaTemplate {

    public ConsolidatedRequestVisedTemplate(ConsolidatedRequest request, Collection visaPersonList) {
        super(request, visaPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("������ �� ").append(request.getNumber());
        msg.append(" ������������. ");
        return msg.toString();
    }

}
