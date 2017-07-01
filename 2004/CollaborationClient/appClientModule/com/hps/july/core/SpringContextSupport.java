/*
 *  $Id: SpringContextSupport.java,v 1.2 2007/06/15 17:12:31 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ��� ���������
 */
package com.hps.july.core;

import org.springframework.context.ApplicationContext;

/**
 * ��������� ��� ��������� �������� ������� � ���������
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/15 17:12:31 $
 */
public interface SpringContextSupport {

	public void setSpringApplicationContext(ApplicationContext context);

	/**
	 * ����� ���������� ��� �� ��� ��������������
	 * @param name
	 * @return ��������� ���
	 */
	public Object getBean(String name);
}
