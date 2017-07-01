/*
 * Created on 19.10.2006
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

public class EquipManufacturersAdapter extends DefaultCollaboration {

	public static class EquipManufacturersMap extends ColumnMap {
		EquipManufacturersMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("manufid", "manufid", true);
			//
			addMap("name", "name", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	EquipManufacturersAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.equipmanufacturers", "equipmanufacturers", null, new EquipManufacturersMap(), dbSource);
	}

}
