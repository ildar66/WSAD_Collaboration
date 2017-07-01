package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class Dct_StatusAdaptor extends DefaultCollaboration {

	private static class Dct_StatusMap extends ColumnMap {
		Dct_StatusMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("statusid", "statusid", true);

			addMap("statusname", "statusname", false);
			addMap("statuscomment", "statuscomment", false);
			addMap("statusunique", "statusunique", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public Dct_StatusAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlDCTStatus", "DCT_STATUS", null, new Dct_StatusMap(), sconLOG_DB);
	}
}
