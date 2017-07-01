package com.hps.beeline.initialDataLoader.data;

import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.utils.DateUtil;
import com.hps.framework.utils.Win32FileAttributes;

import java.io.File;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 16.03.2005
 * Time: 17:30:43
 * To change this template use File | Settings | File Templates.
 */
public class SiteDocInfo {
    private File file;
    private Date lastModified;
    private long size;
    private Integer siteDoc;
    private String note=null;


    private boolean isNeedUpdate = false;
    private boolean isNeedCreate = false;
    private Date created;

    public SiteDocInfo() {
    }

    public void init(File file, Integer siteDoc) {
        init(file, siteDoc, null);
    }

    public void init(File file, Integer siteDoc, String note) {

        if(note==null)
            this.note = "Автоматическая загрузка";   
        else
            this.note = note;

        this.siteDoc = siteDoc;
        this.file = file;

        this.lastModified = DateUtil.truncDateToSeconds(file.lastModified());
        long createdTime = Win32FileAttributes.getAccessDateAsLong(file);
        this.created = DateUtil.truncDateToSeconds(createdTime);
        this.size = file.length();
    }


    public void createOrUpdate() throws BaseException {
        SiteDocHelper.getInstance().updateSiteDocFile(this);
    }



    public boolean isNeedUpdate() {
        return isNeedUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        isNeedUpdate = needUpdate;
    }

    public boolean isNeedCreate() {
        return isNeedCreate;
    }

    public void setNeedCreate(boolean needCreate) {
        isNeedCreate = needCreate;
    }



    public File getFile() {
        return file;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public Integer getSiteDoc() {
        return siteDoc;
    }

    public String getNote() {
        return note;
    }

    public Integer getSize() {
        return new Integer((int)size);
    }

    public Date getCreated() {
        return created;
    }

    
}
