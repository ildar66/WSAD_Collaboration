/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 28.03.2005
 * Time: 17:10:00
 * To change this template use File | Settings | File Templates.
 */
package com.hps.beeline.global.loader;

import com.hps.beeline.Config;
import com.hps.beeline.LoaderException;
import com.hps.beeline.LogHelper;
import com.hps.beeline.global.data.AbstractDirectoryInfo;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.beeline.global.helper.FileHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.log.MessageService;

import java.io.File;
import java.sql.Connection;

public class PositionLoader {
    private static PositionLoader ourInstance = new PositionLoader();

    public static PositionLoader getInstance() {
        return ourInstance;
    }

    private PositionLoader() {
    }

    public void loadPositionFiles(Integer idQuery, Connection connection, Connection logConnection,
                             String initialDataDirPath, AbstractDirectoryInfo dirInfo,
                             String description, AbstractStoragePlaceHelper helper) throws LoaderException {
        System.out.println(new java.util.Date() +" Старт '"+description+"' из директории, idQuery="+idQuery+" директория="+initialDataDirPath );
        //AppLog.setThreadLogLevel(AppLog.DEBUG_LEVEL);
        int count = 0;
        MessageService.getInstance().init();

        try {
            Config.getInstance().init(connection, logConnection);
            helper.initSession(idQuery);

            File[] files = FileHelper.getFiles(initialDataDirPath);
            for(int i=0;i<files.length ;i++) {
                count++;
                try{
                    File place = files[i];
                    dirInfo.init(idQuery,place);
                    if(dirInfo.isOk()) {
                        dirInfo.calculateStoragePlace(helper);
                        AppLog.info("process dir "+place.getName());
                        dirInfo.processSubFiles();
                    }
                }catch (BaseException e) {
                    e.printStackTrace();
                    LogHelper.logError(idQuery, e.getMessage());
                    MessageService.getInstance().addMessage("Во время загрузки начальных данных произошла ошибка, см. лог");
                }
            }
            MessageService.getInstance().checkForErrors();
            System.out.println(new java.util.Date() +" Функция '"+description+"' завершила работу! Обработано "+count+" позиций");
        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" Функция '"+description+"' завершила работу! Обработано "+count+" позиций");
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        } finally{
            Config.getInstance().clearInfo();
            helper.closeSession();
            AppLog.setDefaultLogLevel();
        }
    }
}
