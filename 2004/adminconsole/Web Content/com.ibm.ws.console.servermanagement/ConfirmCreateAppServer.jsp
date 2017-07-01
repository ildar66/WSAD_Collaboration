<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.servermanagement.wizard.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<% CreateNewAppServerForm confirmForm = (CreateNewAppServerForm) session.getAttribute("ConfirmCreateAppServerForm"); %>

<% String serverName = confirmForm.getServerName();
    String nodeName = confirmForm.getSelectedNode();
  %>        

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">

  <tr valign="baseline" >
      <td class="wizard-step-text" width="100%" align="left"> 
          <bean:message key="appserver.confirmAppServer.msg1"/>
          <bean:message key="appserver.confirmAppServer.msg2"/>  
	  </td>
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table" class="framing-table">
   <tr> 
      <th class="column-head-name" scope="col">
          <bean:message key="appserver.confirmAppServer.msg3"/>
	  </th>
   </tr>
   <tr> 
      <td class="table-text"  valign="top">
        <bean:message key="appserver.confirmAppServer.msg4" arg0="<%=serverName%>" arg1="<%=nodeName%>"/>
	  </td>
   </tr>
   <tr> 
      <th class="column-head-name" scope="col">
      <bean:message key="appserver.confirmAppServer.msg7"/>
	  </th>
   </tr>
   <tr> 
      <td class="table-text"  valign="top"> 
         <bean:message key="appserver.confirmAppServer.msg8" arg0="<%=nodeName%>"/>
	  </td>
   </tr>
</table>
