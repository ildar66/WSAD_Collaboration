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

public class AntennesWorkBandsAdapter extends DefaultCollaboration {

	public static class AntennesWorkBandsMap extends ColumnMap {
		AntennesWorkBandsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("id_anten", "id_anten", true);
			addMap("band", "band", true);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AntennesWorkBandsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.antennesworkbands", "antennesworkbands", null, new AntennesWorkBandsMap(), dbSource);
	}

}


