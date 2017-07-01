package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.kladr.KladrFacadeAdapter;
import com.hps.july.sync.kladr.TempKladrFacadeAdapter;
import com.hps.july.sync.kladr.StreetTask;

import java.util.Properties;

/**
 * Date: 19.03.2007
 * Time: 15:39:24
 *
 * @author <a href="mailto: NIzhikov@gmail.com">Izhikov Nikolay</a>
 */
public class FactoryKladr extends AbstractFactory {
    public static final String QRY_KLADR_LOAD = "KLADR_LOAD";
    public static final String QRY_KLADR_PROCESS = "KLADR_PROCESS";
    public static final String QRY_STREET_PROCESS = "STREET_PROCESS";
    public static final String QRY_KLADR_TEMP_PROCESS = "TEMP_KLADR_PROCESS";


    public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
        DB nri = new DB(prop, "NRI");
        DB kladr = new DB(prop, "NFSKLADR");

        if (qry.isQueryType(QRY_KLADR_LOAD) || qry.isQueryType(QRY_KLADR_PROCESS)) {
            return new KladrFacadeAdapter(nri, kladr);
        } else if (qry.isQueryType(QRY_KLADR_TEMP_PROCESS)) {
            return new TempKladrFacadeAdapter(nri, kladr);
        } else if (qry.isQueryType(QRY_STREET_PROCESS)) {
            return new StreetTask(nri);
        }


        return null;
    }
}
