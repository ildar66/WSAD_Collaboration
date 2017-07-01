/*
 *  $Id: TrailcomFrequencyPermitsUploadAdapter.java,v 1.7 2007/06/15 17:12:39 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.sync.trailcom.adapters;

import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DefaultCollaboration;
import ru.trailcom.rchs2nri.service.Declarations;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.7 $ $Date: 2007/06/15 17:12:39 $
 */
public class TrailcomFrequencyPermitsUploadAdapter extends DefaultCollaboration {

    private Properties prop = null;
    /**
     * @param targerDB
     * @param logDB
     * @param prop
     */
    public TrailcomFrequencyPermitsUploadAdapter(DB targerDB, DB logDB, Properties prop) {
        super(targerDB, null, "_TRAILCOM_FREQUENCY_PERMITS_LOAD", null, null, null, logDB);
        this.prop = prop;
    }

    protected boolean doBeforeTask(Query qry) {
		boolean executeResult = false;
        //Вызов сторонней процедуры:
        String uploadDirPath = prop.getProperty("DirPath.FREQUENCE.PERMITS.upload.dir");
        if(uploadDirPath == null) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find property 'DirPath.FREQUENCE.PERMITS.upload.dir' for Declaration loader!");
            return executeResult;
        }
        File uploadDirPathFile = new File(uploadDirPath);
        QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_INFO, "DirPath.FREQUENCE.PERMITS.upload.dir ["+uploadDirPath +"]");
        if(!uploadDirPathFile.exists()) {
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Cannot find file to declaration loader!");
            return executeResult;
        }

        List errors = new ArrayList();
        try {
            System.out.println("Вызов сторонней процедуры Declaration Loader : start");
			Declarations mainClass = new Declarations();
			executeResult = mainClass.execute(getTargetDbConnection(), getLogDbConnection(), uploadDirPathFile, errors);
            System.out.println("Вызов сторонней процедуры Declaration Loader : end !");
        } catch (RuntimeException le) {
            System.out.println("Declaration Loader  : LoaderException: " + le.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Declaration loader  : RuntimeException: " + le.getMessage());
            le.printStackTrace(System.out);
            return executeResult;
        } catch (Exception e) {
            System.out.println("Declaration Loader :Unexpected  ERROR: " + e.getMessage());
            QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Declaration loader :Unexpected error: " + e.getMessage());
            e.printStackTrace(System.out);
            return executeResult;
        } finally {
            if(errors.size() > 0) {
                for(int i = 0; i < errors.size(); i++) {
                    String error = (String)errors.get(i);
                    QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, error);
                }
            }
        }
        return executeResult;
    }
}
