package com.hps.april.common.utils.context;

import org.springframework.beans.factory.FactoryBean;

/**
 * Заглушка для возможности переопределения параметров
 * @author dimitry
 * 21.12.2006
 */
public class NullBeanStub implements FactoryBean {

	public Object getObject() throws Exception {
		return null;
	}

	public Class getObjectType() {
		return Object.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
