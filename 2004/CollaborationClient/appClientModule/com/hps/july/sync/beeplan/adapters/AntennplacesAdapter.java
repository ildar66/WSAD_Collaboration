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

public class AntennplacesAdapter extends DefaultCollaboration {

	public static class AntennplacesMap extends ColumnMap {
		AntennplacesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("antplaceid", "antplaceid", true);
			//
			addMap("name", "name", false);
			addMap("isvc", "isvc", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AntennplacesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.antennplaces", "antennplaces", null, new AntennplacesMap(), dbSource);
	}

}

