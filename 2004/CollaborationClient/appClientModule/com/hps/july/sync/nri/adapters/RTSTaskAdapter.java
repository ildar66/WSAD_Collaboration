/*
 *  $Id: RTSTaskAdapter.java,v 1.3 2007/06/15 17:12:37 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.sync.nri.adapters;

import com.hps.july.rts.task.object.RTSTaskInfo;
import com.hps.july.rts.task.service.RTSTaskEventHandler;
import com.hps.july.rts.task.service.RTSTaskModule;
import com.hps.july.core.DB;
import com.hps.july.core.Query;
import com.hps.july.core.QueryProcessing;
import com.hps.july.core.SpringContextDefaultCollaboration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/06/15 17:12:37 $
 */
public class RTSTaskAdapter extends SpringContextDefaultCollaboration {

	private Log log = LogFactory.getLog(RTSTaskAdapter.class);
	private Properties prop = null;
	/**
	 * @param targerDB
	 * @param logDB
	 * @param prop
	 */
	public RTSTaskAdapter(DB targerDB, DB logDB, Properties prop) {
		super(targerDB, logDB);
		this.prop = prop;
	}

	/* (non-Javadoc)
     * точка входа для обработки всех задача для модуля RTS
     * @param qry -  запроса
	 */
	protected boolean doBeforeTask(Query qry) {
		log.info("RTSTaskAdapter with query ["+qry.getQueryId()+"] running...");
		boolean result = true;
		try {
			RTSTaskModule module = (RTSTaskModule)getBean(RTSTaskModule.SERVICE_NAME);
			ArrayList list = new ArrayList();
			module.processReadyTasks(list);
			for(int i = 0; i < list.size(); i++) {
				RTSTaskInfo info = (RTSTaskInfo)list.get(i);
				log.info("RTSTaskAdapter ["+info.getFlag()+"] "+info.getComment()+" ");
				if(info.getFlag().equals(RTSTaskEventHandler.ERROR)) {
					result = false;
				}
				QueryProcessing.addLogMessage(getLogDbConnection(), qry, info.getFlag(), info.getComment());
			}
			return result;
		} catch(IllegalStateException e) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, e.toString());
			log.error("RTSTaskAdapter with query ["+qry.getQueryId()+"] error", e);
			result = false;
			return result;
		} catch(BeansException e) {
			QueryProcessing.addLogMessage(getLogDbConnection(), qry, QueryProcessing.MSG_ERROR, e.toString());
			log.error("RTSTaskAdapter with query ["+qry.getQueryId()+"] error", e);
			result = false;
			return result;
		} finally {
			log.info("RTSTaskAdapter with query ["+qry.getQueryId()+"] ended with result ["+result+"].");
		}
	}
}
