package com.hps.july.rts.object.notificationlist;

import com.hps.april.common.object.ExtensibleObject;
import com.hps.april.auth.object.Person;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 06.02.2006
 * Time: 18:41:55
 */
public class NotificationItem extends ExtensibleObject {

    private String requestId;
    private Person owner;
    private Integer ownerId;
    private Person recipient;
    private Integer recipientId;

    public NotificationItem() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }
}
