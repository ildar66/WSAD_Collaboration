/*
 * Created on 23.10.2006
 *
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author irogachev
 *
 */

public class BsStandsAdapter extends DefaultCollaboration {

	public static class BsStandsMap extends ColumnMap {
		BsStandsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("standid", "standid", true);
			//
			addMap("equipment", "equipment", false);
			addMap("resource", "resource", false);
			addMap("stnomer", "stnomer", false);
			addMap("planfacttype", "planfacttype", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	BsStandsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.bsstands", "bsstands", null, new BsStandsMap(), dbSource);
	}

}


