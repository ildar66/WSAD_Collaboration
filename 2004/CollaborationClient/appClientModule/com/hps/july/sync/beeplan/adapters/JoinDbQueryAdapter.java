/*
 * Created on 25.10.2006
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

public class JoinDbQueryAdapter extends DefaultCollaboration {

	public static class JoinDbQueryMap extends ColumnMap {
		JoinDbQueryMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("idQuery", "idQuery", true);
			//
			addMap("idApp", "idApp", false);
			addMap("reqstate", "reqstate", false);
			addMap("posttime", "posttime", false);
			addMap("starttime", "starttime", false);
			addMap("finishtime", "finishtime", false);
			addMap("reqtype", "reqtype", false);
			addMap("selstartdate", "selstartdate", false);
			addMap("selenddate", "selenddate", false);
			addMap("MinInterval", "MinInterval", false);
			addMap("MaxInterval", "MaxInterval", false);
			addMap("reqparams", "reqparams", false);
			//Колонки, предопределенные в таблице NRI
			// addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	JoinDbQueryAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.join_db_query", "join_db_query", null, new JoinDbQueryMap(), dbSource);
	}

	protected String getSqlChangeInSource() {
		StringBuffer result = new StringBuffer(super.getSqlChangeInSource());
		result.append(" WHERE idApp = 18 ");
		return result.toString();
	}

}

