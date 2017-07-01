package com.hps.beeline;

import com.hps.framework.exception.BaseException;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;
import com.hps.framework.exception.BaseException;
import com.hps.framework.sql.StoredProc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Properties;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: mikl
 * Date: 05.11.2004
 * Time: 15:43:09
 * To change this template use File | Settings | File Templates.
 */
public class Config {
    private static Config instance =null;

    private Connection connection = null;
    private Connection logConnection = null;

    private Config() {
    }


    public static Config getInstance() {
        if(instance==null)
            instance = new Config();
        return instance;
    }



    public Connection getDatabaseConnection() throws BaseException {
        if(connection==null) {
            throw new BaseException("Не задано соединение с базой данных!");
        } else
            return connection;
    }

    public Connection getLogConnection() {
        return logConnection;
    }

//    public void closeConnection() throws BaseException {
//        try{
//            connection.close();
//            connection = null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new BaseException("Ошибка получения соединения", e);
//        }
//    }




    public void init(Connection connection, Connection logConnection) throws BaseException {
        this.connection = connection;
        this.logConnection = logConnection;
        if(connection!=null) {
            StoredProc proc = new StoredProc("set lock mode to wait 30");
            proc.executeUpdate();
        }
    }

    public void clearInfo() {
        connection = null;
        logConnection = null;
    }

    public static Connection getTestDatabaseConnection() throws BaseException {
        try{
                DriverManager.registerDriver(new com.informix.jdbc.IfxDriver());
                Properties props = new Properties();
                props.put("user", "informix");
                props.put("password", "in4mix");
                //props.put("password", "beeinf01");
                //props.put("password", "djkr04");
                props.put("DB_LOCALE", "RU_RU.1251");
                props.put("CLIENT_LOCALE", "RU_RU.1251");
                //String url = "jdbc:informix-sqli://10.176.32.18:1545/mikl:informixserver=beeline";
                String url = "jdbc:informix-sqli://wbapp:1526/mikl:informixserver=ol_wbapp77";
                //String url = "jdbc:informix-sqli://nri-application:1541/july:informixserver=beeinf_app";
                //String url = "jdbc:informix-sqli://172.21.9.138:1542/july:informixserver=beeinfprg";
                Connection connection = DriverManager.getConnection(url, props);
                return connection;
            } catch (SQLException e) {
                System.out.println("state = "+e.getSQLState());
                System.out.println("code = "+e.getErrorCode());
                e.printStackTrace();
                throw new BaseException("Ошибка получения соединения", e);
            }
    }

    public static Connection initSpecificConnection(java.sql.Driver driver, String connectionUrl) throws BaseException {
        try {
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection(connectionUrl, new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BaseException("Не возможно подключится к источнику данных "+connectionUrl);
        }
    }

    public static Connection getTestDatabaseConnection(String user, String password, String url) throws BaseException {
        try{
                DriverManager.registerDriver(new com.informix.jdbc.IfxDriver());
                Properties props = new Properties();
                props.put("user", user);
                props.put("password", password);
                props.put("DB_LOCALE", "RU_RU.1251");
                props.put("CLIENT_LOCALE", "RU_RU.1251");
                Connection connection = DriverManager.getConnection(url, props);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BaseException("Ошибка получения соединения", e);
            }

    }
}
