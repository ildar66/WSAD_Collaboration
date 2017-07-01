package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Query;
import com.hps.july.sync.mssql.adapters.*;
import com.hps.july.core.DB;
import com.hps.july.core.Collaboration;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryGalchenkovMSSQL extends AbstractFactory{
	public static final String QRY_BSGENERALCR = "GLCHBSGENERALCR";
	public static final String QRY_BSGENERALCRP = "GLCHBSGENERALCRP";
	public static final String QRY_PROC = "GLCHPROC";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourceDB = new DB(prop, "MSSQL");
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_BSGENERALCR)) {
			adaptor = new BaseStationsAdapter2(targetDB, sourceDB, logDB);
		} else if (qry.isQueryType(QRY_BSGENERALCRP)) {
			adaptor = new BaseStationsAdapter1(targetDB, sourceDB, logDB);
		} else if (qry.isQueryType(QRY_PROC)) {
			adaptor = new ProcAdaptor(targetDB, sourceDB, logDB);
		}
		return adaptor;
	}
}
