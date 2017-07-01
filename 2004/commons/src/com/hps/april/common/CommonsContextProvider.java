package com.hps.april.common;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;

/**
 * Класс обеспечивает доступ к Spring BeanFactory для кода, 
 * который находится вне контекста Spring. Внутри контекста Spring 
 * рекомендуется для доступа к сервисам использовать внутренние связи Spring, 
 * в action классах лучше использовать получение контекста через
 * com.hps.april.commons.utils.web.WebApplicationContextProvider.getWebApplicationContext(ServletContext servletContext)
 * <br>
 *
 * @author Dimitry Krivenko 
 * 22.02.2006
 */
public class CommonsContextProvider {

	private static String BEAN_FACTORY_LOCATOR_KEY = "classpath*:ctx-*-beanRefFactory.xml";
	private static String APPLICATION_CONTEXT_KEY = "com.hps.april.commons";
	
	public static BeanFactory getBeanFactory(){
		BeanFactoryLocator factoryLocator = ContextSingletonBeanFactoryLocator.getInstance(BEAN_FACTORY_LOCATOR_KEY);
		BeanFactoryReference factoryReference = factoryLocator.useBeanFactory(APPLICATION_CONTEXT_KEY);
		return factoryReference.getFactory();
	}
	
	public static Object getBean(String beanName){
		return getBeanFactory().getBean(beanName);
	}
	
	public static Object getBean(String beanName, Class requiredType){
		return getBeanFactory().getBean(beanName, requiredType);
	}

}
