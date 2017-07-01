package com.hps.beeline.loader.helpers;

import com.hps.framework.exception.BaseException;
import com.hps.beeline.Config;
import com.hps.beeline.loader.info.ExtDocInfo;
import com.hps.beeline.loader.info.SiteDocInfo;
import com.hps.beeline.loader.info.PassListInfo;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.utils.AdvHashMap;
import com.hps.framework.sql.StoredProc;

import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 09.11.2004
 * Time: 16:18:58
 * To change this template use File | Settings | File Templates.
 */
public class SiteDocHelper {
    private static final String SQL_FIND_SITE_DOC = "{call dbsInsWayMapDoc(?,?,?)}";
    private static final String SQL_FIND_PASS_LIST_DOC = "{call dbsInsPassListDoc(?,?,?,?)}";

    private SiteDocHelper() {
    }

    public static void findSiteDoc(SiteDocInfo docInfo) throws BaseException{
        StoredProc proc = new StoredProc(SQL_FIND_SITE_DOC);
        proc.setObject(1, docInfo.getStorageplace());
        proc.setObject(2, new Date(docInfo.getFile().lastModified()));
        proc.setObject(3, docInfo.getFile().getName());
        Map result = proc.executeFunctionCall();
        Integer errorCode = (Integer) result.get(StoredProc.RESULT1);
        String errorText = (String) result.get(StoredProc.RESULT2);
        Integer siteDoc = (Integer) result.get(StoredProc.RESULT3);
        Integer siteDocFile = (Integer) result.get(StoredProc.RESULT4);
        Integer isBlobUpdatable = (Integer) result.get(StoredProc.RESULT5);

        if(errorCode.intValue()!=0)
            throw new BaseException(errorText);

        docInfo.setSitedocfile(siteDocFile);
        if(isBlobUpdatable.intValue()==1)
            docInfo.setBlobUpdatable(true);
        else
            docInfo.setBlobUpdatable(false);
    }

    public static void findPassListDoc(PassListInfo docInfo) throws BaseException{
        StoredProc proc = new StoredProc(SQL_FIND_PASS_LIST_DOC);
        proc.setObject(1, docInfo.getStorageplace());
        proc.setObject(2, new Date(docInfo.getFile().lastModified()));
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

        docInfo.setSitedocfile(siteDocFile);
        if(isBlobUpdatable.intValue()==1)
            docInfo.setBlobUpdatable(true);
        else
            docInfo.setBlobUpdatable(false);
    }

    public static void processFileList(Iterator files) throws BaseException {
        while(files.hasNext()) {
            PassListInfo passInfo = (PassListInfo) files.next();
            findPassListDoc(passInfo);
        }
    }
}
