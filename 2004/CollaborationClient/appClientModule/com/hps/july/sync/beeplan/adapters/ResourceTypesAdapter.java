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

public class ResourceTypesAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resourcetype", "resourcetype", true);
			//
			addMap("name", "name", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	ResourceTypesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.resourcetypes", "resourcetypes", null, new ResourceTypesMap(), dbSource);
	}

}

