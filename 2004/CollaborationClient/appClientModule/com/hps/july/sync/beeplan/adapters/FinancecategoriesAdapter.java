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

public class FinancecategoriesAdapter extends DefaultCollaboration {

	public static class FinancecategoriesMap extends ColumnMap {
		FinancecategoriesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("fincategid", "fincategid", true);
			//
			addMap("fincategname", "fincategname", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	FinancecategoriesAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.financecategories", "financecategories", null, new FinancecategoriesMap(), dbSource);
	}

}

