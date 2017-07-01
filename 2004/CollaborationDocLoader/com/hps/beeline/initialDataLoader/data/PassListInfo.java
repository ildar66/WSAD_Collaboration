package com.hps.beeline.initialDataLoader.data;

import com.hps.framework.exception.BaseException;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.beeline.initialDataLoader.helper.PassListHelper;

import java.sql.Date;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 08.04.2005
 * Time: 16:34:20
 * To change this template use File | Settings | File Templates.
 */
public class PassListInfo extends SiteDocInfo {
    private Date expireDate;
    private Integer storagePlace;

    public PassListInfo() {
    }


    public void init(File file, String note, Integer storagePlace, Date expireDate) {
        super.init(file, null, note);
        this.expireDate = expireDate;
        this.storagePlace = storagePlace;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Integer getStoragePlace() {
        return storagePlace;
    }

    public void createOrUpdate() throws BaseException {
        PassListHelper.getInstance().updatePassListFile(this);
    }

}
