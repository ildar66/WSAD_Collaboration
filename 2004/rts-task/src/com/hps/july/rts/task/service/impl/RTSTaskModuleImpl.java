/*
 *  $Id: RTSTaskModuleImpl.java,v 1.4 2007/05/22 10:03:53 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком    
 */
package com.hps.july.rts.task.service.impl;

import com.hps.july.rts.task.service.RTSTaskModule;
import com.hps.july.rts.task.service.RTSTaskService;
import com.hps.july.rts.task.service.RTSTaskEventHandler;
import com.hps.july.rts.task.object.RTSTask;
import com.hps.july.rts.task.object.RTSTaskInfo;
import com.hps.july.rts.SystemException;
import com.hps.april.auth.service.AuthService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StopWatch;

/**
 * Обработчик заданий для модуля Collaboration
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.4 $ $Date: 2007/05/22 10:03:53 $
 */
public class RTSTaskModuleImpl implements RTSTaskModule {

	private RTSTaskService taskService;
	private AuthService authService;
	private HashMap handlers;

	public RTSTaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(RTSTaskService taskService) {
		this.taskService = taskService;
	}

	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * Метод для выполнения бизнес логики, обрботки всех готовых к выполеннию заданий
	 * @param info <String >возвращает log выполнения заданий
	 */
	public void processReadyTasks(List info) {

		try {
			List readyTasks = taskService.findReadyTasks();
			for(int i = 0; i < readyTasks.size(); i++) {
				RTSTask task = (RTSTask)readyTasks.get(i);
				if(task == null) {
					info.add(new RTSTaskInfo(RTSTaskEventHandler.ERROR, "task is null, admin must check"));
				} else {
					RTSTaskEventHandler handler = (RTSTaskEventHandler)getHandlers().get(task.getTaskType().toString());
					if(handler == null) {
						info.add(new RTSTaskInfo(RTSTaskEventHandler.ERROR, "handler for taskType ["+task.getTaskType()+"] not found"));
						continue;
					} else {
						String taskState = handler.handleTask(task, info);
						task.setStatus(taskState);
						getTaskService().saveRTSTask(authService.getSystemAuthInfo(), task);
					}
				}
			}
		} catch(SystemException e) {
			e.printStackTrace();
			info.add(new RTSTaskInfo(RTSTaskEventHandler.ERROR, "Error while processing RTSTask: " + e.toString()));
		}

	}
	/**
	 * @return
	 */
	public HashMap getHandlers() {
		return handlers;
	}

	/**
	 * @param map
	 */
	public void setHandlers(HashMap map) {
		handlers = map;
	}

}
