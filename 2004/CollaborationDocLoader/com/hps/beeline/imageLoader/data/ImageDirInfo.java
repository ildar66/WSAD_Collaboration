package com.hps.beeline.imageLoader.data;

import com.hps.beeline.imageLoader.helper.PhotoDatabaseHelper;
import com.hps.beeline.LogHelper;
import com.hps.framework.log.MessageService;
import com.hps.framework.log.AppLog;
import com.hps.beeline.global.data.AbstractDirectoryInfo;
import com.hps.framework.exception.BaseException;
import com.hps.framework.log.AppLog;
import com.hps.framework.exception.BaseException;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 15.03.2005
 * Time: 17:13:43
 * To change this template use File | Settings | File Templates.
 */
public class ImageDirInfo extends AbstractDirectoryInfo {

    protected ImageInfo imageInfo = new ImageInfo();

    public ImageDirInfo() {
    }


    public void processSubFiles() {
        File[] subDurs = dir.listFiles();
        for(int i=0;i<subDurs.length;i++) {
            File subDir = subDurs[i];
            AppLog.info("process sub dir="+subDir.getName());
            processSubDir(subDir);
        }
    }

    private void processSubDir(File subDir) {
        File[] images = subDir.listFiles();
        if(images!=null) {
            for(int i=0;i<images.length && (processedFilesCount<maxLoadCount || maxLoadCount<1) ;i++) {
                File image = images[i];
                if(image.isDirectory()) {
                    String errorText = "Внутри папки с фотографиями обнаружена директория!! "+image.getAbsolutePath();
                    System.out.println("Error!!!="+errorText);
                    LogHelper.logError(idQuery, errorText);
                    MessageService.getInstance().addMessage(errorText);
                } else {
                    AppLog.info("process image "+image.getName());
                    processImage(image, subDir.getName());
                }
            }
        }
    }

    private void processImage(File image, String subDirName) {
        try{
            imageInfo.init(image, storagePlaceId, subDirName);
            imageInfo.createOrUpdate();
            if(imageInfo.isNeedCreate() || imageInfo.isNeedCreate())
                processedFilesCount++;
        }catch(BaseException e) {
            e.printStackTrace();
            LogHelper.logError(idQuery, e.getMessage());
            MessageService.getInstance().addMessage("Во время загрузки фотографий произошла ошибка, см. лог");
        }
    }

}
