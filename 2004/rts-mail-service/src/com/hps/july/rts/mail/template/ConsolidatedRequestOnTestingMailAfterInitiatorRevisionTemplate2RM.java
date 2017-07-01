/*
 *  $Id: ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2RM.java,v 1.1 2007/04/28 17:42:16 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.InitiatorRequest;
import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ��������� ������� ������ ������ "�� ������������" � "���������"
 * � �������� �� ������������ ��� ���������� ������������, �� ����� ���� ������
 * ��: �������� ����������������� ������ �� ������������
 * ����������: ������������, PM, KM, ������������� �� ������ ����������
 * � ������ ����������� ���� � ����������������� ������ ��������� ������ �����������

 * Subject: ������ ��  ���������� �� �������������� ������������ ��� �� �� �����������
 * To: 	RM  + ������ ����������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/04/28 17:42:16 $
 */
public class ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2RM extends ConsolidatedRequestTemplate {

	private InitiatorRequest initiatorRequest;
	public ConsolidatedRequestOnTestingMailAfterInitiatorRevisionTemplate2RM(InitiatorRequest request, ConsolidatedRequest cRequest, Collection mailPersonList, String host) {
		super(cRequest, mailPersonList);
		initiatorRequest = request;
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=RM&itemMenu=item1_17_5_8&isNumber=on&number=").append(request.getNumber());
	}

	public List getTo() {
		List recipients = new ArrayList();
		//��
		Person person = request.getRegMan();
		if(person != null && person.getEmail() != null) {
			recipients.add(person.getEmail());
		}
		return recipients;
	}

	public List getCC() {
		List recipients = new ArrayList();
		for(Iterator i = mailPersonList.iterator(); i.hasNext();) {
			Person person = (Person)i.next();
			if(person != null && person.getEmail() != null) {
				recipients.add(person.getEmail());
			}
		}
		return recipients;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("������ �� ").append(request.getNumber());
		msg.append(" ���������� �� �������������� ������������. ");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("initiatorRequest", initiatorRequest);
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
