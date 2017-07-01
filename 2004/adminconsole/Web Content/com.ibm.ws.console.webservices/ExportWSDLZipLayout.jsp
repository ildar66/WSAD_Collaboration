<%-- @(#) 1.1 ws/code/webui.webservices/src/webservices/com.ibm.ws.console.webservices/ExportWSDLZipLayout.jsp, WAS.webui.webservices, ASV51X, trial0343.04 3/28/03 12:21:32 [11/4/03 16:54:10] --%>
<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 2002, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>
<%-- --%>
<%-- Change History: --%>
<%-- Date     UserId      Defect          Description --%>
<%-- ---------------------------------------------------------------------------- --%>
<%-- mm/dd/yy <cmvcid>    <track #>       <brief explanation> --%>

<%@ page import="java.util.*"%>
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
</HEAD>

<% String wsdlFileName = (String) session.getAttribute("com.ibm.ws.console.webservices.publish.action.GetUrlPrefixesAction.zipFileName");
%>

<BODY>
<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

<H1 id="bread-crumb"><bean:message key="<%=titleKey%>"/></H1>

<p class="instruction-text"><bean:message key="<%=descKey%>"/></p>
  
   <TABLE class="framing-table" BORDER=0 CELLPADDING="4" CELLSPACING="1" WIDTH="100%" SUMMARY="Content Table">
        <tr>
	  <TD VALIGN="TOP" CLASS="column-head">
            <bean:message key="<%=titleKey%>"/></TD>
        </tr>


        <tr>
          <td valign=top class="table-text" HEADER="header1"><label for="checkbox"><A HREF='secure/downloadFile/<%=wsdlFileName%>'> <%=wsdlFileName%></A></LABEL></td>
        </tr>

	
        <tr>
          <th class="button-section">
            <input type="submit" name="ok" value="<bean:message key="button.back"/>" class="buttons" id="navigation">
          </th>
        </tr>
		
      </TABLE>
	
</html:form>

</BODY>
</html:html>
