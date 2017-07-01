package com.hps.july.sync.nri.adapters;

import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.terrabyte.imp.command.status.RequestAndPermitCommand;
import com.hps.july.terrabyte.imp.command.CommandNames;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.util.Properties;
import java.io.File;

/**
 * Адаптер для загрузки общей информации по разрешениям и завякам по БС
 * от Копачинского Евгения
 * Author: Vadim Glushkov (mailto: v.glushkov@mail.ru)
 * Date: 04.04.2006
 * Time: 11:29:56
 */
public class BSRequestAndPermitLoaderAdapter extends DefaultCollaboration {

    private Properties prop = null;
    /**
     * @param targerDB
     * @param logDB
     * @param prop
     */
    public BSRequestAndPermitLoaderAdapter(DB targerDB, DB logDB, Properties prop) {
        super(targerDB, null, "_LOADBSRequestAndPermit", null, null, null, logDB);
        this.prop = prop;
    }
    /* (non-Javadoc)
      * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
      */
    protected boolean doBeforeTask(Query qry) {
        //Вызов сторонней процедуры:
        String imageDirPath = prop.getProperty("DirPath.BSRequestAndPermit");
        if(imageDirPath == null) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.BSRequestAndPermit' for loader !");
            return false;
        }
        File imageDirPathFile = new File(imageDirPath);
        if(!imageDirPathFile.exists()) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to BS request loader !");
            return false;
        }

        try {
            System.out.println("Вызов сторонней процедуры BSRequestAndPermitLoaderAdapter :");
            RequestAndPermitCommand command = new RequestAndPermitCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection());
            command.setParameter(CommandNames.FILE_CATALOG, imageDirPath);
            TerrabyteLoaderProcessor.executeLoaderCommand(command, null, new Integer(-1));
            return true;
        } catch (LoaderException le) {
            System.out.println("BSRequestAndPermitLoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "BSRequestAndPermitLoaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            le.printStackTrace(System.out);
            return false;
        } catch(BaseException e) {
            System.out.println("BSRequestAndPermitLoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "BSRequestAndPermitLoaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        } catch (Exception e) {
            System.out.println("TerrabyteSanPassportLoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "BSRequestAndPermitLoaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        }
    }
}
