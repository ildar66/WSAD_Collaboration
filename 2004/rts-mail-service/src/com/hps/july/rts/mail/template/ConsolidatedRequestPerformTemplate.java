package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * ��������� (����. ������)
 * <li>Subject: ������ ��  ���������
 * <li>Body: ����� ����. ������, ���� ��������
 * <li>To: ���� ���������� ��������, ������� ���������� 
 * 		� ����������� �� ������ ����������, ��������� �  ����. ������
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
        msg.append("������ �� ").append(request.getNumber());
        msg.append(" ���������. ");
        return msg.toString();
    }

    public String getBody() {
        return "������ �� N " + request.getNumber() + ", " +
                "��������� " + request.getCreated() + ", ���������.";
    }

}
