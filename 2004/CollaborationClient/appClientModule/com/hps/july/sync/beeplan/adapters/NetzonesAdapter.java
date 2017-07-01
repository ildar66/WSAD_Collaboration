/*
 * Created on 20.10.2006
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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class NetzonesAdapter extends DefaultCollaboration {

	public static class NetzonesMap extends ColumnMap {
		NetzonesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("netzone", "netzone", true);
			//
			addMap("regionid", "regionid", false);
			addMap("name", "name", false);
			addMap("zonecode", "zonecode", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	NetzonesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.netzones", "netzones", null, new NetzonesMap(), dbSource);
	}

}

