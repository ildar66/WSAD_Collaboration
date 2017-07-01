<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.workspace.query.*,com.ibm.ws.sm.workspace.RepositoryContext, com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
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

<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>" enctype="multipart/form-data">
<H1 id="bread-crumb"> 
<% if (session.getAttribute(Constants.APPMANAGEMENT_INSTALL_TYPE).equals("redeployApplication")) { %>
   <bean:message key="appinstall.update.title"/>
<% } else { %>
  <bean:message key="<%=titleKey%>"/>
<% } %>
</H1>
<p class="instruction-text"> 
<bean:message key="<%=descKey%>"/>
</p>


<table border="0" class="wizard-table" cellpadding="8" cellspacing="0" width="100%" summary="List table">
      <tbody>
      <tr>
        <td class="wizard-step-text" >
      <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
         <tr valign="top">
          <td class="table-text"nowrap >
		      <label for="thepath"><bean:message key="appinstall.upload.path" /></label>:
          </td>
          <td  class="table-text" > 
            <fieldset id="thepath">
            <legend><bean:message key="appinstall.upload.browselocal" /></legend>
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr valign="baseline">
                <td class="table-text" width="1%">
                  <html:radio property="radioButton" value="local"/>
                </td>
                <td class="table-text" nowrap><bean:message key="appinstall.upload.localpath" /><br>
                  <html:file property="localFilepath" size="25"/>
                </td>
              </tr>
              <tr valign="baseline">
                <td class="table-text" width="1%">
                  <html:radio property="radioButton" value="server"/>
                </td>
                <td class="table-text" nowrap><bean:message key="appinstall.upload.serverpath" /><br>
                  <html:text property="remoteFilepath" size="25"/>
<%   WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();
        RepositoryContext context = (RepositoryContext) session.getAttribute("currentCellContext");
          if (!util.isStandAloneCell(context) ) { %>
		  <html:submit property="installAction">
                      <bean:message key="appmanagement.button.browse"/>
                  </html:submit>
<% } %>
                </td>
              </tr>
            </table>
            <fieldset>
          </td>
          <td class="table-text" valign="top" width="70%"> 
             <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.path"/>
             <bean:message key="appmanagement.upload.path" />
		  </td>
        </tr>

        <tr valign="top">
          <td  class="table-text" nowrap >
		     <label for="contextRoot"><bean:message key="appinstall.upload.context.root" /></label>
		  </td>
          <td width="40%" class="table-text" header="specify_file stand_alone_module" >
            <bean:message key="appinstall.upload.standalone.modules" /><br>
            <html:text property="contextRoot" size="25" styleId="contextRoot"/>
          </td>
          <td class="table-text" valign="top" width="70%"> 
             <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.contextRoot"/>
             <bean:message key="appmanagement.upload.contextRoot" />
		  </td>
        </tr>
      </table>
      
      </td>
   </tr>

   <tr>
      <td class="wizard-button-section" >
        <html:submit property="nextAction" styleId="navigation" styleClass="buttons">
          <bean:message key="appmanagement.button.next"/>
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
