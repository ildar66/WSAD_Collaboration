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

public class StorageplacesAdapter extends DefaultCollaboration {

	public static class StorageplacesMap extends ColumnMap {
		StorageplacesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("storageplace", "storageplace", true);
			//
			addMap("name", "name", false);
			addMap("address", "address", false);
			addMap("type", "type", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	StorageplacesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.storageplaces", "storageplaces", null, new StorageplacesMap(), dbSource);
	}

}

