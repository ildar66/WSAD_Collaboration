<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="com.ibm.ws.security.core.SecurityContext" errorPage="/error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<%
		String formAction = request.getContextPath()+"/secure/logon.jsp";
        if (SecurityContext.isSecurityEnabled()) 
            formAction = request.getContextPath()+ "/logon.jsp";
%>

<ibmcommon:detectLocale/>

<html:html locale="true">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
</HEAD>

<body class="content">

<H1 id="bread-crumb"> 
<bean:message key='desc.session.invalid.title'/>
</H1>

<p class="instruction-text"><bean:message key='desc.session.invalid.text'/></p>

<table border="0" cellpadding="0" cellspacing="0" valign="top" width="100%" summary="Framing Table">
    <tr> 
        <td class="framing-table"> 
            <table border=0 cellpadding="2" cellspacing="1" width="100%" summary="Login Table" class="framing-table">
                <form method=GET action="<%=formAction%>" target="_top">
                <tr>
                    <td valign=top class="column-head" header="header1" >
                    	<bean:message key="desc.session.invalid.title"/>
                    </td>
                </tr>
                <tr>
                    <td valign=top class="table-text" header="header1" >
                    	<bean:message key="msg.invalid.session.text"/>
                    </td>
                </tr>
                <tr align="center" > 
                    <td valign=top class="button-section" header="header1" bgcolor="#FFFFFF" colspan="2"> 
                            <input type="submit" name="okaction" class="buttons" id="navigation" value='<bean:message key="button.ok"/>'>
                    </td>
                </tr>
                </form>
            </table>
        </td>
    </tr>
</table>

</body>
</html:html>
