package com.hps.july.core.process;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.*;
import java.sql.Date;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 02.03.2005
 * Time: 11:39:16
 */
public abstract class Process {

    public Connection getConnection() {
        Connection connection = null;
        try {
            DataSource dataSource = null;
            InitialContext initialContext = null;
            try {
                initialContext = new InitialContext();
                dataSource = (DataSource)initialContext.lookup("julysec");
            } catch(NamingException e) {
                System.out.println("Cannot find datasource [julysec]");
                //e.printStackTrace();
            }
            connection = dataSource.getConnection();
/*
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println("connection.isClosed() " + connection.isClosed());
            pstmt = connection.prepareStatement("SELECT * from sequences;");
            pstmt.executeQuery();
*/
/*
            Class.forName("com.informix.jdbc.IfxDriver");
            connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.138:1541:informixserver=beeinf;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=informix;password=djkr04;informixLockModeWait=60");
            //connection = DriverManager.getConnection("jdbc:informix-sqli://192.168.18.202:1541:informixserver=beeinf_app;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=informix;password=beeinf01;informixLockModeWait=60");
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(" set lock mode to wait 60; ");
            pstmt.executeUpdate();
*/
        } catch(Exception e) {
            System.out.println("Cannot find datasource ! ");
            e.printStackTrace();
        }
        return connection;
    }

    public Integer getNextKey(Connection connection, String alias) throws SQLException {
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
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new SQLException("While getting serial id for " + alias);
        } finally {
            try{ rs.close(); } catch( Throwable t )  {}
            try{ pstmt.close(); } catch( Throwable t ) {}
        }
        return result;
    }

    public Integer getSerialNextKey(Connection connection) throws SQLException {
        Integer result = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement("SELECT DBINFO('sqlca.sqlerrd1') serres FROM dummy");
            rs = pstmt.executeQuery();
            rs.next();
            result = new Integer(rs.getInt(1));
        } catch( Exception e ) {
            if(e instanceof SQLException) {
                System.out.println("---Code ["+((SQLException)e).getErrorCode()+"]");
            }
            e.printStackTrace();
            throw new SQLException("While getting serial id for ");
        } finally {
            try{ rs.close(); } catch( Throwable t )  {}
            try{ pstmt.close(); } catch( Throwable t ) {}
        }
        return result;
    }

    public Integer getInteger(ResultSet resultSet, String name) throws SQLException {
        int value = resultSet.getInt(name);
        if (resultSet.wasNull()) return null ;
        return new Integer(value);
    }

    public void setInteger(PreparedStatement pstmt, int pos, Integer value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.INTEGER);
        else pstmt.setInt(pos, value.intValue());
    }

    public String getString(ResultSet resultSet, String name) throws SQLException {
        String value = resultSet.getString(name);
        if (resultSet.wasNull()) return null ;
        return value ;
    }

    public void setString(PreparedStatement pstmt, int pos, String value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.VARCHAR);
        else pstmt.setString(pos, value);
    }

    public void setDate(PreparedStatement pstmt, int pos, Date value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.DATE);
        else pstmt.setDate(pos, value);
    }

    public void setTimestamp(PreparedStatement pstmt, int pos, Timestamp value) throws SQLException {
        if (value == null) pstmt.setNull(pos, Types.TIMESTAMP);
        else pstmt.setTimestamp(pos, value);
    }

    public Timestamp getTimestamp(ResultSet resultSet, String name) throws SQLException {
        Timestamp value = resultSet.getTimestamp(name);
        if (resultSet.wasNull()) return null ;
        return value ;
    }

    public byte[] getByteArray(ResultSet resultSet, String name) throws SQLException, IOException {
        java.io.InputStream is = resultSet.getBinaryStream(name);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (resultSet.wasNull()) return null ;
        byte[] buf = new byte[4096];
        int count = -1 ;
        while ((count = is.read(buf)) != -1) out.write(buf, 0, count);
        is.close();
        buf = null;
        return out.toByteArray();
    }

    public void setByteArray(PreparedStatement pstmt, int pos, byte[] stream) throws SQLException, IOException {
        if (stream == null) pstmt.setNull(pos, Types.BLOB);
        else pstmt.setBinaryStream(pos, new ByteArrayInputStream(stream), stream.length);
    }

    public boolean isValidString(String s) {
        return (s != null && s.trim().length() > 0);
    }
/*
    private Collection analysis(HashMap groups) {
        ArrayList result = new ArrayList();
        ArrayList result1 = new ArrayList();

        Set s = groups.keySet();
        for(Iterator iterator = s.iterator(); iterator.hasNext();) {
            ArrayList temp = (ArrayList)iterator.next();
            if(temp.size() == 1) result.add((RangeAllow)temp.get(0));
            else {
                Object [] aTemp = temp.toArray();
                Arrays.sort(aTemp, new Comparator() {
                    public int compare(Object o, Object o1) {
                        RangeAllow a = (RangeAllow)o;
                        RangeAllow b = (RangeAllow)o1;
                        if(a.getCount() > b.getCount())
                            return 1;
                        else if(a.getCount() == b.getCount())
                            return 0;
                        else
                            return -1;
                    }
                });
                if(aTemp.length > 0) result.add((RangeAllow)aTemp[0]);
            }
        }
        Object [] resultAr = result.toArray();
        for(int i = 0; i < resultAr.length; i++) {
            RangeAllow ra = (RangeAllow)resultAr[i];

            if(i != resultAr.length - 1) {
                RangeAllow ra1 = (RangeAllow)resultAr[i + 1];
                if(ra.getEndnumber() < ra1.getStartnumber()) {
                    result1.add(ra);
                    continue;
                } else {
                    int k = -1;
                    RangeAllow ra3 = null;
                    for(int j = i + 1; j < resultAr.length; j++) {
                        RangeAllow ra2 = (RangeAllow)resultAr[j];
                        if(ra.getEndnumber() > ra1.getStartnumber()) {
                            k = j;
                            if(ra3 == null) ra3 = ra2;
                            else {
                                if(ra3.getCount() < ra2.getCount()) ra3 = ra2;
                            }
                        }
                    }
                    if(k != -1 && ra3 != null) {
                        result1.add(ra3);
                        i = k++;
                    }
                }
            } else {
                result1.add(ra);
            }
        }

        return result;
*/
}
