<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="java.util.ArrayList,com.ibm.ws.console.appmanagement.form.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<ibmcommon:setPluginInformation pluginIdentifier="com.ibm.ws.console.appmanagement" pluginContextRoot=""/>

<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
</HEAD>

<BODY class="content">
<ibmcommon:errors/>

<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">
<H1 id="bread-crumb"> 
   <bean:message key="<%=titleKey%>"/>
</H1>
<p class="instruction-text"> 
<bean:message key="<%=descKey%>"/>
</p>

  <bean:define id="warningsList" name="<%=actionForm%>" property="warnings" type="java.util.ArrayList"/>

<table class="wizard-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">

  <logic:iterate id="warning" name="warningsList">
    <tr>
      <td class="table-text" headers="header1"><%= warning%> </td>
    </tr>
  </logic:iterate>

  <tr>
      <td class="wizard-button-section" >
        <html:submit property="nextAction" styleId="navigation" styleClass="buttons">
          <bean:message key="appmanagement.button.continue"/>
        </html:submit>
        <html:cancel property="cancelAction" styleId="navigation" styleClass="buttons">
          <bean:message key="appmanagement.button.cancel"/>
        </html:cancel>
      </td>
   </tr>
   
</table>


</html:form>

</BODY>
</html:html>

