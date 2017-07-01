package com.hps.july.sync.interbase.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author Shafigullin Ildar
 * Адаптер для переноса контрактов.
 */
public class DctRegionTypeAdaptor extends DefaultCollaboration {

	private static class DctRegionTypeMap extends ColumnMap {
		DctRegionTypeMap() {
			super();
			//     Interbase,     NRI,     isKey
			addMap("RGNTYPEID", "rgntypeid", true);

			addMap("RGNTYPEIDENTITY", "rgntypeidentity", false);
			//addMap("ADD_NUMBER", "add_number", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public DctRegionTypeAdaptor(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "kzlDCTRegionType", "DCT_REGION_TYPE", null, new DctRegionTypeMap(), sconLOG_DB);
	}
}
