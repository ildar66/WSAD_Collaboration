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

public class ResourceClassesAdapter extends DefaultCollaboration {

	public static class	ResourceClassesMap extends ColumnMap {
		ResourceClassesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("classid", "classid", true);
			//
			addMap("classname", "classname", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	ResourceClassesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.resourceclasses", "resourceclasses", null, new ResourceClassesMap(), dbSource);
	}

}

