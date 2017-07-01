package com.hps.beeline.mdbLoader.processor;

import com.hps.beeline.mdbLoader.Helper.MdbInventoryHelper;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryOutDoc;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryOutDoc;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryLoadFactory;
import com.hps.beeline.mdbLoader.beans.LoadFactory;
import com.hps.beeline.mdbLoader.beans.LoadableBean;
import com.hps.beeline.Config;
import com.hps.beeline.LogHelper;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 19.05.2005
 * Time: 14:24:17
 * To change this template use File | Settings | File Templates.
 */
public class MdbInventoryProcessor {

    public static void processFile(Integer idQuery, Connection connection, Connection logConnection, String functionName, String mdbFilePath, LoadFactory loadFactory) throws LoaderException {
         try {
            Config.getInstance().init(connection, logConnection);
            MdbInventoryHelper.getInstance().initSession(mdbFilePath);

            Integer latestExternalIdInDatabase = loadFactory.getLastLoadedId();
            System.out.println("latest id="+latestExternalIdInDatabase);
            ResultSet allMdbRecords = loadFactory.loadBeans(latestExternalIdInDatabase);
            LoadableBean bean = loadFactory.getBean();
            int count=0, i=0;
            while (allMdbRecords.next()) {
                bean.init(allMdbRecords);
                bean.storeInDb();
                count++;i++;
                if(i==1000) {
                    System.out.println("Loaded "+count+" records");
                    i=0;
                }
            }
            System.out.println(new java.util.Date() +" Функция "+functionName+" завершила работу! Обработано "+count+" записей");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(new java.util.Date() +" Функция "+functionName+" завершила работу!");
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
         }finally{
            AppLog.setDefaultLogLevel();
            MdbInventoryHelper.getInstance().closeSession();
         }
    }
}
