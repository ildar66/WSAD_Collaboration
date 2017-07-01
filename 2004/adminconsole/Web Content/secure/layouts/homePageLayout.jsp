<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<tiles:useAttribute name="homePageList" classname="java.util.List" />
<ibmcommon:detectLocale/>

<html:html locale="true">
<HEAD>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>


</HEAD>


<body class="content">

<jsp:include page="/secure/layouts/content_accessibility.jsp" flush="true"/>

<%
Iterator i = homePageList.iterator();
while( i.hasNext() ) {
	org.apache.struts.tiles.beans.SimpleMenuItem item = (org.apache.struts.tiles.beans.SimpleMenuItem)i.next();
%>

<tiles:insert name="<%=item.getLink()%>" flush="true" />

<%
}
%>

    
</body>
</html:html>
