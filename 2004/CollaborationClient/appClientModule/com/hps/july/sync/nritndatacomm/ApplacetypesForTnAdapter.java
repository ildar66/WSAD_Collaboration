/*
 * Created on 31.01.2007
 *
 */
package com.hps.july.sync.nritndatacomm;

import com.hps.july.core.DB;
import com.hps.july.core.ColumnMap;
import com.hps.july.core.DefaultCollaboration;

import java.sql.Timestamp;

/**
 * @author ABaykov
 *
 */
public class ApplacetypesForTnAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
            super();
			//     MSSQL,     NRI,     isKey
			addMap("applacetypeid", "applacetypeid", true);
			addMap("name", "name", false);
			addPredefinedColumnTargetDb("created", new Timestamp(new java.util.Date().getTime()));
			addPredefinedColumnTargetDb("modified", new Timestamp(new java.util.Date().getTime()));
			addPredefinedColumnTargetDb("recordStatus", "A");
		}
	}

	public ApplacetypesForTnAdapter(DB dbTarget, DB dbSource) {
        super(dbTarget, dbSource, "applacetypes", "applacetypes", null, new ResourceTypesMap(), dbSource);
    }

}
