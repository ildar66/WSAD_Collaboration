package com.hps.beeline.loader.info.sqlValueBeans;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 09.11.2004
 * Time: 17:35:56
 * To change this template use File | Settings | File Templates.
 */
public class DbsPositionsBean {
    private Integer dampsid;
    private Integer gsmid;
    private Integer wlanid;
    private Date    datederrick;
    private Integer storageplace;

    public DbsPositionsBean() {
    }

    public Integer getDampsid() {
        return dampsid;
    }

    public void setDampsid(Integer dampsid) {
        this.dampsid = dampsid;
    }

    public Integer getGsmid() {
        return gsmid;
    }

    public void setGsmid(Integer gsmid) {
        this.gsmid = gsmid;
    }

    public Integer getWlanid() {
        return wlanid;
    }

    public void setWlanid(Integer wlanid) {
        this.wlanid = wlanid;
    }

    public Date getDatederrick() {
        return datederrick;
    }

    public void setDatederrick(Date datederrick) {
        this.datederrick = datederrick;
    }

    public Integer getStorageplace() {
        return storageplace;
    }

    public void setStorageplace(Integer storageplace) {
        this.storageplace = storageplace;
    }
}
