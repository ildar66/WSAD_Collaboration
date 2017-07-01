/*
 *  $Id: ReglamentEventType6Template2KM.java,v 1.1 2007/05/02 08:25:50 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ConsolidatedRequest;
import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.FormatUtils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * �������� ������ ��� ������� 6
 * ��
 * ��������� � ������������� ������������
 * ����������������� ������
 * (������� � �������� �������� ��� ����� ������� ������)
 * Subject: ������ ��  �� ������������� � ������� ...
 * To: 	��
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/05/02 08:25:50 $
 */
public class ReglamentEventType6Template2KM extends RTSMailTemplate {

	private long delay;
	protected ConsolidatedRequest request;
	protected StringBuffer reference;

	public ReglamentEventType6Template2KM(ConsolidatedRequest request, long createTime, String host) {
		this.request = request;
		delay = (int)((System.currentTimeMillis() - createTime)/(1000L * 60L * 60L * 24L));
		this.reference = new StringBuffer(host);
		this.reference.append("/rts/ConsolidatedRequestsListAction.do?navclear=1&currentRole=KM&itemMenu=item1_17_5_9&isNumber=on&number=").append(request.getNumber());
	}

	public void validateProperties() {

	}

	public List getTo() {
		List recipients = new ArrayList();
		Person person = request.getKeyManager();
		if(person != null && person.getEmail() != null
				&& !recipients.contains(person.getEmail()))
						recipients.add(person.getEmail());
		return recipients;
	}

	public List getCC() {
		List recipients = Collections.EMPTY_LIST;
		return recipients;
	}

	public String getSubject() {
		StringBuffer msg = new StringBuffer();
		msg.append("������ �� ").append(request.getNumber());
		msg.append(" �� ������������� � ������� ");
		msg.append(delay);
		msg.append(".");
		return msg.toString();
	}

	public String getBody() {
		TextFormatter formatter = new TextFormatter();
		formatter.addParameter("request", request);
		formatter.addParameter("created", FormatUtils.formatDate(request.getCreated(), "dd.MM.yyyy"));
		formatter.addParameter("delayed", new Integer((int)(delay / (1000L * 60L * 60L * 24L))));
		formatter.addParameter("reference", reference);
		return formatter.format(new InputSource(MailTemplateFactory.findTemplate(this)));
	}

}
