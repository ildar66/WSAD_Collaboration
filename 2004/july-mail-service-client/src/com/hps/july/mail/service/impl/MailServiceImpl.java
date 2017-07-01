package com.hps.july.mail.service.impl;

import org.apache.log4j.Logger;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.object.MailTransportObject;
import com.hps.july.mail.service.MailService;
import com.hps.july.mail.MailException;
import com.hps.july.mail.service.impl.dao.MailDAO;

public class MailServiceImpl implements MailService {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	protected MailDAO mailDAO;
	
    /**
     * Метод для отправки писем
     * @param authInfo
     * @param senderService
     * @param mailMessage
     * @throws MailException
     */
	public void sendMail(AuthInfo authInfo, String senderService, MailMessage mailMessage)
        throws MailException {

        // TODO validate mailMessage
		MailTransportObject mailTransportObject = new MailTransportObject();
		mailTransportObject.setMailMessage(mailMessage);
		mailTransportObject.setAuthInfo(authInfo);
		mailTransportObject.setSenderService(senderService);
		
		logger.debug("SendMail: " + mailMessage);
        mailDAO.sendMail(mailTransportObject);
    }

    /**
     * Метод для отправки идентифицированного письма
     * @param authInfo
     * @param senderService
     * @param mailMessage
     * @return идентификационный номер присваиваемый письму при отправки
     * @throws MailException
     */
    public Integer sendIdentifiedMail(AuthInfo authInfo, String senderService, MailMessage mailMessage)
        throws MailException {

        // TODO validate mailMessage
		MailTransportObject mailTransportObject = new MailTransportObject();
		mailTransportObject.setMailMessage(mailMessage);
		mailTransportObject.setAuthInfo(authInfo);
		mailTransportObject.setSenderService(senderService);

		logger.debug("SendMail: " + mailMessage);
        mailDAO.sendMail(mailTransportObject);
        return mailTransportObject.getMailMessage().getId();
    }

	public MailDAO getMailDAO() {
		return mailDAO;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}

}
