package com.hps.july.mail.object;

import java.io.Serializable;

import com.hps.april.auth.object.AuthInfo;

public class MailTransportObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public static String JMS_OBJECT_PROPERTY = "july_mail_MailTransportObject";
	public static String JMS_MESSAGE_TYPE = "july_mail_MailTransportMessage";
	
	private MailMessage mailMessage;
	private AuthInfo authInfo; 
	private String senderService;
	
	public AuthInfo getAuthInfo() {
		return authInfo;
	}
	public void setAuthInfo(AuthInfo authInfo) {
		this.authInfo = authInfo;
	}
	public MailMessage getMailMessage() {
		return mailMessage;
	}
	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	public String getSenderService() {
		return senderService;
	}
	public void setSenderService(String senderService) {
		this.senderService = senderService;
	}
}
