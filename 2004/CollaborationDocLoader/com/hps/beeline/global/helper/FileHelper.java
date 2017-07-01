package com.hps.beeline.global.helper;

import com.hps.framework.exception.BaseException;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 28.03.2005
 * Time: 17:10:56
 * To change this template use File | Settings | File Templates.
 */
public class FileHelper {

    private FileHelper() {
    }

    public static File[] getFiles(String dirPath) throws BaseException {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if(files==null||!dir.exists())
            throw new BaseException("В указанной директории не найдены папки с файлами, или директория не существует "+dirPath);
        return files;
    }

    public static File getSubDir(File dir, String subDirName) throws BaseException {
        File[] files = dir.listFiles();
        File result = null;
        boolean isSubDirFound = false;
        for(int i=0;i<files.length;i++) {
            if(files[i].getName().trim().equals(subDirName.trim())) {
                result = files[i];
                isSubDirFound = true;
                break;
            }
        }
        if(!isSubDirFound)
                throw new BaseException("Не найдена обязательная поддиректория "+subDirName);
        return result;
    }
}
