package com.hps.july.sync.nri.adapters;

import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.terrabyte.imp.command.DrawingPPCCommand;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.util.Properties;
import java.io.File;

/**
 * @author vglushkov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TerrabyteDrawingPPCLoaderAdapter extends DefaultCollaboration {

    private Properties prop = null;
    /**
     * @param targerDB
     * @param logDB
     * @param prop
     */
    public TerrabyteDrawingPPCLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
        //super(targerDB, logDB);
        super(targerDB, null, "_LOADDRAWINGPPC2TERRABYTE", null, null, null, logDB);
        this.prop = prop;
    }
    /* (non-Javadoc)
      * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
      */
    protected boolean doBeforeTask(Query qry) {
        //Вызов сторонней процедуры:
        //--- Чертежи_РРС
        String imageDirPath = prop.getProperty("DirPath.DRAWING_PPC.2terrabyte");
        QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "DirPath.DRAWING_PPC.2terrabyte ["+imageDirPath+"]");
        if(imageDirPath == null) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.DRAWING_PPC.2terrabyte' for drawing loader!");
            return false;
        }
        File imageDirPathFile = new File(imageDirPath);
        if(!imageDirPathFile.exists()) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to drawing PPC!");
            return false;
        }

        String catalogName = prop.getProperty("catalogName.DRAWING_TU.2terrabyte");
        if(catalogName != null) catalogName = catalogName.trim();
        String host = prop.getProperty("terrabyte.host", null);
        Integer port = new Integer(prop.getProperty("terrabyte.port", "-1"));
        try {

            DrawingPPCCommand command = new DrawingPPCCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), imageDirPath, "PPC");
            catalogName = prop.getProperty("catalogName.DRAWING_PPC.2terrabyte");
            if(catalogName != null) catalogName = catalogName.trim();
            System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter_PPC :");
            command.setDirectory(imageDirPath);
            if(catalogName != null && catalogName.length() > 0) command.setCatalog(catalogName);
            TerrabyteLoaderProcessor.executeLoaderCommand(command, host, port);
            System.out.println("Вызов сторонней процедуры TerrabyteDrawingLoaderAdapter_PPC : end !");

            return true;
        } catch (LoaderException le) {
            System.out.println("TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            le.printStackTrace(System.out);
            return false;
        } catch(BaseException e) {
            System.out.println("TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        } catch (Exception e) {
            System.out.println("TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "TerrabyteDrawingPPCLoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        } finally {
            System.out.println("Вызов сторонней процедуры TerrabyteDrawingPPCLoaderAdapter : finally !");
        }
    }

}
