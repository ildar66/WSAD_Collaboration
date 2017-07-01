/*
 * Created on 22.11.2004
 *
 * Загрузка Сан паспортов
 */
package com.hps.july.sync.nri.adapters;

import com.hps.beeline.LoaderException;
import com.hps.beeline.initialDataLoader.SanPassportLoader;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.DefaultCollaboration;

import java.util.Properties;
/**
 * @author ildar
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SanPassportLoaderAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public SanPassportLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LOADSANPASPORT", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
     * Загрузка Сан паспортов
     * com.hps.beeline.initialDataLoader.SanPassportLoader.loadSanPassportData(Integer idQuery, Connection connection, Connection logConnection,  String initialDataDirPath) throws LoaderException
     * @param idQuery -  номер запроса
     * @param connection - соединение с базой данных
     * @param logConnection - соединение с базой данных
     * @param sanPasportDirPath - путь к директории с сан паспортами
	 */
	protected boolean doBeforeTask(Query qry) {
		//путь к директории с папками санитарных паспортов
		String sanPasportDirPath = prop.getProperty("sanPasportDirPath");
		try {
			System.out.println("Вызов сторонней процедуры SanPassportLoader.loadSanPassportData :");
			SanPassportLoader.loadSanPassportData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), sanPasportDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("SanPassportLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры SanPassportLoader.loadSanPassportData: LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("SanPassportLoaderAdapter.doBeforeTask  :Вызов сторонней процедуры SanPassportLoader.loadSanPassportData ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
