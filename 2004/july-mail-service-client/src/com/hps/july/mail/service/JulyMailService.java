package com.hps.july.mail.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.template.JulyMailTemplate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 05.07.2006
 * Time: 16:10:34
 */
public interface JulyMailService {

    public String ARENDA_SERVICE_NAME = "july.arenda.mail.mailService";
    public String BSPROJECT_SERVICE_NAME = "july.bsproject.mail.mailService";
    public String COMPROJECT_SERVICE_NAME = "july.comproject.mail.mailService";

	/**
	 * @deprecated
	 */
	public void sendDirectMail(AuthInfo authInfo, String serviceName, MailMessage mailMessage);
    
    /**
     * @deprecated
     * @param authInfo
     * @param recipientList
     * @param templateName
     * @param subject
     * @param prop
     * @param serviceName
     */
    public void sendMail(AuthInfo authInfo, java.util.List recipientList, String templateName, String subject, HashMap prop, String serviceName);

	public void sendBaseMail(AuthInfo authInfo, JulyMailTemplate mailTemplate, String serviceName);

	public void sendHTMLMailByTemplate(AuthInfo authInfo, java.util.List recipientList, String subject, String templateName, HashMap prop, String serviceName);

    public Integer sendHTMLMail(AuthInfo authInfo, java.util.List recipientList, String subject, String body, HashMap prop, String serviceName);

    public Integer sendTextMail(AuthInfo authInfo, java.util.List recipientList, String subject, String body, HashMap prop, String serviceName);

    public void addRecipient(String recipientEmail);

    public void addRecipientCC(String recipientEmail);

    public void addRecipientBCC(String recipientEmail);

    public void setRecipients(ArrayList recipients);

    public void setRecipientsCC(ArrayList recipientsCC);

    public void setRecipientsBCC(ArrayList recipientsBCC);

    public String getFrom();

    public void setFrom(String from);

}
