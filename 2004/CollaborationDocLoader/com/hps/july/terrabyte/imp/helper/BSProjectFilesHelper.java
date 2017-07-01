package com.hps.july.terrabyte.imp.helper;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.essence.SimpleFileInfo;
import com.hps.july.terrabyte.imp.essence.BSProjectFileInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.AppLog;

import java.sql.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 11:00:50
 */
public class BSProjectFilesHelper extends DatabaseHelper {

    public static Collection getFilesInfo(Connection connection) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select d.ID, d.PROJECTID, d.TYPE, d.owner, d.CRDATE, TRIM(d.name) name, \n" +
                    " TRIM(d.comment) comment,d.DOCSIZE, TRIM(d.BODYTYPE) mimetype, d.VERSION, d.VISIBLE, \n" +
                    " d.ISARCHIVE, d.ISAGREE, d.HASLIST , d.owner, TRIM(o.loiginid) login \n" +
                    " FROM comprojects_doc d, comprojects c, bsprojects b, operators o WHERE \n" +
                    " c.projectid = b.projectid AND d.projectid = c.projectid  AND d.owner = o.man and d.isarchive is null";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = getInteger(rs, "id");
                String name = getString(rs, "name");
                String description = getString(rs, "comment");
                Integer projectId = getInteger(rs, "projectid");
                Integer type = getInteger(rs, "type");
                Timestamp crDate = getTimestamp(rs, "crdate");
                Integer isArchived = getInteger(rs, "isarchive");
                Integer isAgreed = getInteger(rs, "isagree");
                Integer creator = getInteger(rs, "owner");
                String login = getString(rs, "login");

                BSProjectFileInfo info = new BSProjectFileInfo();
                info.setIdent(id);
                info.setCreator(creator);
                info.setType(type);
                info.setDescr(description);
                info.setLastModified(crDate);
                info.setProjectId(projectId);
                info.setName(name);
                info.setAgree(isAgreed);
                info.setArchive(isArchived);
                info.setLogin(login);
                list.add(info);
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            return list;
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new BaseException("Cannot get files ! ");
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }

    public static InputStream getFileBody(Connection connection, BSProjectFileInfo info) {
        InputStream result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select d.ID, d.body " +
                    " FROM comprojects_doc d WHERE \n" +
                    " d.ID = ? ";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getIdent());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = rs.getBinaryStream("body");
                if(rs.wasNull()) result = null;
            }
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        return result;
    }

    public static void setExtendedParameters(Connection connection, BSProjectFileInfo info, Integer fileId) {
        PreparedStatement pstmt = null;

        try {
            String sql = " UPDATE tb_files " +
                    " SET archived = ?, agreed = ? \n" +
                    " WHERE id = ? ";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getArchive());
            setInteger(pstmt, 2, info.getAgree());
            setInteger(pstmt, 3, fileId);
            pstmt.executeUpdate();
            if(pstmt != null) pstmt.close();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
    }

    public static String getName(Connection connection, Integer objectId) {
        PreparedStatement pstmt = null; //postion adn names
        ResultSet rs = null;
        try {
            //connection.setAutoCommit(false);
            String sql = "SELECT  b.projectid as id, ('มั น ' || b.gsmid || ' ' || TRIM(c.name) ) as name FROM comprojects c, bsprojects b WHERE c.projectid = b.projectid AND b.projectid = ?";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, objectId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                String bsName = getString(rs, "name");
                return bsName;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
        }
        return null;
    }



}
