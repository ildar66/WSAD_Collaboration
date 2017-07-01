package com.hps.beeline.excelLoader;

import com.hps.beeline.Config;
import com.hps.beeline.LogHelper;
import com.hps.beeline.LoaderException;
import com.hps.beeline.excelLoader.helpers.BalanceDataHelper;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.beeline.excelLoader.helpers.FileInfoHelper;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.beeline.excelLoader.info.ExcelFileInfo;
import com.hps.beeline.loader.DocConfig;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.NonFatalException;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;

import java.sql.Connection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:34:59
 * To change this template use File | Settings | File Templates.
 */
public class BalanceExcelFilesProcessor {

    private BalanceExcelFilesProcessor() {
    }

    /**
     * �������� �������� �� Excel ������
     * @param idQuery - ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� ��� ������� ����
     * @param excelDirPath - ���� � ���������� ��� ����� ����� Excel
     * @throws LoaderException
     */


    public static void processBalanceFiles(Integer idQuery, Connection connection, Connection logConnection,  String excelDirPath) throws LoaderException {
        //AppLog.setThreadLogLevel(AppLog.DEBUG_LEVEL);
        System.out.println(new java.util.Date() +" ����� '�������� �������� �� excel', idQuery="+idQuery+" ���������� � �������="+excelDirPath );
        int count = 0;
        try {
            Config.getInstance().init(connection, logConnection);
            Iterator files = ExcelHelper.getAllFiles(excelDirPath);
            String errorText = null;
            while(files.hasNext()) {
                try{
                    ExcelFileInfo info = (ExcelFileInfo) files.next();
                    info.updateFileId();
                    info.loadFileData();
                    BalanceDataHelper.updateFileData(idQuery, info);
                    System.out.println("��������� ���� "+info.getFile().getName());
                } catch (NonFatalException e) {
                    System.out.println("info text=" + e.getMessage());
                    LogHelper.logWarning(idQuery, e.getMessage());
                } catch (BaseException e) {
                    e.printStackTrace();
                    System.out.println("error text=" + e.getMessage());
                    LogHelper.logError(idQuery, e.getMessage());
                    errorText = "�� ����� �������� �������� ��������� ������, ��. ���";
                }
                count++;
            }
            Config.getInstance().clearInfo();
            System.out.println(new java.util.Date() +" ������� '�������� �������� �� excel' ��������� ������! ���������� "+count+" �������");
            if(errorText!=null)
                throw new LoaderException(errorText);

        } catch (BaseException e) {
            System.out.println(new java.util.Date() +" ������� '�������� �������� �� excel' ��������� ������! ���������� "+count+" �������");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            LogHelper.logError(idQuery, e.getMessage());
            throw new LoaderException(e.getMessage());
        }
    }

    /**
     * ��������� �������� ���������� �� Excel ������ ��������
     * @param connection - ���������� � ����� �����
     * @param excelDirPath - ���� � ���������� ��� ����� ����� Excel
     */


    public static void updateFileListInfo(Connection connection, String excelDirPath)  {
        try{
            Config.getInstance().init(connection, connection);
            Iterator files = ExcelHelper.getAllFiles(excelDirPath);
            FileInfoHelper.clearFilesInfo();
            int count = 0;
            while(files.hasNext()) {
                count++;
                ExcelFileInfo info = (ExcelFileInfo) files.next();
                FileInfoHelper.updateFileInfo(info.getFile(),count);
            }
        } catch (BaseException e) {
            e.printStackTrace();
            System.out.println("error text=" + e.getMessage());
        }
        Config.getInstance().clearInfo();
    }



    public static void main(String[] args) throws BaseException, LoaderException {
        BalanceExcelFilesProcessor.processBalanceFiles(new Integer(1),
                Config.getTestDatabaseConnection(),
                Config.getTestDatabaseConnection(),
                "F:\\Beeline\\excelFiles");


//          BalanceExcelFilesProcessor.updateFileListInfo(
//            Config.getTestDatabaseConnection(),
//            "F:\\Beeline\\excelFiles");
    }
}
