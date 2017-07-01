<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="java.util.StringTokenizer, com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
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
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();
    String[] column4 = thisForm.getColumn4();
    String[] column5 = thisForm.getColumn5();

    for (int i=0; i < column2.length; i++) {
    if (i == 0) {
%>

  <tr>
    <th class="column-head-name" scope="col"><%= (String)column2[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column3[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column5[i]%> </th>
  </tr>
<%
    } else  {
        String backEndIds = column4[i];
        StringTokenizer strTok = new StringTokenizer(backEndIds, "+");
%>

  <tr>
    <td class="table-text" ><%= (String)column2[i]%> </td>
    <td class="table-text" ><%= (String)column3[i]%> </td>
    <td class="table-text" headers="header1">
      <html:select property="column5" value="<%=(String)column5[i]%>" >
<%
       while (strTok.hasMoreTokens()) { 
           String backendId = strTok.nextToken().trim(); 
           backendId = backendId.trim();
           if (!backendId.equals("")) {
%>
               <html:option value="<%=backendId%>"><%=backendId%></html:option>
<%
           } else {
%>
               <html:option value="<%=backendId%>"><bean:message key="none.text"/></html:option>
<%     
           }
       }
%>
      </html:select>
    </td>
  </tr>

  <%
    }
    }
  %>

</table>

