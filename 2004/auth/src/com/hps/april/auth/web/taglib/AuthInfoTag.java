package com.hps.april.auth.web.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.service.AuthService;
import com.hps.april.common.utils.web.WebApplicationContextProvider;

/**
 * Сохраняет на странице объект AuthInfo
 * 
 * <p>Использование:</p>
 * <p>
 * &lt;app:authInfo var="authInfo"/&gt;
 * 
 * @author Dimitry Krivenko 
 * 13.11.2006
 */
public class AuthInfoTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(this.getClass());
	
	protected WebApplicationContext getWebApplicationContext(){
		return WebApplicationContextProvider.getWebApplicationContext(
				pageContext.getServletContext());
	}
	
	protected AuthService getAuthService(){
		return (AuthService) getWebApplicationContext().getBean(
				AuthService.SERVICE_NAME);
	}
	
	private String var;
	
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}

	public int doStartTag() throws JspException {
		super.bodyContent = null;  // clean-up body (just in case container is pooling tag handlers)

	    try {
	    	AuthInfo authInfo = getAuthService().getAuthInfo(
	    			(HttpServletRequest) pageContext.getRequest());
	    	  
	    	if (var != null)
	    		pageContext.getRequest().setAttribute(var, authInfo);
	    	else 
	    		out(pageContext, authInfo.getLogin());
	    		  
		    return SKIP_BODY;
		    
	    } catch (IOException ex) {
	    	logger.error(ex,ex);
	    	throw new JspException(ex.getMessage(), ex);
	    }
	}
	
	
	public static void out(PageContext pageContext, String text) throws IOException {
		JspWriter w = pageContext.getOut();
		w.write(text);
	}
}
