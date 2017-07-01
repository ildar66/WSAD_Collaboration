/*
 *  $Id: OfficeMemoHeadersFilesHelper.java,v 1.2 2007/06/15 17:12:49 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.terrabyte.imp.helper.arenda;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.essence.arenda.OfficeMemoHeadersFileInfo;
import com.hps.july.terrabyte.imp.helper.DatabaseHelper;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.*;
import java.io.InputStream;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 30.06.2005
 * Time: 11:00:50
 */
public class OfficeMemoHeadersFilesHelper extends DatabaseHelper {

    public static Collection getFilesInfo(Connection connection) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select idheader, filename, man, date, filename  from officememoheaders where filetext is not null";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = getInteger(rs, "idheader");
                String name = getString(rs, "filename");
                Timestamp created = new Timestamp((new java.util.Date()).getTime());
                Timestamp modified = getTimestamp(rs, "date");
                Integer modifiedBy = getInteger(rs, "man");
                if(name == null || (name != null && name.trim().length() == 0)) name = "memoWord.doc";
                OfficeMemoHeadersFileInfo info = new OfficeMemoHeadersFileInfo();
                info.setIdHeader(id);
                info.setCreated(created);
                info.setLastModified(modified);
                info.setModifiedBy(modifiedBy);
                info.setDocFileName(name.trim());
                info.setName(name.trim());
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

    public static InputStream getFileBody(Connection connection, OfficeMemoHeadersFileInfo info) {
        InputStream result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = " select filetext from officememoheaders where idheader = ? ";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getIdHeader());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = rs.getBinaryStream("filetext");
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
            if(rs != null) try {  rs.close(); } catch(Exception e) {}
            if(pstmt != null)  try { pstmt.close(); } catch(Exception e) {}
        }
        return result;
    }

    public static void setExtendedParameters(Connection connection, OfficeMemoHeadersFileInfo info, Integer fileId) {
        PreparedStatement pstmt = null;

        try {
            String sql = " UPDATE tb_files " +
                    " SET modified = ?, modifiedBy = ? \n" +
                    " WHERE id = ? ";
            pstmt = connection.prepareStatement(sql);
            setTimestamp(pstmt, 1, (info.getModified() != null)?info.getModified():new Timestamp(System.currentTimeMillis()));
            setInteger(pstmt, 2, (info.getModifiedBy() != null)?info.getModifiedBy():new Integer(22173));
            setInteger(pstmt, 3, fileId);
            pstmt.executeUpdate();
            if(pstmt != null) pstmt.close();
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
        PreparedStatement pstmt = null; //postion adn names
        ResultSet rs = null;
        try {
            //connection.setAutoCommit(false);
            StringBuffer sql = new StringBuffer(" SELECT l.leasedocument as id, getNameLeaseDocument(l.leasedocument) as name ");
            sql.append("   FROM leasedocuments l WHERE l.leasedocument = ? ");
            pstmt = connection.prepareStatement(sql.toString());
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
