package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.nri.adapters.ImageShrinkerAdapter;

import java.util.Properties;
/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryPhotos extends AbstractFactory {
	public static final String QRY_SHRINKPHOTOBYID = "SHRINKPHOTOBYID";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if(qry.isQueryType(QRY_SHRINKPHOTOBYID)) {
			adaptor = new ImageShrinkerAdapter(targetDB, logDB, prop);
		}
		return adaptor;
	}
}
