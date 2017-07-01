package com.hebrid.notification;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.MailException;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.service.MailService;
import com.hps.july.mail.template.NRIMailTemplate;

public class MailSenderFactoryImpl implements MailSenderFactory {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	private MailService mailService;
	
	private DefaultMailTemplateFactory mailTemplateFactory;
	
	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void sendMail(AuthInfo authInfo, MailTemplate mailTemplate) {
        MailMessage mailMessage = mailTemplate.createMailMessage();
        if(false) {
            mailMessage.setRecipients(null);
            mailMessage.setRecipientsCC(null);
            mailMessage.setRecipientsBCC(null);
            mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS_TEST);
        } else {
            mailMessage.setFrom(NRIMailTemplate.FROM_NRI_TECHNICAL_SUPPORT_ADDRESS);
        }
        //всегда посылаем письма админам
        //mailMessage.addRecipientBCC("dkrivenko@beeline.ru");
        mailMessage.addRecipientBCC("vglushkov@partners.beeline.ru");

        logger.info("\nSEND MESSAGE: \n" + 
        		mailMessage + 
        		"\n" +
        		"BODY ---------------- \n" +
        		"" + mailMessage.getBody() + "\n\n" +
        		"END -----------------\n");
        
        try {
            mailService.sendMail(authInfo, MailSenderFactory.SERVICE_NAME, mailMessage);
        } catch (MailException e) {
            logger.error("Could not send mail, cause " + e.getMessage(), e);
        }

    }

	public void sendMail(AuthInfo authInfo, Map context, List recipList) {
		this.sendMail(authInfo, mailTemplateFactory.getMailTemplate(context, recipList));	
	}

	public DefaultMailTemplateFactory getMailTemplateFactory() {
		return mailTemplateFactory;
	}

	public void setMailTemplateFactory(
			DefaultMailTemplateFactory mailTemplateFactory) {
		this.mailTemplateFactory = mailTemplateFactory;
	}

}
