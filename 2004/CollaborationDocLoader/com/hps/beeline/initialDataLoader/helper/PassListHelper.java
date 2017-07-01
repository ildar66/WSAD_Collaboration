/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.03.2005
 * Time: 16:14:48
 * To change this template use File | Settings | File Templates.
 */
package com.hps.beeline.initialDataLoader.helper;

import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.beeline.initialDataLoader.data.PassListInfo;
import com.hps.beeline.initialDataLoader.data.SiteDocInfo;
import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.sql.BinaryStreamData;
import com.hps.framework.utils.AdvHashMap;

import java.sql.Date;
import java.sql.Types;
import java.util.Map;

public class PassListHelper extends AbstractStoragePlaceHelper {

    private static PassListHelper ourInstance = new PassListHelper();
    public static PassListHelper getInstance() {
        return ourInstance;
    }

    private PassListHelper() {
    }

    private static final String SQL_FIND_PASS_LIST_DOC = "{call dbsInsPassListDoc(?,?,?,?)}";

    private StoredProc findPassListSiteDocProc;
    private StoredProc updateSiteDocFileProc;;


    public void initSession(Integer idQuery) throws BaseException {
        super.initSession(idQuery);
        findPassListSiteDocProc = new StoredProc("{call dbsInsPassListDoc(?,?,?,?)}");
        updateSiteDocFileProc = new StoredProc("update sitedocfiles " +
                        "set note=?," +
                        "    sitedocfilebody=?," +
                        "    sitedocfiledate=?, " +
                        "    file_size=?, "+
                        "    file_modified=?, "+
                        "    file_created=?, "+
                        "    modified=?, "+
                        "    created=?, "+
                        "    modifiedby=? "+
                        " where sitedocfile=?");
    }

    public void closeSession(){
        super.closeSession();
        try{
            findPassListSiteDocProc.close();
            updateSiteDocFileProc.close();
        }catch(BaseException err) {
            err.printStackTrace();
        }
        findPassListSiteDocProc = null;
    }

    public static Integer findPassListDoc(PassListInfo docInfo) throws BaseException{
        StoredProc proc = new StoredProc(SQL_FIND_PASS_LIST_DOC);
        proc.setObject(1, docInfo.getStoragePlace());
        proc.setObject(2, docInfo.getLastModified());
        proc.setObject(3, docInfo.getFile().getName());
        proc.setObject(4, docInfo.getExpireDate(), Types.DATE);
        Map result = proc.executeFunctionCall();
        Integer errorCode = (Integer) result.get(StoredProc.RESULT1);
        String errorText = (String) result.get(StoredProc.RESULT2);
        Integer siteDoc = (Integer) result.get(StoredProc.RESULT3);
        Integer siteDocFile = (Integer) result.get(StoredProc.RESULT4);
        Integer isBlobUpdatable = (Integer) result.get(StoredProc.RESULT5);

        if(errorCode.intValue()!=0)
            throw new BaseException(errorText);

        if(isBlobUpdatable.intValue()==1)
            return siteDocFile;
        else
            return null;
    }


    public void updatePassListFile(PassListInfo info) throws BaseException {
        Integer autoLoaderPeopleId = getAutoLoaderId();
        Integer siteDocFile = findPassListDoc(info);

        if(siteDocFile!=null) {
            BinaryStreamData data = DirectoryHelper.loadData(info.getFile());
            Date curDate = new Date(System.currentTimeMillis());

            updateSiteDocFileProc.setObject(1, info.getNote(), Types.CHAR);
            updateSiteDocFileProc.setObject(2, data, Types.BLOB);
            updateSiteDocFileProc.setObject(3, info.getLastModified(), Types.DATE);
            updateSiteDocFileProc.setObject(4, info.getSize(), Types.INTEGER);
            updateSiteDocFileProc.setObject(5, info.getLastModified(), Types.DATE);
            updateSiteDocFileProc.setObject(6, info.getCreated(), Types.DATE);
            updateSiteDocFileProc.setObject(7, curDate, Types.DATE);
            updateSiteDocFileProc.setObject(8, curDate, Types.DATE);
            updateSiteDocFileProc.setObject(9, autoLoaderPeopleId, Types.INTEGER);
            updateSiteDocFileProc.setObject(10, siteDocFile, Types.INTEGER);
            updateSiteDocFileProc.executeUpdate();
        }
    }


}
