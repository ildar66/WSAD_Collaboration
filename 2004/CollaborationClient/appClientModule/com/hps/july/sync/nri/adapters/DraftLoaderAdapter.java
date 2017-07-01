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
public class DraftLoaderAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public DraftLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
		super(targerDB, null, "_DESIGNDOC", null, null, null, logDB);
		this.prop = prop;
	}

	/**
		 * Загрузка чертежей
		 * @param idQuery -  номер запроса
		 * @param connection - соединение с базой данных
		 * @param logConnection - соединение с базой данных
		 * @param loadDraftsDirPath - путь к директории с чертежами
		 * @throws com.hps.beeline.LoaderException
		 */
	public boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String loadDraftsDirPath = prop.getProperty("loadDraftsDirPath");
		try {
			System.out.println("Вызов сторонней процедуры DraftLoader.loadDrafts():");
			com.hps.beeline.initialDataLoader.DraftLoader.loadDrafts(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), loadDraftsDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("DraftLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("DraftLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
