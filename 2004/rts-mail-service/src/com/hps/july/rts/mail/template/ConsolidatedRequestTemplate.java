package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.ConsolidatedRequest;

import java.util.Collection;

public abstract class ConsolidatedRequestTemplate extends RTSMailTemplate {

    protected ConsolidatedRequest request;
    protected Collection mailPersonList;
    protected StringBuffer reference;

    public ConsolidatedRequestTemplate() {
    }

    public ConsolidatedRequestTemplate(ConsolidatedRequest request) {
        this.request = request;
    }

    protected ConsolidatedRequestTemplate(ConsolidatedRequest request, Collection mailPersonList) {
        this.request = request;
        this.mailPersonList = mailPersonList;
    }

    public ConsolidatedRequest getRequest() {
        return request;
    }
    public void setRequest(ConsolidatedRequest request) {
        this.request = request;
    }

    public void validateProperties() {
        if (request == null)
            throw new IllegalArgumentException("ConsolidatedRequest must be defined");
    }
}
