/*
 *  $Id: AbstractMailTemplate.java,v 1.3 2007/06/02 12:33:14 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.mail.template;

import com.hps.july.mail.object.MailMessage;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/06/02 12:33:14 $
 * Date: 04.04.2006
 * Time: 14:50:25
 */
public abstract class AbstractMailTemplate implements MailTemplate {

    private String from;
    private String messageEncoding;
    private String messageMimeType;

    public MailMessage createMailMessage(){
        validateProperties();

        MailMessage mailMessage = new MailMessage();
        mailMessage.setFrom(getFrom());
        mailMessage.setRecipients(getTo());
        mailMessage.setRecipientsCC(getCC());
        mailMessage.setRecipientsBCC(getBCC());

        mailMessage.setSubject(getSubject());
        mailMessage.setBody(getBody());

        mailMessage.setAttachmentList(getAttachmentList());

        return mailMessage;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List getCC() {
        return null;
    }

    public List getBCC() {
        return null;
    }

    public List getAttachmentList() {
        return null;
    }

    public String getMessageEncoding() {
        return messageEncoding;
    }

    public void setMessageEncoding(String messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    public String getMessageMimeType() {
        return messageMimeType;
    }

    public void setMessageMimeType(String messageMimeType) {
        this.messageMimeType = messageMimeType;
    }
}
