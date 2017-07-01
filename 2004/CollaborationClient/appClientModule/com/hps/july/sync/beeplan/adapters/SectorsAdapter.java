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

public class SectorsAdapter extends DefaultCollaboration {

	public static class SectorsMap extends ColumnMap {
		SectorsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("id_sect", "id_sect", true);
			//
			addMap("standid", "standid", false);
			addMap("equipment", "equipment", false);
			addMap("name_sect", "name_sect", false);
			addMap("band", "band", false);
			addMap("num_sect", "num_sect", false);
			addMap("azimut", "azimut", false);
			addMap("power", "power", false);
			addMap("bts_number", "bts_number", false);
			addMap("trx_num", "trx_num", false);
			addMap("lac", "lac", false);
			addMap("rac", "rac", false);
			addMap("cellname", "cellname", false);
			addMap("ncc", "ncc", false);
			addMap("bcc", "bcc", false);
			addMap("modified", "modified", false);
			addMap("created", "created", false);
			addMap("cellinfo", "cellinfo", false);
			addMap("planfacttype", "planfacttype", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	SectorsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.sectors", "sectors", null, new SectorsMap(), dbSource);
	}

}


