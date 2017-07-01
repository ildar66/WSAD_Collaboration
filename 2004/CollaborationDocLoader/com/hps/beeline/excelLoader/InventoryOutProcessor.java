package com.hps.beeline.excelLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LogHelper;
import com.hps.beeline.LoaderException;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.beeline.excelLoader.helpers.*;
import com.hps.beeline.excelLoader.info.ExcelFileInfo;
import com.hps.beeline.excelLoader.info.InventorySheetInfo;
import com.hps.beeline.loader.DocConfig;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.log.AppLog;

import java.sql.Connection;
import java.util.Iterator;
import java.util.Collection;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:34:59
 * To change this template use File | Settings | File Templates.
 */
public class InventoryOutProcessor {

    private InventoryOutProcessor() {
    }

    /**
     * Загрузка расходов из Excel файлов
     * @param idQuery - номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение для посылки лога
     * @param excelFilePath - путь к файлу excel
     * @throws LoaderException
     */

    public static void loadInventoryOutDocs(Integer idQuery, Connection connection, Connection logConnection,  String excelFilePath) throws LoaderException {
        AppLog.setThreadLogLevel(AppLog.INFO_LEVEL);
        System.out.println(new java.util.Date() +" Старт 'Загрузка расходов из Excel файлов', idQuery="+idQuery+" файл="+excelFilePath );
        File file = null;
        int count = 0;
        try {
            //File file = new File(excelFilePath);
            file = ExcelHelper.getCopyOfFile(excelFilePath);
            Config.getInstance().init(connection, logConnection);
            Iterator sheets = InventoryHelper.getSheetList(file).iterator();
            while(sheets.hasNext()) {
                InventorySheetInfo sheet = (InventorySheetInfo) sheets.next();
                if(sheet.isNew())
                    count+=InventoryHelper.loadSheet(file,sheet);
            }
            Config.getInstance().clearInfo();
            System.out.println(new java.util.Date() +" Функция 'Загрузка расходов из Excel файлов' завершила работу! Обработано "+count+" записей");
        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" Функция 'ЗЗагрузка расходов из Excel файлов' завершила работу! Обработано "+count+" записей");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        } finally{
            AppLog.setDefaultLogLevel();
            if(file!=null)
                file.delete();
        }
    }




    public static void main(String[] args) throws BaseException, LoaderException {
          File f = ExcelHelper.getCopyOfFile("P:\\Книга1.xls");

//
//        InventoryOutProcessor.loadInventoryOutDocs(new Integer(1),
//                Config.getTestDatabaseConnection(),
//                Config.getTestDatabaseConnection(),
//                //"F:\\Beeline\\excelFiles\\sklad");
//                "F:\\Beeline\\InventoryOutDocs\\8-расход весь с 19.11.03.xls");
//
//        InventoryOutProcessor.loadInventoryOutDocs(new Integer(1),
//                Config.getTestDatabaseConnection(),
//                Config.getTestDatabaseConnection(),
//                //"F:\\Beeline\\excelFiles\\sklad");
//                "F:\\Beeline\\InventoryOutDocs\\8-расход весь с 19.11.03.xls");
        
                //"F:\\Beeline\\InventoryOutDocs\\Copy of test.xls");


//          InventoryOutProcessor.updateFileListInfo(
//            Config.getTestDatabaseConnection(),
//            "F:\\Beeline\\excelFiles\\sklad");
    }
}
