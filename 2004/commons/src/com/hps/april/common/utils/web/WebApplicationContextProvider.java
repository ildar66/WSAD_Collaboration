package com.hps.april.common.utils.web;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Dimitry Krivenko
 * 14.02.2006
 */
public class WebApplicationContextProvider {
	
	/**
	 * Получение WebApplicationContext
	 * @param servletContext
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext(ServletContext servletContext) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}
	
	
}
