<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java"%>
<%@ page import="com.ibm.ws.security.core.SecurityContext"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<ibmcommon:errors/>

<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>



<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">

</HEAD>


<BODY CLASS="content">

<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

<H1 id="bread-crumb">  
<bean:message key="<%=titleKey%>"/>
</H1>
<p class="instruction-text"> 
<bean:message key="<%=descKey%>"/>
</p>

<P>

<table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" >
<TBODY>
     <tr valign="top"> 
          <td class="table-text"  nowrap valign="top" scope="row">
	      <label  for="{attributeName}"> <bean:message key="add.node.connector.displayName"/></label>
		  </td>
          <td class="table-text" nowrap valign="top"> 
            <html:select property="connectorType" name="<%=actionForm%>" styleId="connectorType">
                      <html:option value="SOAP"><bean:message key="add.node.soap.displayName"/></html:option>
                      <html:option value="RMI"><bean:message key="add.node.rmi.displayName"/></html:option>
            </html:select>
                    &nbsp;
		  </td>
          <td class="table-text" valign="top"> 
	  <bean:message key="add.node.connector.description"/>
	  </td>
     </tr>
     
     <tr valign="top"> 
          <td class="table-text"  nowrap valign="top" scope="row">
	      <label  for="{attributeName}"> <bean:message key="add.node.host.displayName"/></label>
		  </td>
          <td class="table-text" nowrap valign="top"> 
              <html:text property="host" size="30" />
                    &nbsp;
		  </td>
          <td class="table-text" valign="top"> 
	  <bean:message key="add.node.host.description"/>
		  </td>
     </tr>
     
     <tr valign="top"> 
          <td class="table-text"  scope="row"><label  for="{attributeName}">
	  <label for="{attributeName}"><bean:message key="add.node.port.displayName"/></label>
		  </td>
          <td class="table-text"><label> 
              <html:text property="port" size="6" value="8880"/>
		  </td>
          <td class="table-text" valign="top">
	       <bean:message key="add.node.port.description" />	  
	  </td>
     </tr>

<%    if (SecurityContext.isSecurityEnabled()) { %>
     <tr valign="top"> 
          <td class="table-text"  nowrap valign="top" scope="row">
	      <label  for="{attributeName}"> <bean:message key="add.node.user.displayName"/></label>
		  </td>
          <td class="table-text" nowrap valign="top"> 
              <html:text property="user" size="30" />
                    &nbsp;
		  </td>
          <td class="table-text" valign="top"> 
	  <bean:message key="add.node.user.description"/>
		  </td>
     </tr>
     
     <tr valign="top"> 
          <td class="table-text"  scope="row"><label  for="{attributeName}">
	  <label for="{attributeName}"><bean:message key="add.node.password.displayName"/></label>
		  </td>
          <td class="table-text"><label> 
              <input type="password" class="short" id="password" name="password" size="30"/>	  
		  </td>
          <td class="table-text" valign="top">
	       <bean:message key="add.node.password.description" />	  
	  </td>
     </tr>
	 
	 <TR VALIGN="TOP">
          <td class="table-text"  scope="row">
		  	  <label for="{attributeName}"><bean:message key="add.node.configURL.displayName"/></label>
		  </td>
          <td class="table-text">
		  <html:text property="configURL" size="50" value="file:/${USER_INSTALL_ROOT}/properties/sas.client.props"/>
		  </td>
          <td class="table-text" valign="top">
	       <bean:message key="add.node.configURL.description"/>	  
    	  </td>	 
	 </TR>

<% } %>     
     <tr valign="top"> 
          <td class="table-text"  scope="row"><label  for="{attributeName}">
	  <bean:message key="add.node.include.apps"/></label>
		  </td>
          <td class="table-text"><label> 
              <html:checkbox property="includeApps"/>
	  </td>
          <td class="table-text" valign="top">
	       <bean:message key="add.node.include.apps.description" />	  
	  </td>
     </tr>     
     <tr>
        <th class="button-section" colspan="3">
          <html:submit property="action" styleId="navigation" styleClass="buttons">
              <bean:message key="button.ok"/>
          </html:submit>
          <html:cancel property="action" styleId="navigation" styleClass="buttons">
            <bean:message key="button.cancel"/>
          </html:cancel>     
	</th>
     </TR>
     </TBODY>
</table>

</BODY>
</html:form>
</html:html>
