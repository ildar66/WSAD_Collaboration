package com.hps.july.sync.client;

import com.hps.july.core.Query;
import com.hps.july.sync.nri.adapters.DraftLoaderAdapter;
import com.hps.july.sync.nri.adapters.ImageProcessorAdapter;
import com.hps.july.sync.nri.adapters.InitialDataLoaderAdapter;
import com.hps.july.sync.nri.adapters.LoadPassListsAdapter;
import com.hps.july.sync.nri.adapters.LoadWayMapsAdapter;
import com.hps.july.sync.nri.adapters.SanPassportLoaderAdapter;
import com.hps.july.sync.nri.adapters.RTSTaskAdapter;
import com.hps.july.core.DB;
import com.hps.july.core.Collaboration;

import java.util.Properties;
/**
 * @author Shafigullin Ildar
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class FactoryNRI extends AbstractFactory {
	public static final String QRY_WAYMAPDOC = "WAYMAPDOC";
	public static final String QRY_LISTPASSDOC = "LISTPASSDOC";
	public static final String QRY_LOADPHOTOS = "LOADPHOTOS";
	public static final String QRY_LOADSANPASPORT = "LOADSANPASPORT";
	public static final String QRY_LOADINITDATA = "LOADINITDATA";
	public static final String QRY_DESIGNDOC = "DESIGNDOC";
	public static final String QRY_RTSTASKPROCESSOR = "RTSTASKPROCESSOR";
	public Collaboration getAdapter(Query qry, DB logDB, Properties prop) {
		DB targetDB = new DB(prop, "NRI");
		Collaboration adaptor = null;
		if (qry.isQueryType(QRY_WAYMAPDOC)) {
			adaptor = new LoadWayMapsAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_LISTPASSDOC)) {
			adaptor = new LoadPassListsAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_LOADPHOTOS)) {
			adaptor = new ImageProcessorAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_LOADSANPASPORT)) {
			adaptor = new SanPassportLoaderAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_LOADINITDATA)) {
			adaptor = new InitialDataLoaderAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_DESIGNDOC)) {
			adaptor = new DraftLoaderAdapter(targetDB, logDB, prop);
		} else if (qry.isQueryType(QRY_RTSTASKPROCESSOR)) {
			adaptor = new RTSTaskAdapter(targetDB, logDB, prop);
		}
		return adaptor;
	}
}
