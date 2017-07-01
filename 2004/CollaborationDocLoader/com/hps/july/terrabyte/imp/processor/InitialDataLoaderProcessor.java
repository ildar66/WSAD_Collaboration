package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.essence.PositionDirInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.exception.BaseException;

import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 18.07.2005
 * Time: 12:32:27
 */
public class InitialDataLoaderProcessor extends TypedFileTreeLoaderProcessor {

    private Integer currentType = new Integer(3);

    public InitialDataLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException{
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("InitialDataLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        appLog.log("Initial data load started ["+new Date()+ "] ");
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
                    else addLogMessage(command.getLogConnection(), command.getIdQuery(), "E", "Process load of initial data .Position not found for dir ["+info.getName()+"]");
                }
                ////AppLog.log("Process POSITION DIR '"+info.getName()+"' -> started ");
            }
        } catch(BaseException e) {
            e.printStackTrace();
            ////AppLog.log(e);
        } finally {
            appLog.log("Initial data load ended ["+new Date()+ "] ");
            appLog.log("Initial data: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
            addLogMessage(command.getLogConnection(), command.getIdQuery(), "I", "Initial data: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");            
            spHelper.closeSession();
/*
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
*/
        }
    }

    public Integer getCurrentType() {
        return currentType;
    }
}
