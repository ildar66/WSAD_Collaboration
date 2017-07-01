/*
 *  $Id: LeaseMutualActsFileInfo.java,v 1.2 2007/06/15 17:12:48 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.essence.arenda;

import com.hps.july.terrabyte.imp.essence.AbstractFileInfo;
import com.hps.july.terrabyte.core.DocumentTypes;

import java.sql.Timestamp;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 03.10.2005
 * Time: 16:02:02
 */
public class LeaseMutualActsFileInfo extends AbstractFileInfo {

    private Integer idHeader;
    private String docFileName;
    private Timestamp created;
    private Timestamp lastModified;
    private Integer createdBy;
    private Integer modifiedBy;

    public LeaseMutualActsFileInfo() {
        idHeader = null;
        docFileName = null;
        created = null;
        lastModified = null;
        createdBy = null;
        modifiedBy = null;
    }

    public long getLastModified() {
        return lastModified.getTime();
    }

    public Timestamp getModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(Integer idHeader) {
        this.idHeader = idHeader;
    }

    public String getDocFileName() {
        return docFileName;
    }

    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getType() {
        return DocumentTypes.LEASE_MUTUAL_ACTS;
    }

}
