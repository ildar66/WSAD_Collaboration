/*
 *  $Id: SimpleHTMLMailTemplate.java,v 1.2 2007/06/02 12:33:14 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.mail.template;

import com.hps.july.mail.object.MailMessage;

/**
 * Абстрактный шаблон для почтовых сообщений в виде HTML и кодировке Cp1251
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/02 12:33:14 $
 * Date: 04.04.2006
 * Time: 14:50:25
 */
public abstract class SimpleHTMLMailTemplate extends NRIMailTemplate {

    public MailMessage createMailMessage() {
        MailMessage mailMessage = super.createMailMessage();
        mailMessage.setMessageMimeType("text/html");
        mailMessage.setMessageEncoding("Cp1251");
        return mailMessage;
    }



}
