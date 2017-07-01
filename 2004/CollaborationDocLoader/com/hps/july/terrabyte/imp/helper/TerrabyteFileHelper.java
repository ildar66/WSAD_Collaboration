package com.hps.july.terrabyte.imp.helper;

import com.hps.july.terrabyte.client.TerrabyteClient;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.essence.AbstractFileInfo;
import com.hps.july.terrabyte.imp.essence.FileInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.core.Attribute;
import com.hps.july.terrabyte.core.Document;
import com.hps.july.terrabyte.core.Constants;
import com.hps.july.terrabyte.imp.AppLog;
import com.hps.framework.exception.BaseException;
import com.hps.beeline.imageLoader.helper.ImageHelper;

import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.sql.*;
import java.io.FileInputStream;
import java.text.FieldPosition;
import java.text.DecimalFormat;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 20:13:51
 */
public class TerrabyteFileHelper extends DatabaseHelper {

    public static FileInfo findFile(Connection connection, Integer catalogId, String name) {
        AbstractFileInfo info = null;
        PreparedStatement pstmtFile = null;
        ResultSet rs = null;
        try {

            StringBuffer sqlFile = new StringBuffer("SELECT f.id, f.name, f.file_name, f.file_size, f.descr, f.version, f.mime_type, f.typeid, cf.catalogid as cid FROM tb_catalog c, tb_files f, tb_catalog2files cf WHERE ");
            StringBuffer whereClauseFile = new StringBuffer(" cf.fileid = f.id AND c.id = cf.catalogid AND f.typeid = 1");
            //выборка только одного узла
            whereClauseFile.append(" AND c.id = ? ");
            whereClauseFile.append(" AND f.file_name = ? ");
            sqlFile.append(whereClauseFile);

            //System.out.println("SQLFILE ["+sqlFile.toString()+"]");
            pstmtFile = connection.prepareStatement(sqlFile.toString());
            setInteger(pstmtFile, 1, catalogId);
            setString(pstmtFile, 2, name);
            rs = pstmtFile.executeQuery();
            Integer ident = null;
            if(rs.next()) {
                info = new AbstractFileInfo();
                ident = new Integer(rs.getInt("id"));
                info.setIdent(ident);
                info.setFileSize(getInteger(rs, "file_size"));
                info.setType(getInteger(rs, "typeid"));
                info.setName(getString(rs, "file_name"));
            }

        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
                //AppLog.log("---Code ["+((SQLException)e).getErrorCode()+"]");

            }
            e.printStackTrace();
            //AppLog.log(e);
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmtFile != null) pstmtFile.close(); } catch(Exception e) {}
        }
        return info;
    }

    public static void insertFile(Connection connection, IFileInfo info, Integer catalogId, TerrabyteClient client) throws BaseException {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        Date currentDate = new Date();

        try {
            connection.setAutoCommit(false);
            Integer serial = getNextKey(connection, "tables.tb_files");
            connection.commit();

            //insert to server
            FileInputStream fileBody = null;
            Integer serverId = null;
            try {
                fileBody = new FileInputStream(info.getFile().getAbsolutePath());
                if(fileBody.available() == 0) {
                    throw new BaseException("File is empty");
                }

                HashMap attributes = new HashMap();
                Attribute a = new Attribute(Constants.ATTRIBUTE_FILE_DESCRIPTION, info.getDescr());
                attributes.put(Constants.ATTRIBUTE_FILE_DESCRIPTION, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_MIME_TYPE, ImageHelper.getMimeType(info.getName(), -1));
                attributes.put(Constants.ATTRIBUTE_FILE_MIME_TYPE, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_SIZE, new Integer(new Long(info.getFile().length()).intValue()));
                attributes.put(Constants.ATTRIBUTE_FILE_SIZE, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_VERSION_NAME, info.getName());
                attributes.put(Constants.ATTRIBUTE_FILE_VERSION_NAME, a);
                Document document = new Document(fileBody, attributes, serial, info.getName());
                document.setClientId(serial);
                document.setClientAttributes(attributes);
                document.setFileSize(new Integer(new Long(info.getFile().length()).intValue()));
                document = client.addDocument(document);
                //System.out.println("BLA ! ["+document.getServerId()+"]" );
                serverId = document.getServerId();
            } catch(BaseException e) {
                //AppLog.log(e);
                throw e;
            } catch(Exception e) {
                e.printStackTrace(System.out);
                //AppLog.log(e);
                if(e instanceof SQLException) System.out.println("CODE ERROR {"+((SQLException)e).getErrorCode()+"}");
                throw new BaseException("Cannot insert file to server, cause : " + e.getMessage());
            } finally {
                try { if(fileBody != null) fileBody.close(); } catch(Exception e) {}
            }


            pstmt = connection.prepareStatement("INSERT INTO tb_files " +
            "(id, docid, typeid, file_name, file_size, mime_type, descr, file_ext, isimage, small_image, name, serverid, createdby, modifiedby, version ) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )");
            //String trans = Converter.transliterate(file.getFileName());
            setInteger(pstmt, 1, serial);
            setInteger(pstmt, 2, null);
            setInteger(pstmt, 3, info.getType());
            setString(pstmt, 4, info.getName());
            setInteger(pstmt, 5, new Integer(new Long(info.getFile().length()).intValue()));
            setString(pstmt, 6, ImageHelper.getMimeType(info.getName(), -1));
            setString(pstmt, 7, info.getDescr());
            setString(pstmt, 8, getExtension(info.getName()));
            byte [] smallImage = null;
            Integer isImage  = new Integer(0);
            if(ImageHelper.isImage(info.getName())) {
                isImage  = new Integer(1);
                smallImage = ImageHelper.compressImage(info.getFile(), 100);
            }
            setInteger(pstmt, 9, isImage);
            setByteArray(pstmt, 10, smallImage);
            setString(pstmt, 11, info.getName());
            setInteger(pstmt, 12, serverId);
            Integer createdBy = info.getCreator();
            setInteger(pstmt, 13, createdBy);
            setInteger(pstmt, 14, createdBy);
            pstmt.executeUpdate();

/*
            if(file.getCatalogId() != null) {
*/
                pstmt1 = connection.prepareStatement("INSERT INTO tb_catalog2files (catalogid, fileid) VALUES (?, ?)");
                setInteger(pstmt1, 1, catalogId);
                setInteger(pstmt1, 2, serial);
                pstmt1.executeUpdate();
/*
            }
*/

            connection.commit();
        } catch(BaseException e) {
            try { if(connection != null) connection.rollback(); } catch(Exception e1) {}
            e.printStackTrace();
            throw e;
        } catch(Exception e) {
            try { if(connection != null) connection.rollback(); } catch(Exception e1) {}
            if(e instanceof SQLException) System.out.println("CODE ERROR {"+((SQLException)e).getErrorCode()+"}");
            e.printStackTrace();
            //AppLog.log(e);
            throw new BaseException("Cannot insert file , cause : " + e.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }

    public static void updateFile(Connection connection, IFileInfo info, Integer catalogId, TerrabyteClient client) throws BaseException {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        try {

            connection.setAutoCommit(false);

            //обновляется файл
            //отправить на сервер и получить версию !!!
            //получить serverid
            pstmt1 = connection.prepareStatement("SELECT serverid, file_name, file_ext FROM tb_files WHERE id = ?");
            setInteger(pstmt1, 1, info.getIdent());
            rs = pstmt1.executeQuery();
            Integer serverId = null;
            Integer version = null;
            String fileName = null;
            String fileExt = null;
            if(rs.next()) {
                serverId  = getInteger(rs, "serverid");
                fileName = getString(rs, "file_name");
                fileExt = getString(rs, "file_ext");
            }
            //System.out.println("sERVER ID ["+serverId+"]");
            Date currentDate = new Date();
            //ихменить на клиенте
            FileInputStream fileBody = null;
            try {
                fileBody = new FileInputStream(info.getFile().getAbsolutePath());
                if(fileBody.available() == 0) {
                    throw new BaseException("File is empty");
                }
                //System.out.println("-" + info.getName() + "  -" + fileBody.available() );

                HashMap attributes = new HashMap();
                Attribute a = new Attribute(Constants.ATTRIBUTE_FILE_DESCRIPTION, info.getDescr());
                attributes.put(Constants.ATTRIBUTE_FILE_DESCRIPTION, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_MIME_TYPE, ImageHelper.getMimeType(info.getName(), -1));
                attributes.put(Constants.ATTRIBUTE_FILE_MIME_TYPE, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_SIZE, new Integer(new Long(info.getFile().length()).intValue()));
                attributes.put(Constants.ATTRIBUTE_FILE_SIZE, a);
                a = new Attribute(Constants.ATTRIBUTE_FILE_VERSION_NAME, info.getName());
                attributes.put(Constants.ATTRIBUTE_FILE_VERSION_NAME, a);
                Document document = new Document(fileBody, attributes, info.getIdent(), info.getName());
                document.setClientId(info.getIdent());
                document.setClientAttributes(attributes);
                document.setFileSize(new Integer(new Long(info.getFile().length()).intValue()));
                document = client.updateDocument(document);
                version = document.getVersionNumber();
            } catch(BaseException e) {
                throw e;
            } catch(Exception e) {
                e.printStackTrace();
                if(e instanceof SQLException) System.out.println("CODE ERROR {"+((SQLException)e).getErrorCode()+"}");
                throw new BaseException("Cannot insert file to server, cause : " + e.getMessage());
            } finally {
                try { if(fileBody != null) fileBody.close(); } catch(Exception e) {}
            }

            //System.out.println("OGO VERSION ["+version+"]");
            pstmt = connection.prepareStatement("UPDATE tb_files SET " +
            " typeid = ?, file_size = ?, mime_type = ?, descr = ?, " +
            " file_ext = ?, isimage = ?, small_image = ?, version = ?, modifiedby = ?, modified = ? " +
            " WHERE id = ? ");
            setInteger(pstmt, 1, info.getType());
            //setString(pstmt, 2, file.getFileName());
            setInteger(pstmt, 2, new Integer(new Long(info.getFile().length()).intValue()));
            setString(pstmt, 3, ImageHelper.getMimeType(info.getName(), -1));
            setString(pstmt, 4, info.getDescr());
            setString(pstmt, 5, getExtension(info.getName()));
            byte [] smallImage = null;
            Integer isImage  = new Integer(0);
            if(ImageHelper.isImage(info.getName())) {
                isImage  = new Integer(1);
                smallImage = ImageHelper.compressImage(info.getFile(), 100);
                //smallImage = ImageHelper.compressImage(file.getFileBody(), file.getFileName());
            }
            setInteger(pstmt, 6, isImage);
            setByteArray(pstmt, 7, smallImage);
            //setString(pstmt, 9, file.getName());
            setInteger(pstmt, 8, version);

            Integer modifiedBy = info.getCreator();
            setInteger(pstmt, 9, modifiedBy);
            setTimestamp(pstmt, 10, new Timestamp((new java.util.Date()).getTime()));
            setInteger(pstmt, 11, info.getIdent());
            pstmt.executeUpdate();
            connection.commit();
        } catch(BaseException e) {
            try { if(connection != null) connection.rollback(); } catch(Exception e1) {}
            throw e;
        } catch(Exception e) {
            try { if(connection != null) connection.rollback(); } catch(Exception e1) {}
            if(e instanceof SQLException) System.out.println("CODE ERROR {"+((SQLException)e).getErrorCode()+"}");
            e.printStackTrace();
            //AppLog.log(e);
            throw new BaseException("Cannot update file , cause : " + e.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt1 != null) pstmt1.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }

    private static String getExtension(String str) {
        int lastDot = str.lastIndexOf('.');
        if (lastDot != -1 || lastDot == str.length())
            return str.substring(lastDot + 1);
        else
            return "";
    }

    private static String getMethodExecutionTime(Date startTime, Date endTime) {
        DecimalFormat df = new DecimalFormat("00");
        df.setDecimalSeparatorAlwaysShown(false);

        long decelTime = endTime.getTime() - startTime.getTime();
        double hours = Math.floor(decelTime / 3600000);
        double min = Math.floor((decelTime % 3600000) / 60000);
        double sec = Math.floor((decelTime % 3600000 % 60000) / 1000);
        double mSec = Math.floor((decelTime % 3600000 % 60000 % 1000));
        StringBuffer sb = new StringBuffer();
        df.format(hours, sb, new FieldPosition(0));
        sb.append(":");
        df.format(min, sb, new FieldPosition(0));
        sb.append(":");
        df.format(sec, sb, new FieldPosition(0));
        df = new DecimalFormat("000");
        df.setDecimalSeparatorAlwaysShown(false);
        sb.append(":");
        df.format(mSec, sb, new FieldPosition(0));

        return sb.toString();
    }

}
