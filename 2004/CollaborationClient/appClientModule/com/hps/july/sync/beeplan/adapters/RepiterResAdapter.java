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

public class RepiterResAdapter extends DefaultCollaboration {

	public static class RepiterResMap extends ColumnMap {
		RepiterResMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resource", "resource", true);
			//
			addMap("reppower", "reppower", false);
			addMap("islinear", "islinear", false);
			addMap("bandwidth", "bandwidth", false);
			addMap("numchanals", "numchanals", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public RepiterResAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.repiterres", "repiterres", null, new RepiterResMap(), dbSource);
	}

}

