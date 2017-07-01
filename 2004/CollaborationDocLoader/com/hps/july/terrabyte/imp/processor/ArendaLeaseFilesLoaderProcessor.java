package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.TerrabyteCatalogHelper;
import com.hps.july.terrabyte.imp.helper.arenda.ArendaLeaseFilesHelper;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.essence.ArendaLeaseFileInfo;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Date;
import java.io.InputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * Процессор для одноразового переноса данных из таблицы документов для аренды
 * в модуль terrabyte
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class ArendaLeaseFilesLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    private static Integer ARENDA_LEASE_TYPE = new Integer(49394);

    public ArendaLeaseFilesLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("ArendaLeaseFilesLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        System.out.println("["+sdf.format(new Date())+ "] start procedd ");
        try {
            Collection list = ArendaLeaseFilesHelper.getFilesInfo(command.getConnection());
            System.out.println("list count ["+list.size()+"]");
            for(Iterator iterator = list.iterator(); iterator.hasNext();) {
                ArendaLeaseFileInfo info = (ArendaLeaseFileInfo)iterator.next();
                System.out.println("["+info.getIdContract()+"] ["+info.getLeaseDocId()+"] ["+info.getDocFileName()+"]   ");
                Node node = getRootNode(info.getIdContract());//new Node();//getRootNode(info.getProjectId());
                System.out.println("NODE ["+node.getIdent()+"] ["+node.getName()+"] ");
                //node.setIdent(new Integer(44367));
                //node.setIdent(new Integer(33938));
                InputStream body = null;
                try {
                    body = ArendaLeaseFilesHelper.getFileBody(command.getConnection(), info);

//                    FileOutputStream fos = new FileOutputStream("C:\\Warehouse\\r\\"+info.getName());
//                    byte[] buf = new byte[4000];
//                    int count = -1 ;
//                    while ((count = body.read(buf)) != -1) fos.write(buf, 0, count);
//                    fos.flush();
//                    fos.close();

                    //System.out.println("info ["+info.getAgree()+"] ["+info.getArchive()+"] ");
                    processFile(info, node, body);
                    ArendaLeaseFilesHelper.setExtendedParameters(command.getConnection(), info, info.getIdent());
                    if(body != null) try { body.close(); } catch(IOException e) {}
                } catch(Exception e) {
                    System.out.println("Error adding file " + e.toString());
                    e.printStackTrace();
                } finally {
                    addFileSkiped();
                    try { if(body != null) body.close(); } catch(IOException e) {}
                }
            }
        } catch(BaseException e) {
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }
        System.out.println("["+sdf.format(new Date())+ "] All proceeded ["+getFileAdded()+"] skiped ["+getFileSkiped()+"]");
    }

    protected Node getRootNode(Integer id) {
        Node node = TerrabyteCatalogHelper.findCatalogForArendaLease(command.getConnection(), id);
        Node posGrp = null;
        if(node == null) {
            //Ошибка блин !!!! надл имя договора !!!
            String name = ArendaLeaseFilesHelper.getName(command.getConnection(), id);
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

    protected void processFile(IFileInfo info, Node node, InputStream fis) {
        try {
            addFileProceed();
            Document document = null;
//            try {
//                document = extClient.getMetaInfDocumentLastVersionByNameAndCatalogId(info.getName(), node.getIdent(), "vad");
//            } catch(TerrabyteExternalClientException e) {
//            }
//            IFileInfo oldFile = null;
//            if(document != null) oldFile = getFileInfoFromDocument(document);
            //info.setCreator(spHelper.getAutoLoaderId());
            //info.setDescr("Automaic load ["+new Date()+"]");
            Integer newType = ARENDA_LEASE_TYPE;
            info.setType(newType);

//            if(oldFile == null) {
                //AppLog.log("["+new Date()+ "] Process FILE '"+info.getFile().getAbsolutePath()+"' -> create");
                //create
                document = getDocumentFromFileInfo(info);
                //drop bsproject ID !
                document.setClientId(null);
                document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
                document.setAttribute(Constants.ATTRIBUTE_FILE_DESCRIPTION, info.getDescr() + "["+sdf.format(new Date())+"]");
                document.setData(fis);
                Document newDoc = extClient.addDocumentToCatalog(document, node.getIdent(), "vad");
                if(newDoc != null) info.setIdent(newDoc.getClientId());
                addFileAdded();
                //TerrabyteFileHelper.insertFile(command.getConnection(), info, node.getIdent(), client);
//            } else {
//                //update
//                if(!info.equals(oldFile)) {
//                    //AppLog.log("["+new Date()+ "]Process FILE '"+info.getFile().getAbsolutePath()+"' -> update");
//                    info.setIdent(oldFile.getIdent());
//                    document = getDocumentFromFileInfo(info);
//                    document.setAttribute(Constants.ATTRIBUTE_CATALOG_ID, node.getIdent());
//                    document.setData(fis);
//                    extClient.addDocumentToCatalog(document, node.getIdent(), "vad");
//                    addFileUpdated();
//                    //TerrabyteFileHelper.updateFile(command.getConnection(), info, node.getIdent(), client);
//                } else {
//                    addFileSkiped();
//                    //AppLog.log("Process FILE '"+info.getName()+"' -> skip");
//                }
//            }
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
            if(fis != null) try { fis.close(); } catch(IOException e) {}
        }
    }
}
