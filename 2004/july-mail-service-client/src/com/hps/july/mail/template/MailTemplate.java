/*
 *  $Id: MailTemplate.java,v 1.3 2007/06/02 12:33:14 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.mail.template;

import com.hps.july.mail.object.MailMessage;

import java.util.List;

/**
 * Общий интрефейс для тела письма
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/06/02 12:33:14 $
 * Date: 04.04.2006
 * Time: 14:47:12
 */
public interface MailTemplate {

    public static final String ENCODING_CP1251 = "Cp1251";
    public static final String MIME_TYPE_TEXT_HTML = "text/html";
    public static final String MIME_TYPE_TEXT_PLAIN = "text/plain";

    public void validateProperties();

    public MailMessage createMailMessage();

    public String getFrom();

    public void setFrom(String from);

    public List getTo();

    public List getCC();

    public List getBCC();

    public String getSubject();

    public String getBody();

    public List getAttachmentList();

}
