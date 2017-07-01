package com.hps.july.rts.mail.template;

import com.hps.july.rts.object.request.InitiatorRequest;

import java.util.Collection;

public abstract class InitiatorRequestTemplate extends RTSMailTemplate {

    protected InitiatorRequest request;
    protected Collection mailPersonList;
    protected StringBuffer reference; 

    public InitiatorRequestTemplate(InitiatorRequest request, Collection mailPersonList) {
        this.request = request;
        this.mailPersonList = mailPersonList;
    }

    public InitiatorRequest getRequest() {
        return request;
    }
    public void setRequest(InitiatorRequest request) {
        this.request = request;
    }

    public void validateProperties() {
        if (request == null)
            throw new IllegalArgumentException("InitiatorRequest must be defined");
    }

}
