/*
 * Created on 22.11.2004
 *
 * �������� ��� ���������
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
     * �������� ��� ���������
     * com.hps.beeline.initialDataLoader.SanPassportLoader.loadSanPassportData(Integer idQuery, Connection connection, Connection logConnection,  String initialDataDirPath) throws LoaderException
     * @param idQuery -  ����� �������
     * @param connection - ���������� � ����� ������
     * @param logConnection - ���������� � ����� ������
     * @param sanPasportDirPath - ���� � ���������� � ��� ����������
	 */
	protected boolean doBeforeTask(Query qry) {
		//���� � ���������� � ������� ���������� ���������
		String sanPasportDirPath = prop.getProperty("sanPasportDirPath");
		try {
			System.out.println("����� ��������� ��������� SanPassportLoader.loadSanPassportData :");
			SanPassportLoader.loadSanPassportData(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), sanPasportDirPath);
			return true;
		} catch (LoaderException le) {
			System.out.println("SanPassportLoaderAdapter.doBeforeTask  :����� ��������� ��������� SanPassportLoader.loadSanPassportData: LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("SanPassportLoaderAdapter.doBeforeTask  :����� ��������� ��������� SanPassportLoader.loadSanPassportData ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
