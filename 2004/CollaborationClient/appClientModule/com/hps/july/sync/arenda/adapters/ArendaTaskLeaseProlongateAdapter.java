/*
 *  $Id: ArendaTaskLeaseProlongateAdapter.java,v 1.4 2007/06/15 17:12:31 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.sync.arenda.adapters;

import com.hps.beeline.LoaderException;
import com.hps.framework.exception.BaseException;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.DefaultCollaboration;
import com.hps.july.terrabyte.imp.TerrabyteLoaderProcessor;
import com.hps.july.terrabyte.imp.command.arenda.CheckLeaseProlongateCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Properties;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/06/15 17:12:31 $
 */
public class ArendaTaskLeaseProlongateAdapter extends DefaultCollaboration {

	private Log log = LogFactory.getLog(ArendaTaskLeaseProlongateAdapter.class);
	private Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public ArendaTaskLeaseProlongateAdapter(DB targerDB, DB logDB, Properties prop) {
		super(targerDB, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
     * точка входа для обработки всех задача для модуля RTS
     * @param qry -  запроса
	 */
	protected boolean doBeforeTask(Query qry) {
		log.info("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] running...");
		boolean result = true;
		try {
			String dayStr = qry.getParameter(CheckLeaseProlongateCommand.COUNT_DAY_WARNING);
			Integer days = null;
			try {
				days = new Integer(dayStr);
			} catch(Exception e) {
				QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, "Отсутствует параметр 'AddDayWarning'");
				log.error("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] 'Отсутствует параметр 'AddDayWarning'", e);
				return false;
			}
			CheckLeaseProlongateCommand command = new CheckLeaseProlongateCommand(new Integer(qry.getQueryId()), getTargetDbConnection(), getLogDbConnection(), days);
			TerrabyteLoaderProcessor.executeLoaderCommand(command, "nri-application", new Integer(80));

			return result;
		} catch(IllegalStateException e) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, e.toString());
			log.error("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] error", e);
			result = false;
			return result;
		} catch(BaseException e) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, e.toString());
			log.error("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] error", e);
			result = false;
			return result;
		} catch(LoaderException e) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, e.toString());
			log.error("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] error", e);
			result = false;
			return result;
		} finally {
			log.info("ArendaTaskLeaseProlongateAdapter with query ["+qry.getQueryId()+"] ended with result ["+result+"].");
		}
	}
}
