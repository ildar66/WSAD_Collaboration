/*
 * Created on 22.11.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import com.hps.beeline.excelLoader.BalanceExcelFilesProcessor;
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
public class BalanceExcelFilesAdapter extends DefaultCollaboration {
	Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public BalanceExcelFilesAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_BALANCESTORE", null, null, null, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	public boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String excelDirPath = prop.getProperty("excelDirPath");
		try {
			System.out.println("Вызов сторонней процедуры processBalanceFiles:");
			BalanceExcelFilesProcessor.processBalanceFiles(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), excelDirPath);
			return true;
		} catch (Exception e) {
			System.out.println("BalanceExcelFilesAdapter.doBeforeTask  :Вызов сторонней процедуры ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
