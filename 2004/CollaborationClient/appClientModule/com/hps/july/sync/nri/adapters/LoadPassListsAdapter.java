/*
 * Created on 22.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import java.util.Properties;

import com.hps.beeline.initialDataLoader.PassListLoader;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class LoadPassListsAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public LoadPassListsAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LISTPASSDOC", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	public boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String blobDirPath = prop.getProperty("loadPassListsDirPath");
		long time;
		if (getLastUpdateDate() != null) {
			time = getLastUpdateDate().getTime();
		} else {
			System.out.println("getLastUpdateDate() == null");
			time = System.currentTimeMillis() - 2 * (24 * 60 * 60 * 1000); //минус два дня;
		}

		java.sql.Date lastUpdateDate = new java.sql.Date(time);
		System.out.println("init lastUpdateDate="+lastUpdateDate);
		try {
			System.out.println("Вызов сторонней процедуры loadPassLists:");
			//com.hps.beeline.loader.MainProcessor.loadPassLists(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), lastUpdateDate, blobDirPath);
			PassListLoader.loadPassListData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), blobDirPath);
			return true;
		} catch (Exception e) {
			System.out.println("LoadPassListsAdapter.doBeforeTask  :Вызов сторонней процедуры ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
