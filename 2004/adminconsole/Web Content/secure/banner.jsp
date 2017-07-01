<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="com.ibm.ws.console.core.bean.*" %>
<%@ page import="com.ibm.ws.console.core.*" %>


<ibmcommon:detectLocale/>

<%
   //obtain show/hide banner preference from preference.xml for this user
   //the banner property is used to show whether secure/tiles/banner.jsp will
   //be visible (see reference to this file below)
   
   UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(Constants.USER_PREFS);
   String banner = uBean.getProperty("System/workspace#bannerShow", "true");
%>


<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<STYLE TYPE="text/css">
a {  text-decoration: none}
a:hover {  text-decoration: underline}
</STYLE>

</HEAD>

<body class="header" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%if (banner.equals("true")) { %>
    <tiles:insert page="/secure/tiles/banner.jsp"/>
<%}%>

<tiles:insert definition="console.menubar.main" flush="true" />
</body>
</html:html>
