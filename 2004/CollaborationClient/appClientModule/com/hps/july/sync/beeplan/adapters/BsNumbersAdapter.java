/*
 * Created on 23.10.2006
 *
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author irogachev
 *
 */

public class BsNumbersAdapter extends DefaultCollaboration {

	public static class BsNumbersMap extends ColumnMap {
		BsNumbersMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("storageplace", "storageplace", true);
			addMap("numtype", "numtype", true);
			//
			addMap("bsnumber", "bsnumber", false);
			addMap("created", "created", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	BsNumbersAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.bsnumbers", "bsnumbers", null, new BsNumbersMap(), dbSource);
	}

}


