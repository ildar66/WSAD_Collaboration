package com.hps.july.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * ����� ������������ ������ � Spring BeanFactory ��� ����, 
 * ������� ��������� ��� ��������� Spring. ������ ��������� Spring 
 * ������������� ��� ������� � �������� ������������ ���������� ����� Spring, 
 * � action ������� ����� ������������ ��������� ��������� �����
 * com.hps.april.common.utils.web.WebApplicationContextProvider.getWebApplicationContext(ServletContext servletContext)
 * <br>
 * ��� ��������� beanFactory, ��� �������, ����������� ��� ��������� Spring 
 * � ������ com.hsp.july.core ����������� ����� com.hps.july.rts.core.CoreContextProvider
 * 
 * @author Dimitry Krivenko 
 * 22.02.2006
 */
public class MailContextProvider {
	
	protected static Logger logger = Logger.getLogger(MailContextProvider.class);
	
	private static String BEAN_FACTORY_LOCATOR_KEY = "classpath*:ctx-july-mail*-configuration.xml ";
	private static String APPLICATION_CONTEXT_KEY = "com.hps.july.mail";
	
	public static BeanFactory getBeanFactory(){
		BeanFactoryLocator factoryLocator = ContextSingletonBeanFactoryLocator.getInstance(BEAN_FACTORY_LOCATOR_KEY);
		BeanFactoryReference factoryReference = factoryLocator.useBeanFactory(APPLICATION_CONTEXT_KEY);
		return factoryReference.getFactory();
	}
	
	public static Object getBean(String beanName){
		logger.debug("Use RTSContextProvider: getBean(" + beanName + ")");
		return getBeanFactory().getBean(beanName);
	}
	
	public static Object getBean(String beanName, Class requiredType){
		logger.debug("Use RTSContextProvider: getBean(" + beanName + ")");
		return getBeanFactory().getBean(beanName, requiredType);
	}
}
