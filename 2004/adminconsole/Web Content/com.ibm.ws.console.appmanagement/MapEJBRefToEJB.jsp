<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
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
  <html:hidden property="column5" value=""/>

<%
    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();
    String[] column4 = thisForm.getColumn4();
    String[] column5 = thisForm.getColumn5();

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
    <th class="column-head-name" scope="col"><%= (String)column4[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column5[i]%> </th>
  </tr>
<%	} else  {%>

  <tr>
    <td class="table-text" ><%= (String)column0[i]%> </td>
    <td class="table-text" ><%= (String)column1[i]%> </td>
    <td class="table-text" ><%= (String)column2[i]%> </td>
    <td class="table-text" ><%= (String)column3[i]%> </td>
    <td class="table-text" ><%= (String)column4[i]%> </td>
    <td class="table-text" >
       <html:text property="column5" size="25" value="<%=(String)column5[i]%>" />
    </td>
  </tr>

  <% } } %>

</table>

