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
		 * �������� ��������
		 * @param idQuery -  ����� �������
		 * @param connection - ���������� � ����� ������
		 * @param logConnection - ���������� � ����� ������
		 * @param loadDraftsDirPath - ���� � ���������� � ���������
		 * @throws com.hps.beeline.LoaderException
		 */
	public boolean doBeforeTask(Query qry) {
		//����� ��������� ���������:
		String loadDraftsDirPath = prop.getProperty("loadDraftsDirPath");
		try {
			System.out.println("����� ��������� ��������� DraftLoader.loadDrafts():");
			com.hps.beeline.initialDataLoader.DraftLoader.loadDrafts(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), loadDraftsDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("DraftLoaderAdapter.doBeforeTask  :����� ��������� ��������� LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("DraftLoaderAdapter.doBeforeTask  :����� ��������� ��������� ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
