package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.essence.PositionDirInfo;
import com.hps.july.terrabyte.imp.essence.IDirInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.DrawingCommand;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.exception.BaseException;

import java.util.Date;

/**
 * Процессор для загрузки данных по черетжам
 *
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 15:23:12
 *
 */
public class DrawingPPCLoaderProcessor extends TypedFileTreeLoaderProcessor {


    private Integer currentType = new Integer(12); //РРС

    public DrawingPPCLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("DrawingLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        String logName = (String)command.getParameter(DrawingCommand.LOG_NAME);
        if(logName != null) appLog = new AppLog("DrawingPPCLoaderProcessor_" + logName);
        appLog.log("Drawing PPC load started ["+sdf.format(new Date())+ "] ["+logName+"]" );
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
                    else addLogMessage(command.getLogConnection(), command.getIdQuery(), "E", "Process load of drawnig РРС.Position not found for dir ["+info.getName()+"]");
                }
                ////AppLog.log("Process POSITION DIR '"+info.getName()+"' -> started ");
            }
        } catch(BaseException e) {
            e.printStackTrace();
            ////AppLog.log(e);
        } finally {
            appLog.log("Drawing PPC load ended ["+new Date()+ "] ");
            spHelper.closeSession();
        }

    }

    public void setCurrentType(Integer currentType) {
    }

    protected void processPositionDir(PositionDirInfo info) {
        Node node = getRootNode(info.getStorageplace());
        if(node == null) {
//            unProceedPositionDirs.add(info.getName() + ", cause can't find node for positionid  ["+info.getStorageplace()+"] !");
            return;
        }
        IDirInfo[] list = info.getDirList();
        for(int i = 0; i < list.length; i++) {
            IDirInfo dirInfo = list[i];
            //AppLog.log("["+dirInfo.getName()+"] ["+currentType+"]");
            Node nodeDir = findCurrentNode(node.getIdent(), dirInfo.getName());

            //AppLog.log("Node ["+node+"] ");
            if(nodeDir != null) {
                //AppLog.log("["+node+"] ["+node.getName()+"]");
                processDir(dirInfo, nodeDir);
            }
        }
        ////AppLog.log("Process POSITION DIR files '"+info.getName()+"' ->");
        IFileInfo[] fileList = info.getFiles();
        for(int i = 0; i < fileList.length; i++) {
            IFileInfo fileInfo = fileList[i];
            processFile(fileInfo, node);
        }
//        proceedPositionDirs.add(info.getName());
    }

}
