/*
 *  $Id: RTSAuthenticationFilter.java,v 1.2 2006/11/17 09:48:00 vglushkov Exp $
 *  Copyright (c) 2003 - 2006 ОАО Вымпелком
 */
package com.hps.july.rts.auth.web;

import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.hps.april.common.utils.web.WebApplicationContextProvider;
import com.hps.april.auth.service.AuthService;

import com.hps.july.rts.auth.service.RTSAuthService;

/**
 * @author <a href="mailto:vadim.glushkov@sntru.com">Vadim Glushkov</a>
 * Date: 22.05.2006
 * Time: 13:33:57
 */
public class RTSAuthenticationFilter implements Filter {

	// ----------------------------------------------------- Instance Variables

	/**
	 * The filter configuration object we are associated with.  If this value
	 * is null, this filter instance is not currently configured.
	 */
	protected FilterConfig filterConfig = null;

	// --------------------------------------------------------- Public Methods

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {
		this.filterConfig = null;

	}

	/**
	 * Place this filter into service.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
    // --------------------------------------------------------- Public Methods


    /**
     * Поиск и инициализация объекта индентификации текущего пользователя для модуля
     * Заявки на Транспортную сеть
     *
     * @param request  The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain    The filter chain we are processing
     * @throws java.io.IOException      if an input/output error occurs
     * @throws javax.servlet.ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        if(request instanceof HttpServletRequest) {
        	if(((HttpServletRequest)request).getRemoteUser() != null)
				getRTSAuthService().getAuthInfo((HttpServletRequest)request);
        }
            

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Exception in RTSAuthenticationFilter", e);
        }
    }

    // ------------------------------------------------------ Protected Methods

	// ------------------------------------------------------ Protected Methods

	protected WebApplicationContext getWebApplicationContext(){
		return WebApplicationContextProvider.getWebApplicationContext(
				filterConfig.getServletContext()
		);
	}

    protected RTSAuthService getRTSAuthService(){
        return (RTSAuthService) getWebApplicationContext().getBean(
                RTSAuthService.SERVICE_NAME);
    }
}
