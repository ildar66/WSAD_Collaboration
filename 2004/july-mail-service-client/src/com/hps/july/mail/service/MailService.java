package com.hps.july.mail.service;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.mail.object.MailMessage;
import com.hps.july.mail.MailException;
import com.hps.april.common.Service;

public interface MailService extends Service {

    String SERVICE_NAME = "july.mail.mailService";

    /**
     * Метод для отправки писем 
     * @param authInfo
     * @param senderService
     * @param mailMessage
     * @throws MailException
     */
    void sendMail(AuthInfo authInfo, String senderService, MailMessage mailMessage) throws MailException;

    /**
     * Метод для отправки идентифицированного письма
     * @param authInfo
     * @param senderService
     * @param mailMessage
     * @return идентификационный номер присваиваемый письму при отправки
     * @throws MailException
     */
    public Integer sendIdentifiedMail(AuthInfo authInfo, String senderService, MailMessage mailMessage) throws MailException;

}
