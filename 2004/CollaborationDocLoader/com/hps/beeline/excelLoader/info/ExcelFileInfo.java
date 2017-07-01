package com.hps.beeline.excelLoader.info;

import com.hps.beeline.excelLoader.helpers.FileInfoHelper;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.beeline.excelLoader.helpers.ExcelHelper;
import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 18.11.2004
 * Time: 13:56:54
 * To change this template use File | Settings | File Templates.
 */
public class ExcelFileInfo {
    private File file;
    private FileInfo generalInfo = null;
    private Collection data = null;

    public ExcelFileInfo(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void updateFileId() throws BaseException {
        generalInfo =  FileInfoHelper.findFile(file.getName());
    }

    public void loadFileData() throws BaseException {
        File tempFile = ExcelHelper.getCopyOfFile(file.getAbsolutePath());
        data = ExcelHelper.loadData(tempFile, generalInfo.getSheet_name());
        tempFile.delete();
    }

    public Iterator getData() {
        return data.iterator();
    }

    public FileInfo getGeneralInfo() {
        return generalInfo;
    }
}
