/*
 * Created on 23.10.2006
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

public class AntennaResBandAdapter extends DefaultCollaboration {

	public static class	AntennaResBandMap extends ColumnMap {
		AntennaResBandMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resource", "resource", true);
			addMap("band", "band", true);
			//
			addMap("horzwidth", "horzwidth", false);
			addMap("vertwidth", "vertwidth", false);
			addMap("amplification", "amplification", false);
			addMap("electricangle", "electricangle", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AntennaResBandAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.antennaresband", "antennaresband", null, new AntennaResBandMap(), dbSource);
	}

}

