<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.ws.console.core.item.ActionSetItem,com.ibm.ws.security.core.SecurityContext"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="configStep" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />



<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

    <input type="hidden" name="currentStep" value="this">
    <table border="0" cellpadding="8" cellspacing="0" width="100%" summary="Multiple Step Process Table">
    <TBODY>
      <tr>
        <td class="wizard-step-expanded">   
          <tiles:insert page="<%=configStep%>" flush="true" >
             <tiles:put name="actionForm" beanName="actionForm" beanScope="page"/>
          </tiles:insert>
        </td>
      </tr>
      <tr>
<%  
  boolean showItem = true;
  if (SecurityContext.isSecurityEnabled()) {
      String[] roles = new String[]{"administrator","configurator"};
      showItem = false;
      for (int idx = 0; idx < roles.length; idx++) {
      if (request.isUserInRole(roles[idx])) {
          showItem = true;
          break;
      }
      }
  }      

  if ( showItem ) {%>

        <td class="wizard-button-section">
          <html:submit property="installAction" styleId="navigation" styleClass="buttons">  
            <bean:message key="button.ok"/>
          </html:submit>
          <html:cancel property="installAction" styleId="navigation" styleClass="buttons">
            <bean:message key="button.cancel"/>
          </html:cancel>
        </td>
    <% } %>
      </tr>
      </TBODY>      
    </table>
</html:form>

