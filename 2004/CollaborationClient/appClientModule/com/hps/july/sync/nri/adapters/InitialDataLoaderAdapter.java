/*
 * Created on 22.11.2004
 *
 * �������� �������� ������
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
	 * �������� �������� ������
	 * @param idQuery -  ����� �������
	 * @param connection - ���������� � ����� ������
	 * @param logConnection - ���������� � ����� ������
	 * @param initialDataDirPath - ���� � ���������� � ������� �������� ������
	 * @throws com.hps.beeline.LoaderException
	 */
	public boolean doBeforeTask(Query qry) {
		//���� � ���������� � ������� �������� ������
		String initialDataDirPath = prop.getProperty("initialDataDirPath");
		try {
			System.out.println("����� ��������� ��������� InitialDataLoader.loadInitialData :");
			InitialDataLoader.loadInitialData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), initialDataDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("InitialDataLoaderAdapter.doBeforeTask  :����� ��������� ��������� InitialDataLoader.loadInitialData: LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("InitialDataLoaderAdapter.doBeforeTask  :����� ��������� ��������� InitialDataLoader.loadInitialData ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
