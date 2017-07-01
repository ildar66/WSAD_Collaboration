package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.helper.Node;
import com.hps.july.terrabyte.imp.helper.AnsimovImageHelper;
import com.hps.july.terrabyte.imp.helper.TerrabyteFileHelper;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Connection;
import java.io.File;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class AnsimovImageLoaderProcessor extends AbstractFileTreeLoaderProcessor {

    //fixed
    public Integer TYPE_PHOTO = new Integer(1);


    public AnsimovImageLoaderProcessor(String hostName, Integer port) {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        appLog = new AppLog("AnsimovImageLoaderProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {
            Collection list = AnsimovImageHelper.getFiles(command.getConnection());
            for(Iterator iterator = list.iterator(); iterator.hasNext();) {
                SimpleFileInfo info = (SimpleFileInfo) iterator.next();
                Node node = getRootNode(info.getStoragePlace());
                processImageFile(info, node);
                File f = info.getFile();
                for(int i = 0; i < 5&&f.exists(); i++) {
                    //AppLog.log("Process FILE '"+info.getName()+"' -> delete ->" + f.delete());
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

    private void processImageFile(SimpleFileInfo info, Node node) {
        try {
            if(node != null && info != null) {
                //AppLog.log("Process FILE '"+info.getName()+"' -> create");
                //create
                TerrabyteFileHelper.insertFile(command.getConnection(), info, node.getIdent(), client);
            }
        } catch(BaseException e) {
            e.printStackTrace();
            //AppLog.log("Process FILE '"+info.getName()+"' -> error");
            //AppLog.log(e);
        }
    }


}
