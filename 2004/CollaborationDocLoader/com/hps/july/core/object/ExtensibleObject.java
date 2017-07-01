package com.hps.july.core.object;

import java.security.acl.Acl;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 19.04.2005
 * Time: 17:20:31
 */
public class ExtensibleObject {

    private Integer ident;
    private String name;
    private String description;
    private boolean isRead;
    private boolean isWrite;

    private Acl acl = null;

    public ExtensibleObject() {
        this.ident = null;
        this.name = null;
        this.description = null;
        this.isRead = false;
        this.isWrite = false;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int hashCode() {
        int result;
        result = (getIdent() != null)?getIdent().hashCode():0;
        result = 29 * result + ((getName() != null)?getName().hashCode():0);
        return result;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof ExtensibleObject)) return false;
        ExtensibleObject key = (ExtensibleObject)obj;
        boolean result = false;
        boolean result1 = false;
        if(this.getIdent() == null && key.getIdent() == null) result = true;
        if(this.getIdent() != null && key.getIdent() != null
                && this.getIdent().equals(key.getIdent()))
                result = true;
        if(this.getName() == null && key.getName() == null) result1 = true;
        if(this.getName() != null && key.getName() != null
                && this.getName().equals(key.getName()))
                result1 = true;
        return (result && result1);
    }

}
