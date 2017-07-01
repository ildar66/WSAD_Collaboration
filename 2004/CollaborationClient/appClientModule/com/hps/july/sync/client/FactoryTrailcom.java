package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.trailcom.adapters.TrailcomEquipmentCalcAdaptor;
import com.hps.july.sync.trailcom.adapters.TrailcomEquipmentLoadAdaptor;
import com.hps.july.sync.trailcom.adapters.TrailcomFrequencyPermitsUploadAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Dmitry Dneprov
 *
 */
public class FactoryTrailcom extends AbstractFactory{
	public static final String QRY_TRAILCOMEQUIPLOAD = "TRAILCOMEQUIPLOAD";
	public static final String QRY_TRAILCOMEQUIPCALC = "TRAILCOMEQUIPCALC";
	public static final String QRY_TRAILCOMFREQUENCYPERMITSLOAD = "TRAILCOM_FREQ_PERMITS";
    public static final String QRY_EXCELPROCESSOR = "EXCEL_RLL_UPLOADER";

	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourceDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_TRAILCOMEQUIPLOAD)) {
			adaptor = new TrailcomEquipmentLoadAdaptor(targetDB, sourceDB, logDB);
		} else if (qry.isQueryType(QRY_TRAILCOMEQUIPCALC)) {
			adaptor = new TrailcomEquipmentCalcAdaptor(targetDB, sourceDB, logDB);
		} else if (qry.isQueryType(QRY_TRAILCOMFREQUENCYPERMITSLOAD)) {
			adaptor = new TrailcomFrequencyPermitsUploadAdapter(targetDB, logDB, prop);
		}
		return adaptor;
	}

    /**
	 * Тест адаптера
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		Query qry = new Query(QRY_CALCPROLET);
		qry.setQueryId(5); // Test Query ID
		qry.setIdApp(8);
		//qry.setReqType();
		Properties prop = null;
		prop = CollaborationClientForNRI.readSyncProperties("D:/WSAD512/workspace/CollaborationClient/appClientModule/sync.cfg");
		DB logDB = new DB(prop, "NRI");
		FactoryTrailcom fc = new FactoryTrailcom();
		Collaboration col = fc.getAdapter(qry, logDB, prop);
		col.doTask(qry);
		*/
	}
}
