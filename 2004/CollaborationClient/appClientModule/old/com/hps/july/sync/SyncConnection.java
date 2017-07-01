package old.com.hps.july.sync;

import java.sql.*;
import java.util.Properties;

/**
 * @author Dmitry Dneprov
 *
 * Абстрактный класс соединения с базой данных
 */
public class SyncConnection {
    protected Properties prop;
    protected String prefix;

    public SyncConnection(Properties argProp, String argPrefix) {
        prop = argProp;
        prefix = argPrefix;
    }

    protected String getDriverClass() {
        String prpname = prefix + "Driver";
        String res = prop.getProperty(prpname); //"com.informix.jdbc.IfxDriver";
        return res;
    }

    protected String getConnectionString() {
        return prop.getProperty(prefix + "Conn"); //"jdbc:informix-sqli://172.19.4.50:1541:informixserver=beeinf;database=nfs;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251"
    }

    protected String getUser() {
        return prop.getProperty(prefix + "User");
    }

    protected String getPassword() {
        return prop.getProperty(prefix + "Password");
    }

	public Connection getConnection() {
		Connection con = null;
        System.out.println("Getting Connection to " + prefix);
		try {
			Class.forName(getDriverClass());
		} catch (Exception e) {
			System.out.println("Cannot load jdbc driver, class=" + getDriverClass());
			e.printStackTrace(System.out);
		}
		try {
			con = DriverManager.getConnection(getConnectionString(), getUser(), getPassword());
		} catch (Exception e) {
			System.out.println("Cannot get " + prefix + " connection");
			e.printStackTrace(System.out);
		}
        if (con != null)
            System.out.println(prefix + " Connection OK !");
		return con;
	}
}


