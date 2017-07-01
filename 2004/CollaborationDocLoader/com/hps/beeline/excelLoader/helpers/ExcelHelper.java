package com.hps.beeline.excelLoader.helpers;

import com.hps.beeline.excelLoader.info.ExcelFileInfo;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.log.AppLog;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import sun.jdbc.odbc.JdbcOdbcDriver;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:53:09
 * To change this template use File | Settings | File Templates.
 */
public class ExcelHelper {

    private ExcelHelper() {
    }

    public static Iterator getAllFiles(String path) throws BaseException {
        File dir = new File(path);
        if(!dir.exists() || dir.listFiles()==null)
            throw new BaseException("Задана несуществующая директория "+path);
        File[] files = dir.listFiles();
        Collection result = new ArrayList(files.length);
        for(int i=0;i<files.length;i++) {
            if(!files[i].isDirectory()) {
                ExcelFileInfo info = new ExcelFileInfo(files[i]);
                result.add(info);
            }
        }
        return result.iterator();
    }

    public static Connection getExcelConnection(File file) throws BaseException {
        try {
            DriverManager.registerDriver(new JdbcOdbcDriver());
            String excelConnectionString= "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+file.getAbsolutePath()+";READONLY=true";
            AppLog.debug("excelConnectionString="+excelConnectionString);
            return DriverManager.getConnection(excelConnectionString);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException("Невозможно открыть соединение с файлом "+file.getAbsolutePath(),e);
        }
    }

    public static Collection loadData(File file, String sheetName) throws BaseException {
        Connection excelConnection = getExcelConnection(file);
        try{
            String tablename="["+sheetName.trim()+"$]";
            StoredProc proc = new StoredProc("select * from "+tablename, excelConnection);
            Collection data = proc.executeQueryVector();
            return data;
        }catch(BaseException err) {
            err.printStackTrace();
            throw new BaseException("В файле Excel Не найден sheet '"+sheetName+"'");
        }  finally{
            try{
                excelConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                throw new BaseException("Ошибка закрытия соединения '"+sheetName+"'");
            }
        }
    }

    public static ResultSet loadDataClassic(File file, String sheetName) throws BaseException {
        try {
            Connection excelConnection = getExcelConnection(file);
            String tablename="["+sheetName.trim()+"$]";
            StoredProc proc = new StoredProc("select * from "+tablename, excelConnection);
            return proc.executeQueryClassic();
        }catch(BaseException err) {
            err.printStackTrace();
            throw new BaseException("В файле Excel Не найден sheet '"+sheetName+"'");
        }
    }

    public static ResultSet loadDataClassic(Connection excelConnection, String sheetName) throws BaseException {
        try {
            String tablename="["+sheetName.trim()+"$]";
            StoredProc proc = new StoredProc("select * from "+tablename, excelConnection);
            return proc.executeQueryClassic();
        }catch(BaseException err) {
            err.printStackTrace();
            throw new BaseException("В файле Excel Не найден sheet '"+sheetName+"'");
        }
    }

    /*
      Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" );
      c = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+Address+";DriverID=22;READONLY=false","","");
      stmnt = c.createStatement();
      DatabaseMetaData dbmd=c.getMetaData();
      ResultSet set=dbmd.getTables(null,null,null,null);
      while( set.next() )            	{
      String data=set.getString(3);
      System.out.println(data);
      }
    */


    public static File getCopyOfFile(String fileName) throws BaseException {
        try {
            String tempFilePefix = fileName;
            if(tempFilePefix.lastIndexOf('\\')!=-1)
                tempFilePefix = tempFilePefix.substring(tempFilePefix.lastIndexOf('\\')+1);
            if(tempFilePefix.lastIndexOf('/')!=-1)
                tempFilePefix = tempFilePefix.substring(tempFilePefix.lastIndexOf('/')+1);
            if(tempFilePefix.lastIndexOf('.')!=-1)
                tempFilePefix = tempFilePefix.substring(0,tempFilePefix.lastIndexOf('.'));

            File tempFile = File.createTempFile(tempFilePefix+"_temp", ".xls");

            FileInputStream fis = new FileInputStream(fileName);
            FileOutputStream fos = new FileOutputStream(tempFile);
            byte[] array = new byte[1000];
            int c = 0;
            while(c!=-1) {
                c = fis.read(array);
                if(c!=-1) {
                    fos.write(array, 0, c);
                }
            }
            fis.close();
            fos.close();

            System.out.println("temp file="+tempFile.getAbsolutePath());
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw new BaseException("Ошибка при создании временного файла",e);
        }
    }
}
