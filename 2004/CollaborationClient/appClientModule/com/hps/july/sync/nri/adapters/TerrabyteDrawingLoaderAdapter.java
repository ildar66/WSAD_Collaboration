/*
 * Created on 18.07.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.hps.july.sync.nri.adapters;

import java.io.File;
import java.util.Properties;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.july.terrabyte.imp.command.DrawingCommand;

/**
 * @author vglushkov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TerrabyteDrawingLoaderAdapter extends DefaultCollaboration {

	private Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public TerrabyteDrawingLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
		//super(targerDB, logDB);
		super(targerDB, null, "_LOADDRAWING2TERRABYTE", null, null, null, logDB);
		this.prop = prop;
	}
	/* (non-Javadoc)
	 * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
	 */
	protected boolean doBeforeTask(Query qry) {
		//Вызов сторонней процедуры:
		String imageDirPath = prop.getProperty("DirPath.DRAWING_TU.2terrabyte");
		if(imageDirPath == null) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.DRAWING_TU.2terrabyte' for drawing loader!");
			return false;
		}
		File imageDirPathFile = new File(imageDirPath);
        QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "DirPath.DRAWING_TU.2terrabyte ["+imageDirPath+"]");
        if(!imageDirPathFile.exists()) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to drawing TU!");
			return false;
		}

		String catalogName = prop.getProperty("catalogName.DRAWING_TU.2terrabyte");
		if(catalogName != null) catalogName = catalogName.trim();
		String host = prop.getProperty("terrabyte.host", null);
		Integer port = new Integer(prop.getProperty("terrabyte.port", "-1"));
		try {
            //--Чертежи ТУ
            System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter TU :");
			DrawingCommand command = new DrawingCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), imageDirPath, "TU");
			command.setDirectory(imageDirPath);
			if(catalogName != null && catalogName.length() > 0) command.setCatalog(catalogName);
			TerrabyteLoaderProcessor.executeLoaderCommand(command, host, port);
			System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter  TU : end !");
            //--- Чертежи_от_подрядчиков
            imageDirPath = prop.getProperty("DirPath.DRAWING_FROM_CONTRACT.2terrabyte");
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "DirPath.DRAWING_FROM_CONTRACT.2terrabyte ["+imageDirPath+"]");
            if(imageDirPath == null) {
                QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.DRAWING_FROM_CONTRACT.2terrabyte' for drawing loader!");
                return false;
            }
            imageDirPathFile = new File(imageDirPath);
            if(!imageDirPathFile.exists()) {
                QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to drawing FROM_CONTRACT!");
                return false;
            }

            catalogName = prop.getProperty("catalogName.DRAWING_FROM_CONTRACT.2terrabyte");
            if(catalogName != null) catalogName = catalogName.trim();
            System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter FROM_CONTRACT :");
			command = new DrawingCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), imageDirPath, "CONTRACTER");
			command.setDirectory(imageDirPath);
			if(catalogName != null && catalogName.length() > 0) command.setCatalog(catalogName);
			TerrabyteLoaderProcessor.executeLoaderCommand(command, host, port);
			System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter  FROM_CONTRACT: end !");

            return true;
		} catch (LoaderException le) {
			System.out.println("TerrabyteDrawingTULoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingTULoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
			le.printStackTrace(System.out);
			return false;
		} catch(BaseException e) {
			System.out.println("TerrabyteDrawingTULoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingTULoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		} catch (Exception e) {
			System.out.println("TerrabyteDrawingTULoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingTULoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
			e.printStackTrace(System.out);
			return false;
		} finally {
			System.out.println("Вызов сторонней процедуры TerrabyteDrawingTULoaderAdapter : finally !");
		}
	}

}
