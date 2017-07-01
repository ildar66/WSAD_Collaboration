<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<ibmcommon:detectLocale/>

<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=*">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="login.title"/></title>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

</head>

<body topmargin="0" leftmargin="0" marginwidth="0" marginheight="0" >


 <table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%"  align="center" >
    <tr> 
      <td align="center">
        <p class="validation-error"><img src="<%= request.getContextPath() + "/" %>images/Error.gif" width="16" height="16"><bean:message key="help.notInstalled.title"/></p>
      </td>
    </tr>
 </table>
</body>
</html:html>

