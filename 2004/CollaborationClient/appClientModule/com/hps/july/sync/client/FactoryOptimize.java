package com.hps.july.sync.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hps.july.sync.optimize.adapters.*;
import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;

/**
 * @author Dmitry Dneprov
 *
 */
public class FactoryOptimize extends AbstractFactory{
	public static final String QRY_OPT_PEOPLE = "OPT_PEOPLE";
	public static final String QRY_OPT_REGIONS = "OPT_REGIONS";
	public static final String QRY_OPT_FILIALS = "OPT_FILIALS";
	public static final String QRY_OPT_MSC = "OPT_MSC";
	public static final String QRY_OPT_BSC = "OPT_BSC";
	public static final String QRY_OPT_SITE = "OPT_SITE";
	public static final String QRY_OPT_BTS = "OPT_BTS";
	public static final String QRY_OPT_SECTOR = "OPT_SECTOR";
	public static final String QRY_OPT_UPDATE = "OPT_UPDATE";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		Collaboration adaptor = null;
		DB sourceDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "OPTIMIZE");
		if (qry.isQueryType(QRY_OPT_PEOPLE)) {
			adaptor = new PeopleAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_REGIONS)) {
			adaptor = new RegionAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_FILIALS)) {
			adaptor = new FilialAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_MSC)) {
			adaptor = new MSCAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_BSC)) {
			adaptor = new BSCAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_SITE)) {
			adaptor = new SiteAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_BTS)) {
			adaptor = new BTSAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_SECTOR)) {
			adaptor = new SectorAdaptor(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_OPT_UPDATE)) {
			adaptor = new NriSettingsAdaptor(targetDB, sourceDB);
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
