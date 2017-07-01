package com.hps.july.sync.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hps.july.sync.plan.adapters.*;
import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;

/**
 * @author Dmitry Dneprov
 *
 */
public class FactoryPlan extends AbstractFactory{
	public static final String QRY_PLAN_BTS = "PLAN_BTS";
	public static final String QRY_PLAN_SECTOR = "PLAN_SECTOR";
	public static final String QRY_PLAN_SCARDS = "PLAN_SCARDS";
	public static final String QRY_PLAN_IBP = "PLAN_IBP";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		Collaboration adaptor = null;
		DB sourceDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "PLAN");
		if (qry.isQueryType(QRY_PLAN_BTS)) {
			adaptor = new BTSAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_PLAN_SECTOR)) {
			adaptor = new SectorsAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_PLAN_SCARDS)) {
			adaptor = new StorageCardsAdaptor(targetDB, sourceDB, "storagecards", false);
		} else if (qry.isQueryType(QRY_PLAN_IBP)) {
			adaptor = new StorageCardsAdaptor(targetDB, sourceDB, "ibpcards", true);
		}
		return adaptor;
	}
	/**
	 * Тест адаптера
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
/*
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Query qry = new Query(QRY_ALCATEL_MSK);
		qry.setQueryId(5); // Test Query ID
		qry.setIdApp(12);
		Properties prop = null;
		prop = CollaborationClientForNRI.readSyncProperties("D:/WSAD512/workspace/CollaborationClient/appClientModule/sync.cfg");
		DB logDB = new DB(prop, "NRI");
		FactoryOptimize fc = new FactoryOptimize();
		Collaboration col = fc.getAdapter(qry, logDB, prop);
		col.doTask(qry);
		Connection conn = DB.getConnection(logDB);
		//((AlcatelAdaptor)col).ProcessLoadACIESectorsFile(conn, qry, new Integer(1), new File("E:\\beeline\\OMCR\\AlcatelBts_Sector.csv"));
		//((AlcatelAdaptor)col).ProcessLoadACIEBTSFile(conn, qry, new Integer(1), new File("E:\\beeline\\OMCR\\AlcatelBtsSiteManager.csv"));
	}
*/
}
