package com.hps.july.sync.equipment.adapters;

import com.hps.july.core.DefaultCollaboration;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.terrabyte.imp.command.ListBsForSORMCommand;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;

import java.util.Properties;
import java.io.File;

/**
 * @author vglushkov
 *
 * Адаптер для выгрузки списка проблем.
 */
public class ListBsForSORMUnloaderAdapter extends DefaultCollaboration {

    private Properties prop = null;
    /**
     * @param targerDB
     * @param logDB
     * @param prop
     */
    public ListBsForSORMUnloaderAdapter(DB targerDB, DB logDB, Properties prop) {
        //super(targerDB, logDB);
        super(targerDB, null, "_UNLOADINGLISTOFPROBLEM", null, null, null, logDB);
        this.prop = prop;
    }
    /**
     *  (non-Javadoc)
     * @see com.hps.july.core.DefaultCollaboration#doBeforeTask(com.hps.july.core.Query)
     */
    protected boolean doBeforeTask(Query qry) {
        //Вызов сторонней процедуры:
        String dirPath = prop.getProperty("DirPath.SORM.Unloading");
        if(dirPath == null) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.SORM.Unloading' for list of problem unloading !");
            return false;
        }
        File dirPathFile = new File(dirPath);
        if(!dirPathFile.exists()) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find path to list of problem unloading!");
            return false;
        }

        try {
            System.out.println("Вызов сторонней процедуры ListBsForSORMUnloaderAdapter :");
            ListBsForSORMCommand command = new ListBsForSORMCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection());
            command.setDirectory(dirPath);
            TerrabyteLoaderProcessor.executeLoaderCommand(command, null, new Integer(-1));
            return true;
        } catch (LoaderException le) {
            System.out.println("ListBsForSORMUnloaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "ListOfProblemUnloaderAdapter.doBeforeTask  : LoaderException: " + le.getMessage());
            le.printStackTrace(System.out);
            return false;
        } catch(BaseException e) {
            System.out.println("ListBsForSORMUnloaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "ListBsForSORMUnloaderAdapter.doBeforeTask  : BaseException: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        } catch (Exception e) {
            System.out.println("ListBsForSORMUnloaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "ListBsForSORMUnloaderAdapter.doBeforeTask  :Unexpected  ERROR: " + e.getMessage());
            e.printStackTrace(System.out);
            return false;
        }
    }

}
