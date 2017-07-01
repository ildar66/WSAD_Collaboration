/*
 *  $Id: TestArendaCheckProlongate.java,v 1.1 2007/06/18 15:55:38 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.terrabyte.imp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.BeansException;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.terrabyte.imp.AppLog1;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.july.terrabyte.imp.command.DrawingCommand;
import com.hps.july.terrabyte.imp.command.arenda.CheckLeaseProlongateCommand;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.1 $ $Date: 2007/06/18 15:55:38 $
 */
public class TestArendaCheckProlongate {
	public static final String CONTEXT_CONFIG_LOCATION = "collaborationApplicationContext.xml";

	public void test() {
			Date st = new Date();
			Connection con = null;
			Connection log = null;

			try {
				con = getConnection();
				CheckLeaseProlongateCommand command = new CheckLeaseProlongateCommand(new Integer(345), con, log, new Integer(30));
				TerrabyteLoaderProcessor.executeLoaderCommand(command, "localhost", new Integer(8090));
			} catch(LoaderException e) {
				e.printStackTrace();
				AppLog1.log(e);
			} catch(BaseException e) {
				e.printStackTrace();
				AppLog1.log(e);
			} catch(Exception e) {
				e.printStackTrace();
				AppLog1.log(e);
			} finally{
				try { if(con != null) { con.close();} } catch(SQLException e) {}
				try { if(log != null) { log.close();} } catch(SQLException e) {}
			}
			//AppLog1.log("Execution time ["+getMethodExecutionTime(st, new Date())+"]");
	}

	public Connection getConnection() {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
/*
			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup("java:comp/env/jdbc/terrabyte");
			connection = dataSource.getConnection();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
*/
			Class.forName("com.informix.jdbc.IfxDriver");
			connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.133:1541:informixserver=beeinf_app;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;informixLockModeWait=60;user=vad;password=vad01");
			//connection = DriverManager.getConnection("jdbc:informix-sqli://192.168.18.202:1541:informixserver=beeinf_app;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=informix;password=beeinf01;informixLockModeWait=60");
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			pstmt = connection.prepareStatement(" set lock mode to wait 60; ");
			pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { if(pstmt != null) pstmt.close(); } catch(SQLException e) {}
		}
		return connection;
	}


	public static void main(String[] args) {
		TestArendaCheckProlongate test = new TestArendaCheckProlongate();
		test.test();

	}
}
