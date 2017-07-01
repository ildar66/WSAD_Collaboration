package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.msaccess.adapters.*;

/**
 * @author Shafigullin ILdar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryBichkovMSAccess extends AbstractFactory{
	public static final String QRY_BchkDocPositions = "bchkdocpositions";
	public static final String QRY_BchkDocuments = "bchkdocuments";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourseDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "MSACCESS1");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_BchkDocuments)) {
			adaptor = new BchkDocumentsAdaptor(targetDB, sourseDB, logDB);
		}
		else if (qry.isQueryType(QRY_BchkDocPositions)) {
			adaptor = new BchkDocPositionsAdaptor(targetDB, sourseDB, logDB);
		}
		return adaptor;
	}
}