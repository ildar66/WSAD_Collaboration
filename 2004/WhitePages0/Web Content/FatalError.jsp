<!--**************************************************************-->
<!--                                                              -->
<!-- This code is generated automatically by IBM WebSphere Studio -->
<!--                                                              -->
<!--**************************************************************-->
<html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<head>
<%@ page 
	language="java"
	contentType="text/html"
%>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<h1>Error Report</h1>

	<p>The following error has occured in the application </p>

	<jsp:directive.page isErrorPage="true" />

	<font color="red">
	<%=
		request.getSession().getAttribute("FatalErrorMessage").toString()
	%>
	</font>
	<BR>
	<font color="blue">
		in
		<%=
			request.getSession().getAttribute("ErrorInBean").toString()
		%>
	</font>

	<p>Please contact your local support</p>
</body>
</html>
