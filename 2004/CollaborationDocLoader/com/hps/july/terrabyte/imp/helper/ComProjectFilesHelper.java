package com.hps.july.terrabyte.imp.helper;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.essence.ArendaLeaseFileInfo;
import com.hps.july.terrabyte.imp.essence.ComProjectFileInfo;
import com.hps.july.terrabyte.imp.essence.IFileInfo;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.*;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 11:00:50
 */
public class ComProjectFilesHelper extends DatabaseHelper {

    public static Collection getAllProjects(Connection connection) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT projectid as prjid FROM comprojects WHERE projectid not in (select b.projectid from bsprojects b) "
                    + " and projectid in (6683)"; //357
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = getInteger(rs, "prjid");
                ComProjectFileInfo info = new ComProjectFileInfo();
                info.setIdent(id);
                list.add(info);
            }
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

    public static Collection getFilesInfo(Connection connection, IFileInfo info) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = " select id, type, name, crdate, comment, bodytype, owner,version, projectid, isarchive,isagree  " +
                    " from comprojects_doc " +
                    " where projectid = ? and id in (5814) order by version";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getIdent());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = getInteger(rs, "id");
                Integer type = getInteger(rs, "type");
                Integer projectId = getInteger(rs, "projectId");
                String name = getString(rs, "name");
                Timestamp created = getTimestamp(rs, "crdate");
                String comment = getString(rs, "comment");
                String mimeType = getString(rs, "bodytype");
                Integer createdBy = getInteger(rs, "owner");
                Integer version = getInteger(rs, "version");
                Integer archive = getInteger(rs, "isarchive");
                Integer agree = getInteger(rs, "isagree");
                ComProjectFileInfo comInfo = new ComProjectFileInfo();
                comInfo.setIdent(id);
                comInfo.setCreated(created);
                comInfo.setCreatedBy(createdBy);
                comInfo.setName(name.trim());
                comInfo.setDescr((comment != null)?comment.trim():"");
                comInfo.setMimeType(mimeType);
                comInfo.setType(type);
                comInfo.setProjectId(projectId);
                comInfo.setAgree(agree);
                comInfo.setArchive(archive);
                list.add(comInfo);
            }
            return list;
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new BaseException("Cannot get files ! ");
        } finally {
            if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
    }

    public static InputStream getFileBody(Connection connection, IFileInfo info) {
        InputStream result = null;
        //ByteArrayInputStream result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = " select body from comprojects_doc where id=? ";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getIdent());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = rs.getBinaryStream("body");
                //result = rs.getBlob("body").getBinaryStream();
                //result = new ByteArrayInputStream(rs.getBytes("body"));
                if(rs.wasNull()) result = null;
            }
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
        return result;
    }

    public static void setExtendedParameters(Connection connection, ComProjectFileInfo info, Integer fileId) {
        PreparedStatement pstmt = null;

        try {
            String sql = " UPDATE tb_files " +
                    " SET created = ?, createdBy = ?, modified = ?, modifiedBy = ?, agreed = ?, archived = ? " +
                    " WHERE id = ? ";
            pstmt = connection.prepareStatement(sql);
            setTimestamp(pstmt, 1, info.getCreated());
            setInteger(pstmt, 2, info.getCreatedBy());
            setTimestamp(pstmt, 3, info.getCreated());
            setInteger(pstmt, 4, info.getCreatedBy());
            setInteger(pstmt, 5, info.getAgree());
            setInteger(pstmt, 6, info.getArchive());
            setInteger(pstmt, 7, fileId);
            pstmt.executeUpdate();
/*
            if(pstmt != null) pstmt.close();
            sql = " UPDATE COMPROJECTS_DOC  set terabytedocid = ? WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getFileId());
            setInteger(pstmt, 2, info.getIdent());
            pstmt.executeUpdate();
*/
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
    }

    public static void setUpdateComProjectsDoc(Connection connection, ComProjectFileInfo info) {
        if(info.getFileId() == null) return;
        PreparedStatement pstmt = null;
        try {
            String sql = " UPDATE COMPROJECTS_DOC set terabytedocid = ? WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getFileId());
            setInteger(pstmt, 2, info.getIdent());
            pstmt.executeUpdate();
        } catch(Exception e) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
        } finally {
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
    }

    public static String getName(Connection connection, Integer objectId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //connection.setAutoCommit(false);
            String sql = "SELECT c.projectid as id, c.name as name FROM comprojects c WHERE c.projectid = ?";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, objectId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                String name= getString(rs, "name");
                return name;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try { rs.close(); } catch(Exception e) {}
            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
        }
        return null;
    }



}
