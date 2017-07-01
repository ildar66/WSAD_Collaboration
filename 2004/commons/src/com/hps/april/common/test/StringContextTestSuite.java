package com.hps.april.common.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;


import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class StringContextTestSuite extends TestSuite {

	protected Log logger = LogFactory.getLog(getClass());
	
	private String beanRefFactoryLocation;
	private String beanRefFactoryKey;
	
	public StringContextTestSuite(String string) {
		super(string);
	}

	public StringContextTestSuite(String testName, String beanRefFactoryLocation, String beanRefFactoryKey) {
		super(testName);
		this.beanRefFactoryLocation = beanRefFactoryLocation;
		this.beanRefFactoryKey = beanRefFactoryKey;
	}

	public String getBeanRefFactoryKey() {
		return beanRefFactoryKey;
	}

	public void setBeanRefFactoryKey(String beanRefFactoryKey) {
		this.beanRefFactoryKey = beanRefFactoryKey;
	}

	public String getBeanRefFactoryLocation() {
		return beanRefFactoryLocation;
	}

	public void setBeanRefFactoryLocation(String beanRefFactoryLocation) {
		this.beanRefFactoryLocation = beanRefFactoryLocation;
	}

	public void setUp(){
		if (beanRefFactoryKey != null && beanRefFactoryLocation != null) {
			BeanFactoryLocator factoryLocator = ContextSingletonBeanFactoryLocator.getInstance(beanRefFactoryLocation);
			BeanFactoryReference factoryReference = factoryLocator.useBeanFactory(beanRefFactoryKey);
			SpringTestContextBeanFactoryLocator.bindBeanFactory(factoryReference.getFactory());
		}
	}
	
	
	
	public void run(TestResult arg0) {
		setUp();
		super.run(arg0);
		tearDown();
	}
	
	public void tearDown() {
		SpringTestContextBeanFactoryLocator.unbindBeanFactory();	
	}

	public void runTest(Test test, TestResult testResult) {
		logger.info("RunTest: " + test);
		super.runTest(test, testResult);
	}
	
	
	
}
