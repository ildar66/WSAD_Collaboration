package com.hebrid.notification;

import java.util.List;
import java.util.Map;

import com.hps.april.auth.object.AuthInfo;

public interface MailSenderFactory {
	
	public String SERVICE_NAME = "com.hebrid.notification.MailSender";
	
	void sendMail(AuthInfo authInfo, Map context, List recipList);
}