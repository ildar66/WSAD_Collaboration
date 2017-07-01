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

public class ResourcesAdapter extends DefaultCollaboration {

	public static class ResourcesMap extends ColumnMap {
		ResourcesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resource", "resource", true);
			//
			addMap("resourcesubtype", "resourcesubtype", false);
			addMap("manufacturer", "manufacturer", false);
			addMap("resourceclass2", "resourceclass2", false);
			addMap("unit", "unit", false);
			addMap("name", "name", false);
			addMap("model", "model", false);
			addMap("manucode", "manucode", false);
			addMap("resourceclass", "resourceclass", false);
			addMap("manufid", "manufid", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	ResourcesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.resources", "resources", null, new ResourcesMap(), dbSource);
	}

}

