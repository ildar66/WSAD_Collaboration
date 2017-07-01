/*
 *  $Id: RTSTaskEventHandler.java,v 1.2 2007/05/02 08:25:52 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.task.service;

import com.hps.july.rts.task.object.RTSTask;

import java.util.List;

/**
 *  ���������� ������� ��� ������ ������ ��
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/05/02 08:25:52 $
 */
public interface RTSTaskEventHandler {

	public final String INFO = "I";
	public final String ERROR = "E";

	/**
	 * ���������� �������
	 * @param task ������
	 * @param processInfo <String, String> ��� ������� (I-info,E-error), �������� �������
	 * @return ��������� �������
	 */
	public String handleTask(RTSTask task, List processInfo);
}
