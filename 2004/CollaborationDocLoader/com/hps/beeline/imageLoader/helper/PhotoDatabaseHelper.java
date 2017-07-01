/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.03.2005
 * Time: 16:14:48
 * To change this template use File | Settings | File Templates.
 */
package com.hps.beeline.imageLoader.helper;

import com.hps.beeline.imageLoader.data.ImageInfo;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;

import java.sql.Timestamp;
import java.sql.Types;
import java.sql.Date;
import java.util.Map;

public class PhotoDatabaseHelper extends  AbstractStoragePlaceHelper {
    private static PhotoDatabaseHelper ourInstance = new PhotoDatabaseHelper();
    public static PhotoDatabaseHelper getInstance() {
        return ourInstance;
    }
    private PhotoDatabaseHelper() {
    }

    private static final int ICON_SIZE=150;
    protected StoredProc checkExistanceProc;
    protected StoredProc insertProc;
    protected StoredProc updateProc;
    protected StoredProc getSequenceProc;

    protected StoredProc updateSmallImageByPhotoId = null;
    protected StoredProc getImageByPhotoId = null;
    protected int maxSize;

    public void init(int maxSize) {
        this.maxSize = maxSize;
    }

    public void initSession(Integer idQuery) throws BaseException {
           super.initSession(idQuery);
           getSequenceProc = new StoredProc("{call GetSequence (\"tables.photos\")}");
           checkExistanceProc = new StoredProc("Select photo,file_size,file_modified from photos " +
                   "where storageplace=? and photofilename=? and groupName=?");
           updateProc = new StoredProc("update photos set " +
                   "  description=?," +
                   "  date=?," +
                   "  photofilename=?," +
                   "  photoimage=?," +
                   "  smallimage=?," +
                   "  photographer=?," +
                   "  mime_type=?," +
                   "  exphotgrname=?, "+
                   "  groupname=?, "+

                   //new
                   "  file_size=?, "+
                   "  file_modified=?, "+
                   "  file_created=?, "+
                   "  modified=?, "+
                   "  modifiedby=? "+

                   "where photo=?");
           insertProc = new StoredProc("insert into photos(" +
                   "  photo," +
                   "  storageplace," +
                   "  description," +
                   "  date," +
                   "  photofilename," +
                   "  photoimage," +
                   "  smallimage," +
                   "  photographer," +
                   "  mime_type," +
                   "  exphotgrname, "+
                   "  groupname , "+

                   //new
                   "  file_size , "+
                   "  file_modified , "+
                   "  file_created , "+
                   "  modified , "+
                   "  created , "+
                   "  createdby , "+
                   "  modifiedby) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        updateSmallImageByPhotoId = new StoredProc("UPDATE photos SET smallimage = ? WHERE photo = ?");
        getImageByPhotoId = new StoredProc("SELECT photofilename, photoimage FROM photos WHERE photo = ?");

    }

     public void closeSession(){
        super.closeSession();
        try{
            checkExistanceProc.close();
            updateProc.close();
            insertProc.close();
            updateSmallImageByPhotoId.close();
            getImageByPhotoId.close();

        }catch(BaseException err) {
            err.printStackTrace();
        }
        checkExistanceProc = null;
        updateProc = null;
        insertProc = null;
        updateSmallImageByPhotoId = null;
        getImageByPhotoId = null;
    }



    public void checkExistance(ImageInfo imageInfo) throws BaseException {
        boolean isNeedUpdate = false;
        boolean isNeedCreate = false;
        checkExistanceProc.setObject(1, imageInfo.getStoragePlaceId(), Types.INTEGER);
        checkExistanceProc.setObject(2, imageInfo.getFile().getName(), Types.CHAR);
        checkExistanceProc.setObject(3, imageInfo.getGroupName(), Types.CHAR);
        Map record = checkExistanceProc.executeQuery();
        if(record==null) {
            isNeedCreate = true;
        } else {
            Integer photoId = (Integer) record.get("photo");
            Integer oldSize = (Integer) record.get("file_size");
            Timestamp oldDateModified = (Timestamp) record.get("file_modified");

            if(oldSize==null || oldSize.intValue()!=(int)imageInfo.getSize() ||
               oldDateModified==null || oldDateModified.getTime()!=imageInfo.getLastModified().getTime()) {
                isNeedUpdate = true;
                imageInfo.setPhotoId(photoId);
            }
        }

        imageInfo.setNeedUpdate(isNeedUpdate);
        imageInfo.setNeedCreate(isNeedCreate);
    }

    public void update(ImageInfo info) throws BaseException {
        Integer autoLoaderPeopleId = getAutoLoaderId();
        byte[] data = DirectoryHelper.loadFile(info.getFile());
        byte[] shortData = ImageHelper.compressImage(data, info.getFile(),ICON_SIZE);
        if(shortData==null) {
            AppLog.info("Файл не является картинкой! "+info.getFile().getAbsolutePath());
            return;
        }
        data = ImageHelper.compressImage(data, info.getFile(), maxSize);
        String fileType = ImageHelper.getMimeType(info.getFile().getName(), maxSize);
        Date curDate = new Date(System.currentTimeMillis());
        updateProc.setObject(1, info.getDescription(), Types.CHAR);
        updateProc.setObject(2, info.getLastModified(), Types.DATE);
        updateProc.setObject(3, info.getFile().getName(), Types.CHAR);
        updateProc.setObject(4, data);
        updateProc.setObject(5, shortData);
        updateProc.setObject(6, autoLoaderPeopleId, Types.INTEGER);   //photographer
        updateProc.setObject(7, fileType, Types.CHAR); //mime type
        updateProc.setObject(8, null, Types.CHAR);                     //exphotgrname varchar(255),
        updateProc.setObject(9, info.getGroupName(), Types.CHAR);      //groupname varchar(128),

        updateProc.setObject(10, new Integer((int)info.getSize()), Types.INTEGER); //file_size integer
        updateProc.setObject(11, info.getLastModified(), Types.DATE);   //file_modified datetime year to second
        updateProc.setObject(12, info.getCreated(), Types.DATE);        //file_modified datetime year to second
        updateProc.setObject(13, curDate, Types.DATE);   //modified datetime year to fraction(3)
        updateProc.setObject(14, autoLoaderPeopleId, Types.INTEGER);       // modifiedby integer
        updateProc.setObject(15, info.getPhotoId(), Types.INTEGER);
        updateProc.executeUpdate();
    }

    public Integer getPk() throws BaseException {
        Map result =  getSequenceProc.executeQuery();
        Integer res = (Integer) result.get(StoredProc.RESULT1);
        return res;
    }


    public void create(ImageInfo info) throws BaseException {
        Integer autoLoaderPeopleId = getAutoLoaderId();
        byte[] data = DirectoryHelper.loadFile(info.getFile());
        byte[] shortData = ImageHelper.compressImage(data, info.getFile(), ICON_SIZE);
        if(shortData==null) {
            AppLog.info("Файл не является картинкой! "+info.getFile().getAbsolutePath());
            return;
        }
        data = ImageHelper.compressImage(data, info.getFile(), maxSize);
        String fileType = ImageHelper.getMimeType(info.getFile().getName(), maxSize);
        Date curDate = new Date(System.currentTimeMillis());
        Integer photoId = getPk();
        insertProc.setObject(1, photoId, Types.INTEGER);
        insertProc.setObject(2, info.getStoragePlaceId(), Types.INTEGER);
        insertProc.setObject(3, info.getDescription(), Types.CHAR);
        insertProc.setObject(4, info.getLastModified(), Types.DATE);
        insertProc.setObject(5, info.getFile().getName(), Types.CHAR);
        insertProc.setObject(6, data);
        insertProc.setObject(7, shortData);
        insertProc.setObject(8, autoLoaderPeopleId, Types.INTEGER);   //photographer
        insertProc.setObject(9, fileType, Types.CHAR); //mime type
        insertProc.setObject(10, null, Types.CHAR);                     //exphotgrname varchar(255),
        insertProc.setObject(11, info.getGroupName(), Types.CHAR);      //groupname varchar(128),
        insertProc.setObject(12, new Integer((int)info.getSize()), Types.INTEGER); //file_size integer
        insertProc.setObject(13, info.getLastModified(), Types.DATE);   //file_modified datetime year to second
        insertProc.setObject(14, info.getCreated(), Types.DATE);        //file_modified datetime year to second
        insertProc.setObject(15, curDate, Types.DATE);   //modified datetime year to fraction(3)
        insertProc.setObject(16, curDate, Types.DATE);        //created datetime year to fraction(3)
        insertProc.setObject(17, autoLoaderPeopleId, Types.INTEGER);       // createdby integer
        insertProc.setObject(18, autoLoaderPeopleId, Types.INTEGER);       // modifiedby integer
        insertProc.executeUpdate();
    }

    public void updateSmallImage(Integer photoId) throws BaseException {
        getImageByPhotoId.setObject(1, photoId, Types.INTEGER);
        Map record = getImageByPhotoId.executeQuery();
        if(record != null) {
            String fileName = (String)record.get("photofilename");
            byte [] image = (byte [])record.get("photoimage");
            if(image != null && image.length > 0) {
                byte [] shortData = ImageHelper.compressImage(image, fileName, fileName, ICON_SIZE);
                if(shortData == null) {
                    AppLog.info("Файл не является картинкой! "+fileName);
                    return;
                }
                updateSmallImageByPhotoId.setObject(1, shortData);
                updateSmallImageByPhotoId.setObject(2, photoId);
                updateSmallImageByPhotoId.executeUpdate();
            }
        }
    }


}
