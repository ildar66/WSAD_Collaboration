/*
 * Created on 23.10.2006
 *
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author irogachev
 *
 */

public class BaseStationsAdapter extends DefaultCollaboration {

	public static class BaseStationsMap extends ColumnMap {
		BaseStationsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("equipment", "equipment", true);
			//
			addMap("antplaceid", "antplaceid", false);
			addMap("controller", "controller", false);
			addMap("switch", "switch", false);
			addMap("type", "type", false);
			addMap("repeater", "repeater", false);
			addMap("number", "number", false);
			addMap("name", "name", false);
			addMap("n_stoek", "n_stoek", false);
			addMap("prefixcellid", "prefixcellid", false);
			addMap("state900", "state900", false);
			addMap("state1800", "state1800", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	BaseStationsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.basestations", "basestations", null, new BaseStationsMap(), dbSource);
	}

}

