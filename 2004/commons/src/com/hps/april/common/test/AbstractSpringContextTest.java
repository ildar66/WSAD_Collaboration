package com.hps.april.common.test;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;

public abstract class AbstractSpringContextTest extends TestCase {

	protected Log logger = LogFactory.getLog(getClass()); 
	
	protected void setUp() throws Exception {
	}
	
	public void runBare() throws Throwable {
		try {
			super.runBare();
		} catch (Throwable ex){
			logger.error(ex,ex);
			throw ex;
		}
	}
	
	protected BeanFactory getBeanFactory(){
		if (SpringTestContextBeanFactoryLocator.hasBeanFactory())
			return SpringTestContextBeanFactoryLocator.getBeanFactory();
		
		throw new IllegalArgumentException("Spring beanFactory not found in SpringTestContextBeanFactoryLocator. Please bind spring beanFactory to SpringTestContextBeanFactoryLocator before run this test.");
	}
	
	protected Object getBean(String beanName){
		return getBeanFactory().getBean(beanName);
	}

	protected Object getBean(String beanName, Class requiredType){
		return getBeanFactory().getBean(beanName, requiredType);
	}

	protected void tearDown() throws Exception {
	}
	
}
