package com.hps.beeline.excelLoader.helpers;

import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.NonFatalException;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.excelLoader.info.ExcelFileInfo;
import com.hps.beeline.excelLoader.info.FileInfo;

import java.util.Iterator;
import java.util.ArrayList;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:51:42
 * To change this template use File | Settings | File Templates.
 */
public class FileInfoHelper {

    private FileInfoHelper() {
    }

    public static FileInfo findFile(String name) throws BaseException {
        StoredProc proc = new StoredProc("select * from balanceStoreFiles where file_name=?");
        proc.setObject(1, name);
        FileInfo  info = (FileInfo) proc.executeQuery("com.hps.beeline.excelLoader.info.FileInfo");
        if(info==null)
            throw new NonFatalException("В списке заданных файлов не задан файл "+name);
        return info;
    }

    public static void clearFilesInfo() throws BaseException {
        StoredProc freeData = new StoredProc("delete from balanceStoreFiles");
        freeData.executeUpdate();
    }

    public static void updateFileInfo(File file, int count) throws BaseException {
        String name = file.getName();
        name = name.substring(0, name.indexOf('.'));

        StoredProc freeData = new StoredProc("insert into balanceStoreFiles(id_file,file_name,sheet_name) values(?,?,?)");
        freeData.setObject(1, new Integer(count));
        freeData.setObject(2, file.getName());        
        freeData.setObject(3, "Лист1");
        freeData.executeUpdate();
    }
}
