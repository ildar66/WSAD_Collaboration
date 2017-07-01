package com.hps.july.mail;

import com.hps.july.mail.service.MailService;
import com.hps.july.mail.service.impl.MailServiceImpl;
import com.hps.july.mail.service.impl.dao.MailDAOMessageBean;
import com.hps.july.mail.service.impl.dao.HttpMailDao;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 04.07.2006
 * Time: 16:19:59
 */
public class MailServiceFactory {

    private static MailServiceImpl mailService;

    public static MailService createMailService() {
        if(mailService == null) {
            mailService = new MailServiceImpl();
            MailDAOMessageBean dao = new MailDAOMessageBean();
            mailService.setMailDAO(dao);
        }
        return mailService;
    }

    public static MailService createHttpMailService() {
        if(mailService == null) {
            mailService = new MailServiceImpl();
            HttpMailDao dao = new HttpMailDao();
            mailService.setMailDAO(dao);
        }
        return mailService;
    }

}
