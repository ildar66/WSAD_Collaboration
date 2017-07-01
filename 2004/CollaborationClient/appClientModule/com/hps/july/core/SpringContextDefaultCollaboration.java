/*
 *  $Id: SpringContextDefaultCollaboration.java,v 1.2 2007/06/15 17:12:31 nizhikov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.core;

import org.springframework.context.ApplicationContext;
import com.hps.july.core.DefaultCollaboration;

/**
 * Реализация адаптера с подключением контекста spring
 *
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * @version $Revision: 1.2 $ $Date: 2007/06/15 17:12:31 $
 */
public abstract class SpringContextDefaultCollaboration extends
        DefaultCollaboration implements SpringContextSupport {

	private ApplicationContext context;

	protected SpringContextDefaultCollaboration(DB targetDb, DB logDb) {
		super(targetDb, logDb);
	}

	public void setSpringApplicationContext(ApplicationContext context) {
		this.context = context;
	}

	/**
	 * Метод возвращает бин по его идентификатору
	 * @param name
	 * @return требуемый бин
	 */
	public Object getBean(String name) {
		if(context == null) {
			throw new IllegalStateException("Spring application context not bound");
		}
		return context.getBean(name);
	}
}
