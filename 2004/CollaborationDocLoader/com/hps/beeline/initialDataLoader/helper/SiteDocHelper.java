/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.03.2005
 * Time: 16:14:48
 * To change this template use File | Settings | File Templates.
 */
package com.hps.beeline.initialDataLoader.helper;

import com.hps.framework.utils.*;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.sql.BinaryStreamData;
import com.hps.beeline.imageLoader.data.ImageInfo;
import com.hps.beeline.imageLoader.helper.PhotoDatabaseHelper;
import com.hps.beeline.imageLoader.helper.ImageHelper;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.beeline.LogHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.beeline.initialDataLoader.data.SiteDocInfo;

import java.sql.Connection;
import java.sql.Types;
import java.sql.Timestamp;
import java.sql.Date;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Map;

public class SiteDocHelper extends AbstractStoragePlaceHelper {


    private static SiteDocHelper ourInstance = new SiteDocHelper();
    public static SiteDocHelper getInstance() {
        return ourInstance;
    }
    private SiteDocHelper() {
    }


    private StoredProc getStoragePlaceProc;
    private StoredProc getSiteDocProc;
    private StoredProc getSiteDocFileProc;
    private StoredProc updateSiteDocFileProc;

    public void initSession(Integer idQuery) throws BaseException {
        super.initSession(idQuery);
        getStoragePlaceProc = new StoredProc("{call dbsFindImagePosition(?,?,?)}");
        getSiteDocProc = new StoredProc("{call dbsCreateSiteDoc(?,?)}");
        getSiteDocFileProc = new StoredProc("{call dbsInsSiteDocFile(?,?,?,?)}");
        updateSiteDocFileProc = new StoredProc("update sitedocfiles " +
                        "set note=?," +
                        "    sitedocfilebody=?," +
                        "    sitedocfiledate=?, " +
                        "    file_size=?, "+
                        "    file_modified=?, "+
                        "    file_created=?, "+
                        "    modified=current, "+
                        //"    created=?, "+
                        "    modifiedby=? "+
                        " where sitedocfile=?");
    }

    public void closeSession(){
        super.closeSession();
        try{
            getStoragePlaceProc.close();
            getSiteDocProc.close();
            getSiteDocFileProc.close();
            updateSiteDocFileProc.close();
        }catch(BaseException err) {
            err.printStackTrace();
        }
        getStoragePlaceProc = null;
    }

    public Integer getSiteDoc(Integer storagePlaceId, String docTypeName) throws BaseException {
        getSiteDocProc.setObject(1, storagePlaceId, Types.INTEGER);
        getSiteDocProc.setObject(2, docTypeName, Types.INTEGER);
        Map result = getSiteDocProc.executeFunctionCall();

        Integer errorCode = (Integer) result.get(StoredProc.RESULT1);
        String errorText = (String) result.get(StoredProc.RESULT2);
        Integer siteDoc = (Integer) result.get(StoredProc.RESULT3);
        if(errorCode.intValue()!=0)
            throw new BaseException(errorText);

        return siteDoc;
    }

    public void updateSiteDocFile(SiteDocInfo info) throws BaseException {
        Date curDate = new Date(System.currentTimeMillis());
        Integer autoLoaderPeopleId = getAutoLoaderId();
        getSiteDocFileProc.setObject(1, info.getSiteDoc(), Types.INTEGER);
        getSiteDocFileProc.setObject(2, info.getLastModified(), Types.DATE);
        getSiteDocFileProc.setObject(3, info.getFile().getName(), Types.DATE);
        getSiteDocFileProc.setObject(4, autoLoaderPeopleId, Types.INTEGER);
        System.out.println("curdate="+curDate);
        Map result = getSiteDocFileProc.executeFunctionCall();
        Integer errorCode = (Integer) result.get(StoredProc.RESULT1);
        String errorText = (String) result.get(StoredProc.RESULT2);
        Integer siteDocFile = (Integer) result.get(StoredProc.RESULT3);
        Integer isUpdate = (Integer) result.get(StoredProc.RESULT4);

        if(errorCode.intValue()!=0)
            throw new BaseException(errorText);

        if(isUpdate.intValue()==1) {
            BinaryStreamData data = DirectoryHelper.loadData(info.getFile());

            updateSiteDocFileProc.setObject(1, info.getNote(), Types.CHAR);
            updateSiteDocFileProc.setObject(2, data, Types.BLOB);
            updateSiteDocFileProc.setObject(3, info.getLastModified(), Types.DATE);
            updateSiteDocFileProc.setObject(4, info.getSize(), Types.INTEGER);
            updateSiteDocFileProc.setObject(5, info.getLastModified(), Types.DATE);
            updateSiteDocFileProc.setObject(6, info.getCreated(), Types.DATE);
            updateSiteDocFileProc.setObject(7, autoLoaderPeopleId, Types.INTEGER);
            updateSiteDocFileProc.setObject(8, siteDocFile, Types.INTEGER);
            updateSiteDocFileProc.executeUpdate();
        }
    }


}
