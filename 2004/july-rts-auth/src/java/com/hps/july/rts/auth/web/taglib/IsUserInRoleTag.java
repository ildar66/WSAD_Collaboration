package com.hps.july.rts.auth.web.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.hps.april.auth.object.AuthInfo;
import com.hps.july.rts.auth.service.RTSAuthService;
import com.hps.july.rts.auth.object.RTSRole;
import com.hps.april.common.FinderException;

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
 * &lt;rts:isUserInRole user="operatorLogin" role="${role.name}"&gt;
 * 		body here
 * &lt;/rts:isUserInRole&gt;
 * </p>
 *
 * @author Dimitry Krivenko
 * 15.02.2006
 */
public class IsUserInRoleTag extends com.hps.april.auth.web.taglib.isUserInRoleTag {
    private static final long serialVersionUID = 1L;

    protected Logger logger = Logger.getLogger(IsUserInRoleTag.class);

    public IsUserInRoleTag() {
    }

    protected RTSAuthService getRtsAuthService(){
        return (RTSAuthService) getWebApplicationContext().getBean(
                RTSAuthService.SERVICE_NAME);
    }

    protected boolean condition() throws JspTagException {
        // check superRoles
        boolean superResult = false;//super.condition();
        //if (superResult) return true;

        try {
            if (getRoleExpression() == null)
                throw new JspTagException("Attribute role is mandatory");

            Object roleObject = ExpressionEvaluatorManager.evaluate(
                    "role", getRoleExpression(), Object.class, this, pageContext);

            if (roleObject == null)
                throw new JspTagException("Can't evaluate attribute role: " + getRoleExpression());

            RTSRole role = defineRtsRole(roleObject);

            AuthInfo authInfo;
            if (getUserExpression() == null){
                // если не задан атрибут user используется текущий пользователь
                authInfo = getAuthService().getAuthInfo(
                        (HttpServletRequest) pageContext.getRequest());
            } else {
                Object userObject = ExpressionEvaluatorManager.evaluate(
                        "user", getUserExpression(), Object.class, this, pageContext);

                if (userObject == null)
                    throw new JspTagException("Can't evaluate attribute user: " + getUserExpression());

                authInfo = defineAuthInfo(userObject);
            }

            boolean result = false;
            //если роль по алиасу не найдена
            if(role != null) result = getRtsAuthService().isUserInRtsRole(authInfo, role);
            else {
            	if(roleObject instanceof String) result = getRtsAuthService().isUserInRole(authInfo, (String)roleObject);
            }

            if (getVarExpression() != null){
                pageContext.setAttribute(getVarExpression(), new Boolean(result || superResult));
            }

            return result;

        } catch (RuntimeException e){
            logger.error(e,e);
            return false;
        } catch (JspException ex) {
            throw new JspTagException(ex.toString());
        }
    }


    protected RTSRole defineRtsRole(Object roleObject) throws JspTagException {
        if (roleObject instanceof String)
            return loadRtsRole((String)roleObject);
        else
            throw new JspTagException("Don't know role of type: " + roleObject.getClass().getName());
    }

    protected RTSRole loadRtsRole(String roleName) {
        try {
            return getRtsAuthService().getRTSRoleByAlias(roleName);
        } catch (FinderException e) {
            return null;
        }
    }
}
