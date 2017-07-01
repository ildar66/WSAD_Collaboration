package com.hps.july.terrabyte.imp.test;

import com.hps.july.terrabyte.imp.command.ListBsForSORMCommand;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.FieldPosition;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 17:07:10
 */
public class TestListBsForSORMProcessor {

    public void test() {
            Date st = new Date();
            Connection con = null;
            Connection log = null;

            try {
                con = getConnection(0);
                //log = getConnection(2);

                ListBsForSORMCommand command = new ListBsForSORMCommand(new Integer(2222), con, log);
                command.setDirectory("C:\\6\\2");
                TerrabyteLoaderProcessor.executeLoaderCommand(command, null, new Integer(9080));
            } catch(LoaderException e) {
                e.printStackTrace();
                //AppLog.log(e);
            } catch(BaseException e) {
                e.printStackTrace();
                //AppLog.log(e);
            } catch(Exception e) {
                e.printStackTrace();
                //AppLog.log(e);
            } finally{
                try { if(con != null) { con.close(); } } catch(SQLException e) {}
                try { if(log != null) { log.close(); } } catch(SQLException e) {}
            }
            //AppLog.log("Execution time ["+getMethodExecutionTime(st, new Date())+"]");
    }

    public Connection getConnection(int i) {
        Connection connection = null;
        try {
/*
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource)initialContext.lookup("java:comp/env/jdbc/terrabyte");
            connection = dataSource.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
*/
            Class.forName("com.informix.jdbc.IfxDriver");
            if(i == 2) connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.138:1541:informixserver=inftest;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=informix;password=beeinf01;informixLockModeWait=60");
            else if(i == 1) connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.131:1541:informixserver=beeinf_app;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=nrisync;password=TZ1N&u5j;informixLockModeWait=60");
            else connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.137:1541:informixserver=infdata;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=vad;password=AKpASCSX;informixLockModeWait=60");
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static void main(String[] args) {
        TestListBsForSORMProcessor test = new TestListBsForSORMProcessor();
        test.test();

    }

    public String getMethodExecutionTime(Date startTime, Date endTime) {
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
