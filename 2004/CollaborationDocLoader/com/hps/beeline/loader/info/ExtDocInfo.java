package com.hps.beeline.loader.info;

import com.hps.beeline.loader.info.sqlValueBeans.DbsPositionsBean;

import java.sql.Date;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 05.11.2004
 * Time: 17:00:14
 * To change this template use File | Settings | File Templates.
 */
public class ExtDocInfo implements SiteDocInfo {
    private Integer gsmId,dumpsId,wlanId;
    private Integer storageplace;
    private Integer sitedocfile;
    private Date closeDate;
    private File file;
    private boolean isBlobUpdatable;
    private Collection passFiles;

    public ExtDocInfo(DbsPositionsBean position) {
        this.gsmId = position.getGsmid();
        this.dumpsId = position.getDampsid();
        this.wlanId = position.getWlanid();
        this.closeDate = position.getDatederrick();
        this.storageplace = position.getStorageplace();
//        System.out.println("gsmId="+gsmId);
//        System.out.println("storagePlace="+storageplace);
    }

    public Integer getGsmId() {
        return gsmId;
    }

    public Integer getDumpsId() {
        return dumpsId;
    }

    public Integer getWlanId() {
        return wlanId;
    }

    public Integer getStorageplace() {
        return storageplace;
    }

    public Integer getSitedocfile() {
        return sitedocfile;
    }

    public void setSitedocfile(Integer sitedocfile) {
        this.sitedocfile = sitedocfile;
    }

    

    public Date getCloseDate() {
        return closeDate;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setBlobUpdatable(boolean blobUpdatable) {
        isBlobUpdatable = blobUpdatable;
    }

    public boolean isBlobUpdatable() {
        return isBlobUpdatable;
    }

    public Collection getPassFiles() {
        return passFiles;
    }

    public void setPassFiles(Collection passFiles) {
        this.passFiles = passFiles;
    }
}
