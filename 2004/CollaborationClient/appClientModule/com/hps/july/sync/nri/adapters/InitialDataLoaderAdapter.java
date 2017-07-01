/*
 * Created on 22.11.2004
 *
 * Загрузка исходных данных
 */
package com.hps.july.sync.nri.adapters;

import java.util.Properties;

import com.hps.beeline.LoaderException;
import com.hps.beeline.initialDataLoader.InitialDataLoader;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

/**
 * @author ildar
 */
public class InitialDataLoaderAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public InitialDataLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LOADINITDATA", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * Загрузка исходных данных
	 * @param idQuery -  номер запроса
	 * @param connection - соединение с базой данных
	 * @param logConnection - соединение с базой данных
	 * @param initialDataDirPath - путь к директории с папками исходных данных
	 * @throws com.hps.beeline.LoaderException
	 */
	public boolean doBeforeTask(Query qry) {
		//путь к директории с папками исходных данных
		String initialDataDirPath = prop.getProperty("initialDataDirPath");
		try {
			System.out.println("Вызов сторонней процедуры InitialDataLoader.loadInitialData :");
			InitialDataLoader.loadInitialData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), initialDataDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("InitialDataLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры InitialDataLoader.loadInitialData: LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("InitialDataLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры InitialDataLoader.loadInitialData ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
