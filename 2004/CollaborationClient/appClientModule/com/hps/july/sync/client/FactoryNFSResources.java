package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.nfs.adapters.*;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryNFSResources extends AbstractFactory {
	public static final String QRY_DAILYRES = "DAILYRES";
	public static final String QRY_DAILYCATEG = "DAILYCATEG";
	public static final String QRY_DAILYRES2CAT = "DAILYRES2CAT";
	public static final String QRY_DAILYPROC = "DAILYPROC";
	/* (non-Javadoc)
	 * @see com.hps.july.sync.client.AbstractFactory#getAdapter(com.hps.july.core.Query, com.hps.july.core.DB, com.hps.july.core.DB, com.hps.july.core.DB, java.util.Properties)
	 */
	public Collaboration getAdapter(Query qry, DB logSourse, Properties prop) {
		DB targetDB = new DB(prop, "NRI");
		DB sourceDailyDB = new DB(prop, "NFSDAILY");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_DAILYRES)) {
			adaptor = new DailyResourcesAdaptor(targetDB, sourceDailyDB);
		} else if (qry.isQueryType(QRY_DAILYCATEG)) {
			adaptor = new DailyCategoriesAdaptor(targetDB, sourceDailyDB);
		} else if (qry.isQueryType(QRY_DAILYRES2CAT)) {
			adaptor = new DailyCat2ResourcesAdaptor(targetDB, sourceDailyDB);
		} else if (qry.isQueryType(QRY_DAILYPROC)) {
			adaptor = new DailyProcAdaptor(targetDB, sourceDailyDB, targetDB);
		}
		return adaptor;
	}
}
