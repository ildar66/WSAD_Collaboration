<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.core.form.*, com.ibm.ws.console.core.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<html:html locale="true">
<head>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>

<body>
<html:form action="browseRemoteNodes.do" name="BrowseRemoteForm" type="com.ibm.ws.console.core.form.BrowseRemoteForm">

<H1 id="bread-crumb"><bean:message key="<%=titleKey%>"/></H1>
  <p class="instruction-text"><bean:message key="<%=descKey%>"/></P>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td class="framing-table">
		<table border="0" cellpadding="0" cellspacing="0" width="100%" summary="List framing table">
         <tbody> 
    	  <tr> 
      		<td  class="framing-table">
        	  <table cols=2 width="100%" cellpadding="3" cellspacing="1">
          		<tr>
				  <td class="column-head-name" colspan="2">
				      <img src="images/onepix.gif" width="1" height="16">
					   <bean:message key="browse.contents" />&nbsp;<bean:write name="BrowseRemoteForm" property="selectedItem"/>
				  </td>
				</tr>

			   <logic:iterate id="node" name="BrowseRemoteForm" property="nodesList">
			   <% String link = "browseRemoteNodes.do?nodeName=" + ConfigFileHelper.urlEncode((String)node);%>
          		<tr>
				  <td class="table-text">
				      <img src="images/closed_folder.gif" width="16" height="16" align="absmiddle" border="0">
				      &nbsp;
                      <a href="<%= link   %>"><%=(String) node%></a>
				  </td>
				</tr>
				</logic:iterate>

        	  </table>
			</td>
		  </tr>
         </tbody> 
  		</table>
       </td>
	</tr>
  </table>
</html:form>

</body>
</html:html>
