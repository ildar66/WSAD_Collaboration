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

public class AntennaResAdapter extends DefaultCollaboration {

	public static class AntennaResMap extends ColumnMap {
		AntennaResMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resource", "resource", true);
			//
			addMap("anttype", "anttype", false);
			addMap("isomni", "isomni", false);
			addMap("freqrange", "freqrange", false);
			addMap("polarization", "polarization", false);
			addMap("electricangle", "electricangle", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AntennaResAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.antennares", "antennares", null, new AntennaResMap(), dbSource);
	}

}

