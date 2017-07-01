<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page isErrorPage="true"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<ibmcommon:detectLocale/>
<html:html locale="true">
<head>
<title><bean:message key="errorpage.title"/></title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
</head>

<BODY>

<H2><bean:message key="errorpage.title"/></H2>

<H4><bean:message key="errorpage.request"/> <%= request.getAttribute("javax.servlet.error.request_uri") %></H4>
<B><bean:message key="errorpage.message"/> </B><%=exception%>
<BR>
<B><bean:message key="errorpage.stacktrace"/> </B>
<PRE>
<%exception.printStackTrace(new java.io.PrintWriter(out));%>
</PRE>
</BODY>
</html:html>




