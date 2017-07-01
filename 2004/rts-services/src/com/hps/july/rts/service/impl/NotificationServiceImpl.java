package com.hps.july.rts.service.impl;

import com.hps.july.rts.core.process.DefaultProcess;
import com.hps.april.auth.object.Person;
import com.hps.july.rts.SystemException;
import com.hps.july.rts.service.NotificationService;
import com.hps.july.rts.service.RTSOperatorService;
import com.hps.july.rts.service.impl.dao.NotificationDAO;
import com.hps.july.rts.object.notificationlist.NotificationItem;

import java.util.Collection;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 02.03.2006
 * Time: 16:18:09
 */
public class NotificationServiceImpl
        extends DefaultProcess implements NotificationService {

    private NotificationDAO notificationDAO;
    private RTSOperatorService operatorService;


    public NotificationServiceImpl() {
    }

    public NotificationDAO getNotificationDAO() {
        return notificationDAO;
    }

    public void setNotificationDAO(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    public RTSOperatorService getOperatorService() {
        return operatorService;
    }

    public void setOperatorService(RTSOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public Collection findNotificationList(String requestNumber, Person currentPerson) {
        return notificationDAO.findNotificationList(requestNumber, currentPerson);
    }

    public void saveNotificationItem(String requestNumber, Integer personId, Person owner) throws SystemException {
        Person recipient = operatorService.findPersonsById(personId);
        if(recipient == null) return;
        System.out.println("recep ["+recipient+"]");
        NotificationItem item = new NotificationItem();
        item.setOwnerId(owner.getId());
        item.setRecipientId(recipient.getId());
        item.setRequestId(requestNumber);
        notificationDAO.saveNotificationItem(item);
    }

    public void removeNotificationList(String requestNumber, Person currentPerson) throws SystemException {
        try {
            Collection items = findNotificationList(requestNumber, currentPerson);
            notificationDAO.removeNotificationList(items);
        } catch (Exception e) {
            System.out.println("Error removeNotificationList " + e.toString());
            throw new SystemException("Error removing notification list, cause " + e.getMessage());
        }
    }
}
