package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class RegionsHierarhyAdaptor extends DefaultCollaboration {

	private static class RegionsHierarhyMap extends ColumnMap {
		RegionsHierarhyMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("REGIONID", "regionid", true);

			addMap("PARENT_REGIONID", "parent_regionid", true);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public RegionsHierarhyAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlregionshierarhy", "REGIONS_HIERARHY", null, new RegionsHierarhyMap(), sconLOG_DB);
	}
}
