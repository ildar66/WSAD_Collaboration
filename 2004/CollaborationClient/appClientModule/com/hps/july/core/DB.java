package com.hps.july.core;

import com.hps.july.cdbc.lib.CDBCResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Safigullin Ildar
 *
 * Инормация о базе данных
 */
public class DB {
	private String prefix;
	private String driver;
	private String conn;
	private String user;
	private String password;
	private String roleName;
    private String charSet;
    private Map parameters;

    public DB(Properties prop, String prefix) {
		this.prefix = prefix;
		driver = prop.getProperty(prefix + "Driver");
		conn = prop.getProperty(prefix + "Conn");
		user = prop.getProperty(prefix + "User");
		password = prop.getProperty(prefix + "Password");
		roleName = prop.getProperty(prefix + "RoleName");
        charSet = prop.getProperty(prefix + "CharSet");
        parameters = new HashMap();

        String parametersString = prop.getProperty(prefix + "ExtendedProperty");

        if (null != parametersString && !"".equals(parametersString)) {
            StringTokenizer ampTokenizer = new StringTokenizer(parametersString, "&");
            while(ampTokenizer.hasMoreTokens()) {
                String keyValue = ampTokenizer.nextToken();
                StringTokenizer equalTokenizer = new StringTokenizer(keyValue, "=");

                parameters.put(equalTokenizer.nextToken(), equalTokenizer.nextToken());
            }
        }

    }

    public static Connection getConnection(DB db, Properties properties) {
        if (db == null) {
            throw new IllegalArgumentException("Db parameter is null");
        }
        if (properties == null) {
            throw new IllegalArgumentException("properties is null");
        }

        try {
            Class.forName(db.driver);

            properties.setProperty("user", db.user);
            properties.setProperty("password", db.password);

            return DriverManager.getConnection(db.conn, properties);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * WHY THIS METHOD IS SYNCHRONIZED?????
     * this shit have to be not-static method!!!!
     * @param db
     * @return
     */
    public static Connection getConnection(DB db) {
        Connection con = null;
        //System.out.println("Getting Connection to " + db.prefix);
        try {
            Class.forName(db.driver);

            if (db.charSet != null && !"".equals(db.charSet)) {
                Properties props = new Properties();

                props.setProperty("user", db.user);
                props.setProperty("password", db.password);
                props.setProperty("charSet", db.charSet);

                con = DriverManager.getConnection(db.conn, props);
            } else if (db.roleName == null) {
                con = DriverManager.getConnection(db.conn, db.user, db.password);
            } else {
                Properties props = new Properties();
                props.setProperty("user", db.user);
                props.setProperty("password", db.password);
                props.setProperty("roleName", db.roleName);

                con = DriverManager.getConnection(db.conn, props);
            }
        } catch (Exception e) {
            System.out.println("Cannot get " + db.prefix + " connection");
            e.printStackTrace(System.out);
        }

        try {
            if (con != null)
                con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); //new
        } catch (Exception e) {
            System.out.println("Уровень транзакции не поддерживается: " + e.getMessage());
        }

        if (con != null) {
            if (con instanceof com.informix.jdbc.IfxSqliConnect)
                setLockModeWait(con);
        }

        return con;
    }

	public static void setLockModeWait(Connection conn) {
		CDBCResultSet rs = new CDBCResultSet();
		rs.executeUpdate(conn, "SET LOCK MODE TO WAIT 60", new Object[]{});
	}


    public String getParameter(String key) {
        if (parameters.containsKey(key)) {
            return (String) parameters.get(key);
        }

        return "";
    }
}
