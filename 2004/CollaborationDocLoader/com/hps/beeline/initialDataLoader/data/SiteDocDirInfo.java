package com.hps.beeline.initialDataLoader.data;

import com.hps.beeline.imageLoader.data.ImageDirInfo;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.beeline.LogHelper;
import com.hps.framework.log.MessageService;
import com.hps.beeline.global.data.AbstractDirectoryInfo;
import com.hps.beeline.global.helper.FileHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.MessageService;
import com.hps.framework.log.AppLog;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 22.03.2005
 * Time: 16:53:10
 * To change this template use File | Settings | File Templates.
 */
public class SiteDocDirInfo extends AbstractDirectoryInfo {    
    protected Integer siteDoc = null;
    protected SiteDocInfo data = new SiteDocInfo();
    private String subDirName;
    private String typeName;

    public SiteDocDirInfo(String subDirName, String typeName) {
        this.subDirName = subDirName;
        this.typeName = typeName;
    }

    public void init(Integer idQuery, Integer wlanId, Integer dumpsId, Integer gsmId, File dir) throws BaseException {
        if(subDirName!=null)
            dir = FileHelper.getSubDir(dir, subDirName);
        super.init(idQuery, wlanId, dumpsId, gsmId, dir);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void processSubFiles() {
        File[] subDurs = dir.listFiles();
        for(int i=0;i<subDurs.length;i++) {
            File file = subDurs[i];
            AppLog.info("process file="+file.getName());
            if(!file.isDirectory())
                processFile(file);
        }
    }

    private void processFile(File file) {
        try{
            data.init(file, siteDoc);
            data.createOrUpdate();
        }catch(BaseException e) {
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            MessageService.getInstance().addMessage("Во время загрузки начальных данных произошла ошибка, см. лог");
        }
    }

    public void calculateStoragePlace(AbstractStoragePlaceHelper helper) throws BaseException {
        super.calculateStoragePlace(helper);
        siteDoc = SiteDocHelper.getInstance().getSiteDoc(storagePlaceId, typeName);
    }

}
