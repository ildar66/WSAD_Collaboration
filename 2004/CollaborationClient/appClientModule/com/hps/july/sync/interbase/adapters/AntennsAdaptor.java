package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class AntennsAdaptor extends DefaultCollaboration {

	private static class AntennsMap extends ColumnMap {
		AntennsMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("antennid", "antennid", true);

			addMap("antennname", "antennname", false);
			addMap("antennvendor", "antennvendor", false);
			addMap("antennfromfile", "antennfromfile", false);
			addMap("antennband", "antennband", false);
			addMap("antenntype", "antenntype", false);
			addMap("antennpolarization", "antennpolarization", false);
			addMap("antenngain", "antenngain", false);
			addMap("antennhplan", "antennhplan", false);
			addMap("antennvplan", "antennvplan", false);
			addMap("antennetilt", "antennetilt", false);
			addMap("antennhdiagram", "antennhdiagram", false);
			addMap("antennvdiagram", "antennvdiagram", false);
			addMap("antenndate", "antenndate", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public AntennsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlantenns", "ANTENNS", null, new AntennsMap(), sconLOG_DB);
	}
}
