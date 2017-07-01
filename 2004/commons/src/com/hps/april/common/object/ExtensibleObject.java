package com.hps.april.common.object;

import java.security.acl.Acl;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 19.04.2005
 * Time: 17:20:31
 */
public class ExtensibleObject extends ValueObject {

    private boolean isRead;
    private boolean isWrite;

    private Acl acl = null;

    public ExtensibleObject() {
        setId(null);
        setName(null);
        this.isRead = false;
        this.isWrite = false;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public boolean getRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean getWrite() {
        return isWrite;
    }

    public void setWrite(boolean write) {
        isWrite = write;
    }

}
