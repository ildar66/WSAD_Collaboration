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

public class AntennesAdapter extends DefaultCollaboration {

	public static class AntennesMap extends ColumnMap {
		AntennesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("id_anten", "id_anten", true);
			//
			addMap("resource", "resource", false);
			addMap("equipment", "equipment", false);
			addMap("num_ant", "num_ant", false);
			addMap("kind_ant", "kind_ant", false);
			addMap("nakl", "nakl", false);
			addMap("az_ant", "az_ant", false);
			addMap("h_set1", "h_set1", false);
			addMap("len_cable", "len_cable", false);
			addMap("cableres", "cableres", false);
			addMap("locationtype", "locationtype", false);
			addMap("electricaltilt", "electricaltilt", false);
			addMap("planfacttype", "planfacttype", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AntennesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.antennes", "antennes", null, new AntennesMap(), dbSource);
	}

}


