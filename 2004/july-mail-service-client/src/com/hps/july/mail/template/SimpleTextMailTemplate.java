package com.hps.july.mail.template;

import com.hps.july.mail.object.MailMessage;

/**
 * Абстрактный шаблон для тексовых почтовых сообщений и кодировке Cp1251
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 04.04.2006
 * Time: 14:50:25
 */
public abstract class SimpleTextMailTemplate extends NRIMailTemplate {

    public MailMessage createMailMessage() {
        MailMessage mailMessage = super.createMailMessage();
        mailMessage.setMessageMimeType("text/plain");
        mailMessage.setMessageEncoding("Cp1251");
        return mailMessage;
    }

}
