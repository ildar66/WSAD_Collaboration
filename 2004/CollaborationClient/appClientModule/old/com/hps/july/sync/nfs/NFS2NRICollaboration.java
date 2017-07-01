package old.com.hps.july.sync.nfs;

import old.com.hps.july.sync.nfs.adapters.*;
import old.com.hps.july.sync.*;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 * @author Dmitry Dneprov
 *
 * Алгоритм переноса данных из NFS в NRI
 */
public class NFS2NRICollaboration extends JOIN_DBtoNRICollaboration {
	public final static int ID_APP = 1;	

	// Основной метод
	public void process() throws IOException, FileNotFoundException {
		System.out.println("Starting NFS2NRI Collaboration");
		readSyncProperties("C:/WSAD_Collaboration/CollaborationClient/appClientModule/sync.cfg");
		SyncConnection cnfsConn = new SyncConnection(prop, "NFS");
		SyncConnection cnriConn = new SyncConnection(prop, "NRI");

		QueryQueue queue = new QueryQueue(cnriConn);
		        while (true) {
		            Query qry = queue.findLatestQuery();
		            if (qry != null) {
		                Adaptor adaptor = null;
		                if (qry.isQueryType(QueryType_NFS.QRY_BANKS)) {
		                    adaptor = new BanksAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_CURRENCY)) {
		                    adaptor = new CurrencyAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_VENDORSITES)) {
		                    adaptor = new VendorSitesAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_VENDORS)) {
		                    adaptor = new VendorsAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_ACCOUNTS)) {
		                    adaptor = new AccountsAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_USERS)) {
		                    adaptor = new UsersAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_ZP)) {
		                    adaptor = new ZPAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_SF)) {
		                    adaptor = new SFAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_PAYS)) {
		                    adaptor = new PaysAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_CONTRACTS)) {
		                    adaptor = new ContractsAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_TAXRATES)) {
		                    adaptor = new TaxesAdaptor(cnriConn, cnfsConn);
		                } else if (qry.isQueryType(QueryType_NFS.QRY_CURRRATES)) {
		                    adaptor = new CurrencyRatesAdaptor(cnriConn, cnfsConn);
		                } else {
		                    qry.startProcessing();
		                    qry.addLogMessage(Query.MSG_ERROR, "Неизвестный тип запроса - запрос игнорируется");
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
		NFS2NRICollaboration collab = new NFS2NRICollaboration();
		collab.process();
	}
}
