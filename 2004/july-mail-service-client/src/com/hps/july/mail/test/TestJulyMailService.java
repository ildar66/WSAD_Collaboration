package com.hps.july.mail.test;

import com.hps.july.mail.service.JulyMailService;
import com.hps.july.mail.JulyMailServiceFactory;
import com.hps.april.auth.object.AuthInfoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 15.08.2006
 * Time: 19:56:47
 */
public class TestJulyMailService {

    public void startTestJulyMailService(List recip) {
        JulyMailService service = JulyMailServiceFactory.createMailService(false);

//        //��������� ������ ������������
//        service.addRecipient("");
//        //��������� ������ ������������ � �����
//        service.addRecipientCC("");
//        //��������� ������ ������������ � ������� �����
//        service.addRecipientBCC("");

        ArrayList list = new ArrayList();
        list.addAll(recip);
		AuthInfoImpl info = new AuthInfoImpl("vad");
        //info.setPrincipalName("vad");
        info.setPersonId(new Integer(22173));
        service.sendTextMail(info, list, "��������", "������ ��������", null, JulyMailService.COMPROJECT_SERVICE_NAME);
        HashMap map = new HashMap();
        map.put("bubu", "���-��");

        service.sendMail(info, list, "com/hps/july/common/mail/template/TestTemplate.message", "�������� C ����������", map, JulyMailService.COMPROJECT_SERVICE_NAME);
        service.sendHTMLMailByTemplate(info, list, "�������� C ���������� 2", "com/hps/july/common/mail/template/TestTemplate.message", map, JulyMailService.COMPROJECT_SERVICE_NAME);
        service.sendHTMLMail(info, list, "��������-HTML ��� ��������!", "<html><body><span style='color:red;'>������ ��������</span></body></html>", null, JulyMailService.COMPROJECT_SERVICE_NAME);

    }

	public void startTestHttpJulyMailService(List recip) {
		JulyMailService service = JulyMailServiceFactory.createHttpMailService();
		System.out.println("---");
		ArrayList list = new ArrayList();
		list.addAll(recip);
		AuthInfoImpl info = new AuthInfoImpl("vad");
		//info.setPrincipalName("vad");
		info.setPersonId(new Integer(22173));
		try {
			service.sendTextMail(info, list, "��������", "������ ��������", null, JulyMailService.COMPROJECT_SERVICE_NAME);
		}catch(Exception e) {
		}
		HashMap map = new HashMap();
		map.put("bubu", "���-��");
		try {
			service.sendMail(info, list, "com/hps/july/common/mail/template/TestTemplate.message", "�������� C ����������", map, JulyMailService.COMPROJECT_SERVICE_NAME);
		}catch(Exception e) {
		}
		try {
			service.sendHTMLMailByTemplate(info, list, "�������� C ���������� 2", "com/hps/july/common/mail/template/TestTemplate.message", map, JulyMailService.COMPROJECT_SERVICE_NAME);
		}catch(Exception e) {
		}
		try {
				service.sendHTMLMail(info, list, "��������-HTML ��� ��������!", "<html><body><span style='color:red;'>������ ��������</span></body></html>", null, JulyMailService.COMPROJECT_SERVICE_NAME);
		}catch(Exception e) {
		}

	}

}
