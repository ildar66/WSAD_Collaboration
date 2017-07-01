package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.nri.adapters.BalanceExcelFilesAdapter;
import com.hps.july.sync.nri.adapters.InventoryOutProcessorAdapter;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryMolkom extends AbstractFactory {
	public static final String QRY_BALANCESTORE = "BALANCESTORE";
	public static final String QRY_OUTSTORE = "OUTSTORE";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_BALANCESTORE)) {
			adaptor = new BalanceExcelFilesAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_OUTSTORE)) {
			adaptor = new InventoryOutProcessorAdapter(targetDB, logDB, prop);
		}
		return adaptor;
	}
}
