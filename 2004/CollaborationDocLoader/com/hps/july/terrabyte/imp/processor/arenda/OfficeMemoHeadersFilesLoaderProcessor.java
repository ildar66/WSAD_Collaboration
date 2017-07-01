/*
 *  $Id: OfficeMemoHeadersFilesLoaderProcessor.java,v 1.2 2007/06/15 17:12:50 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.terrabyte.imp.processor.arenda;

import com.hps.july.terrabyte.imp.processor.AbstractFileTreeLoaderProcessor;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.imp.essence.arenda.OfficeMemoHeadersFileInfo;
import com.hps.july.terrabyte.imp.helper.ComProjectFilesHelper;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.TerrabyteCatalogHelper;
import com.hps.july.terrabyte.imp.helper.arenda.OfficeMemoHeadersFilesHelper;
import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.ObjectTypes;
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
 * ��������� ��� ������������ �������� ������ �� ������� ������ officememoheaders
 * � ������ terrabyte
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class OfficeMemoHeadersFilesLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    public OfficeMemoHeadersFilesLoaderProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("OfficeMemoHeadersFilesLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        appLog.log("["+sdf.format(new Date())+ "] start procceed ");
        System.out.println("["+sdf.format(new Date())+ "] start procceed ");
        try {
            Collection fileList = OfficeMemoHeadersFilesHelper.getFilesInfo(command.getConnection());
            System.out.println(" fileList count ["+fileList.size()+"] ");
            for (Iterator i1 = fileList.iterator(); i1.hasNext();) {
                OfficeMemoHeadersFileInfo fileInfo = (OfficeMemoHeadersFileInfo) i1.next();
                System.out.println("Documents ["+fileInfo.getIdHeader()+"] ["+fileInfo.getName()+"] ");
                InputStream body = null;
                try {
                    body = OfficeMemoHeadersFilesHelper.getFileBody(command.getConnection(), fileInfo);

//                    FileOutputStream fos = new FileOutputStream("C:\\Warehouse\\"+ fileInfo.getIdent() + "_" + fileInfo.getName());
//                    byte[] buf = new byte[4000];
//                    int count = -1 ;
//                    while ((count = body.read(buf)) != -1) fos.write(buf, 0, count);
//                    fos.flush();
//                    fos.close();

                    //System.out.println("info ["+fileInfo.getAgree()+"] ["+fileInfo.getArchive()+"] ");
                    processFile(fileInfo, body);
                    OfficeMemoHeadersFilesHelper.setExtendedParameters(command.getConnection(), fileInfo, fileInfo.getIdent());
                    if(body != null) try { body.close(); } catch(IOException e) {}
                } catch(Exception e) {
                    System.out.println("Error adding file ["+fileInfo.getIdHeader()+"]" + e.toString());
                    e.printStackTrace();
                    addFileSkiped();
                } finally {
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
        appLog.log("["+sdf.format(new Date())+ "] All proceeded ["+getFileProceed()+"] added["+getFileAdded()+"] skiped ["+getFileSkiped()+"]");
        System.out.println("["+sdf.format(new Date())+ "] All proceeded ["+getFileProceed()+"] added["+getFileAdded()+"] skiped ["+getFileSkiped()+"]");
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

    protected void processFile(OfficeMemoHeadersFileInfo info, InputStream fis) {
        try {
            addFileProceed();
            Document document = null;
            //create
            document = getDocumentFromFileInfo(info);
            document.setClientId(null);
            if(document.getDocumentName() == null) document.setDocumentName("memoWord.doc");
            document.setData(fis);
            Document newDoc = extClient.addObjectDocument(document, info.getIdHeader(), ObjectTypes.OFFICE_MEMO_HEADERS, "vad");
            if(newDoc != null) info.setIdent(newDoc.getClientId());
            //System.out.println("newDoc.getClientId()-["+newDoc.getClientId()+"]" );
            addFileAdded();
        } catch(TerrabyteExternalClientException e) {
            e.printStackTrace();
            addFileSkiped();
            appLog.log("["+new Date()+ "]Process FILE '"+info.getIdHeader()+"' -> error");
            appLog.log(e);
        } catch(Exception e) {
            e.printStackTrace();
            addFileSkiped();
            appLog.log("["+new Date()+ "]Process FILE '"+info.getIdHeader()+"' -> error");
            appLog.log(e);
        } finally {
            if(fis != null) try { fis.close(); } catch(IOException e) {}
        }
    }
}
