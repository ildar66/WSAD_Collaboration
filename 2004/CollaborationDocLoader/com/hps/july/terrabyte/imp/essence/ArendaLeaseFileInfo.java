package com.hps.july.terrabyte.imp.essence;

import java.sql.Timestamp;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 03.10.2005
 * Time: 16:02:02
 */
public class ArendaLeaseFileInfo extends AbstractFileInfo {

    private Integer idContract;
    private Integer leaseDocId;
    private String docFileName;
    private Timestamp created;
    private Timestamp lastModified;
    private Integer createdBy;
    private Integer modifiedBy;

    public ArendaLeaseFileInfo() {
        idContract = null;
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

    public Integer getIdContract() {
        return idContract;
    }

    public void setIdContract(Integer idContract) {
        this.idContract = idContract;
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

    public Integer getLeaseDocId() {
        return leaseDocId;
    }

    public void setLeaseDocId(Integer leaseDocId) {
        this.leaseDocId = leaseDocId;
    }
}
