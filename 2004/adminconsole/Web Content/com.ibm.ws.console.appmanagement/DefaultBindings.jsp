<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="java.util.Vector, com.ibm.websphere.management.application.*,com.ibm.websphere.management.application.client.*, com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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

<jsp:include page="/secure/layouts/browser_detection.jsp" flush="true"/>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">

</HEAD>

<BODY class="content">
<ibmcommon:errors/>
<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>"  enctype="multipart/form-data">
<H1 id="bread-crumb"> 
<% if (session.getAttribute(Constants.APPMANAGEMENT_INSTALL_TYPE).equals("redeployApplication")) { %>
   <bean:message key="appinstall.update.title"/>
<% } else { %>
  <bean:message key="<%=titleKey%>"/>
<% } %>
</H1>
<p class="instruction-text"> 
<bean:message key="<%=descKey%>"/>
 <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.genDefBindings"/>
</p>


<%
    Vector validBindings = (Vector) session.getAttribute(Constants.APPMANAGEMENT_DFLTBNDNGS_VECTOR);
%>


<table border="0" class="wizard-table" cellpadding="8" cellspacing="0" width="100%" summary="List table">
<% if (session.getAttribute(Constants.APPMANAGEMENT_INSTALL_TYPE).equals("redeployApplication")) { %>
  <tr valign="baseline" >
      <td class="wizard-step-text">
          <bean:message key="appinstall.specify.binding.label" />
          <html:select property="updateBindings">
             <option value="merge"><bean:message key="appinstall.specify.binding.merge" /></option>
             <option value="keep_new"><bean:message key="appinstall.specify.binding.new" /></option>
             <option value="keep_old"><bean:message key="appinstall.specify.binding.old" /></option>
          </html:select>
          <br>
      </td>
  </tr>
  <tr>
<% } else {%>
	<tr valign="baseline" >
<% }  %>

     <td class="wizard-step-expanded">
                    <html:checkbox  property="generateBindings"/>
                  <bean:message key="appinstall.dflt.generate.default.label" /> 
<% if (session.getAttribute(Constants.APPMANAGEMENT_INSTALL_TYPE).equals("redeployApplication")) { %>
				  &nbsp;<bean:message key="appinstall.binding.applicable" />
<% } %>
                  <br><br>
	    <table border="0" cellpadding="3" cellspacing="1" width="95%" summary="List table">
		<% if (validBindings.contains("defaultbinding.ejbjndi.prefix") ){ %>
        <tr valign="top">
          <td class="table-text"   nowrap ><label for="prefix"><bean:message key="appinstall.dflt.prefixes.label" /></label>
          </td>
          <td  class="table-text" header="stand_alone_module" ><fieldset id="prefix">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr>
                <td class="table-text" width="1%">
                  <html:radio property="prefixButton" value="nouniqueprefix"/><bean:message key="appinstall.dflt.no.prefixes" />
                </td>
              </tr>
              <tr>
                <td class="table-text" width="1%">
                  <html:radio property="prefixButton" value="uniqueprefix"/><bean:message key="appinstall.dflt.specify.prefix" />
                </td>
              </tr>
              <tr>
                <td class="complex-property">
                  <html:text property="prefixText" size="25"/>
                </td>
              </tr>
            </table>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.prefixes"/>
            <bean:message key="appmanagement.upload.prefixes" /> 
          </td>
        </tr>
		<% } %>
		<% if (validBindings.contains("defaultbinding.force") ){ %>
        <tr valign="top">
          <td class="table-text" nowrap ><label for="Override"><bean:message key="appinstall.dflt.override.label" /></label>
          </td>
          <td  class="table-text"  >
          <fieldset id="Override">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr>
                <td class="table-text">                          
                  <html:radio property="overrideButton" value="nooverride"/><bean:message key="appinstall.dflt.no.override" />
                </td>
              </tr>
              <tr>
                <td class="table-text">
                  <html:radio property="overrideButton" value="override"/><bean:message key="appinstall.dflt.override" />
                </td>
              </tr>
            </table>
            </fieldset>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.override"/>
            <bean:message key="appmanagement.upload.override" /> 
          </td>
        </tr>
		<% } %>
		<% if ( validBindings.contains("defaultbinding.datasource.jndi") ){ %>
        <tr valign="top">
          <td class="table-text" nowrap ><label for="ejbbindings"><bean:message key="appinstall.dflt.ejbcmp.label" /></label></td>
          <td  class="table-text">
          <fieldset id="ejbbindings">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr>
                <td class="table-text" colspan="2">
                  <html:radio property="bindCMPButton" value="nodefault"/><bean:message key="appinstall.dflt.no.cmp" />
                </td>
              </tr>
              <tr>
                <td class="table-text" colspan="2">
                  <html:radio property="bindCMPButton" value="default"/><bean:message key="appinstall.dflt.cmp" />
                </td>
              <tr>
                <td class="complex-property" nowrap><bean:message key="appinstall.jndi.name" /></td>
                <td class="table-text">
                       <html:text property="jndiName" size="25"/>
              </td>                             
              </tr>
                    <tr>
                      <td class="complex-property" nowrap><bean:message key="appinstall.user.name" />:</td>
                      <td class="table-text">
                        <html:text property="userName" size="25"/>
                        </td>
                    </tr>
                    <tr>
                      <td class="complex-property" nowrap><bean:message key="appinstall.password" />:</td>
                      <td class="table-text">
                        <html:password  property="password" size="25"/>
                        
                      </td>
                    </tr>
                    <tr>
                      <td class="complex-property" nowrap><bean:message key="appinstall.verify.password" />:</td>
                      <td class="table-text">
                        <html:password  property="password2" size="25"/>
                      </td>
                    </tr>
            </table>
            </fieldset>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.ejbCmpBindings"/>
            <bean:message key="appmanagement.upload.ejbCmpBindings" /> 
          </td>
        </tr>
		<% } %>
		<% if ( validBindings.contains("defaultbinding.cf.jndi") ){ %>
        <tr valign="top">
          <td class="table-text" nowrap ><label for="connfactory"><bean:message key="appinstall.dflt.cfbinding.label" /></label>: 
          </td>
          <td  class="table-text"  >
          <fieldset id="connfactory">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr>
                <td class="table-text" nowrap colspan="2">
                  <html:radio property="bindConnectionButton" value="nodefault"/><bean:message key="appinstall.dflt.no.cfbinding" />
                </td>
              </tr>
              <tr>
                <td class="table-text" nowrap colspan="2">
                  <html:radio property="bindConnectionButton" value="default"/><bean:message key="appinstall.dflt.cfbinding" />
                </td>
              <tr>
                <td class="complex-property" nowrap><bean:message key="appinstall.jndi.name" /></td>
                <td class="table-text">
                       <html:text property="jndiName2" size="25"/>
                        
                </td>
              </tr>
<%
     AppDeploymentTaskMessages appM = new  AppDeploymentTaskMessages(request.getLocale());
     String containerValue = AppConstants.APPDEPL_CMPBINDING_RESAUTHTYPE_CONTAINER;
     String perConnFactoryValue = AppConstants.APPDEPL_CMPBINDING_RESAUTHTYPE_PER_CONNECTION_FACTORY;
     String container = appM.getMessage(containerValue);
     String perConnFactory = appM.getMessage(perConnFactoryValue);
%>
              <tr>
                <td class="complex-property" nowrap><bean:message key="appinstall.resource.authorization" /></td>
                  <td class="table-text">
                    <html:select property="resAuth">
			<html:option value="PerConnFact">
			  <%= perConnFactory %>
                        </html:option>
			<html:option value="Container">
			  <%= container %>
                        </html:option>
		    </html:select>
                </td>
              </tr>
            </table>
            </fieldset>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.connFacBindings"/>
            <bean:message key="appmanagement.upload.connFacBindings" /> 
          </td>
        </tr>
		<% } %>
		<% if ( validBindings.contains("defaultbinding.virtual.host") ){ %>
        <tr valign="top">
          <td class="table-text"   nowrap ><label for="vHost"><bean:message key="appinstall.dflt.virtualhost.label" /></label>
          </td>
          <td  class="table-text" >
          <fieldset id="vHost">
            <table width="100%" border="0" cellspacing="0" cellpadding="3">
              <tr>
                <td class="table-text" width="1%">
                  <html:radio property="bindVirtualHostButton" value="nodefault"/><bean:message key="appinstall.dflt.no.virtualhost" /> 
                </td>
              </tr>
              <tr>
                <td class="table-text" width="1%">
                  <html:radio property="bindVirtualHostButton" value="default"/><bean:message key="appinstall.dflt.virtualhost" />
                </td>
              </tr>
              <tr>
                <td class="complex-property">
                  <html:text property="virtualHost" size="25"/>
                </td>
              </tr>
            </table>
            </fieldset>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.virtualHost"/>
            <bean:message key="appmanagement.upload.virtualHost" /> 
          </td>
        </tr>
		<% } %>
        <tr valign="top">
          <td class="table-text" nowrap ><label for="bindingsFile"><bean:message key="appinstall.dflt.specific.label" /></label>: 
            </td>
          <td  class="table-text" >
                  <html:file property="bindingsFile" size="25" styleId="bindingsFile"/>
          </td>
          <td class="table-text" valign="top" width="50%">
            <ibmcommon:info image="help.additional.information.image" topic="appmanagement.upload.specificBindingsFile"/>
            <bean:message key="appmanagement.upload.specificBindingsFile" /> 
          </td>
        </tr>
        
      </table>
     </td>
   </tr>

   <tr>
     <td class="wizard-button-section" >
       <html:submit property="previousAction" styleId="navigation" styleClass="buttons">
         <bean:message key="appmanagement.button.previous"/>
       </html:submit>
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
