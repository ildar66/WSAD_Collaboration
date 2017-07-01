package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.essence.*;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.exception.BaseException;

import java.io.File;
import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:23:12
 */
public class ImageLoaderProcessor extends TypedFileTreeLoaderProcessor {


    //fixed
    public Integer TYPE_PHOTO = new Integer(1);

    //private ArrayList proceedFiles = new ArrayList();
    //private ArrayList proceedDirs = new ArrayList();
    //private ArrayList proceedPositionDirs = new ArrayList();
    //private ArrayList unProceedPositionDirs = new ArrayList();


    public ImageLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        try {
            client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
            extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
            appLog = new AppLog("ImageLoaderProcessor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute(Command command) throws LoaderException, BaseException {
        appLog.log("images load started ["+new Date()+ "] ");
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        if(command == null || (command != null && command.getLogConnection() == null))
            throw new LoaderException("Data source is not initalizated. Log Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {
            spHelper = new AbstractStoragePlaceHelper();
            spHelper.initSession(command.getIdQuery());
            PositionDirInfo [] list = getPositionsDir((String)command.getParameter(CommandNames.CURRENT_DIRECTORY));
            for(int i = 0; i < list.length; i++) {
                PositionDirInfo info = list[i];
                if(info == null) continue;
                if(info.isPosition()) {
                    if(info.getStorageplace() != null) processPositionDir(info);
                    else addLogMessage(command.getLogConnection(), command.getIdQuery(), "E", "Process load of images.Position not found for dir ["+info.getName()+"]");
                }
            }
        } catch(BaseException e) {
            e.printStackTrace();
            ////AppLog.log(e);
        } finally {
            appLog.log("Images load ended ["+new Date()+ "] ");
            appLog.log("Images: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
            addLogMessage(command.getLogConnection(), command.getIdQuery(), "I", "Images: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
            spHelper.closeSession();
/*
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
*/
        }
/*
        ////AppLog.log("Proceed POSITION DIR count ->"+proceedPositionDirs.size());
        for(Iterator iterator = proceedPositionDirs.iterator(); iterator.hasNext();) {
            String s = (String) iterator.next();
            ////AppLog.log(s);
        }
        ////AppLog.log("UnProceed POSITION DIR count ->"+unProceedPositionDirs.size());
        for(Iterator iterator = unProceedPositionDirs.iterator(); iterator.hasNext();) {
            String s = (String) iterator.next();
            ////AppLog.log(s);
        }
*/
/*
        ////AppLog.log("Proceed DIR count ->"+proceedDirs.size());
        ////AppLog.log("Proceed FILES count ->"+proceedFiles.size());
*/

    }

    protected PositionDirInfo [] getPositionsDir(String dirName) throws BaseException {
        File dir = new File(dirName);
        if(!dir.isDirectory()) return new PositionDirInfo[] {};
        File[] dirList = dir.listFiles();
        int countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) countDir++;
        }
        PositionImageDirInfo[] list = new PositionImageDirInfo[countDir];
        countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                PositionImageDirInfo info = new PositionImageDirInfo(currDir);
                Integer storagePlace = null;
                try {
                    storagePlace = findStoragePlace(info);
                } catch(BaseException e) {}
                //if(info.getNumber() == null || (info.getNumber() != null && info.getNumber().compareTo("a000") < 0)) continue;
                ////AppLog.log("NAME ["+currDir.getName()+"] ST ["+storagePlace+"]" );
                info.setStorageplace(storagePlace);
                list[countDir] = info;
                countDir++;
            }
        }
        dirList = null;
        dir = null;
        return list;
    }

    public Integer getCurrentType() {
        return TYPE_PHOTO;
    }
}
