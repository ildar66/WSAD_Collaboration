package com.hps.beeline.mdbLoader;

import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.LoaderException;
import com.hps.beeline.LogHelper;
import com.hps.beeline.Config;
import com.hps.beeline.mdbLoader.Helper.MdbInventoryHelper;
import com.hps.beeline.mdbLoader.beans.inventory.MdbInventoryLoadFactory;
import com.hps.beeline.mdbLoader.beans.inventoryRegActs.InventoryRegActLoadFactory;
import com.hps.beeline.mdbLoader.processor.MdbInventoryProcessor;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 19.05.2005
 * Time: 14:02:15
 * To change this template use File | Settings | File Templates.
 */
public class MdbRegActsLoader {
    private static final String FUNC_REG_ACTS="'«агрузка актов регистрации из MDB файла'";

    /**
     * «агрузка актов регистрации
     * @param idQuery -  номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных
     * @param mdbFilePath - путь к файлу MDB с данным
     * @throws LoaderException
     */

    public static void loadInventoryRegistryActs(Integer idQuery, Connection connection, Connection logConnection,  String mdbFilePath) throws LoaderException {
        AppLog.setThreadLogLevel(AppLog.INFO_LEVEL);
        MdbInventoryProcessor.processFile(idQuery, connection, logConnection,
                                          FUNC_REG_ACTS, mdbFilePath, new InventoryRegActLoadFactory());
    }

     public static void main(String[] args) throws BaseException, LoaderException {
        try {
            System.out.println("load InventoryRegistryActs!!!");
            if (args.length == 0) {
                loadInventoryRegistryActs(new Integer(3),
                        Config.getTestDatabaseConnection(),
                        Config.getTestDatabaseConnection(),
                        "F:\\Beeline\\testData\\InventoryMdb");
                //"F:\\",1200);
            } else {
                Connection conn = Config.getTestDatabaseConnection(args[0], args[1], args[2]);
                loadInventoryRegistryActs(new Integer(3),
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
