package com.hps.april.auth.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.service.AuthService;
import com.hps.april.common.utils.web.WebApplicationContextProvider;

/**
 * Cохраняет в request объект AuthInfo под именем authInfo
 * @author dimitry
 * 13.11.2006
 */
public class SetAuthInfoFilter implements Filter {

	protected Log logger = LogFactory.getLog(getClass());
	
	protected static String DEFAULT_AUTH_INFO_PARAMETER = "authInfo"; 

	private String authInfoParameter = DEFAULT_AUTH_INFO_PARAMETER;
	protected FilterConfig filterConfig = null;
	
	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
		this.authInfoParameter = DEFAULT_AUTH_INFO_PARAMETER;
	}
	
	protected WebApplicationContext getWebApplicationContext(ServletContext servletContext){
		return WebApplicationContextProvider.getWebApplicationContext(
				servletContext);
	}
	
	protected AuthService getAuthService(ServletContext servletContext){
		return (AuthService) getWebApplicationContext(servletContext).getBean(
				AuthService.SERVICE_NAME);
	}
	
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		ServletContext servletContext = filterConfig.getServletContext();

		logger.info("Process AuthInfoFilter...");
			
		
		try {
			AuthInfo authInfo = getAuthService(servletContext).getAuthInfo(
					(HttpServletRequest) request);
	
			request.setAttribute(authInfoParameter, authInfo);

			chain.doFilter(request, response);
		} catch (Exception e) {
			String requestPath = request.getRemoteAddr(); 
			if (request instanceof HttpServletRequest){
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				requestPath = httpRequest.getRequestURI();
			}
			
			logger.error("Exception while processing request ["+requestPath+"]: " + e.getMessage(), e);
			throw new ServletException("Exception while processing request ["+requestPath+"]:", e);
		}
	}

	/**
	 * Place this filter into service.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.authInfoParameter = filterConfig.getInitParameter("parameter");
	}



}
