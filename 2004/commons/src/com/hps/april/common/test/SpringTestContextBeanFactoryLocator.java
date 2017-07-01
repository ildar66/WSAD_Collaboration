package com.hps.april.common.test;

import org.springframework.beans.factory.BeanFactory;

public class SpringTestContextBeanFactoryLocator {

	private BeanFactory beanFactory;
	private static SpringTestContextBeanFactoryLocator instance = new SpringTestContextBeanFactoryLocator();
	
	public static void bindBeanFactory(BeanFactory beanFactory){
		instance.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory(){
		return instance.beanFactory;
	}
	
	public static void unbindBeanFactory(){
		instance.beanFactory = null;
	}
	
	public static boolean hasBeanFactory(){
		return instance.beanFactory != null;
	}
}
