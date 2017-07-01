<!--**************************************************************-->
<!--                                                              -->
<!-- This code is generated automatically by IBM WebSphere Studio -->
<!--                                                              -->
<!--**************************************************************-->
<html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<head>
<%@ page 
	language="java"
	contentType="text/html"
%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page isErrorPage="true" import="java.util.*, org.apache.commons.beanutils.*" %>
</head>
<body>

	<h1>Error Report</h1>
	<font color="red">
		<br>Fatal error has occured in the application<br>
	</font>
	<p>Please contact your local support</p>
	<p>Request context (for support) : </p>
	<table border="1" width="100%">
		<tr><th>Attribute</th><th>Value</th></tr>
		<%
		Enumeration rNames = request.getAttributeNames();
		while (rNames.hasMoreElements()) {
			String name = (String) rNames.nextElement();
		%>
		<tr><td><%=name%></td><td><%=request.getAttribute(name)%></td></tr>
		<%
		}
		%>
	</table>
</body>
</htmll>


