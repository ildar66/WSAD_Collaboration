/*
 * Created on 31.01.2007
 *
 */
package com.hps.july.sync.nritndatacomm;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

import java.sql.Timestamp;

/**
 * @author ABaykov
 * @deprecated
 */
public class ResourcesForTnAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("resource", "resource", true);
			addMap("name", "name", false);
            addPredefinedColumnTargetDb("created", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("modified", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("recordStatus", "A");
		}
	}

	public ResourcesForTnAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "resources", "resources", null, new ResourceTypesMap(), dbSource);
	}

}

