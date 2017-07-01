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

public class SwitchesAdapter extends DefaultCollaboration {

	public static class	SwitchesMap extends ColumnMap {
		SwitchesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("equipment", "equipment", true);
			//
			addMap("resource", "resource", false);
			addMap("type", "type", false);
			addMap("number", "number", false);
			addMap("qty_conhlr", "qty_conhlr", false);
			addMap("qty_shell", "qty_shell", false);
			addMap("max_vlr", "max_vlr", false);
			addMap("comment", "comment", false);
			addMap("qty_tkgroute_nss", "qty_tkgroute_nss", false);
			addMap("e1_qty_total", "e1_qty_total", false);
			addMap("e1_use_percent", "e1_use_percent", false);
			addMap("e1_bss", "e1_bss", false);
			addMap("e1_nss", "e1_nss", false);
			addMap("e1_hlr", "e1_hlr", false);
			addMap("ss7_bss", "ss7_bss", false);
			addMap("ss7_nss", "ss7_nss", false);
			addMap("ss7_hlrin", "ss7_hlrin", false);
			addMap("mscid", "mscid", false);
			addMap("capacityused", "capacityused", false);
			addMap("capacityplan", "capacityplan", false);
			addMap("expertisedate", "expertisedate", false);
			addMap("haveproject", "haveproject", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	SwitchesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.switches", "switches", null, new SwitchesMap(), dbSource);
	}

}

