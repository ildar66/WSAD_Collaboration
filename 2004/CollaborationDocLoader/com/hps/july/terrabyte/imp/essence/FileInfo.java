package com.hps.july.terrabyte.imp.essence;

import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 14:56:47
 */
public abstract class FileInfo implements IFileInfo {

    private Integer ident = null;
    private Integer storagePlace = null;
    private Integer type = null;
    private Integer creator = null;
    private String name = null;
    private String descr = null;
    private String photo = null;
    private String exphoto = null;
    private long lastModified;
    private boolean active = false;

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getExphoto() {
        return exphoto;
    }

    public void setExphoto(String exphoto) {
        this.exphoto = exphoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(Integer storagePlace) {
        this.storagePlace = storagePlace;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract long getLastModified();
    public abstract Integer getFileSize();
    public abstract File getFile();

    public boolean equals(Object obj) {
        if(!(obj instanceof FileInfo)) return false;
        FileInfo key = (FileInfo)obj;
        boolean result = false;
        boolean result1 = false;
        if(this.getFileSize() == null && key.getFileSize() == null) result = true;
        if(this.getFileSize() != null && key.getFileSize() != null
                && this.getFileSize().equals(key.getFileSize()))
                result = true;
        if(this.getName() == null && key.getName() == null) result1 = true;
        if(this.getName() != null && key.getName() != null
                && this.getName().equals(key.getName()))
                result1 = true;
        return (result && result1);
    }

}
