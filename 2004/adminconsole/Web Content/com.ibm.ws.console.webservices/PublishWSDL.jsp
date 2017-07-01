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
  <html:hidden property="column0" value=""/>

  <%
    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();

    for (int i=0; i < column0.length; i++) {
        System.out.println("column0[" + i + "] : " + column0[i]);
  	if ( i == 0 ) {
%>

  <tr>
    <th class="column-head-name" scope="col"><%= (String)column0[i]%> </th>
  </tr>
<%	} else  {%>

  <tr>
     <td class="table-text" >
       <html:text property="column0" size="25" value="<%=(String)column0[i]%>" />
    </td>
  </tr>

  <%
	 }
	}
  %>

</table>

