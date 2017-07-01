/*
 * Created on 23.10.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author irogachev
 *
 */

public class PositionsAdapter extends DefaultCollaboration {

	public static class	PositionsMap extends ColumnMap {
		PositionsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("storageplace", "storageplace", true);
			//
			addMap("netzone", "netzone", false);
			addMap("latitude", "latitude", false);
			addMap("longitude", "longitude", false);
			addMap("regionid", "regionid", false);
			addMap("posstate", "posstate", false);
			addMap("inmetro", "inmetro", false);
			addMap("adminregionid", "adminregionid", false);
			addMap("settlementid", "settlementid", false);
			addMap("fincategid", "fincategid", false);
			addMap("antplaceid", "antplaceid", false);
			addMap("renter", "renter", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	PositionsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.positions", "positions", null, new PositionsMap(), dbSource);
	}

}


