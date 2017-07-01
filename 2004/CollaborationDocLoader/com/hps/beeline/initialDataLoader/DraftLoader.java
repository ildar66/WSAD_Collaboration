package com.hps.beeline.initialDataLoader;

import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.initialDataLoader.data.SiteDocDirInfo;
import com.hps.beeline.initialDataLoader.data.TreeSiteDocDirInfo;
import com.hps.beeline.initialDataLoader.data.SiteDocTypeInfo;
import com.hps.beeline.global.loader.PositionLoader;
import com.hps.beeline.initialDataLoader.helper.SiteDocHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;

import java.sql.Connection;
import java.util.Iterator;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 31.03.2005
 * Time: 13:36:40
 * To change this template use File | Settings | File Templates.
 */
public class DraftLoader {

    private DraftLoader() {
    }

     /**
     * Загрузка чертежей
     * @param idQuery -  номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных
     * @param initialDataDirPath - путь к директории с чертежами
     * @throws com.hps.beeline.LoaderException
     */

    public static void loadDrafts(Integer idQuery, Connection connection, Connection logConnection,  String initialDataDirPath) throws LoaderException {
        //AppLog.setThreadLogLevel(AppLog.DEBUG_LEVEL);
        TreeSiteDocDirInfo dirInfo = new TreeSiteDocDirInfo(new SiteDocTypeInfo[] {
            new SiteDocTypeInfo("Энергопотребление", "EnergoDocType"),
            new SiteDocTypeInfo("Схема_кабельных_связей", "SchemeCabelDocType"),
            new SiteDocTypeInfo("РРС", "PPCType"),
            new SiteDocTypeInfo("ТУ", "TUDocType"),
        });
        PositionLoader.getInstance().loadPositionFiles(idQuery, connection, logConnection, initialDataDirPath,
                dirInfo,"Загрузка чертежей", SiteDocHelper.getInstance());
    }


    public static void main(String[] args) throws BaseException, LoaderException {
        loadDrafts(new Integer(3),
                Config.getTestDatabaseConnection(),
                Config.getTestDatabaseConnection(),
                "O:\\Чертежи");
                //"F:\\Beeline\\test");
                //"F:\\Beeline\\docs\\чертежи");
    }

}
