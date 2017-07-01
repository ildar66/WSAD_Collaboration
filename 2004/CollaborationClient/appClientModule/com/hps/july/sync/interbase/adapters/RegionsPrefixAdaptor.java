package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class RegionsPrefixAdaptor extends DefaultCollaboration {

	private static class RegionsPrefixMap extends ColumnMap {
		RegionsPrefixMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("REGIONID", "regionid", true);

			addMap("CELLID_PREFIX", "cellid_prefix", true);
			addMap("CELLNAME_PREFIX", "cellname_prefix", false);
			addMap("GROUP_PREFIX", "group_prefix", false);
			addMap("SUPER_PREFIX", "super_prefix", false);

			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public RegionsPrefixAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlregionsprefix", "REGIONS_PREFIX", null, new RegionsPrefixMap(), sconLOG_DB);
	}
}
