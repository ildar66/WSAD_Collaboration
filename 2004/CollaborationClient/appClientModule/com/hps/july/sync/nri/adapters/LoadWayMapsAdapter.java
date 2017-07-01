/*
 * Created on 22.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import java.util.Properties;

import com.hps.beeline.LoaderException;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadWayMapsAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public LoadWayMapsAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_WAYMAPDOC", null, null, null, logDB);
		this.prop = prop;
	}

	/**
		 * Загрузка маршрут карт
		 * @param idQuery -  номер запроса
		 * @param connection - соединение с базой данных
		 * @param logConnection - соединение с базой данных,
		 * из cfg loadWayMapsDirPath  - путь к директории с марш картами
		 * @throws com.hps.beeline.LoaderException
		 */

	public boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String loadWayMapsDirPath = prop.getProperty("loadWayMapsDirPath");
/**
		long time;
		if (getLastUpdateDate() != null) {
			time = getLastUpdateDate().getTime();
		} else {
			System.out.println("getLastUpdateDate() == null");
			time = System.currentTimeMillis() - 2 * (24 * 60 * 60 * 1000); //минус два дня;
		}

		java.sql.Date lastUpdateDate = new java.sql.Date(time);
		System.out.println("init lastUpdateDate="+lastUpdateDate);
*/
		try {
			System.out.println("Вызов сторонней процедуры MayMapLoader.loadWayMapData():");
			//com.hps.beeline.loader.MainProcessor.loadWayMaps(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), lastUpdateDate, blobDirPath);
			com.hps.beeline.initialDataLoader.MayMapLoader.loadWayMapData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), loadWayMapsDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("LoadWayMapsAdapter.doBeforeTask  :Вызов сторонней процедуры LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("LoadWayMapsAdapter.doBeforeTask  :Вызов сторонней процедуры ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
