package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.imp.essence.ComProjectFileInfo;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.TerrabyteCatalogHelper;
import com.hps.july.terrabyte.imp.helper.ComProjectFilesHelper;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.Date;
import java.util.Collection;
import java.util.Iterator;
import java.io.InputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * Процессор для одноразового переноса данных из таблицы документов
 * для проектов транспортной сети в модуль terrabyte
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class ComProjectFilesLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    public ComProjectFilesLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("ComProjectFilesLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        System.out.println("["+sdf.format(new Date())+ "] start procceed ");
        appLog.log("["+sdf.format(new Date())+ "] start procceed ");
        try {
            Collection list = ComProjectFilesHelper.getAllProjects(command.getConnection());
            System.out.println("list count ["+list.size()+"]");
            appLog.log("list count ["+list.size()+"]");
            for(Iterator iterator = list.iterator(); iterator.hasNext();) {
                ComProjectFileInfo info = (ComProjectFileInfo)iterator.next();
                //Node node = getRootNode(info.getIdent());//new Node();//getRootNode(info.getProjectId());
                //System.out.println("NODE ["+node.getIdent()+"] ["+node.getName()+"] ");
                //node.setIdent(new Integer(44367));
                //node.setIdent(new Integer(33938));
                Collection fileList = ComProjectFilesHelper.getFilesInfo(command.getConnection(), info);
                System.out.println(" fileList count ["+fileList.size()+"] ["+info.getIdent()+"] ["+info.getName()+"] ");
                for (Iterator i1 = fileList.iterator(); i1.hasNext();) {
                    ComProjectFileInfo fileInfo = (ComProjectFileInfo) i1.next();
                    System.out.println("Documents ["+fileInfo.getIdent()+"] ["+fileInfo.getName()+"] ");
                    InputStream body = null;
                    try {
                        body = ComProjectFilesHelper.getFileBody(command.getConnection(), fileInfo);

//                    FileOutputStream fos = new FileOutputStream("C:\\Warehouse\\"+ fileInfo.getIdent() + "_" + fileInfo.getName());
//                    byte[] buf = new byte[4000];
//                    int count = -1 ;
//                    while ((count = body.read(buf)) != -1) fos.write(buf, 0, count);
//                    fos.flush();
//                    fos.close();

                        //System.out.println("info ["+fileInfo.getAgree()+"] ["+fileInfo.getArchive()+"] ");
                        processFile(fileInfo, body);
                        ComProjectFilesHelper.setExtendedParameters(command.getConnection(), fileInfo, fileInfo.getFileId());
                        ComProjectFilesHelper.setUpdateComProjectsDoc(command.getConnection(), fileInfo);
                        if(body != null) try { body.close(); } catch(IOException e) {}
                    } catch(Exception e) {
                        System.out.println("Error adding file " + e.toString());
                        e.printStackTrace();
                        addFileSkiped();
                    } finally {
                        try { if(body != null) body.close(); } catch(IOException e) {}
                    }
                }
            }
        } catch(BaseException e) {
            e.printStackTrace();
            addFileSkiped();
            //AppLog.log(e);
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }
        System.out.println("["+sdf.format(new Date())+ "] All proceeded ["+getFileProceed()+"] added["+getFileAdded()+"] skiped ["+getFileSkiped()+"]");
        appLog.log("["+sdf.format(new Date())+ "] All proceeded ["+getFileProceed()+"] added["+getFileAdded()+"] skiped ["+getFileSkiped()+"]");
    }

    protected Node getRootNode(Integer id) {
        Node node = TerrabyteCatalogHelper.findCatalogForArendaLease(command.getConnection(), id);
        Node posGrp = null;
        if(node == null) {
            String name = ComProjectFilesHelper.getName(command.getConnection(), id);
            try {
                posGrp = TerrabyteCatalogHelper.insertNode(command.getConnection(), null, name, "", false);
                TerrabyteCatalogHelper.linkCatalogWithArendaLease(command.getConnection(), id, posGrp.getIdent());
            } catch(BaseException e) {
                e.printStackTrace();
            }
            node = posGrp;
        }
        return node;
    }

    protected void processFile(ComProjectFileInfo info, InputStream fis) {
        try {
            addFileProceed();
            Document document = null;
            //create
            document = getDocumentFromFileInfo(info);
            //drop bsproject ID !
            document.setClientId(null);
            if(info.getMimeType() != null)
            document.setAttribute(Constants.ATTRIBUTE_FILE_MIME_TYPE, info.getMimeType());
            document.setData(fis);
            Document newDoc = extClient.addDocumentToCatalog(document, info.getProjectId(), "comprj", null, "vad");
            if(newDoc != null) info.setFileId(newDoc.getClientId());
            //System.out.println("newDoc.getClientId()-["+newDoc.getClientId()+"]" );
            addFileAdded();
        } catch(TerrabyteExternalClientException e) {
            e.printStackTrace();
            addFileSkiped();
            appLog.log("["+new Date()+ "]Process FILE '"+info.getProjectId()+"' '"+info.getIdent()+"' -> error");
            appLog.log(e);
        } catch(Exception e) {
            e.printStackTrace();
            addFileSkiped();
            appLog.log("["+new Date()+ "]Process FILE '"+info.getProjectId()+"' '"+info.getIdent()+"' -> error");
            appLog.log(e);
        } finally {
            if(fis != null) try { fis.close(); } catch(IOException e) {}
        }
    }
}
