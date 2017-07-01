<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="java.util.*,com.ibm.ws.console.appmanagement.*,com.ibm.ws.console.appmanagement.form.*"%>
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
    <th class="column-head-name" scope="col"> <bean:message key="appinstall.summary.options"/>
      <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>
    <th class="column-head-name" scope="col"><bean:message key="appinstall.summary.values"/></th>
  </tr>

<%
    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
	Hashtable options = thisForm.getOptions();
	Enumeration e = options.keys();
	while (e.hasMoreElements()) {
		String key = (String) e.nextElement();
		String option = (String) options.get(key);
%>

  <tr>
    <td class="table-text" headers="header1"><%= key%> </td>
    <td class="table-text" headers="header1"><%= option%> </td>
  </tr>

  <%
	}
  %>
</table>

