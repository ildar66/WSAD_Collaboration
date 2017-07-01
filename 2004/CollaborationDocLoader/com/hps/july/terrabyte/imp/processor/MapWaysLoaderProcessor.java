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
import com.hps.july.terrabyte.core.ObjectTypes;
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
public class MapWaysLoaderProcessor extends TypedFileTreeLoaderProcessor {


    //private ArrayList proceedFiles = new ArrayList();
    //private ArrayList proceedDirs = new ArrayList();
    //private ArrayList proceedPositionDirs = new ArrayList();
    //private ArrayList unProceedPositionDirs = new ArrayList();
    private Integer currentStoragePlace;

    public MapWaysLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        currentStoragePlace = null;
        appLog = new AppLog("MapWaysLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        appLog.log("MapWaysLoaderProcessor load started ["+new Date()+ "] ");
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
                    if(info.getStorageplace() != null) {
                        currentStoragePlace = info.getStorageplace();
                        processPositionDir(info);
                    }
                    else addLogMessage(command.getLogConnection(), command.getIdQuery(), "E", "Process load of maps, way, conditions.Position not found for dir ["+info.getName()+"]");
                }
                ////AppLog.log("Process POSITION DIR '"+info.getName()+"' -> started ");
            }
        } catch(BaseException e) {
            e.printStackTrace();
            ////AppLog.log(e);
        } finally {
            appLog.log("Maps, way, condition, load ended ["+new Date()+ "] ");
            appLog.log("Maps, way, condition: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
            addLogMessage(command.getLogConnection(), command.getIdQuery(), "I", "Maps, way, condition: files proceed ["+getFileProceed()+ "] added ["+getFileAdded()+"] updated ["+getFileUpdated()+"] skiped ["+getFileSkiped()+"] ");
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
        if(!dir.isDirectory()) return new PositionSortableDirInfo[] {};
        File [] dirList = dir.listFiles();
        int countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) countDir++;
        }
        PositionSortableDirInfo[] list = new PositionSortableDirInfo[countDir];
        countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                PositionSortableDirInfo info = new PositionSortableDirInfo(currDir);
                Integer storagePlace = null;
                try {
                    storagePlace = findStoragePlace(info);
                } catch(BaseException e) {}
                //System.out.println("stor" + storagePlace);
                //if(info.getNumber() == null || (info.getNumber() != null && info.getNumber().compareTo("a000") < 0)) continue;
                //System.out.println("NAME ["+currDir.getName()+"] ST ["+storagePlace+"]" );
                info.setStorageplace(storagePlace);
                list[countDir++] = info;
            }
        }
        dirList = null;
        dir = null;
        return list;
    }


    protected void processDir(IDirInfo info, Node node) {
        //System.out.println("processDir->"+node.getName()+"'");
        IDirInfo[] list = info.getDirList();
        for(int i = 0; i < list.length; i++) {
            IDirInfo dirInfo = list[i];
            Node nodeDir = findCurrentNode(node.getIdent(), dirInfo.getName());
            if(node != null) {
                processDir(dirInfo, nodeDir);
            }
        }
        list = null;
        ////AppLog.log("Process DIR '"+info.getName()+"'");
        IFileInfo[] fileList = info.getFiles();
        //System.out.println("--------------------");
        for(int i = 0; i < fileList.length; i++) {
            IFileInfo fileInfo = fileList[i];
            //System.out.println("["+fileInfo.getFile().getAbsolutePath()+"] ["+node.getName()+"] ["+node.getIdent()+"] ");
            processFile(fileInfo, node);
            fileInfo = null;
        }
        //System.out.println("--------------------");
        fileList = null;
/*
        proceedDirs.add(info.getName());
*/
    }

    protected void processFile(IFileInfo info, Node node) {
        FileInputStream fis = null;
        try {
            addFileProceed();
            Document document = null;
            try {
                //System.out.println("node ["+node.getName()+"]");
                document = extClient.getMetaInfDocumentLastVersionByNameAndCatalogId(info.getName(), node.getIdent(), "vad");
            } catch(TerrabyteExternalClientException e) {
            }
            IFileInfo oldFile = null;
            if(document != null) oldFile = getFileInfoFromDocument(document);
            info.setCreator(spHelper.getAutoLoaderId());
            info.setDescr("Automaic load ["+sdf.format(new Date())+"]");
            info.setType(getCurrentType());
            if(oldFile == null) {
                appLog.log("["+sdf.format(new Date())+ "] Process FILE '"+info.getFile().getAbsolutePath()+"' -> create");
                //create
                fis = new FileInputStream(info.getFile());
                document = getDocumentFromFileInfo(info);
                document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
                document.setData(fis);
                document = extClient.addDocumentToCatalog(document, node.getIdent(), "vad");
                //System.out.println("doc=["+document.getClientId()+"] type=["+getCurrentType()+"] st =["+currentStoragePlace+"]");
                if(new Integer(9).equals(getCurrentType()) || new Integer(10).equals(getCurrentType())) {
                    if(info.isActive()) {
                        extClient.setActiveDocument(currentStoragePlace, ObjectTypes.POSITION,
                                document.getClientId(), getCurrentType(), "vad");
                    }
                }
                addFileAdded();
            } else {
                //update
                if(!info.equals(oldFile)) {
                    appLog.log("["+sdf.format(new Date())+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> update");
                    fis = new FileInputStream(info.getFile());
                    info.setIdent(oldFile.getIdent());
                    document = getDocumentFromFileInfo(info);
                    document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
                    document.setData(fis);
                    extClient.addDocumentToCatalog(document, node.getIdent(), "vad");
                    addFileUpdated();
                    //TerrabyteFileHelper.updateFile(command.getConnection(), info, node.getIdent(), client);
                } else {
                    addFileSkiped();
                    //AppLog.log("Process FILE '"+info.getName()+"' -> skip");
                }
            }
            //proceedFiles.add(info.getName());
        } catch(TerrabyteExternalClientException e) {
            e.printStackTrace();
            appLog.log("["+sdf.format(new Date())+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> error");
            appLog.log(e);
        } catch(BaseException e) {
            e.printStackTrace();
            appLog.log("["+sdf.format(new Date())+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> error");
            appLog.log(e);
        } catch(Exception e) {
            e.printStackTrace();
            appLog.log("["+sdf.format(new Date())+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> error");
            appLog.log(e);
        } finally {
            try {
                if(fis != null) fis.close();
            } catch(IOException e) {
            }
        }
    }


}
