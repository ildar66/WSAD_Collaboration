/*
 * Created on 01.02.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class StationsInRegionAdaptor extends DefaultCollaboration {

	private static class StationsInRegionMap extends ColumnMap {
		StationsInRegionMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("REGIONID", "regionid", true);

			addMap("STATIONSID", "stationsid", false);

			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public StationsInRegionAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlstationsinreg", "STATIONS_IN_REGION", null, new StationsInRegionMap(), sconLOG_DB);
	}
}
