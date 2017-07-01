/*
 * Created on 20.10.2006
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

public class SuperregionsAdapter extends DefaultCollaboration {

	public static class SuperregionsMap extends ColumnMap {
		SuperregionsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("supregid", "supregid", true);
			//
			addMap("supregname", "supregname", false);
			addMap("supregcode", "supregcode", false);
			addMap("modified", "modified", false);
			addMap("created", "created", false);
			// addMap("shortname", "shortname", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public SuperregionsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.superregions", "superregions", null, new SuperregionsMap(), dbSource);
	}

}

