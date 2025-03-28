<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.websphere.management.application.*,com.ibm.websphere.management.application.client.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
      <td class="wizard-step-text">
          <bean:write name="<%=actionForm%>" property="goalString" />
          <br>
      </td>
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr>
    <th class="column-head-name" scope="col"> <bean:message key="appinstall.ejbDeployment.options" />
      <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>
    <th class="column-head-name" scope="col"><bean:message key="appinstall.enable" /></th>
  </tr>
  <html:hidden property="column1" value=""/>


  <%
     AppDeploymentTaskMessages appM = new  AppDeploymentTaskMessages(request.getLocale());
     String dbType = appM.getColumnName(AppConstants.APPDEPL_DEPLOYEJB_DBTYPE_OPTION, "EJBDeployOptions");

    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();

    for (int i=1; i < column0.length; i++) {
  %>

  <tr>
    <td class="table-text" ><%= (String)column0[i]%> </td>
    <td class="table-text" >
	   <% if (column0[i].equals(dbType)) { %>
           <html:select property="column1" value="<%=(String)column1[i]%>">
              <bean:define id="globalForm" name="globalForm" scope="session" type="GlobalForm"/>
			  <logic:iterate id="dBaseType" name="globalForm" property="databaseTypes">
                            <html:option value="<%= (String) dBaseType %>" >
			        <%= (String) dBaseType %>
			    </html:option>
			  </logic:iterate>
            </html:select>
	   <% } else  { %>
           <html:text property="column1" size="25" value="<%=(String)column1[i]%>" />
	   <% }  %>
    </td>
  </tr>

  <%
	}
  %>

</table>