/*
 * Created on 18.07.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.july.terrabyte.imp.command.InitialDataCommand;

import java.io.File;
import java.util.Properties;
/**
 * @author vglushkov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TerrabyteInitialDataLoaderAdapter extends DefaultCollaboration {

	private Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public TerrabyteInitialDataLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LOADINITIALDATA2TERRABYTE", null, null, null, logDB);
		this.prop = prop;
	}
	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	protected boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String imageDirPath = prop.getProperty("DirPath.InitailData.2terrabyte");
        QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "DirPath.InitailData.2terrabyte ["+imageDirPath+"]");
        if(imageDirPath == null) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.InitailData.2terrabyte' for initial data !");
			return false;
		}
		File imageDirPathFile = new File(imageDirPath);
		if(!imageDirPathFile.exists()) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to initial data !");
			return false;
		}

		String catalogName = prop.getProperty("catalogName.InitailData.2terrabyte");
        QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "catalogName.InitailData.2terrabyte ["+catalogName+"]");
        if(catalogName != null) catalogName = catalogName.trim();
		String host = prop.getProperty("terrabyte.host", null);
		Integer port = new Integer(prop.getProperty("terrabyte.port", "-1"));
		try {
			System.out.println("Вызов сторонней процедуры TerrabyteInitialDataLoaderAdapter :");
			InitialDataCommand command = new InitialDataCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection());
			command.setDirectory(imageDirPath);
			if(catalogName != null && catalogName.length() > 0) command.setCatalog(catalogName);
			TerrabyteLoaderProcessor.executeLoaderCommand(command, host, port);
			return true;
		} catch (LoaderException le) {
			System.out.println("TerrabyteInitialDataLoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteImageLoaderProcessorAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch(BaseException e) {
			System.out.println("TerrabyteInitialDataLoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteImageLoaderProcessorAdapter.doBeforeTask  : BaseException: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("TerrabyteInitialDataLoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteImageLoaderProcessorAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		}
	}

}
