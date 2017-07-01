package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.essence.*;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.imp.helper.*;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.global.helper.AbstractStoragePlaceHelper;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.io.*;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class BSProjectFilesLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    private static HashMap types;

    static {
        types = new HashMap();
        types.put(new Integer(0), new Integer(49387));
        types.put(new Integer(1), new Integer(49388));
        //types.put(new Integer(2), new Integer(48831));
    }

    public BSProjectFilesLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("BSProjectFilesLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {
            Collection list = BSProjectFilesHelper.getFilesInfo(command.getConnection());
            for(Iterator iterator = list.iterator(); iterator.hasNext();) {
                BSProjectFileInfo info = (BSProjectFileInfo)iterator.next();
                System.out.println("["+info.getName()+"] ["+info.getProjectId()+"] ["+info.getLogin()+"]");
                Node node = getRootNode(info.getProjectId());//new Node();//getRootNode(info.getProjectId());
                System.out.println("NODE ["+node.getIdent()+"] ["+node.getName()+"] ");
                //node.setIdent(new Integer(44367));
                //node.setIdent(new Integer(33938));
                InputStream body = null;
                try {
                    body = BSProjectFilesHelper.getFileBody(command.getConnection(), info);

/*
                    FileOutputStream fos = new FileOutputStream("C:\\Warehouse\\r\\"+info.getName());
                    byte[] buf = new byte[4000];
                    int count = -1 ;
                    while ((count = body.read(buf)) != -1) fos.write(buf, 0, count);
                    fos.flush();
                    fos.close();
*/

                    //System.out.println("info ["+info.getAgree()+"] ["+info.getArchive()+"] ");
                    processFile(info, node, body);
                    BSProjectFilesHelper.setExtendedParameters(command.getConnection(), info, info.getIdent());
                } catch(Exception e) {
                    System.out.println("Error adding file " + e.toString());
                    e.printStackTrace();
                } finally {
                    //try { if(body != null) body.close(); } catch(IOException e) {}
                }
            }
        } catch(BaseException e) {
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }

    }

    protected Node getRootNode(Integer id) {
        Node node = TerrabyteCatalogHelper.findCatalogForBSProject(command.getConnection(), id);
        Node posGrp = null;
        if(node == null) {
            String name = BSProjectFilesHelper.getName(command.getConnection(), id);
            try {
                posGrp = TerrabyteCatalogHelper.insertNode(command.getConnection(), null, name, "", false);
                TerrabyteCatalogHelper.linkCatalogWithBSProject(command.getConnection(), id, posGrp.getIdent());
            } catch(BaseException e) {
                e.printStackTrace();
            }
            node = posGrp;
        }
        return node;
    }

    protected void processFile(IFileInfo info, Node node, InputStream fis) {
        try {
            addFileProceed();
            Document document = null;
            try {
                document = extClient.getMetaInfDocumentLastVersionByNameAndCatalogId(info.getName(), node.getIdent(), "vad");
            } catch(TerrabyteExternalClientException e) {
            }
            IFileInfo oldFile = null;
            if(document != null) oldFile = getFileInfoFromDocument(document);
            //info.setCreator(spHelper.getAutoLoaderId());
            //info.setDescr("Automaic load ["+new Date()+"]");
            Integer newType = (Integer)types.get(info.getType());
            info.setType(newType);

            if(oldFile == null) {
                //AppLog.log("["+new Date()+ "] Process FILE '"+info.getFile().getAbsolutePath()+"' -> create");
                //create
                document = getDocumentFromFileInfo(info);
                //drop bsproject ID !
                document.setClientId(null);
                document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
                document.setData(fis);
                Document newDoc = extClient.addDocumentToCatalog(document, node.getIdent(), ((BSProjectFileInfo)info).getLogin());
                if(newDoc != null) info.setIdent(newDoc.getClientId());
                addFileAdded();
                //TerrabyteFileHelper.insertFile(command.getConnection(), info, node.getIdent(), client);
            } else {
                //update
                if(!info.equals(oldFile)) {
                    //AppLog.log("["+new Date()+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> update");
                    info.setIdent(oldFile.getIdent());
                    document = getDocumentFromFileInfo(info);
                    document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
                    document.setData(fis);
                    extClient.addDocumentToCatalog(document, node.getIdent(), ((BSProjectFileInfo)info).getLogin());
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
            appLog.log("["+new Date()+ "]Process FILE '"+info+"' -> error");
            appLog.log(e);
        } catch(Exception e) {
            e.printStackTrace();
            appLog.log("["+new Date()+ "]Process FILE '"+info+"' -> error");
            appLog.log(e);
        } finally {
            try {
                if(fis != null) fis.close();
            } catch(IOException e) {
            }
        }
    }

}
