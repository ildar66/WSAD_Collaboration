package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class SectorsAdaptor extends DefaultCollaboration {

	private static class SectorsMap extends ColumnMap {
		SectorsMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("SECTORID", "SECTORID", true);

			addMap("STATIONSID", "STATIONSID", false);
			addMap("MNC", "MNC", false);
			addMap("LAC", "LAC", false);
			addMap("SECTORCI", "SECTORCI", false);
			addMap("SECTORNUMBER", "SECTORNUMBER", false);
			addMap("SECTORSUFFIX", "SECTORSUFFIX", false);
			addMap("SECTORNAME", "SECTORNAME", false);
			addMap("SECTORBAND", "SECTORBAND", false);
			addMap("SECTORSTATUS", "SECTORSTATUS", false);
			addMap("SECTORRADIUS", "SECTORRADIUS", false);
			addMap("SECTORCHANNELS", "SECTORCHANNELS", false);
			addMap("RAC", "RAC", false);
			addMap("SECTOREQUIPMENT", "SECTOREQUIPMENT", false);
			addMap("SECTORNUMCASE", "SECTORNUMCASE", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public SectorsAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "KZLSECTORS", "SECTORS", null, new SectorsMap(), sconLOG_DB);
	}
}
