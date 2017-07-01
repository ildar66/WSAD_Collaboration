/*
 * Created on 20.10.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author irogachev
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class AdminregionsAdapter extends DefaultCollaboration {

	public static class AdminregionsMap extends ColumnMap {
		AdminregionsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("regionid", "regionid", true);
			//
			addMap("regiontype", "regiontype", false);
			addMap("parent_regionid", "parent_regionid", false);
			addMap("regionnri", "regionnri", false);
			addMap("filialnri", "filialnri", false);
			addMap("kzlregionid", "kzlregionid", false);
			addMap("regionname", "regionname", false);
			addMap("REGIONCOMMENT", "REGIONCOMMENT", false);
			addMap("REGIONPOPULATION", "REGIONPOPULATION", false);
			addMap("RURALPOPULATION", "RURALPOPULATION", false);
			addMap("REGIONAREA", "REGIONAREA", false);
			addMap("regionlon", "regionlon", false);
			addMap("regionlat", "regionlat", false);
			addMap("regionkind", "regionkind", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	AdminregionsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.adminregions", "adminregions", null, new AdminregionsMap(), dbSource);
	}

	/*
	protected String getSqlChangeInSource() {
		StringBuffer result = new StringBuffer(super.getSqlChangeInSource());
		// result.append(" ORDER BY regionid ");
		result.append(" ORDER BY parent_regionid, regionid ");
		return result.toString();
	}
	*/
}

