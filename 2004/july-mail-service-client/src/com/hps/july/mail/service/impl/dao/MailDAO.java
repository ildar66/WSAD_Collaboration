package com.hps.july.mail.service.impl.dao;

import com.hps.july.mail.object.MailTransportObject;
import com.hps.july.mail.MailException;

public interface MailDAO {

    void sendMail(MailTransportObject mailTransportObject) throws MailException;

}
