/*
 *  $Id: RTSTaskModule.java,v 1.3 2007/05/13 15:08:25 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������    
 */
package com.hps.july.rts.task.service;

import java.util.List;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.3 $ $Date: 2007/05/13 15:08:25 $
 */
public interface RTSTaskModule {

	public static final String SERVICE_NAME = "july.rts.service.taskModule";
	/**
	 * ����� ��� ���������� ������ ������, �������� ���� ������� � ���������� �������
	 * @param info <String >���������� log ���������� �������
	 */
	public void processReadyTasks(List info);

}
