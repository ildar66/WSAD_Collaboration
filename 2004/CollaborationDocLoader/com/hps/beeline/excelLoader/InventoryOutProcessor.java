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
     * �������� �������� �� Excel ������
     * @param idQuery - ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� ��� ������� ����
     * @param excelFilePath - ���� � ����� excel
     * @throws LoaderException
     */

    public static void loadInventoryOutDocs(Integer idQuery, Connection connection, Connection logConnection,  String excelFilePath) throws LoaderException {
        AppLog.setThreadLogLevel(AppLog.INFO_LEVEL);
        System.out.println(new java.util.Date() +" ����� '�������� �������� �� Excel ������', idQuery="+idQuery+" ����="+excelFilePath );
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
            System.out.println(new java.util.Date() +" ������� '�������� �������� �� Excel ������' ��������� ������! ���������� "+count+" �������");
        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" ������� '��������� �������� �� Excel ������' ��������� ������! ���������� "+count+" �������");
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
          File f = ExcelHelper.getCopyOfFile("P:\\�����1.xls");

//
//        InventoryOutProcessor.loadInventoryOutDocs(new Integer(1),
//                Config.getTestDatabaseConnection(),
//                Config.getTestDatabaseConnection(),
//                //"F:\\Beeline\\excelFiles\\sklad");
//                "F:\\Beeline\\InventoryOutDocs\\8-������ ���� � 19.11.03.xls");
//
//        InventoryOutProcessor.loadInventoryOutDocs(new Integer(1),
//                Config.getTestDatabaseConnection(),
//                Config.getTestDatabaseConnection(),
//                //"F:\\Beeline\\excelFiles\\sklad");
//                "F:\\Beeline\\InventoryOutDocs\\8-������ ���� � 19.11.03.xls");
        
                //"F:\\Beeline\\InventoryOutDocs\\Copy of test.xls");


//          InventoryOutProcessor.updateFileListInfo(
//            Config.getTestDatabaseConnection(),
//            "F:\\Beeline\\excelFiles\\sklad");
    }
}
