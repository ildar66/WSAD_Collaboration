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
 *
 */
public class ApparatplacesForTnAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("applaceid", "applaceid", true);
			addMap("name", "name", false);

            addPredefinedColumnTargetDb("created", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("modified", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("recordStatus", "A");
        }
	}

	public ApparatplacesForTnAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "apparatplaces", "apparatplaces", null, new ResourceTypesMap(), dbSource);
	}

}
