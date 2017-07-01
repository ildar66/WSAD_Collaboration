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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ResourceSubTypesAdapter extends DefaultCollaboration {

	public static class	ResourceSubTypesMap extends ColumnMap {
		ResourceSubTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resourcesubtype", "resourcesubtype", true);
			//
			addMap("resourcetype", "resourcetype", false);
			addMap("name", "name", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	ResourceSubTypesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.resourcesubtypes", "resourcesubtypes", null, new ResourceSubTypesMap(), dbSource);
	}

}

