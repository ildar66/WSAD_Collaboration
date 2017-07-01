package com.hps.july.terrabyte.imp.helper;

import com.hps.july.terrabyte.imp.AppLog;

import java.sql.*;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 17:57:26
 */
public class DatabaseHelper {

    protected DatabaseHelper() {
    }

    public static Integer getNextKey(Connection connection, String alias) throws SQLException {
        Integer result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("EXECUTE PROCEDURE getSequence(?)");
            pstmt.setString(1, alias);
            rs = pstmt.executeQuery();
            rs.next();
            result = new Integer(rs.getInt(1));
        } catch( Exception e ) {
            if(e instanceof SQLException) {
                //AppLog.log("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new SQLException("While getting serial id for " + alias);
        } finally {
            try{ rs.close(); } catch( Throwable t )  {}
            try{ pstmt.close(); } catch( Throwable t ) {}
        }
        return result;

    }

    public static Integer getInteger(ResultSet resultSet, String name) throws SQLException {
        int value = resultSet.getInt(name);
        if (resultSet.wasNull()) return null ;
        return new Integer(value);
    }

    public static void setInteger(PreparedStatement pstmt, int pos, Integer value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.INTEGER);
        else pstmt.setInt(pos, value.intValue());
    }

    public static String getString(ResultSet resultSet, String name) throws SQLException {
        String value = resultSet.getString(name);
        if (resultSet.wasNull()) return null ;
        return value ;
    }

    public static void setString(PreparedStatement pstmt, int pos, String value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.VARCHAR);
        else pstmt.setString(pos, value);
    }

    public static void setDate(PreparedStatement pstmt, int pos, Date value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.DATE);
        else pstmt.setDate(pos, value);
    }

    public static void setTimestamp(PreparedStatement pstmt, int pos, Timestamp value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.TIMESTAMP);
        else pstmt.setTimestamp(pos, value);
    }

    public static Timestamp getTimestamp(ResultSet resultSet, String name) throws SQLException {
        Timestamp value = resultSet.getTimestamp(name);
        if (resultSet.wasNull()) return null ;
        return value ;
    }

    public static byte[] getByteArray(ResultSet resultSet, String name) throws SQLException, IOException {
        java.io.InputStream is = resultSet.getBinaryStream(name);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (resultSet.wasNull()) return null ;
        byte[] buf = new byte[65535];
        int count = -1 ;
        while ((count = is.read(buf)) != -1) out.write(buf, 0, count);
        is.close();
        buf = null;
        return out.toByteArray();
    }

    public static void setByteArray(PreparedStatement pstmt, int pos, byte[] stream) throws SQLException, IOException {
        if (stream == null) pstmt.setNull(pos, Types.BLOB);
        else pstmt.setBinaryStream(pos, new ByteArrayInputStream(stream), stream.length);
    }

}
