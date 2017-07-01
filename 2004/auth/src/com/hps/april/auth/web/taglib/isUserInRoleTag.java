package com.hps.april.auth.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.web.context.WebApplicationContext;

import com.hps.april.auth.object.AuthInfo;
import com.hps.april.auth.object.Role;
import com.hps.april.auth.service.AuthService;
import com.hps.april.common.utils.FormatUtils;
import com.hps.april.common.utils.web.WebApplicationContextProvider;

/**
 * Включает тело тега, если пользователь принадлежит заданной роли, 
 * нет в противном случае. 
 * 
 * <p>Использование:</p>
 * <p>
 * &lt;rts:isUserInRole user="${operator.login}" role="${role.name}"&gt;
 * 		body here
 * &lt;/rts:isUserInRole&gt; 
 * <br>
 * &lt;rts:isUserInRole user="${operator.id}" role="administrator"&gt;
 * 		body here
 * &lt;/rts:isUserInRole&gt; 
 * <br>
 * &lt;rts:isUserInRole user="operatorLogin" role="${role.id}"&gt;
 * 		body here
 * &lt;/rts:isUserInRole&gt; 
 * </p>
 * 
 * @author Dimitry Krivenko 
 * 15.02.2006
 */
public class isUserInRoleTag extends ConditionalTagSupport {
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
	
	protected boolean condition() throws JspTagException {
		try {
			if (roleExpression == null)
				throw new JspTagException("Attribute role is mandatory");
			
            Object roleObject = ExpressionEvaluatorManager.evaluate(
                    "role", roleExpression, Object.class, this, pageContext);
			
    		if (roleObject == null)
    			throw new JspTagException("Can't evaluate attribute role: " + roleExpression);
            
			Role role = defineRole(roleObject);
			
			AuthInfo authInfo;
			if (userExpression == null){
				// если не задан атрибут user используется текущий пользователь
				authInfo = getAuthService().getAuthInfo(
						(HttpServletRequest) pageContext.getRequest());
			}
				
			else {
	            Object userObject = ExpressionEvaluatorManager.evaluate(
	                    "user", userExpression, Object.class, this, pageContext);
				
	    		if (userObject == null)
	    			throw new JspTagException("Can't evaluate attribute user: " + userExpression);
	            
				authInfo = defineAuthInfo(userObject);
			}

			boolean result = getAuthService().isUserInRole(authInfo, role);
			
			if (varExpression != null){
				pageContext.setAttribute(varExpression, new Boolean(result));
			}
			
			return result;
			
		} catch (RuntimeException e){
			logger.error(e,e);
			return false;
        } catch (JspException ex) {
        	throw new JspTagException(ex.toString());
        }
	}

	
	protected Role defineRole(Object roleObject) throws JspTagException {
		if (roleObject instanceof String) 
			return loadRole((String)roleObject);
		
		else 
			throw new JspTagException("Don't know role of type [" + roleObject.getClass().getName() + "#" + roleObject +"]");
	}

	protected Role loadRole(String roleName) {
		return getAuthService().getRole(roleName);
	}

	
	protected AuthInfo defineAuthInfo(Object userObject) throws JspTagException {
		if (userObject instanceof Long) 
			return loadAuthInfo(FormatUtils.toInteger((Long)userObject));
		
		else if (userObject instanceof Integer) 
			return loadAuthInfo((Integer)userObject);
		
		else if (userObject instanceof String) 
			return loadAuthInfo((String)userObject);
		
		else 
			throw new JspTagException("Don't know user of type: " + userObject.getClass().getName());
	}

	protected AuthInfo loadAuthInfo(Integer operatorId) {
		return getAuthService().getAuthInfo(operatorId);
	}

	private AuthInfo loadAuthInfo(String operatorLogin) {
		return getAuthService().getAuthInfo(operatorLogin);
	}

	private String roleExpression;
	private String userExpression;
	private String varExpression; 
	
	protected String getRoleExpression() {
		return roleExpression;
	}
	protected String getUserExpression() {
		return userExpression;
	}

	public String getRole() {
		return roleExpression;
	}
	public void setRole(String roleExpression) {
		this.roleExpression = roleExpression;
	}
	public String getUser() {
		return userExpression;
	}
	public void setUser(String userExpression) {
		this.userExpression = userExpression;
	}
	public String getVarExpression() {
		return varExpression;
	}
	public void setVarExpression(String varExpression) {
		this.varExpression = varExpression;
	}
	
}
