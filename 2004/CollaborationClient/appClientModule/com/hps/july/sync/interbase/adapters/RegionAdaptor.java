package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class RegionAdaptor extends DefaultCollaboration {

	private static class RegionMap extends ColumnMap {
		RegionMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("REGIONID", "regionid", true);

			addMap("REGIONNAME", "regionname", false);
			addMap("REGIONCOMMENT", "regioncomment", false);
			addMap("REGIONTYPE", "regiontype", false);
			addMap("REGIONPOPULATION", "regionpopulation", false);
			addMap("RURALPOPULATION", "ruralpopulation", false);
			addMap("REGIONAREA", "regionarea", false);
			addMap("REGIONLON", "regionlon", false);
			addMap("REGIONLAT", "regionlat", false);
			addMap("REGIONINMAP", "regioninmap", false);
			addMap("REGIONKIND", "regionkind", false);
			addMap("REGIONCODE", "regioncode", false);
			addMap("REGIONMNC", "regionmnc", false);
			addMap("REGIONMCC", "regionmcc", false);

			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public RegionAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlregion", "REGION", null, new RegionMap(), sconLOG_DB);
	}
}
