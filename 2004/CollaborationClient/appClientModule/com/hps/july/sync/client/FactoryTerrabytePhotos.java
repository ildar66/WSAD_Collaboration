package com.hps.july.sync.client;

import com.hps.july.core.Collaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.sync.nri.adapters.BSRequestAndPermitLoaderAdapter;
import com.hps.july.sync.nri.adapters.RRLRequestAndPermitLoaderAdapter;
import com.hps.july.sync.nri.adapters.TerrabyteDrawingLoaderAdapter;
import com.hps.july.sync.nri.adapters.TerrabyteImageLoaderProcessorAdapter;
import com.hps.july.sync.nri.adapters.TerrabyteInitialDataLoaderAdapter;
import com.hps.july.sync.nri.adapters.TerrabyteMapWaysLoaderAdapter;
import com.hps.july.sync.nri.adapters.TerrabyteSanPassportLoaderAdapter;

import java.util.Properties;

/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryTerrabytePhotos extends AbstractFactory {
    public static final String QRY_LOADPHOTOS2TERRABYTE = "LOADPHOTOS2TERRABYTE";
    public static final String QRY_LOADDRAWING2TERRABYTE = "LOADDRAWING2TERRABYTE";
    public static final String QRY_LOADINITIALDATA2TERRABYTE = "LOADINITIALDATA2TERRABYTE";
    public static final String QRY_LOADSANPASSPORT2TERRABYTE = "LOADSANPASSPORT2TERRABYTE";
    public static final String QRY_LOADMAPWAY2TERRABYTE = "LOADMAPWAY2TERRABYTE";
    public static final String QRY_LOADRRLREQUESTANDPERMIT = "LOADRRLREQUESTANDPERMIT";
    public static final String QRY_LOADBSREQUESTANDPERMIT = "LOADBSREQUESTANDPERMIT";

    public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
        DB targetDB = new DB(prop, "NRI");
        Collaboration adaptor = null;
        if(qry.isQueryType(QRY_LOADPHOTOS2TERRABYTE)) {
            adaptor = new TerrabyteImageLoaderProcessorAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADDRAWING2TERRABYTE)) {
            adaptor = new TerrabyteDrawingLoaderAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADINITIALDATA2TERRABYTE)) {
            adaptor = new TerrabyteInitialDataLoaderAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADSANPASSPORT2TERRABYTE)) {
            adaptor = new TerrabyteSanPassportLoaderAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADMAPWAY2TERRABYTE)) {
            adaptor = new TerrabyteMapWaysLoaderAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADRRLREQUESTANDPERMIT)) {
            adaptor = new RRLRequestAndPermitLoaderAdapter(targetDB, logDB, prop);
        } else if(qry.isQueryType(QRY_LOADBSREQUESTANDPERMIT)) {
            adaptor = new BSRequestAndPermitLoaderAdapter(targetDB, logDB, prop);
        }
        return adaptor;
    }
}
