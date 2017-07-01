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

public class AdminregiontypesAdapter extends DefaultCollaboration {

	public static class AdminregiontypesMap extends ColumnMap {
		AdminregiontypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("regiontype", "regiontype", true);
			//
			addMap("nametype", "nametype", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AdminregiontypesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.adminregiontypes", "adminregiontypes", null, new AdminregiontypesMap(), dbSource);
	}

}

