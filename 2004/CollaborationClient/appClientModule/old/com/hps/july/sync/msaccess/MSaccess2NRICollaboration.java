package old.com.hps.july.sync.msaccess;

import old.com.hps.july.sync.msaccess.adapters.*;
import old.com.hps.july.sync.*;

import java.sql.*;
import java.util.*;
import java.io.*;

import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 * @author ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MSaccess2NRICollaboration extends JOIN_DBtoNRICollaboration {
	public final static int ID_APP = 2;

	/**
	 * @see com.hps.july.sync.JOIN_DBtoNRICollaboration#process()
	 */
	protected void process() throws IOException, FileNotFoundException {
		System.out.println("Starting MSaccess2NRICollaboration Collaboration");
		readSyncProperties("C:/WSAD_Collaboration/CollaborationClient/appClientModule/sync.cfg");
		SyncConnection cnriConn = new SyncConnection(prop, "NRI");
		SyncConnection msaConn = new SyncConnection(prop, "MSACCESS");
		QueryQueue queue = new QueryQueue(cnriConn);
		/**
		 *Query qry = queue.findLatestQuery();
		 *System.out.println("Query.getIdApp()="+qry.getIdApp());
		 *insert into informix.join_db_query values(0, 2, 'R', current, null, null, "ALLДля_NRI_таблица", null, null)       		        
		*/
		while (true) {
			Query qry = queue.findLatestQuery();
			if (qry != null && qry.getIdApp()==ID_APP) {
				Adaptor adaptor = null;
				if (qry.isQueryType(QueryType_MSAccess.QRY_ForNRITable)) {
					adaptor = new ForNRITableAdaptor(cnriConn, msaConn);
				} else {
					qry.startProcessing();
					qry.addLogMessage(
						Query.MSG_ERROR,
						"Неизвестный тип запроса - запрос игнорируется");
					qry.finishError();
					continue;
				}
				if (adaptor != null)
					processRequest(qry, adaptor);
			} else {
				try {
					Thread.sleep(SERVICE_TIME);
				} catch (InterruptedException e) {
					System.out.println("Interrupt received - exiting");
					break;
				}
			}
		}
	}
	public static void main(String[] args)
		throws IOException, FileNotFoundException {
		MSaccess2NRICollaboration collab = new MSaccess2NRICollaboration();
		collab.process();
		/**
		 * тест работы с jndi:
		String dsName = "jdbc/informix";
		//String dsName = "jdbc/oracle";
		//String dsName = "MSACCESS";
		try {
			InitialContext ctx = new InitialContext();
			//String dbDatasource = (String) ctx.lookup("java:comp/env/" + dsName);
			//System.out.println("persister datasource name: " + dbDatasource);
			//DataSource ds = (DataSource) ctx.lookup(dbDatasource);
			Object dsObject = ctx.lookup(dsName);
			System.out.println("object datasource ok! class=" + dsObject.getClass());
			DataSource ds = (DataSource) PortableRemoteObject.narrow(dsObject, DataSource.class);
			System.out.println("datasource ok!! ds=" + ds);
			//Connection con = ds.getConnection();//возникает ошибка ???
			//System.out.println("con ok!");
			//if (con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/	
	}
}
