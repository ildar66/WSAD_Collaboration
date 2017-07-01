package com.hps.beeline.imageLoader.data;

import com.hps.framework.utils.*;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.beeline.imageLoader.helper.ImageHelper;
import com.hps.beeline.imageLoader.helper.PhotoDatabaseHelper;

import java.io.File;
import java.sql.Date;
import java.sql.Types;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 16.03.2005
 * Time: 17:30:43
 * To change this template use File | Settings | File Templates.
 */
public class ImageInfo {
    private File file;
    private Date lastModified;
    private Date created;
    private long size;
    private Integer storagePlaceId;
    private String  description;
    private String  groupName;
    private Integer photoId;

    private boolean isNeedUpdate = false;
    private boolean isNeedCreate = false;

    public ImageInfo() {
    }

    public void init(File file, Integer storagePlaceId, String groupName) {
        this.groupName = groupName;
        this.description = "Автоматическая загрузка, время загрузки "+new java.util.Date().toString();
        this.storagePlaceId = storagePlaceId;
        this.file = file;

        long time = file.lastModified();
        long timeCreation = Win32FileAttributes.getCreationDateAsLong(file);
        this.lastModified = DateUtil.truncDateToSeconds(time);
        this.created = DateUtil.truncDateToSeconds(timeCreation);

        this.size = file.length();
    }


    public void createOrUpdate() throws BaseException {
        PhotoDatabaseHelper.getInstance().checkExistance(this);
        if(isNeedCreate) {
            PhotoDatabaseHelper.getInstance().create(this);
            //System.out.println("create image");
        }
        if(isNeedUpdate) {
            PhotoDatabaseHelper.getInstance().update(this);
            //System.out.println("update image");
        }
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

    public Integer getStoragePlaceId() {
        return storagePlaceId;
    }

    public File getFile() {
        return file;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public long getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public String getGroupName() {
        return groupName;
    }

    public Date getCreated() {
        return created;
    }
}
