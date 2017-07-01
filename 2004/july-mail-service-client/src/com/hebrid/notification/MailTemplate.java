package com.hebrid.notification;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

import com.hps.april.auth.object.Person;
import com.hps.april.common.utils.Assert;
import com.hps.july.mail.template.MailTemplateFactory;
import com.hps.july.mail.template.SimpleHTMLMailTemplate;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.text.TextFormatter;

public class MailTemplate extends SimpleHTMLMailTemplate
    implements InitializingBean {

	protected Log logger = LogFactory.getLog(this.getClass());
	
    private TemplateContextProvider templateContextProvider;
    private String subjectExp;
    private String bodyTextExp;
    private String bodyResourceName;
    
    List recipientList;

    public void afterPropertiesSet() throws Exception {
    	//Assert.notNull(recipientListProvider, "Невозможно определить поставщика списка получателей письма: свойство recipientListProvider должно быть установлено");
    	Assert.notNull(subjectExp, "Невозможно определить тему письма: свойство subject должно быть установлено");
    	//Assert.isTrue(subjectExp != null || bodyResourceName != null, "Невозможно определить тело письма: свойства bodyText или bodyResource должны быть установлены");
    }

    private Map context;

    public Map getContext() {
        return context;
    }
    public void setContext(Map args) {
        this.context = args;
    }
    
    public TemplateContextProvider getTemplateContextProvider() {
        return templateContextProvider;
    }
    public void setTemplateContextProvider(TemplateContextProvider templateResourcesFinder) {
        this.templateContextProvider = templateResourcesFinder;
    }
    public Map getTemplateResources() {
        return templateContextProvider.findContext(getContext());
    }
	public String getBodyResourceName() {
		return bodyResourceName;
	}
	public void setBodyResourceName(String bodyResourceName) {
		this.bodyResourceName = bodyResourceName;
	}
	public List getTo() {
        return recipientList;
    }

    public String getBodyTextExp() {
		return bodyTextExp;
	}
	public void setBodyTextExp(String bodyTextExp) {
		this.bodyTextExp = bodyTextExp;
	}
	public String getSubjectExp() {
		return subjectExp;
	}
	public void setSubjectExp(String subjectExp) {
		this.subjectExp = subjectExp;
	}
	
	public String getSubject() {
        TextFormatter formatter = prepareFormatter();
		
        InputSource inputSource = new InputSource(new StringReader(subjectExp));
        String subject = formatter.format(inputSource);
        return subject;
	}

	protected TextFormatter prepareFormatter(){
        TextFormatter formatter = new TextFormatter();
        
        Map context = templateContextProvider.findContext(this.context);
        
        if(context != null) {
            Set enumSet = context.keySet();
            for (Iterator i = enumSet.iterator(); i.hasNext();) {
                String key = (String)i.next();
                formatter.addParameter(key, context.get(key));
            }
        }
        return formatter;
	}
	
	public String getBody() {
        TextFormatter formatter = prepareFormatter();
		
        InputSource inputSource = null;
        if (bodyTextExp != null){
        	inputSource = new InputSource(new StringReader(bodyTextExp));
        } else {
        	inputSource = new InputSource(MailTemplateFactory.findTemplate(bodyResourceName));
        }
        
        String body = formatter.format(inputSource);
        return body;
    }

	public void validateProperties() {
		
	}
	public void setRecipientList(List to) {
		List mailList = new ArrayList();
		for(Iterator iterator=to.iterator(); iterator.hasNext();){
			Person person = (Person)iterator.next();
			if(person.getEmail().trim().length()>0){
				mailList.add(person.getEmail().trim());
			}
		}
		this.recipientList = mailList;
	}
	public List getRecipientList() {
		return recipientList;
	}
	
	
}