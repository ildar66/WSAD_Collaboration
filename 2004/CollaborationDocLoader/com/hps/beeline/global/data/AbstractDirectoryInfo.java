package com.hps.beeline.global.data;

import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.imageLoader.helper.PhotoDatabaseHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 31.03.2005
 * Time: 11:24:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDirectoryInfo {
    private static final String FILE_TYPE_DUMPS = "a";
    private static final String FILE_TYPE_WLAN = "w";
    protected Integer wlanId;
    protected Integer dumpsId;
    protected Integer gsmId;
    protected Integer storagePlaceId;
    protected File dir;
    protected Integer idQuery;
    protected boolean isOk;
    protected int processedFilesCount = 0;
    protected int maxLoadCount=0;

    protected AbstractDirectoryInfo() {
    }

    public void init(Integer idQuery, Integer wlanId, Integer dumpsId, Integer gsmId, File dir) throws BaseException {
        this.idQuery = idQuery;
        this.isOk = true;
        this.wlanId = wlanId;
        this.dumpsId = dumpsId;
        this.gsmId = gsmId;
        this.dir = dir;
    }

    public void init(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void init(Integer idQuery,File place) throws BaseException {
        init(idQuery, place,-1);
    }

    public void init(Integer idQuery,File place, int maxLoadCount) throws BaseException {
        this.maxLoadCount = maxLoadCount;
        Integer wlanId=null, dumpsId=null, gsmId=null;
        String name = place.getName();

        if(name.startsWith(FILE_TYPE_DUMPS)) {
            dumpsId = new Integer(name.substring(1,name.indexOf("_")));
        } else if(name.startsWith(FILE_TYPE_WLAN)) {
            wlanId = new Integer(name.substring(1,name.indexOf("_")));
        } else if(name.charAt(0)>='0' && name.charAt(0)<='9'){
            if(name.indexOf("_")==-1) {
                init(false);
                return;
            }
            gsmId = new Integer(name.substring(0,name.indexOf("_")));
        } else {
            init(false);
            return;
        }

       init(idQuery, wlanId, dumpsId, gsmId, place);
    }

    public Integer getWlanId() {
        return wlanId;
    }

    public Integer getDumpsId() {
        return dumpsId;
    }

    public Integer getGsmId() {
        return gsmId;
    }

    public File getDir() {
        return dir;
    }

    public void calculateStoragePlace(AbstractStoragePlaceHelper helper) throws BaseException {
        storagePlaceId = helper.getStoragePlaceId(idQuery, gsmId, dumpsId, wlanId);
    }

    public abstract void processSubFiles();

    public int getProcessedFilesCount() {
        return processedFilesCount;
    }
}
