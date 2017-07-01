/*
 * Created on 20.10.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.beeplan.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.ColumnMap;

/**
 * @author irogachev
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class OrganizationsAdapter extends DefaultCollaboration {

	public static class OrganizationsMap extends ColumnMap {
		OrganizationsMap() {
			super();
			//     MSSQL,     NRI,     isKey
			addMap("organization", "organization", true);
			//
			addMap("name", "name", false);
			addMap("issupplier", "issupplier", false);
			addMap("isprovider", "isprovider", false);
			addMap("isrenter", "isrenter", false);
			addMap("legaladdress", "legaladdress", false);
			addMap("organizationtype", "organizationtype", false);
			addMap("created", "created", false);
			addMap("ischannalrenter", "ischannalrenter", false);
			addMap("typeOrg", "typeOrg", false);
			//Колонки, предопределенные в таблице NRI
			addPredefinedColumnTargetDb("recordstatus", "A");
		}
	}

	public 	OrganizationsAdapter(DB dbTarget, DB dbSource) {
		super(dbTarget, dbSource, "smedvetskiy.organizations", "organizations", null, new OrganizationsMap(), dbSource);
	}

}
