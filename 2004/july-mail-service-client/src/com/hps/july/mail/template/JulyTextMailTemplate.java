package com.hps.july.mail.template;

import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.object.MailMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.io.ByteArrayInputStream;

/**
 * (HTMLMailTemplate)
 *
 */
public class JulyTextMailTemplate extends JulyMailTemplate {

    private String body;
    /**
     *
     */
    public JulyTextMailTemplate(List recipientList, String body, String subject, HashMap prop) {
        setProp(prop);
        setTo(recipientList);
        setBody(body);
        setSubject(subject);
    }

    public String getBody() {
        if(body == null) return "";
        TextFormatter formatter = new TextFormatter();
        if(getProp() != null) {
            java.util.Set enum = getProp().keySet();
            for (Iterator i = enum.iterator(); i.hasNext();) {
                String key = (String)i.next();
                formatter.addParameter(key, getProp().get(key));
            }
        }
        return formatter.format(new InputSource(new ByteArrayInputStream(body.getBytes())));
    }

    /**
     * @param body
     */
    protected void setBody(String body) {
        this.body = body;
    }

    public MailMessage createMailMessage() {
        MailMessage mailMessage = super.createMailMessage();
        mailMessage.setMessageMimeType("text/plain");
        mailMessage.setMessageEncoding("Cp1251");
        return mailMessage;
    }

}
