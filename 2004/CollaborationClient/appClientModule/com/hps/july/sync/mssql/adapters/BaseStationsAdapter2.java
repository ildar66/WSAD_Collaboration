/*
 * Created on 05.05.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.mssql.adapters;

import com.hps.july.core.DB;

/**
 * @author dima
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BaseStationsAdapter2 extends BaseStationsAdapter {

	protected static class BaseStationsMap2 extends BaseStationsMap {
		BaseStationsMap2() {
			super();
			addPredefinedKeyColumnTargetDb("g_source", new Integer(2));
		}
	}

	public BaseStationsAdapter2(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "glchBasestations", "pmc.bs_general_table_cr", new BaseStationsMap2());
	}

}
