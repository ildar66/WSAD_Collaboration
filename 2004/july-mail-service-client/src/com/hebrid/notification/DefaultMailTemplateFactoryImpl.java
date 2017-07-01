package com.hebrid.notification;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultMailTemplateFactoryImpl implements
		DefaultMailTemplateFactory {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
    private TemplateContextProvider templateContextProvider;
    private String subject;
    private String bodyText;
    private String bodyResourceName;
    
	public void afterPropertiesSet() throws Exception {
		
	}
    
	public String getBodyResourceName() {
		return bodyResourceName;
	}
	public void setBodyResourceName(String bodyResourceName) {
		this.bodyResourceName = bodyResourceName;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TemplateContextProvider getTemplateContextProvider() {
		return templateContextProvider;
	}
	public void setTemplateContextProvider(
			TemplateContextProvider templateContextProvider) {
		this.templateContextProvider = templateContextProvider;
	}

	public MailTemplate getMailTemplate(Map context, List recipientList) {
		MailTemplate mailTemplate = new MailTemplate();
		// контест - персистентная переменная, 
		// mailTemplate получается смешанным классом - выполняет действия
		// и хранит переменные, что не есть хорошо. 
		// поэтому пришлось сделать для него своеобразную фабрику. 
		mailTemplate.setContext(context);
		
		mailTemplate.setBodyResourceName(bodyResourceName);
		mailTemplate.setBodyTextExp(bodyText);
		mailTemplate.setSubjectExp(subject);
		mailTemplate.setRecipientList(recipientList);
		mailTemplate.setTemplateContextProvider(templateContextProvider);
		return mailTemplate;
	}

}
