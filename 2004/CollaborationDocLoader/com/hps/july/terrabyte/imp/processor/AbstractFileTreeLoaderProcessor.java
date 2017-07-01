package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.essence.PositionDirInfo;
import com.hps.july.terrabyte.imp.essence.IDirInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.essence.AbstractFileInfo;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.TerrabyteCatalogHelper;
import com.hps.july.terrabyte.imp.helper.TerrabyteFileHelper;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClient;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.framework.exception.BaseException;

import java.util.Date;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 13.07.2005
 * Time: 17:23:54
 */
public abstract class AbstractFileTreeLoaderProcessor extends AbstractProcessor {

    protected Command command = null;
    protected AbstractStoragePlaceHelper spHelper = null;
    protected TerrabyteClientImpl client = null;
    protected TerrabyteExternalClient extClient = null;

    protected int fileProceed = 0;
    protected int fileSkiped = 0;
    protected int fileAdded = 0;
    protected int fileUpdated = 0;

    protected AppLog appLog;
    protected SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    protected Integer findStoragePlace(PositionDirInfo info) throws BaseException {
        return spHelper.getStoragePlaceId(command.getIdQuery(), info.getGsmId(),
                info.getDampsId(), info.getWlanId());
    }


    protected PositionDirInfo [] getPositionsDir(String dirName) throws BaseException {
        File dir = new File(dirName);
        if(!dir.isDirectory()) return new PositionDirInfo[] {};
        File [] dirList = dir.listFiles();
        int countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) countDir++;
        }
        PositionDirInfo[] list = new PositionDirInfo[countDir];
        countDir = 0;
        for(int i = 0; i < dirList.length; i++) {
            File currDir = dirList[i];
            if(currDir.isDirectory()) {
                PositionDirInfo info = new PositionDirInfo(currDir);
                Integer storagePlace = null;
                try {
                    storagePlace = findStoragePlace(info);
                } catch(BaseException e) {}
                //if(info.getNumber() == null || (info.getNumber() != null && info.getNumber().compareTo("a000") < 0)) continue;
                ////AppLog.log("NAME ["+currDir.getName()+"] ST ["+storagePlace+"]" );
                info.setStorageplace(storagePlace);
                list[countDir++] = info;
            }
        }
        dirList = null;
        dir = null;
        return list;
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
            Node nodeDir = findCurrentNode(node.getIdent(), dirInfo.getName());
            if(nodeDir != null) {
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

    protected void processDir(IDirInfo info, Node node) {
        IDirInfo[] list = info.getDirList();
        for(int i = 0; i < list.length; i++) {
            IDirInfo dirInfo = list[i];
            Node nodeDir = findCurrentNode(node.getIdent(), dirInfo.getName());
            if(nodeDir != null) {
                processDir(dirInfo, nodeDir);
            }
        }
        list = null;
        ////AppLog.log("Process DIR '"+info.getName()+"'");
        IFileInfo[] fileList = info.getFiles();
        for(int i = 0; i < fileList.length; i++) {
            IFileInfo fileInfo = fileList[i];
            processFile(fileInfo, node);
            fileInfo = null;
        }
        fileList = null;
/*
        proceedDirs.add(info.getName());
*/
    }

    protected void processFile(IFileInfo info, Node node) {
        try {
            addFileProceed();
            IFileInfo oldFile = TerrabyteFileHelper.findFile(command.getConnection(), node.getIdent(), info.getName());
            info.setCreator(spHelper.getAutoLoaderId());
            info.setDescr("Automaic load ["+sdf.format(new Date())+"]");
            if(oldFile == null) {
                appLog.log("["+new Date()+ "] Process IMAGE FILE '"+info.getFile().getAbsolutePath()+"' -> create");
                //create
                TerrabyteFileHelper.insertFile(command.getConnection(), info, node.getIdent(), client);
                addFileAdded();
            } else {
                //update
                if(!info.equals(oldFile)) {
                    appLog.log("["+new Date()+ "]Process IMAGE FILE '"+info.getFile().getAbsolutePath()+"' -> update");
                    info.setIdent(oldFile.getIdent());
                    TerrabyteFileHelper.updateFile(command.getConnection(), info, node.getIdent(), client);
                    addFileUpdated();
                } else {
                    addFileSkiped();
                    //AppLog.log("Process FILE '"+info.getName()+"' -> skip");
                }
            }
/*
            proceedFiles.add(info.getName());
*/
        } catch(BaseException e) {
            e.printStackTrace();
            appLog.log("["+new Date()+ "]Process IMAGE FILE '"+info.getFile().getAbsolutePath()+"' -> error");
            appLog.log(e);
        }
    }

    protected Node getRootNode(Integer id) {
        Node node = TerrabyteCatalogHelper.findCatalogForPosition(command.getConnection(), id);
        Node posGrp = null;
        if(node != null) {
            String childNodeName = (String)command.getParameter(CommandNames.FILE_CATALOG);
            if(childNodeName != null) {
                posGrp = TerrabyteCatalogHelper.findChildNodeByName(command.getConnection(), node.getIdent(), (String)command.getParameter(CommandNames.FILE_CATALOG));
                if(posGrp == null) {
                    ////AppLog.log("Not found position ["+id+"] '"+command.getParameter(CommandNames.FILE_CATALOG)+"', try create");
                    try {
                        posGrp = TerrabyteCatalogHelper.insertNode(command.getConnection(), node.getIdent(), (String)command.getParameter(CommandNames.FILE_CATALOG), "", false);
                    } catch(BaseException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                posGrp = node;
            }
        } else {
            ////AppLog.log("Not found position catalog node [" + id + "]");
        }
        return posGrp;
    }

    protected Node findCurrentNode(Integer id, String name) {
        Node node = TerrabyteCatalogHelper.findChildNodeByName(command.getConnection(), id, name);
        if(node == null) {
            //System.out.println("Not found position ["+id+"] '"+name+"', try create");
            try {
                node = TerrabyteCatalogHelper.insertNode(command.getConnection(), id, name, "", true);
            } catch(BaseException e) {
                e.printStackTrace();
                appLog.log(e);
            }
        }
        //System.out.println("->"+node.getName()+"'");
        return node;
    }

    protected IFileInfo getFileInfoFromDocument(Document document) {
        AbstractFileInfo fileInfo = new AbstractFileInfo();
        fileInfo.setIdent(document.getClientId());
        fileInfo.setName(document.getDocumentName());
        fileInfo.setType((Integer)document.getAttributeValueByName(Constants.ATTRIBUTE_NRI_TYPE_ID));
        fileInfo.setFileSize(document.getFileSize());
        return fileInfo;
    }

    protected Document getDocumentFromFileInfo(IFileInfo fileInfo) {
        Document document = new Document(fileInfo.getIdent());
        document.setDocumentName(fileInfo.getName());
        document.setFileSize(fileInfo.getFileSize());
        document.setAttribute(Constants.ATTRIBUTE_FILE_DESCRIPTION, fileInfo.getDescr());
        document.setAttribute(Constants.ATTRIBUTE_NRI_TYPE_ID, fileInfo.getType());
        //TODO: mime type
        document.setAttribute(Constants.ATTRIBUTE_FILE_MIME_TYPE, "application/octet-stream");
        return document;
    }

    public void addLogMessage(Connection connection, Integer queryId, String argMessageType, String argMessageText) {
        //AppLog.log("LOG MESSAGE " + argMessageText);
        PreparedStatement pstmt1 = null;
        try {
            String sql = "INSERT INTO JOIN_DB_querylog(idquery, typemsg, txtmsg) VALUES (?, ?, ?)";
            pstmt1 = connection.prepareStatement(sql);
            pstmt1.setInt(1, queryId.intValue());
            pstmt1.setString(2, argMessageType);
            pstmt1.setString(3, argMessageText);
            pstmt1.executeUpdate();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
        }
    }

    public int getFileAdded() {
        return fileAdded;
    }

    public void addFileAdded() {
        this.fileAdded++;
    }

    public int getFileProceed() {
        return fileProceed;
    }

    public void addFileProceed() {
        this.fileProceed++;
    }

    public int getFileSkiped() {
        return fileSkiped;
    }

    public void addFileSkiped() {
        this.fileSkiped++;
    }

    public int getFileUpdated() {
        return fileUpdated;
    }

    public void addFileUpdated() {
        this.fileUpdated++;
    }
}
