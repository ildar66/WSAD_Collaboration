/*
 *  $Id: ElementaryRequestOnAcceptToWorkTemplate.java,v 1.3 2007/04/15 16:54:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * �� ���������� (������������ ������)
 * Subject: ����������������� ������ ���������� - �������� ���
 * ��. ������ �������� ������
 * Body:
 * 		1. ����� ����. ������
 * 		2. ���� ��������
 * To: 	���� ���������� ��������
 *
 * @author Dimitry Krivenko
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/04/15 16:54:50 $
 */
public class ElementaryRequestOnAcceptToWorkTemplate extends ElementaryRequestTemplate {

    public ElementaryRequestOnAcceptToWorkTemplate(ElementaryRequest request, Collection mailPersonList, String host) {
        super(request, mailPersonList);
        this.reference = new StringBuffer(host);
        this.reference.append("/rts/ShowElementaryRequestsForExecutor.do?navclear=1&itemMenu=item1_17_5_11&isNumber=on&number=").append(request.getNumber());
    }

    public List getTo() {
        List recipientEmails = new ArrayList();
        //�����������
        Person person = request.getExecutor();
        if(person != null && person.getEmail() != null) {
            recipientEmails.add(person.getEmail());
        }
        return recipientEmails;
    }

    public List getCC() {
        List recipientEmails = new ArrayList();
        Person person = null;
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
        msg.append("������������ ������ �� ").append(request.getNumber());
        msg.append(" ������� � ������. ");
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
