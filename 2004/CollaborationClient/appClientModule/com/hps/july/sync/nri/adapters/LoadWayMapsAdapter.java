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
		 * �������� ������� ����
		 * @param idQuery -  ����� �������
		 * @param connection - ���������� � ����� ������
		 * @param logConnection - ���������� � ����� ������,
		 * �� cfg loadWayMapsDirPath  - ���� � ���������� � ���� �������
		 * @throws com.hps.beeline.LoaderException
		 */

	public boolean doBeforeTask(Query qry) {
		//����� ��������� ���������:
		String loadWayMapsDirPath = prop.getProperty("loadWayMapsDirPath");
/**
		long time;
		if (getLastUpdateDate() != null) {
			time = getLastUpdateDate().getTime();
		} else {
			System.out.println("getLastUpdateDate() == null");
			time = System.currentTimeMillis() - 2 * (24 * 60 * 60 * 1000); //����� ��� ���;
		}

		java.sql.Date lastUpdateDate = new java.sql.Date(time);
		System.out.println("init lastUpdateDate="+lastUpdateDate);
*/
		try {
			System.out.println("����� ��������� ��������� MayMapLoader.loadWayMapData():");
			//com.hps.beeline.loader.MainProcessor.loadWayMaps(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), lastUpdateDate, blobDirPath);
			com.hps.beeline.initialDataLoader.MayMapLoader.loadWayMapData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), loadWayMapsDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("LoadWayMapsAdapter.doBeforeTask  :����� ��������� ��������� LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("LoadWayMapsAdapter.doBeforeTask  :����� ��������� ��������� ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
