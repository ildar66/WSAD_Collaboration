package com.hps.july.terrabyte.imp.processor;

import com.hps.july.terrabyte.imp.command.Command;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.client.impl.TerrabyteClientImpl;
import com.hps.july.terrabyte.imp.helper.*;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientFactory;
import com.hps.july.terrabyte.ext.client.TerrabyteExternalClientException;
import com.hps.july.terrabyte.core.ObjectTypes;
import com.hps.beeline.LoaderException;
import com.hps.beeline.Config;
import com.hps.framework.exception.BaseException;

import java.util.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 10:58:30
 */
public class CreateSecondLevelDirectoryProcessor extends AbstractFileTreeLoaderProcessor {

/*
    public CreateSecondLevelDirectoryProcessor(String hostName, Integer port) throws TerrabyteExternalClientException {
        client = new TerrabyteClientImpl(hostName, port.intValue(), "NRI");
        extClient = TerrabyteExternalClientFactory.getClient(hostName, port.intValue(), "NRI");
        appLog = new AppLog("CreateSecondLevelDirectoryProcessor");
    }
*/
    public CreateSecondLevelDirectoryProcessor() throws TerrabyteExternalClientException {
        appLog = new AppLog("CreateSecondLevelDirectoryProcessor");
    }

    public void execute(Command command) throws LoaderException, BaseException {
        this.command = command;
        if(command == null || (command != null && command.getConnection() == null))
            throw new LoaderException("Data source is not initalizated. Connection is null");
        Config.getInstance().init(command.getConnection(), command.getLogConnection());
        try {
            String name = (String)command.getParameter(CommandNames.FILE_CATALOG);
            String objectType = (String)command.getParameter(CommandNames.OBJECT_TYPE);
            Collection position =getObjects(command.getConnection(), objectType);
            int c = 0;
            int c1 = 0;
            for (Iterator iterator = position.iterator(); iterator.hasNext();) {
                Integer posId = (Integer) iterator.next();
                Node node = getRootNodeByIdAndObjectType(posId, objectType);
                if(node != null) c++; else c1++;
                createDirectory(node, name);
            }
            System.out.println("proceeded ["+c+"] not proceeded ["+c1+"]");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(command.getConnection() != null) command.getConnection().close(); } catch(SQLException e) {}
            try { if(command.getLogConnection() != null) command.getLogConnection().close(); } catch(SQLException e) {}
        }

    }

    protected Collection getObjects(Connection connection, String typeObj) {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
            try {
                if(typeObj.equals(ObjectTypes.POSITION)) {
                    pstmt = connection.prepareStatement("SELECT p.storageplace as id, getnameposition(p.storageplace) as name" +
                        "   FROM positions p, storageplaces s" +
                        "   WHERE s.storageplace = p.storageplace ");
                    rs = pstmt.executeQuery();
                    while(rs.next()) {

                        Integer id = getInteger(rs, "id");
                        list.add(id);
                    }
                }
                if(typeObj.equals(ObjectTypes.PROJECT_BS)) {
                    pstmt = connection.prepareStatement("SELECT b.projectid as id FROM comprojects c, bsprojects b " +
                            "WHERE c.projectid = b.projectid AND b.projectid NOT IN (SELECT  r.projectid FROM comprojects c, idrepiters r WHERE c.projectid = r.projectid) ");
                    rs = pstmt.executeQuery();
                    while(rs.next()) {

                        Integer id = getInteger(rs, "id");
                        list.add(id);
                    }
                }
            } catch(Exception e) {
                if(e instanceof SQLException) {
                    System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
                }
                e.printStackTrace();

            } finally {
                try { if(rs != null) rs.close(); } catch(Exception e) {}
                try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            }
        return list;
    }

    protected Node getRootNode(Integer id) {
        Node node = TerrabyteCatalogHelper.findCatalogForPosition(command.getConnection(), id);
        return node;
    }

    protected Node getRootNodeByIdAndObjectType(Integer id, String objectType) {
        Node node = null;
        if(objectType.equals(ObjectTypes.POSITION)) node = TerrabyteCatalogHelper.findCatalogForPosition(command.getConnection(), id);
        else if(objectType.equals(ObjectTypes.PROJECT_BS)) node = TerrabyteCatalogHelper.findCatalogForBSProject(command.getConnection(), id);
        return node;
    }

    protected void createDirectory(Node node, String name) {
        if(node != null && name != null) {
            Node posGrp = null;
            String childNodeName = (String)command.getParameter(CommandNames.FILE_CATALOG);
            if(childNodeName != null) {
                posGrp = TerrabyteCatalogHelper.findChildNodeByName(command.getConnection(), node.getIdent(), childNodeName);
                if(posGrp == null) {
                    try {
                        posGrp = TerrabyteCatalogHelper.insertNode(command.getConnection(), node.getIdent(), childNodeName, "", false);
                    } catch(BaseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
