package com.hps.april.common.utils.context;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

public class AprilApplicationContextFactory implements FactoryBean, InitializingBean {

	private Resource[] configLocations;
	private ApplicationContext applicationContext;
	
	public void afterPropertiesSet() {
		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(configLocations);
		
		ctx.refresh();
		this.applicationContext = ctx;
	}
	
	
	public Object getObject() throws Exception {
		return applicationContext;
	}

	public Class getObjectType() {
		return ApplicationContext.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public Resource[] getConfigLocations() {
		return configLocations;
	}
	public void setConfigLocations(Resource[] configLocations) {
		this.configLocations = configLocations;
	}
	
}
