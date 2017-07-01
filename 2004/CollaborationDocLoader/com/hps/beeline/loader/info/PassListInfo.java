package com.hps.beeline.loader.info;

import java.io.File;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 24.11.2004
 * Time: 12:17:18
 * To change this template use File | Settings | File Templates.
 */
public class PassListInfo implements SiteDocInfo {
    private File file;
    private Date fileDate;
    private Date expireDate;
    private Integer sitedocfile;

    private boolean isBlobUpdatable;
    private Integer storageplace;
    private int year;
    private boolean isWarning;

    public PassListInfo(File file) {
        this.file = file;
        fileDate = new Date(file.lastModified());
    }

    public boolean isWarning() {
        return isWarning;
    }

    public void setWarning(boolean warning) {
        isWarning = warning;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


    public File getFile() {
        return file;
    }

    public Integer getSitedocfile() {
        return sitedocfile;
    }

    public void setSitedocfile(Integer sitedocfile) {
        this.sitedocfile = sitedocfile;
    }
        

    public boolean isBlobUpdatable() {
        return isBlobUpdatable;
    }

    public void setBlobUpdatable(boolean blobUpdatable) {
        isBlobUpdatable = blobUpdatable;
    }

    public Integer getStorageplace() {
        return storageplace;
    }

    public void setStorageplace(Integer storageplace) {
        this.storageplace = storageplace;
    }
}
