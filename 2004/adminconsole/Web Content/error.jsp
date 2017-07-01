<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="com.ibm.websphere.servlet.error.ServletErrorReport,java.io.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<ibmcommon:detectLocale/>
<!--
   Simple error page for reporting application errors.
   This error page is called when a servlet throws an Exception, or by calling
   response.sendError().  Error pages can use the request-scoped bean named
   "ErrorReport" to get more information about the error.
--->

<jsp:useBean id="errorForm" scope="session" class="com.ibm.ws.console.core.form.ErrorForm"/>

<% 
	if (pageContext.getAttribute("ErrorReport", PageContext.REQUEST_SCOPE) != null) {
		ServletErrorReport errorReport = (ServletErrorReport)pageContext.getAttribute("ErrorReport", PageContext.REQUEST_SCOPE);
		errorForm.setErrorCode(errorReport.getErrorCode());
		errorForm.setRequestUrl((String)request.getAttribute("javax.servlet.error.request_uri"));
		if (errorReport.getRootCause() != null) {
		    StringWriter sw = new StringWriter();
        	PrintWriter pw = new PrintWriter(sw);
        	if (errorReport.getRootCause() instanceof ServletException) {
        		ServletException se = (ServletException)errorReport.getRootCause();
        		if (se.getMessage() != null)
	      			errorForm.setMessage(se.getMessage());
	      		else 
	      			errorForm.setMessage(errorReport.getMessage());
        		if (se.getRootCause() != null) {
					se.getRootCause().printStackTrace(pw);        			
        		}
        		else {
        			se.printStackTrace(pw);
        		}
        	}
        	else {
		      	errorReport.getRootCause().printStackTrace(pw);
				errorForm.setMessage(errorReport.getMessage());
        	}
        	pw.flush();
			errorForm.setStackTrace(sw.toString());
		}
		else {
			errorForm.setMessage(errorReport.getMessage());
			errorForm.setStackTrace(errorReport.getStackTrace().toString());
		}
	}
%>
<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="0">
<title><bean:message key="errorpage.title"/> 
		 <bean:write name="errorForm" property="errorCode"/>
</title>
<script language="JavaScript" src="<%= request.getContextPath() + "/" %>scripts/menu_functions.js"></script>
<script language="JavaScript">
document.write("<link rel='stylesheet' href='<%= request.getContextPath() + "/" %>css/was_style_common.css'>");
if (isNav4) {
document.write("<link rel='stylesheet' href='<%= request.getContextPath() + "/" %>css/was_style_ns.css'>")
}
if (isIE) {
document.write("<link rel='stylesheet' href='<%= request.getContextPath() + "/" %>css/was_style_ie.css'>")
}
</script>
</head>


<body class="content">

<html:form action="error.do" name="errorForm" method="POST" type="com.ibm.ws.console.core.form.ErrorForm">

<p class="bread-crumb"><bean:message key="errorpage.title"/> <bean:write name="errorForm" property="errorCode"/></p>
<p class="table-text"><bean:message key="errorpage.request"/> <bean:write name="errorForm" property="requestUrl"/></p>
<p class="table-text"><bean:message key="errorpage.message"/> <bean:write name="errorForm" property="message"/></p>

<logic:equal name="errorForm" property="showStackTrace" value="true">
<html:submit styleClass="buttons">
<bean:message key="button.hidedetails"/>
</html:submit><br>
<html:textarea name="errorForm" property="stackTrace" cols="90" rows="20"/>
</logic:equal>

<logic:equal name="errorForm" property="showStackTrace" value="false">
<html:submit styleClass="buttons">
<bean:message key="button.showdetails"/>
</html:submit>
</logic:equal>

</html:form>

</body>
</html:html>
