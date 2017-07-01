/*
 * Created on 09.11.2006
 *
 */
package com.hps.july.sync.client;


import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.nritndatacomm.CommonDictionaryForTnAdapter;
import com.hps.july.sync.nritndatacomm.ProjectForTnAdapter;

import java.util.Properties;

/**
 * @author ABaykov
 *
 */
public class FactoryNRITNDatacomm extends AbstractFactory {

    public static final String QRY_COMMONDICTIONARY = "TN_COMMONDICTIONARY";
    public static final String QRY_PROJECT_FOR_TN = "TN_BSPROJECT";

	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
        DB nri_db = new DB(prop, "NRI"); //
		DB tn_db = new DB(prop, "NRITNDatacomm");

        if (qry.isQueryType(QRY_COMMONDICTIONARY)) {
            return new CommonDictionaryForTnAdapter(tn_db, nri_db);
        } else if (qry.isQueryType(QRY_PROJECT_FOR_TN)) {
            return new ProjectForTnAdapter(tn_db, nri_db);
        }

		return null;
	}

}
