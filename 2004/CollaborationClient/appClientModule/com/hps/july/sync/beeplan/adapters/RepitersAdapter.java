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

public class RepitersAdapter extends DefaultCollaboration {

	public static class RepitersMap extends ColumnMap {
		RepitersMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("equipment", "equipment", true);
			//
			addMap("resource", "resource", false);
			addMap("donor_sect", "donor_sect", false);
			addMap("donor_ant", "donor_ant", false);
			addMap("number", "number", false);
			addMap("donor_type", "donor_type", false);
			addMap("repiter_class", "repiter_class", false);
			addMap("reppower", "reppower", false);
			addMap("bandwidth", "bandwidth", false);
			addMap("numchanals", "numchanals", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	RepitersAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.repiters", "repiters", null, new RepitersMap(), dbSource);
	}

}


