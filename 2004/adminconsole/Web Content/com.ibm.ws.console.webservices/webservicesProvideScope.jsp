<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%-- XXX my webserviceProvideScope.jsp --%>

<%@ page language="java" import="com.ibm.ws.console.webservices.*"%>
<%@ page import="com.ibm.ws.console.webservices.editbind.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />


<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">

<%
    String applicationValue = WSWBConstants.SCOPE_APPLICATION;
    String requestValue = WSWBConstants.SCOPE_REQUEST;
    String sessionValue = WSWBConstants.SCOPE_SESSION;

    ProvideScopeDetailForm thisForm = (ProvideScopeDetailForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();

    for (int i=0; i < column0.length; i++) {
		if (i == 0) {
%>

  <tr>
    <th class="column-head-name" scope="col"><%= (String)column0[i]%> 
     <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>
    <th class="column-head-name" scope="col"><%= (String)column1[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column2[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column3[i]%> </th>
    <html:hidden property="column3" value="<%= (String)column3[i]%>" />
  </tr>
<%	} else  {%>

  <tr>
    <td class="table-text" ><%= (String)column0[i]%> </td>
    <td class="table-text" ><%= (String)column1[i]%> </td>
    <td class="table-text" ><%= (String)column2[i]%> </td>
    <td class="table-text" >
        <html:select property="column3"  value="<%=(String)column3[i]%>">
            <html:option value="<%=applicationValue%>" >
                <%= applicationValue %>
            </html:option>
            <html:option value="<%=requestValue%>" >
                <%= requestValue %>
            </html:option>
            <html:option value="<%=sessionValue%>" >
                <%= sessionValue %>
            </html:option>
        </html:select>
    </td>
  </tr>

  <%
          }
        }
        if (column0.length == 1) {
  %>
    <tr>
      <td class="table-text" ><bean:message key="PersistenceWebSvc.none"/></td>
      <td class="table-text"> </td>
      <td class="table-text"> </td>
      <td class="table-text"> </td>
    </tr>
  <%
        }
  %>

</table>
