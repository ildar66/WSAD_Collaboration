/*
 * Created on 20.10.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author irogachev
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class RegionsAdapter extends DefaultCollaboration {

	public static class RegionsMap extends ColumnMap {
		RegionsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("regionid", "regionid", true);
			//
			addMap("orgBeelineBilling", "orgBeelineBilling", false);
			addMap("supregid", "supregid", false);
			addMap("regname", "regname", false);
			addMap("sectorprefix", "sectorprefix", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	RegionsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.regions", "regions", null, new RegionsMap(), dbSource);
	}

}

