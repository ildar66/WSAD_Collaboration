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

public class Anten2SectorsAdapter extends DefaultCollaboration {

	public static class Anten2SectorsMap extends ColumnMap {
		Anten2SectorsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("id_sect", "id_sect", true);
			//
			addMap("id_anten", "id_anten", true);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	Anten2SectorsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.anten2sectors", "anten2sectors", null, new Anten2SectorsMap(), dbSource);
	}

}

