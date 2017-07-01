<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.websphere.product.*,java.util.*,com.ibm.ws.console.resources.j2c.*,com.ibm.ws.sm.workspace.RepositoryContext,com.ibm.ws.console.core.Constants,com.ibm.ws.workspace.query.*"%>
<%@ page import="com.ibm.ws.console.core.mbean.*" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>



<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="stepDescKey" classname="java.lang.String" />
<tiles:useAttribute name="stepHelpDescKey" classname="java.lang.String" />
<tiles:useAttribute name="browseDescKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<%
    RepositoryContext cellContext = (RepositoryContext) session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
    WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();
    Iterator iter = util.getNodeNames(cellContext).iterator();
    Vector nodeList = new Vector ();
    String nodeName = null;
    
    DistributedMBeanHelper helper = DistributedMBeanHelper.getDistributedMBeanHelper(); //Defect 167636
    String dmgrNodeName = helper.getDeploymentManagerNodeName(); //Defect 167636
                 
    while (iter.hasNext()) {
        nodeName = (String) iter.next();
        if (!dmgrNodeName.equals(nodeName)) {  //Defect 167636
        nodeList.addElement(nodeName);
        }
    }   

%>
<ibmcommon:detectLocale/>


<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

</HEAD>

<BODY>
<ibmcommon:errors/>

<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>" enctype="multipart/form-data">

<H1 id="bread-crumb"> 
<bean:message key="<%=titleKey%>"/>
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
		        <label for="thepath"><bean:message key="label.path"/></label>
            </td>
            <td  class="table-text" > 
              <fieldset id="thepath">
              <legend><bean:message key="<%=browseDescKey%>"/></legend>
              <table width="100%" border="0" cellspacing="0" cellpadding="3">
                <tr valign="baseline">
                  <td class="table-text" width="1%">
                    <html:radio property="radioButton" value="local"/>
                  </td>
                  <td class="table-text" nowrap><bean:message key="label.local.path"/><br>
                    <html:file property="localFilepath" size="25"/>
                  </td>
                </tr>
                <tr valign="baseline">
                  <td class="table-text" width="1%">
                    <html:radio property="radioButton" value="server"/>
                  </td>
                  <td class="table-text" nowrap><bean:message key="label.server.path"/><br>
                    <html:text property="remoteFilepath" size="25"/>

<%   
      boolean isND = false;
      try {
          if (util.isStandAloneCell(cellContext)) {
              isND = false;
          }
          else {
              isND = true;
          }
      }
      catch (Exception e) {
          isND = true;
      }
     if (isND){ 
%>
		            <html:submit property="installAction">
                      <bean:message key="button.browse"/>
                   </html:submit>
<% 
     } 
%>

                  </td>
                </tr>
              </table>

            </td>
            <td class="table-text" valign="top" width="70%"> 
		        <bean:message key="<%=stepHelpDescKey%>" />
		      </td>
          </tr>

          <tr valign="top">
            <td class="table-text" nowrap>
              <label for="thescope"><bean:message key="Resources.scope.displayName"/></label>
            </td>

            <td  class="table-text" > 
              <bean:message key="J2CResourceAdapter.upload.select.node"/>
              <html:select property="nodeName" size="1">
<%
    for (int i = 0; i < nodeList.size(); i++)
    { 
        String val = (String) nodeList.elementAt(i);
%>

                      <html:option value="<%=val%>"><%=val%></html:option>

<%
    }
%>
	                 </html:select>

            </td>
            <td class="table-text" valign="top" width="70%">
              <bean:message key="J2CResourceAdapter.upload.nodeName.description"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>

    <tr>
      <td class="wizard-button-section" >
        <html:submit property="nextAction" styleId="navigation" styleClass="buttons">
          <bean:message key="button.next"/>
        </html:submit>
        <html:cancel property="cancelAction" styleId="navigation" styleClass="buttons">
          <bean:message key="button.cancel"/>
        </html:cancel>
      </td>
    </tr>
  </tbody>   
</table>

</html:form>

</BODY>
</html:html>
