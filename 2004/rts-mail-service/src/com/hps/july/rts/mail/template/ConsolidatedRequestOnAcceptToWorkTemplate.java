package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * ������� � ������ (����. ������) 
 * <li>Subject: ������ ��  ������� � ������
 * <li>Body: ����� ������, ���� ��������, ���������, ������������� ����������
 * <li>To: ��, ������������, ������������ ����������, ������������
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestOnAcceptToWorkTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnAcceptToWorkTemplate(ConsolidatedRequest request, Collection mailPersonList) {
        super(request, mailPersonList);
    }

    public String getSubject() {
        StringBuffer msg = new StringBuffer();
        msg.append("������ �� ").append(request.getNumber());
        msg.append(" ������� � ������. ");
        return msg.toString();
    }
}
