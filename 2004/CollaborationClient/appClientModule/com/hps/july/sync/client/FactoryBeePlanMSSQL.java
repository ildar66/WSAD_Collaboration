/*
 * Created on 19.10.2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.client;

import java.util.Properties;

import com.hps.july.core.Collaboration;
import com.hps.july.core.Query;
import com.hps.july.core.DB;
import com.hps.july.sync.beeplan.adapters.AdminregionsAdapter;
import com.hps.july.sync.beeplan.adapters.AdminregiontypesAdapter;
import com.hps.july.sync.beeplan.adapters.Anten2SectorsAdapter;
import com.hps.july.sync.beeplan.adapters.AntennaResAdapter;
import com.hps.july.sync.beeplan.adapters.AntennaResBandAdapter;
import com.hps.july.sync.beeplan.adapters.AntennesAdapter;
import com.hps.july.sync.beeplan.adapters.AntennesWorkBandsAdapter;
import com.hps.july.sync.beeplan.adapters.AntennplacesAdapter;
import com.hps.july.sync.beeplan.adapters.BaseStationsAdapter;
import com.hps.july.sync.beeplan.adapters.BsNumbersAdapter;
import com.hps.july.sync.beeplan.adapters.BsStandsAdapter;
import com.hps.july.sync.beeplan.adapters.ControllersAdapter;
import com.hps.july.sync.beeplan.adapters.EquipManufacturersAdapter;
import com.hps.july.sync.beeplan.adapters.EquipmentAdapter;
import com.hps.july.sync.beeplan.adapters.FinancecategoriesAdapter;
import com.hps.july.sync.beeplan.adapters.JoinDbQueryAdapter;
import com.hps.july.sync.beeplan.adapters.NetzonesAdapter;
import com.hps.july.sync.beeplan.adapters.OrganizationsAdapter;
import com.hps.july.sync.beeplan.adapters.PositionsAdapter;
import com.hps.july.sync.beeplan.adapters.RegionsAdapter;
import com.hps.july.sync.beeplan.adapters.RepiterResAdapter;
import com.hps.july.sync.beeplan.adapters.RepitersAdapter;
import com.hps.july.sync.beeplan.adapters.ResourceClassesAdapter;
import com.hps.july.sync.beeplan.adapters.ResourceSubTypesAdapter;
import com.hps.july.sync.beeplan.adapters.ResourceTypesAdapter;
import com.hps.july.sync.beeplan.adapters.ResourcesAdapter;
import com.hps.july.sync.beeplan.adapters.SectorsAdapter;
import com.hps.july.sync.beeplan.adapters.StorageplacesAdapter;
import com.hps.july.sync.beeplan.adapters.SuperregionsAdapter;
import com.hps.july.sync.beeplan.adapters.SwitchesAdapter;
// import com.hps.july.sync.mssql.adapters.*;

/**
 * @author irogachev
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class FactoryBeePlanMSSQL extends AbstractFactory{

	public static final String QRY_EQUIPMANUFACTURERS = "equipmanufacturers"; // "EQUIPMANUFACTURERS";
	public static final String QRY_ORGANIZATIONS = "organizations";
	public static final String QRY_ANTENNPLACES = "antennplaces";
	public static final String QRY_STORAGEPLACES = "storageplaces";
	public static final String QRY_FINANCECATEGORIES = "financecategories";
	public static final String QRY_NETZONES = "netzones";
	public static final String QRY_ADMINREGIONTYPES = "adminregiontypes";
	public static final String QRY_ADMINREGIONS = "adminregions";
	public static final String QRY_SUPERREGIONS = "superregions";
	public static final String QRY_REGIONS = "regions";
	public static final String QRY_RESOURCECLASSES = "resourceclasses";
	public static final String QRY_RESOURCETYPES = "resourcetypes";
	public static final String QRY_RESOURCESUBTYPES = "resourcesubtypes";
	public static final String QRY_RESOURCES = "resources";
	public static final String QRY_REPITERRES = "repiterres";
	public static final String QRY_ANTENNARES = "antennares";
	public static final String QRY_ANTENNARESBAND = "antennaresband";
	public static final String QRY_POSITIONS = "positions";
	public static final String QRY_EQUIPMENT = "equipment";
	public static final String QRY_SWITCHES = "switches";
	public static final String QRY_CONTROLLERS = "controllers";
	public static final String QRY_BASESTATIONS = "basestations";
	public static final String QRY_BSSTANDS = "bsstands";
	public static final String QRY_SECTORS = "sectors";
	public static final String QRY_ANTENNES = "antennes";
	public static final String QRY_ANTENNESWORKBANDS = "antennesworkbands";
	public static final String QRY_ANTEN2SECTORS = "anten2sectors";
	public static final String QRY_REPITERS = "repiters";
	public static final String QRY_BSNUMBERS = "bsnumbers";
	// служебная таблица
	public static final String QRY_JOIN_DB_QUERY = "join_db_query";

	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB sourceDB = new DB(prop, "NRI");
		DB targetDB = new DB(prop, "BEEPLAN");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_EQUIPMANUFACTURERS)) {
			adaptor = new EquipManufacturersAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ORGANIZATIONS)) {
			adaptor = new OrganizationsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTENNPLACES)) {
			adaptor = new AntennplacesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_STORAGEPLACES)) {
			adaptor = new StorageplacesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_FINANCECATEGORIES)) {
			adaptor = new FinancecategoriesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_NETZONES)) {
			adaptor = new NetzonesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ADMINREGIONTYPES)) {
			adaptor = new AdminregiontypesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ADMINREGIONS)) {
			adaptor = new AdminregionsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_SUPERREGIONS)) {
			adaptor = new SuperregionsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_REGIONS)) {
			adaptor = new RegionsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_RESOURCECLASSES)) {
			adaptor = new ResourceClassesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_RESOURCETYPES)) {
			adaptor = new ResourceTypesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_RESOURCESUBTYPES)) {
			adaptor = new ResourceSubTypesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_RESOURCES)) {
			adaptor = new ResourcesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_REPITERRES)) {
			adaptor = new RepiterResAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTENNARES)) {
			adaptor = new AntennaResAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTENNARESBAND)) {
			adaptor = new AntennaResBandAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_POSITIONS)) {
			adaptor = new PositionsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_EQUIPMENT)) {
			adaptor = new EquipmentAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_SWITCHES)) {
			adaptor = new SwitchesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_CONTROLLERS)) {
			adaptor = new ControllersAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_BASESTATIONS)) {
			adaptor = new BaseStationsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_BSSTANDS)) {
			adaptor = new BsStandsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_SECTORS)) {
			adaptor = new SectorsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTENNES)) {
			adaptor = new AntennesAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTENNESWORKBANDS)) {
			adaptor = new AntennesWorkBandsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_ANTEN2SECTORS)) {
			adaptor = new Anten2SectorsAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_REPITERS)) {
			adaptor = new RepitersAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_BSNUMBERS)) {
			adaptor = new BsNumbersAdapter(targetDB, sourceDB);
		} else if (qry.isQueryType(QRY_JOIN_DB_QUERY)) {
			adaptor = new JoinDbQueryAdapter(targetDB, sourceDB);
		}

		return adaptor;
	}
}
