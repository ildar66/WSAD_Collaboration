package com.hps.july.terrabyte.imp.essence;

import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 03.10.2005
 * Time: 16:02:02
 */
public class BSProjectFileInfo extends AbstractFileInfo {

    private Integer version;
    private Integer isArchive;
    private Integer isAgree;
    private String mimeType;
    private Integer projectId;
    private Date lastModified;
    private String login;

    public BSProjectFileInfo() {
        version = null;
        isArchive = null;
        isAgree = null;
        mimeType = null;
        projectId = null;
    }

    public Integer getAgree() {
        return isAgree;
    }

    public void setAgree(Integer agree) {
        if(agree == null) isAgree = new Integer(0);
        else isAgree = agree;
    }

    public Integer getArchive() {
        return isArchive;
    }

    public void setArchive(Integer archive) {
        if(archive == null) isArchive = new Integer(0);
        else isArchive = archive;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public long getLastModified() {
        return lastModified.getTime();
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
