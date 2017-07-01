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
 */

public class EquipmentAdapter extends DefaultCollaboration {

	public static class EquipmentMap extends ColumnMap {
		EquipmentMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("equipment", "equipment", true);
			//
			addMap("position", "position", false);
			addMap("manufid", "manufid", false);
			addMap("state", "state", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	EquipmentAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.equipment", "equipment", null, new EquipmentMap(), dbSource);
	}

}

