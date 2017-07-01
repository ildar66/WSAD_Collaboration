package com.hps.july.rts.mail.template;

import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.april.auth.object.Person;

/**
 * �� ���������� (����. ������)
 * Subject: ������ ��  ����������
 * Body:
 * 		1. ����� ����. ������
 * 		2. ���� ��������
 * To: 	���� ���������� �������� �� ����������� ���������� � 
 * 		����������� �� ������ ����������, ��������� �  ����. ������
 * @author Dimitry Krivenko 
 * 10.03.2006
 */
public abstract class ConsolidatedRequestOnPerformanceTemplate extends ConsolidatedRequestTemplate {

    public ConsolidatedRequestOnPerformanceTemplate() {
        super();
    }
    public ConsolidatedRequestOnPerformanceTemplate(ConsolidatedRequest request) {
        super(request);
    }

    public ConsolidatedRequestOnPerformanceTemplate(ConsolidatedRequest request, Collection visaPersonList) {
        super(request, visaPersonList);
    }

    public List getTo() {
        // ����������� �� ������ ����������, ��������� �  ����. ������
        List recipientEmails = new ArrayList();
        //KM
        Person person = request.getKeyManager();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        //������������
        person = request.getRegMan();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        //������ ����������
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
        msg.append("������ �� ").append(request.getNumber());
        msg.append(" ����������. ");
        return msg.toString();
    }

}
