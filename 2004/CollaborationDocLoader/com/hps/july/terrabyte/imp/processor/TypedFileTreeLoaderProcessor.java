package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.essence.PositionDirInfo;
import com.hps.july.terrabyte.imp.essence.IDirInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.framework.exception.BaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 19.07.2005
 * Time: 11:18:50
 */
public abstract class TypedFileTreeLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    private Integer currentType = null;

    public Integer getCurrentType() {
        return currentType;
    }

    public void setCurrentType(Integer currentType) {
        this.currentType = currentType;
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
            currentType = dirInfo.getNRIType();//dirInfo.getName();
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

    protected void processFile(IFileInfo info, Node node) {
        FileInputStream fis = null;
        try {
            addFileProceed();
            Document document = null;
            try {
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
                extClient.addDocumentToCatalog(document, node.getIdent(), "vad");
                addFileAdded();
                //TerrabyteFileHelper.insertFile(command.getConnection(), info, node.getIdent(), client);
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
/*
            proceedFiles.add(info.getName());
*/
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
