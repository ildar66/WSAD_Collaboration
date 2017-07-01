/*
 *  $Id: ConsolidatedRequestOnRatificationTemplate.java,v 1.2 2007/04/14 16:41:42 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.rts.mail.template;

import java.util.Collection;

import com.hps.july.rts.object.request.ConsolidatedRequest;

/**
 * �� ����������� (����. ������)
 * Subject: ������ ��  �� �����������
 * Body: 
 * 		1. ����� ����. ������
 * 		2. ���� ��������
 * To: 	
 * 		1. �������������
 * 		2. ��
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
        msg.append("������ �� ").append(request.getNumber());
        msg.append(" ���������� �� �����������. ");
        return msg.toString();
    }

}
