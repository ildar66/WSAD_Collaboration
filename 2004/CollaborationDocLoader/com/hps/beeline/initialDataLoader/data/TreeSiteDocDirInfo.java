package com.hps.beeline.initialDataLoader.data;

import com.hps.beeline.imageLoader.data.ImageDirInfo;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.beeline.LogHelper;
import com.hps.framework.log.MessageService;
import com.hps.framework.log.AppLog;
import com.hps.beeline.global.data.AbstractDirectoryInfo;
import com.hps.beeline.global.helper.FileHelper;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 22.03.2005
 * Time: 16:53:10
 * To change this template use File | Settings | File Templates.
 */
public class TreeSiteDocDirInfo extends AbstractDirectoryInfo {
    protected SiteDocInfo data = new SiteDocInfo();
    private Map siteDocTypes = new HashMap();
    protected Integer siteDoc = null;

    public TreeSiteDocDirInfo(SiteDocTypeInfo[] siteDocArray) {
        for (int i = 0; i < siteDocArray.length; i++) {
            siteDocTypes.put(siteDocArray[i].getPublicName(), siteDocArray[i]);
        }
    }

    public void processSubFiles() {
        AppLog.info("storagePlaceId="+storagePlaceId);
        File[] subFiles = dir.listFiles();
        for (int i = 0; i < subFiles.length; i++) {
            File file = subFiles[i];
            try {
                if (file.isDirectory()) {
                    SiteDocTypeInfo info = (SiteDocTypeInfo) siteDocTypes.get(file.getName());
                    if (info != null) {
                        AppLog.info("process type directory=" + file.getName());
                        //siteDoc = info.getTypeCode();
                        siteDoc = SiteDocHelper.getInstance().getSiteDoc(storagePlaceId, info.getInternalAlias());
                        processSubDir(file, "Автоматическая загрузка -" + info.getPublicName() + " ");
                    }
                }
            } catch (BaseException e) {
                e.printStackTrace();
                LogHelper.logError(idQuery, e.getMessage());
                MessageService.getInstance().addMessage("Во время загрузки произошла ошибка, см. лог");
            }
        }
    }


    private void processSubDir(File subDir, String path) {
        File[] subFiles = subDir.listFiles();
        for (int i = 0; i < subFiles.length; i++) {
            File file = subFiles[i];
            if (!file.isDirectory()) {
                AppLog.info("process file=" + file.getName());
                processFile(file, path);
            } else {
                AppLog.info("process sub dir=" + file.getName());
                processSubDir(file, path + "-" + file.getName());
            }

        }
    }

    private void processFile(File file, String path) {
        try {
            data.init(file, siteDoc, path);
            data.createOrUpdate();
        } catch (BaseException e) {
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            MessageService.getInstance().addMessage("Во время загрузки произошла ошибка, см. лог");
        }
    }

    public void calculateStoragePlace(AbstractStoragePlaceHelper helper) throws BaseException {
        super.calculateStoragePlace(helper);
    }

}
