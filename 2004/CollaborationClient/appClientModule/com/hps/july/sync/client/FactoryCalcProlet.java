package com.hps.july.sync.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.calc.adapters.*;

/**
 * @author Dmitry Dneprov
 *
 */
public class FactoryCalcProlet extends AbstractFactory{
	public static final String QRY_CALCPROLET = "CALCPROLET";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourceDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_CALCPROLET)) {
			String beeplanDir = prop.getProperty("CPRLBEEPLANDIR");
			String terrabyteHost = prop.getProperty("terrabyte.host");
			String terrabytePort = prop.getProperty("terrabyte.port");
			int tport = Integer.parseInt(terrabytePort);
			adaptor = new CalcProletAdaptor(targetDB, sourceDB, logDB,
				beeplanDir, terrabyteHost, tport
				);
		}
		return adaptor;
	}
	/**
	 * Тест адаптера
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, CollaborationException {
		Query qry = new Query(QRY_CALCPROLET);
		qry.setQueryId(5); // Test Query ID
		qry.setIdApp(8);
		//qry.setReqType();
		Properties prop = null;
		prop = CollaborationClientForNRI.readSyncProperties("D:/WSAD512/workspace/CollaborationClient/appClientModule/sync.cfg");
		DB logDB = new DB(prop, "NRI");
		FactoryCalcProlet fc = new FactoryCalcProlet();
		Collaboration col = fc.getAdapter(qry, logDB, prop);
		col.doTask(qry);
	}
}
