package com.hps.july.sync.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.hps.july.sync.oss.adapters.*;
import com.hps.july.core.Collaboration;
import com.hps.july.core.CollaborationException;
import com.hps.july.core.Query;
import com.hps.july.core.DB;

/**
 * @author Dmitry Dneprov
 *
 */
public class FactoryOSS extends AbstractFactory{
	public static final String QRY_ALCATEL_MSK = "ALCATEL_MSK";
	public static final String QRY_ALCATEL_CREG = "ALCATEL_CREG";
	public static final String QRY_ALCATEL_STAVROPOL = "ALCATEL_STAVR";
	public static final String QRY_ALCATEL_CHELIABINSK = "ALCATEL_CHEL";
	public static final String QRY_ALCATEL_KAZAHSTAN = "ALCATEL_KAZAH";
	public static final String QRY_ALCATEL_NNOVGOROD = "ALCATEL_NNOVG";
	public static final String QRY_NOKIA_BER = "NOKIA_BER";
	public static final String QRY_NOKIA_NWR = "NOKIA_NWR";
	public static final String QRY_ERICSSON_SARATOV = "ERIC_SAR";
	public static final String QRY_ERICSSON_EBURG = "ERIC_EBU";
	public static final String QRY_ERICSSON_KHABAROVSK = "ERIC_KHA";
	public static final String QRY_ERICSSON_NOVOSIBIRSK = "ERIC_NOV";
	public static final String QRY_ERICSSON_PITER = "ERIC_PIT";
	public static final String QRY_ERICSSON_ROSTOV = "ERIC_ROS";
	public static final Integer AlcatelMoscowPLMN = new Integer(1);
	public static final Integer NokiaBERPLMN = new Integer(2);
	public static final Integer EricssonSaratovPLMN = new Integer(3);
	public static final Integer EricssonEburgPLMN = new Integer(4);
	public static final Integer EricssonKhabarovskPLMN = new Integer(5);
	public static final Integer EricssonNovosibirskPLMN = new Integer(6);
	public static final Integer EricssonPiterPLMN = new Integer(7);
	public static final Integer EricssonRostovPLMN = new Integer(8);
	public static final Integer AlcatelCRegPLMN = new Integer(9);
	public static final Integer NokiaNWRPLMN = new Integer(10);
	public static final Integer AlcatelStavropolPLMN = new Integer(11);
	public static final Integer AlcatelCheliabinskPLMN = new Integer(13);
	public static final Integer AlcatelKazahstanPLMN = new Integer(14);
	public static final Integer AlcatelNNovgorodPLMN = new Integer(15);
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_ALCATEL_MSK)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_MSKARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_MSKACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelMoscowPLMN);
		} else if (qry.isQueryType(QRY_ALCATEL_CREG)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_CREGARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_CREGACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelCRegPLMN);
		} else if (qry.isQueryType(QRY_ALCATEL_STAVROPOL)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_STAVRARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_STAVRACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelStavropolPLMN);
		} else if (qry.isQueryType(QRY_ALCATEL_CHELIABINSK)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_CHELARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_CHELACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelCheliabinskPLMN);
		} else if (qry.isQueryType(QRY_ALCATEL_KAZAHSTAN)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_KAZAHARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_KAZAHACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelKazahstanPLMN);
		} else if (qry.isQueryType(QRY_ALCATEL_NNOVGOROD)) {
			DB sourceDB = new DB(prop, "NRI");
			DB targetDB = new DB(prop, "NRI");
			String loadARIEDir = prop.getProperty("ALCATEL_NNOVARIEDATADIR");
			String loadACIEDir = prop.getProperty("ALCATEL_NNOVACIEDATADIR");
			adaptor = new AlcatelAdaptor(targetDB, sourceDB, logDB,
				loadARIEDir, loadACIEDir, AlcatelNNovgorodPLMN);
		} else if (qry.isQueryType(QRY_NOKIA_BER)) {
			DB sourceDB = new DB(prop, "NOKIAOSS_BER");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new NokiaAdaptor(targetDB, sourceDB, logDB, NokiaBERPLMN);
		} else if (qry.isQueryType(QRY_NOKIA_NWR)) {
			DB sourceDB = new DB(prop, "NOKIAOSS_NWR");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new NokiaAdaptor(targetDB, sourceDB, logDB, NokiaNWRPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_SARATOV)) {
			DB sourceDB = new DB(prop, "ERICOSS_SARATOV");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonSaratovPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_EBURG)) {
			DB sourceDB = new DB(prop, "ERICOSS_EBURG");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonEburgPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_KHABAROVSK)) {
			DB sourceDB = new DB(prop, "ERICOSS_KHABAROVSK");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonKhabarovskPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_NOVOSIBIRSK)) {
			DB sourceDB = new DB(prop, "ERICOSS_NOVOSIBIRSK");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonNovosibirskPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_PITER)) {
			DB sourceDB = new DB(prop, "ERICOSS_PITER");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonPiterPLMN);
		} else if (qry.isQueryType(QRY_ERICSSON_ROSTOV)) {
			DB sourceDB = new DB(prop, "ERICOSS_ROSTOV");
			DB targetDB = new DB(prop, "NRI");
			adaptor = new EricssonAdaptor(targetDB, sourceDB, logDB, EricssonRostovPLMN);
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
		Query qry = new Query(QRY_ALCATEL_MSK);
		qry.setQueryId(5); // Test Query ID
		qry.setIdApp(12);
		Properties prop = null;
		prop = CollaborationClientForNRI.readSyncProperties("D:/WSAD512/workspace/CollaborationClient/appClientModule/sync.cfg");
		DB logDB = new DB(prop, "NRI");
		FactoryOSS fc = new FactoryOSS();
		Collaboration col = fc.getAdapter(qry, logDB, prop);
		col.doTask(qry);
		Connection conn = DB.getConnection(logDB);
		//((AlcatelAdaptor)col).ProcessLoadACIESectorsFile(conn, qry, new Integer(1), new File("E:\\beeline\\OMCR\\AlcatelBts_Sector.csv"));
		//((AlcatelAdaptor)col).ProcessLoadACIEBTSFile(conn, qry, new Integer(1), new File("E:\\beeline\\OMCR\\AlcatelBtsSiteManager.csv"));
	}
}
