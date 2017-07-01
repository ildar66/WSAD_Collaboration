package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.essence.*;
import com.hps.july.terrabyte.imp.helper.TerrabyteCatalogHelper;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.TerrabyteFileHelper;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClient;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.exception.BaseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Date;

/**
 * Процессор для загрузки данных по черетжам
 *
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:23:12
 *
 */
public class SanPassportLoaderProcessor extends TypedFileTreeLoaderProcessor {


    //private ArrayList proceedFiles = new ArrayList();
    //private ArrayList proceedDirs = new ArrayList();
    //private ArrayList proceedPositionDirs = new ArrayList();
    //private ArrayList unProceedPositionDirs = new ArrayList();

    public SanPassportLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("SanPassportLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        appLog.log("San Passport load started ["+new Date()+ "] ");
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
                    else addLogMessage(command.getLogConnection(), command.getIdQuery(), "E", "Process load of san passport.Position not found for dir ["+info.getName()+"]");
                }
                ////AppLog.log("Process POSITION DIR '"+info.getName()+"' -> started ");
            }
        } catch(BaseException e) {
            e.printStackTrace();
            ////AppLog.log(e);
        } finally {
            appLog.log("San Passport load ended ["+new Date()+ "] ");
            appLog.log("San Passport: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
            addLogMessage(command.getLogConnection(), command.getIdQuery(), "I", "San Passport: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
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

}
