package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.interbase.adapters.*;

/**
 * @author Shafigullin Ildar
 *
 */
public class FactoryKozlovzevInterbase extends AbstractFactory{
	public static final String QRY_ANTENNS = "KZLANTENNS";
	public static final String QRY_DCT_STATUS = "KZLDCT_STATUS";
	public static final String QRY_GATESTATIONS = "KZLGATESTATIONS";
	public static final String QRY_DCT_REGION_TYPE = "KZLDCT_REGION_TYPE";
	public static final String QRY_REGIONS_HIERARHY = "KZLREGIONS_HIERARHY";
	public static final String QRY_REGION = "KZLREGION";
	public static final String QRY_REGIONS_PREFIX = "KZLREGIONS_PREFIX";
	public static final String QRY_STATIONS_IN_REGION = "KZLSTATIONS_IN_REGION";
	public static final String QRY_PROC = "KZLPROC";
	public static final String QRY_ADMINREGIONS = "KZLADMREG";
	public static final String QRY_STATION = "KZLSTATION";
	public static final String QRY_SECTORS= "KZLSECTORS";
	public static final String QRY_SECTORSANTENNS= "KZLSECTORSANTENNS";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourceDB = new DB(prop, "Interbase");
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_ANTENNS)) {
			adaptor = new AntennsAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_DCT_STATUS)) {
			adaptor = new Dct_StatusAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_GATESTATIONS)) {
			adaptor = new GateStationsAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_DCT_REGION_TYPE)) {
			adaptor = new DctRegionTypeAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_REGIONS_HIERARHY)) {
			adaptor = new RegionsHierarhyAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_REGIONS_PREFIX)) {
			adaptor = new RegionsPrefixAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_STATIONS_IN_REGION)) {
			adaptor = new StationsInRegionAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_REGION)) {
			adaptor = new RegionAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_PROC)) {
			adaptor = new ProcAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_ADMINREGIONS)) {
			adaptor = new AdminRegionsAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_STATION)) {
			adaptor = new StationAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_SECTORS)) {
			adaptor = new SectorsAdaptor(targetDB, sourceDB, logDB);
		}
		else if (qry.isQueryType(QRY_SECTORSANTENNS)) {
			adaptor = new SectorsAntennsAdaptor(targetDB, sourceDB, logDB);
		}
		return adaptor;
	}
}
