package com.hps.july.mail.template;

import com.hps.july.mail.text.TextFormatter;
import com.hps.july.mail.text.InputSource;
import com.hps.july.mail.object.MailMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Iterator;

/**
 * Реализация шаблона для почтовых сообщений в виде HTML и кодировке Cp1251
 *
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 04.04.2006
 * Time: 14:50:25
 */
public class JulyHTMLMailTemplate extends JulyMailTemplate {

    private String templateName = null;

    /**
     *
     */
    public JulyHTMLMailTemplate(List recipientList, String templateName, String subject, HashMap prop) {
        super();
        setProp(prop);
        setTo(recipientList);
        setTemplateName(templateName);
        setSubject(subject);
    }

    public String getBody() {
        TextFormatter formatter = new TextFormatter();
        if(getProp() != null) {
            java.util.Set enum = getProp().keySet();
            for (Iterator i = enum.iterator(); i.hasNext();) {
                String key = (String)i.next();
                formatter.addParameter(key, getProp().get(key));
            }
        }
        return formatter.format(new InputSource(MailTemplateFactory.findTemplate(getTemplateName())));
    }

    public MailMessage createMailMessage() {
        MailMessage mailMessage = super.createMailMessage();
        mailMessage.setMessageMimeType("text/html");
        mailMessage.setMessageEncoding("Cp1251");
        return mailMessage;
    }

    /**
     * @return String
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param string
     */
    protected void setTemplateName(String string) {
        templateName = string;
    }



}
