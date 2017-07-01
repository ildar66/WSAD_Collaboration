package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class SectorsAntennsAdaptor extends DefaultCollaboration {

	private static class SectorsAntennsMap extends ColumnMap {
		SectorsAntennsMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("ANTENNNO", "ANTENNNO", true);

			addMap("ANTENNID", "ANTENNID", false);
			addMap("SECTORID", "SECTORID", false);
			addMap("SAHEIGHT", "SAHEIGHT", false);
			addMap("SAAZIMUTH", "SAAZIMUTH", false);
			addMap("SATILT", "SATILT", false);
			addMap("SAPOWER", "SAPOWER", false);
			addMap("SATRX", "SATRX", false);
			addMap("SATIME", "SATIME", false);
			addMap("SACOMMENT", "SACOMMENT", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public SectorsAntennsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "KZLSECTORSANTENNS", "SECTORSANTENNS", null, new SectorsAntennsMap(), sconLOG_DB);
	}
}
