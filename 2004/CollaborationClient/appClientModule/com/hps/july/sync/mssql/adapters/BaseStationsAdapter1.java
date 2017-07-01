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
public class BaseStationsAdapter1 extends BaseStationsAdapter {

	protected static class BaseStationsMap1 extends BaseStationsMap {
		BaseStationsMap1() {
			super();
			addPredefinedKeyColumnTargetDb("g_source", new Integer(1));
		}
	}

	public BaseStationsAdapter1(DB targerDB, DB sourseDB, DB sconLOG_DB) {
		super(targerDB, sourseDB, "glchBasestations", "pmc.bs_general_table_cr_project", new BaseStationsMap1());
	}

	/// —формировать SQL дл€ определени€ изменившихс€ записей в JOIN_DB
	protected String getSqlChangeInSource() {
		StringBuffer result = new StringBuffer(super.getSqlChangeInSource());
		result.append(" WHERE status = 'new' OR status = 'exist' ");
		return result.toString();
	}

}
