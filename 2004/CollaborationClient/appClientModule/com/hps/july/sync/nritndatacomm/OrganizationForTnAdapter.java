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
 */
public class OrganizationForTnAdapter extends DefaultCollaboration {

	public static class ResourceTypesMap extends ColumnMap {
		ResourceTypesMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("organization", "organization", true);
			addMap("shortname", "shortname", false);
            addPredefinedColumnTargetDb("created", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("modified", new Timestamp(new java.util.Date().getTime()));
            addPredefinedColumnTargetDb("recordStatus", "A");
		}
	}

	public OrganizationForTnAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "organizations", "organizations", null, new ResourceTypesMap(), dbSource);
	}


    protected String getSqlChangeInSource() {
        return "SELECT DISTINCT org.organization, nvl(org.shortname, org.name) " +
               "FROM resources rs, basestationres bsr, organizations org " +
               "WHERE rs.resource=bsr.resource AND rs.resourceclass='B' " +
               "AND org.organization=rs.manufacturer";
    }
}

