package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.equipment.adapters.ListBsForSORMUnloaderAdapter;

import java.util.Properties;

/**
 * @author Vadim Glushkov
 *
 */
public class FactoryEquipment extends AbstractFactory{
    public static final String QRY_LISTOFPROBLEM_UNLOADING = "UNLOADINGLISTOFPROBLEM";

    public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
        DB targetDB = new DB(prop, "NRI");
        Collaboration adaptor = null;
        if(qry.isQueryType(QRY_LISTOFPROBLEM_UNLOADING)) {
            adaptor = new ListBsForSORMUnloaderAdapter(targetDB, logDB, prop);
        }
        return adaptor;
    }
}
