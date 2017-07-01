package com.hps.july.rts.service;

import com.hps.april.common.Service;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 02.03.2006
 * Time: 16:16:04
 */
public interface NotificationService extends Service {

    public static final String SERVICE_NAME = "july.rts.service.notification";

    public Collection findNotificationList(String requestNumber, Person currentPerson);

    public void saveNotificationItem(String requestNumber, Integer personId, Person owner) throws SystemException;

    public void removeNotificationList(String requestNumber, Person currentPerson) throws SystemException;

}
