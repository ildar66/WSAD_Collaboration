package com.hps.july.terrabyte.imp.helper.arenda;

import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.essence.BSProjectFileInfo;
import com.hps.july.terrabyte.imp.essence.ArendaLeaseFileInfo;
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
public class ArendaLeaseFilesHelper extends DatabaseHelper {

    public static Collection getFilesInfo(Connection connection) throws BaseException {
        ArrayList list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select nvl(la.mainleasedocument, la.leasedocument) IDContract, " +
                    " la.leasedocument, lc.docFileName, ld.createdby, ld.created, " +
                    " ld.modifiedby, ld.modified, getNamePeople(ld.createdby) as creator \n" +
                    "  from leasearendaagrmnts la, leasecontracts lc, leasedocuments ld\n" +
                    "  where lc.leasedocument = la.leasedocument\n" +
                    "   and lc.leasedocument = ld.leasedocument\n" +
                    "   and docfilename is not null and  lc.docText is not null " +
                    " and la.leasedocument in (40239, 35856, 43068, 40986, 35562) ";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Integer id = getInteger(rs, "IDContract");
                String name = getString(rs, "docFileName");
                Timestamp created = getTimestamp(rs, "created");
                Timestamp modified = getTimestamp(rs, "modified");
                Integer createdBy = getInteger(rs, "createdBy");
                Integer modifiedBy = getInteger(rs, "modifiedBy");
                Integer leaseDoc = getInteger(rs, "leasedocument");
                String creator = getString(rs, "creator");

                ArendaLeaseFileInfo info = new ArendaLeaseFileInfo();
                info.setIdContract(id);
                info.setCreated(created);
                info.setCreatedBy(createdBy);
                info.setLastModified(modified);
                info.setModifiedBy(modifiedBy);
                info.setDocFileName(name.trim());
                info.setName(name.trim());
                info.setLeaseDocId(leaseDoc);
                info.setDescr((creator != null)?creator.trim():"");
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

    public static InputStream getFileBody(Connection connection, ArendaLeaseFileInfo info) {
        InputStream result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = " select nvl(la.mainleasedocument, la.leasedocument) IDContract, \n" +
                    "  la.leasedocument, \n" +
                    " lc. docFileName, ld.createdby, ld.created ,   lc.docText\n" +
                    "   from leasearendaagrmnts la, leasecontracts lc, leasedocuments ld\n" +
                    "   where lc.leasedocument = la.leasedocument\n" +
                    "    and lc.leasedocument = ld.leasedocument\n" +
                    "    and docfilename is not null  AND lc.docText IS NOT NULL \n" +
                    "  AND la.leasedocument = ? ";
            pstmt = connection.prepareStatement(sql);
            setInteger(pstmt, 1, info.getLeaseDocId());
            rs = pstmt.executeQuery();
            while(rs.next()) {
                result = rs.getBinaryStream("docText");
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

    public static void setExtendedParameters(Connection connection, ArendaLeaseFileInfo info, Integer fileId) {
        PreparedStatement pstmt = null;

        try {
            String sql = " UPDATE tb_files " +
                    " SET created = ?, createdBy = ?, modified = ?, modifiedBy = ? \n" +
                    " WHERE id = ? ";
            pstmt = connection.prepareStatement(sql);
            setTimestamp(pstmt, 1, info.getCreated());
            setInteger(pstmt, 2, info.getCreatedBy());
            setTimestamp(pstmt, 3, info.getModified());
            setInteger(pstmt, 4, info.getModifiedBy());
            setInteger(pstmt, 5, fileId);
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
