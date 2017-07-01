package com.hps.beeline.mdbLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LoaderException;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryLoadFactory;
import com.hps.beeline.mdbLoader.processor.MdbInventoryProcessor;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 19.05.2005
 * Time: 14:02:15
 * To change this template use File | Settings | File Templates.
 */
public class MdbInventoryLoader {
    private static final String FUNC_OUT_DOCS="'«агрузка расходов из MDB файла'";

    /**
     * «агрузка расходов
     * @param idQuery -  номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных
     * @param mdbFilePath - путь к файлу MDB с данным
     * @throws LoaderException
     */

    public static void loadInventoryOutDocs(Integer idQuery, Connection connection, Connection logConnection,  String mdbFilePath) throws LoaderException {
        AppLog.setThreadLogLevel(AppLog.INFO_LEVEL);
        MdbInventoryProcessor.processFile(idQuery, connection, logConnection,
                                          FUNC_OUT_DOCS, mdbFilePath, new MdbInventoryLoadFactory());
    }

     public static void main(String[] args) throws BaseException, LoaderException {
        try {
            System.out.println("load InventoryOutDocs!!!");
            if (args.length == 0) {
                loadInventoryOutDocs(new Integer(3),
                        Config.getTestDatabaseConnection(),
                        Config.getTestDatabaseConnection(),
                        "F:\\Beeline\\testData\\InventoryMdb");
                //"F:\\",1200);
            } else {
                Connection conn = Config.getTestDatabaseConnection(args[0], args[1], args[2]);
                loadInventoryOutDocs(new Integer(3),
                        conn,
                        conn,
                        args[3]);
            }
        } catch (LoaderException e) {
            e.printStackTrace(System.out);  //To change body of catch statement use File | Settings | File Templates.
            //"F:\\Beeline\\docs\\mdbData\\расходы.mdb"
            throw e;
        }
     }

}
