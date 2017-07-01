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
import com.hps.april.auth.object.PermitAction;
import com.hps.april.auth.object.PermitObjectId;
import com.hps.april.auth.service.AuthService;
import com.hps.april.common.utils.FormatUtils;
import com.hps.april.common.utils.web.WebApplicationContextProvider;

/**
 * ¬ключает тело тега, если пользователь принадлежит может выполнить 
 * заданное действие, нет в противном случае.
 * 
 * @deprecated ѕерересен в PermissionService
 * 
 * <p>»спользование:</p>
 * <p>
 * &lt;app:checkPermission user="${operator.login}" permission="${permission.name}"&gt;
 * 		body here
 * &lt;/app:checkPermission&gt; 
 * <br>
 * &lt;app:checkPermission user="${operator.id}" permission="openDocument"&gt;
 * 		body here
 * &lt;/rts:checkPermission&gt; 
 * <br>
 * &lt;rts:checkPermission user="operatorLogin" permissison="openDoc"&gt;
 * 		body here
 * &lt;/rts:checkPermission&gt; 
 * </p>
 * »спользуетс€ текущий пользователь: 
 * &lt;rts:checkPermission permissison="openDoc"&gt;
 * 		body here
 * &lt;/rts:checkPermission&gt; 
 * </p>
 * 
 * @author Dimitry Krivenko 
 * 7.12.2006
 */
public class CheckPermissionTag extends ConditionalTagSupport {
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
		
	protected AuthInfo defineAuthInfo() throws JspException {

		AuthInfo authInfo;
		if (userExpression == null){
			// если не задан атрибут user используетс€ текущий пользователь
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
		
		return authInfo;
	}
	
	protected AuthInfo defineAuthInfo(Object userObject) throws JspTagException {
		if (userObject instanceof Long) 
			return getAuthService().getAuthInfo(FormatUtils.toInteger((Long)userObject));
			
		else if (userObject instanceof Integer) 
			return getAuthService().getAuthInfo((Integer)userObject);
		
		else if (userObject instanceof String) 
			return getAuthService().getAuthInfo((String)userObject);
		
		else 
			throw new JspTagException("Don't know user of type: " + userObject.getClass().getName());
	}

	/**
	 * ѕроверка прав
	 */
	protected boolean condition() throws JspTagException {
		try {
			
			AuthInfo authInfo = defineAuthInfo();
			PermitAction action = defineAction();
			PermitObjectId objectId = defineObjectId();
			
			boolean result = false;
			
			if (objectId == null)
				result = getAuthService().checkPermission(authInfo, action);
			else 
				result = getAuthService().checkPermission(authInfo, action, objectId);

			String var = defineVarName();
			if (var != null) {
				pageContext.setAttribute(var, new Boolean(result));
			}
			
			return result;
			
		} catch (RuntimeException e){
			logger.error(e,e);
			return false;
        } catch (JspException ex) {
        	throw new JspTagException(ex.toString());
        }
	}
		
	protected PermitAction defineAction(Object actionObject) throws JspTagException {
		if (actionObject instanceof String) 
			return getAuthService().getPermitAction((String)actionObject);
		
		else 
			throw new JspTagException("Don't know action of type [" + actionObject.getClass().getName() + "#" + actionObject +"]");
	}

	protected PermitAction defineAction() throws JspException {
		if (getActionExpression() == null)
			throw new JspTagException("Attribute action is mandatory");
		
        Object actionObject = ExpressionEvaluatorManager.evaluate(
                "action", getActionExpression(), Object.class, this, pageContext);
		
		if (actionObject == null)
			throw new JspTagException("Can't evaluate attribute action: " + actionExpression);
        
		return defineAction(actionObject);
	}

	protected PermitObjectId defineObjectId(Object objectIdObject) throws JspTagException {
		if (objectIdObject instanceof String) 
			return getAuthService().getPermitObjectId((String)objectIdObject);
		
		else 
			throw new JspTagException("Don't know objectId of type [" + objectIdObject.getClass().getName() + "#" + objectIdObject +"]");
	}

	protected PermitObjectId defineObjectId() throws JspException {
		if (getObjectIdExpression() == null)
			return null;
		
        Object objectIdObject = ExpressionEvaluatorManager.evaluate(
                "objectId", getObjectIdExpression(), Object.class, this, pageContext);
		
		if (objectIdObject == null)
			return null;
        
		return defineObjectId(objectIdObject);
	}

	
	protected String defineVarName() throws JspException{
		if (getVarExpression() == null)
			return null;
		
        Object varObject = ExpressionEvaluatorManager.evaluate(
                "var", getVarExpression(), Object.class, this, pageContext);
		
		if (varObject == null)
			return null;
        
		return defineVarName(varObject);
	}
	
	protected String defineVarName(Object varObject) throws JspTagException {
		if (varObject instanceof String) 
			return (String)varObject;
		
		else 
			throw new JspTagException("Don't know var of type [" + varObject.getClass().getName() + "#" + varObject +"]");
	}

	private String actionExpression;
	private String userExpression;
	private String objectIdExpression;
	private String varExpression; 
		
	protected String getActionExpression() {
		return actionExpression;
	}
	protected String getUserExpression() {
		return userExpression;
	}
	protected String getObjectIdExpression() {
		return userExpression;
	}

	public String getPermission() {
		return actionExpression;
	}
	public void setPermission(String actionExpression) {
		this.actionExpression = actionExpression;
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

	public void setObjectId(String objectId){
		this.objectIdExpression = objectId;
	}
	public String getObjectId(){
		return this.objectIdExpression;
	}
}
