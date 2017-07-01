<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<%@ page import="com.ibm.ws.console.core.bean.StatusBean" %>
<%@ page import="java.util.*,com.ibm.ws.security.core.SecurityContext,com.ibm.websphere.product.*"%>
<%@ page import="java.util.*" %>
<%@ page import="com.ibm.ws.console.core.bean.UserPreferenceBean" %>
<%@ page import="com.ibm.ws.console.core.*" %>


<% 	
boolean val = false;
if (SecurityContext.isSecurityEnabled()) {
    if (request.isUserInRole("administrator")) {
        val = false;
    }
    else if (request.isUserInRole("configurator")) {
        val = false;
    }
    else
    	val = true;
}	
%>

<TABLE BORDER="0" CELLPADDING="3" CELLSPACING="1" WIDTH="100%" SUMMARY="List table" CLASS="framing-table">
      
<form method="post" action="<%=request.getContextPath()%>/configProbCollection.do" name="myform">
<tr> 
  <td class="column-head" colspan="3" nowrap> <bean:message key="configproblems.validation.title"/>
  </td>
</tr>

<tr valign="top"> 
            <td class="table-text"  nowrap valign="top" scope="row"><bean:message key="configproblems.validation.label"/></td>
            <td class="table-text" nowrap valign="top"> 
            
        <% UserPreferenceBean uBean = (UserPreferenceBean)session.getAttribute(Constants.USER_PREFS);
            String policy = (String)uBean.getProperty("System/validation", "validationLevel", "low"); %>

                <table width="100%" border="0" cellspacing="0" cellpadding="2">
                        <tr> 
                          <td class="table-text" nowrap> 
                            <input type="radio" name="radio1" value="highest" <%= (policy.equalsIgnoreCase("highest")) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
                            <bean:message key="configproblems.validation.highest"/>
                            </td>
                        </tr>
                        <tr> 
                          <td class="table-text" nowrap> 
                            <input type="radio" name="radio1" value="high" <%= (policy.equalsIgnoreCase("high")) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
                            <bean:message key="configproblems.validation.high"/>
                            </td>
                        </tr>
                        <tr> 
                          <td class="table-text" nowrap> 
                            <input type="radio" name="radio1" value="medium" <%= (policy.equalsIgnoreCase("medium")) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
                            <bean:message key="configproblems.validation.medium"/>
                            </td>
                        </tr>
                        <tr> 
                          <td class="table-text" nowrap> 
                            <input type="radio" name="radio1" value="low" <%= (policy.equalsIgnoreCase("low")) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
                            <bean:message key="configproblems.validation.low"/>
                            </td>
                        </tr>
                        <tr> 
                          <td class="table-text" nowrap> 
                            <input type="radio" name="radio1" value="none" <%= (policy.equalsIgnoreCase("none")) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
                            <bean:message key="configproblems.validation.none"/>
                            </td>
                        </tr>
                
                </table>


            </td>
            <td class="table-text" valign="top"> 
      <ibmcommon:info image="help.additional.information.image" topic="ProblemDetermination.validation.policy.configDocumentValidation"/>
            <bean:message key="configproblems.validation.description"/>
            </td>
  </tr>



  <tr valign="top"> 
            <td class="table-text"  nowrap valign="top" scope="row"><label  for="thisid"><bean:message key="configproblems.cross.label"/></label></td>
            <td class="table-text" nowrap valign="top"> 
            
        <% Boolean crossValidation = new Boolean(uBean.getProperty("System/validation", "performCrossValidation", "false")); %>
            <input type="checkbox" name="enable" value="Y" <%= (crossValidation.booleanValue()) ? "checked" : ""%> <%=(val) ? "disabled" : ""%>>
            <bean:message key="configproblems.validation.crossMessage"/>
            </td>
            <td class="table-text" valign="top">  
      <ibmcommon:info image="help.additional.information.image" topic="ProblemDetermination.validation.policy.crossValidation"/>
            <bean:message key="configproblems.validation.crossDescription"/>
            </td>
  </tr>
    <tr> 
      <th valign="top" class="button-section" colspan="3"> 
        <input type="submit" name="submit2" value='<bean:message key="button.apply"/>' class="buttons" id="navigation" <%=(val) ? "disabled" : ""%>>
        <input type="reset" name="reset" value='<bean:message key="button.reset"/>' class="buttons" id="navigation" <%=(val) ? "disabled" : ""%>>
      </th>
    </tr>
  
</form>  
</table>  
