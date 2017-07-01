package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.msaccess.adapters.ForNRITableAdaptor;

/**
 * @author Shafigullin ILdar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryDelibashMSAccess extends AbstractFactory{
	public static final String QRY_ForNRITable = "POSITIONS";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourseDB = new DB(prop, "MSACCESS");
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_ForNRITable)) {
			adaptor = new ForNRITableAdaptor(targetDB, sourseDB, prop);
		}
		return adaptor;
	}
}