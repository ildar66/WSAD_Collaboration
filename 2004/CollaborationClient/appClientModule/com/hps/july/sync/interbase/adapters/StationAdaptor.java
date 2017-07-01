package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class StationAdaptor extends DefaultCollaboration {

	private static class StationMap extends ColumnMap {
		StationMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("STATIONSID", "STATIONSID", true);

			addMap("CONTROLLERID", "CONTROLLERID", false);
			addMap("STATIONSCI", "STATIONSCI", false);
			addMap("STATIONSNUMBER", "STATIONSNUMBER", false);
			addMap("STATIONSNAME", "STATIONSNAME", false);
			addMap("STATIONSSNAME", "STATIONSSNAME", false);
			addMap("STATIONSLONGITUDE", "STATIONSLONGITUDE", false);
			addMap("STATIONSLATITUDE", "STATIONSLATITUDE", false);
			addMap("STATIONSTYPE", "STATIONSTYPE", false);
			addMap("STATIONSADDRESS", "STATIONSADDRESS", false);
			addMap("STATIONSTIME", "STATIONSTIME", false);
			addMap("STATIONSCOMMENT", "STATIONSCOMMENT", false);
			addMap("STATIONSEQUIPMENT", "STATIONSEQUIPMENT", false);
			addMap("STATIONSVENDOR", "STATIONSVENDOR", false);
			addMap("STATIONSCASES", "STATIONSCASES", false);
			addMap("STATIONSSTATUS", "STATIONSSTATUS", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public StationAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "KZLSTATIONS", "STATIONS", null, new StationMap(), sconLOG_DB);
	}
}
