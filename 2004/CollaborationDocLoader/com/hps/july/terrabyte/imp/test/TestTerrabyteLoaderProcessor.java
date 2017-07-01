package com.hps.july.terrabyte.imp.test;

import com.hps.july.terrabyte.imp.command.LoadPositionGRPImageCommand;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.FieldPosition;

/**
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 23.06.2005
 * Time: 17:07:10
 */
public class TestTerrabyteLoaderProcessor {

	public void test() {
			Date st = new Date();
			Connection con = null;
			Connection log = null;

			try {
				con = getConnection();
				LoadPositionGRPImageCommand command = new LoadPositionGRPImageCommand(new Integer(345), con, log);
				command.setDirectory("C:\\3");
				command.setCatalog("position.grp1");
				TerrabyteLoaderProcessor.executeLoaderCommand(command, "localhost", new Integer(8090));
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
				try { if(con != null) { con.close();} } catch(SQLException e) {}
				try { if(log != null) { log.close();} } catch(SQLException e) {}
			}
			//AppLog.log("Execution time ["+getMethodExecutionTime(st, new Date())+"]");
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
			connection = DriverManager.getConnection("jdbc:informix-sqli://172.21.9.138:1541:informixserver=beeinf;database=july;DB_LOCALE=ru_RU.1251;CLIENT_LOCALE=ru_RU.1251;user=informix;password=djkr04");
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
		TestTerrabyteLoaderProcessor test = new TestTerrabyteLoaderProcessor();
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
