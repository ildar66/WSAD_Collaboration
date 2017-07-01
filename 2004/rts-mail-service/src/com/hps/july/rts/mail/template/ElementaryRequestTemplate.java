/*
 *  $Id: ElementaryRequestTemplate.java,v 1.2 2007/04/14 16:41:42 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ElementaryRequest;

import java.util.Collection;

/**
 * Класс для обработки темплейтов писем, связанных с Элементарной завкой
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/04/14 16:41:42 $
 */
public abstract class ElementaryRequestTemplate extends RTSMailTemplate {

    protected ElementaryRequest request;
    protected Collection mailPersonList;
    protected StringBuffer reference;

    public ElementaryRequestTemplate() {
    }
    public ElementaryRequestTemplate(ElementaryRequest request) {
        this.request = request;
    }

    protected ElementaryRequestTemplate(ElementaryRequest request, Collection mailPersonList) {
        this.request = request;
        this.mailPersonList = mailPersonList;
    }

    public void validateProperties() {
        if (request == null)
            throw new IllegalArgumentException("ElementaryRequest must be defined");
    }
}
