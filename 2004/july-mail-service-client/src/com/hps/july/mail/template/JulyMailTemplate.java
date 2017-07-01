package com.hps.july.mail.template;

import java.util.HashMap;
import java.util.List;

/**
 * (JulyMailTemplate)
 *
 */
public abstract class JulyMailTemplate extends NRIMailTemplate {

    private HashMap prop = null;
    private List recipientEmails = null;
    private String subject = null;

    public List getTo() {
        return recipientEmails;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * @return HashMap
     */
    public HashMap getProp() {
        return prop;
    }

    /**
     * @param properties
     */
    protected void setProp(HashMap properties) {
        prop = properties;
    }

    /**
     * @param list
     */
    protected void setTo(List list) {
        recipientEmails = list;
    }

    /**
     * @param string
     */
    protected void setSubject(String string) {
        subject = string;
    }

    /* (non-Javadoc)
      * @see com.hps.july.mail.template.MailTemplate#validateProperties()
      */
    public void validateProperties() {
        // TODO Auto-generated method stub
    }

}
