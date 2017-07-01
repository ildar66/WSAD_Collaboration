package com.hps.july.rts.service.impl.dao;

import com.hps.april.auth.object.Person;
import com.hps.july.rts.object.notificationlist.NotificationItem;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 02.03.2006
 * Time: 16:22:33
 */
public interface NotificationDAO {

    public Collection findNotificationList(String requestNumber, Person currentPerson);

    public void saveNotificationItem(NotificationItem item);

    public void removeNotificationItem(NotificationItem item);

    public void removeNotificationList(Collection items);

}
