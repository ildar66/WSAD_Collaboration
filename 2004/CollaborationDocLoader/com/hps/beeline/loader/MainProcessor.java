package com.hps.beeline.loader;

import com.hps.beeline.loader.helpers.DirectoryHelper;
import com.hps.beeline.LogHelper;
import com.hps.beeline.loader.helpers.PositionsHelper;
import com.hps.beeline.loader.helpers.SiteDocHelper;
import com.hps.beeline.loader.info.ExtDocInfo;
import com.hps.beeline.Config;
import com.hps.beeline.LogHelper;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;

import java.sql.Connection;
import java.sql.Date;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 05.11.2004
 * Time: 15:42:59
 * To change this template use File | Settings | File Templates.
 */
public class MainProcessor {

    private MainProcessor() {
    }

    /**
     * Загрузка карт
     * @param idQuery - номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных для ведения журнала
     * @param lastUpdateDate - последнее время обновления
     * @param blobDirPath - путь к директории с файлами например "L:\\BaseSt\\Base map\\ALLMAP"
     * @throws LoaderException
     */

    public static void loadWayMaps(Integer idQuery, Connection connection, Connection logConnection,  Date lastUpdateDate,String blobDirPath) throws LoaderException {
        System.out.println(new java.util.Date() +" Старт 'Загрузка маршрутных карт', idQuery="+idQuery+" дата последнего обновления="+lastUpdateDate+" директория с файлами="+blobDirPath );
        int count = 0;
        try {        
            Config.getInstance().init(connection, logConnection);
            DocConfig.getInstance().init(lastUpdateDate, blobDirPath);
            DirectoryHelper dirHelper = DirectoryHelper.getInstance();
            String errorText = null;

            dirHelper.init();
            Iterator wayMaps = PositionsHelper.findAllWayMaps(lastUpdateDate).iterator();
            while (wayMaps.hasNext()) {
                try {
                    ExtDocInfo info = (ExtDocInfo) wayMaps.next();
                    dirHelper.findFile(info, DirectoryHelper.WAY_MAPS);
                    SiteDocHelper.findSiteDoc(info);
                    if (info.isBlobUpdatable()) {
                        dirHelper.loadFile(idQuery, info);
                        System.out.println("Обновлен файл "+info.getFile().getName()+" sitedocfile="+info.getSitedocfile()+" gsmId="+info.getGsmId()+" dampsId="+info.getDumpsId()+" wlanId="+info.getWlanId());
                        //LogHelper.logInfo(idQuery, "Обновлен файл "+info.getFile().getName()+" sitedocfile="+info.getSitedocfile()+" gsmId="+info.getGsmId()+" dampsId="+info.getDumpsId()+" wlanId="+info.getWlanId());
                    }
                } catch (BaseException e) {
                    System.out.println("error text=" + e.getMessage());
                    LogHelper.logError(idQuery, e.getMessage());
                    errorText = "Во время загрузки маршрутных карт произошла ошибка, см. лог";
                }
                count++;
            }
            Config.getInstance().clearInfo();
            System.out.println(new java.util.Date() +" Функция 'Загрузка маршрутных карт' завершила работу! Обработано "+count+" записей");
            if(errorText!=null)
                throw new LoaderException(errorText);
        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" Функция 'Загрузка маршрутных карт' завершила работу! Обработано "+count+" записей");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        }

    }


    /**
     * Загрузка списков прохода
     * @param idQuery - номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных для ведения журнала
     * @param lastUpdateDate - последнее время обновления
     * @param blobDirPath - путь к директории с файлами например "L:\\BaseSt\\Base map\\ALLMAP"
     * @throws LoaderException
     */

    public static void loadPassLists(Integer idQuery, Connection connection, Connection logConnection,  Date lastUpdateDate,String blobDirPath) throws LoaderException {
        System.out.println(new java.util.Date() +" Старт 'Загрузка списков прохода', idQuery="+idQuery+" дата последнего обновления="+lastUpdateDate+" директория с файлами="+blobDirPath );
        int count = 0;
        String errorText = null;
        try {
            Config.getInstance().init(connection, logConnection);
            DocConfig.getInstance().init(lastUpdateDate, blobDirPath);
            DirectoryHelper dirHelper = DirectoryHelper.getInstance();
            dirHelper.init();
            Iterator wayMaps = PositionsHelper.findAllPassList(lastUpdateDate).iterator();
            while (wayMaps.hasNext()) {
                try {
                    ExtDocInfo info = (ExtDocInfo) wayMaps.next();
                    dirHelper.findFiles(info, DirectoryHelper.PASS_LIST);
                    SiteDocHelper.processFileList(info.getPassFiles().iterator());
                    dirHelper.loadFiles(idQuery, info.getPassFiles().iterator());
                    System.out.println("Карта прохода обработана, sitedocfile="+info.getSitedocfile()+" gsmId="+info.getGsmId()+" dampsId="+info.getDumpsId()+" wlanId="+info.getWlanId());
                } catch (BaseException e) {
                    e.printStackTrace();
                    System.out.println("error text=" + e.getMessage());
                    LogHelper.logError(idQuery, e.getMessage());
                    errorText = "Во время загрузки списков прохода произошла ошибка, см. лог";
                }
                count++;
            }
            Config.getInstance().clearInfo();
            System.out.println(new java.util.Date() +" Функция 'Загрузка списков прохода' завершила работу! Обработано "+count+" записей");
            if(errorText!=null)
                throw new LoaderException(errorText);
        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" Функция 'Загрузка списков прохода' завершила работу! Обработано "+count+" записей");
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        }

    }

    public static void main(String[] args) throws BaseException, LoaderException {
//        MainProcessor.loadWayMaps(new Integer(1),
//                Config.getTestDatabaseConnection(),
//                Config.getTestDatabaseConnection(),
//                null,
//                //"L:\\BaseSt\\Base map\\ALLMAP");
//                "F:\\Beeline\\testData");

        MainProcessor.loadPassLists(new Integer(1),
                Config.getTestDatabaseConnection(),
                Config.getTestDatabaseConnection(),
                null,
                //"L:\\BaseSt\\Base map\\ALLMAP");
                "F:\\Beeline\\testData");
    }
}
