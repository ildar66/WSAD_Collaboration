<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="com.ibm.ws.security.core.SecurityContext" errorPage="error.jsp" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<% if (SecurityContext.isSecurityEnabled()) {
      ((HttpServletResponse)pageContext.getResponse()).sendRedirect(request.getContextPath() + "/secure/securelogon.do?action=secure");
   }
%>

<%
		Locale locale = request.getLocale();
        String currentLocale = "en";
        String[] availableLocales = new String [] {"en","de","es","fr","it","ja","ko","pt","zh_TW","zh"};
        if (locale.toString().startsWith("en") || locale.toString().equals("C"))
            currentLocale = availableLocales [0];
        else
        {
            for (int i = 1; i < availableLocales.length; i++)
            {
                if (locale.toString().equals(availableLocales[i]))
                {
                    currentLocale = availableLocales [i];
                    break;
                }
                else
                {
                    if (locale.toString().startsWith(availableLocales[i]))
                    {
                        currentLocale = availableLocales [i];
                        break;
                    }
                }
            }
        }

	String localeStr = currentLocale;
%>


<ibmcommon:detectLocale/>

<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="login.title"/></title>

<jsp:include page="/secure/layouts/browser_detection.jsp" flush="true"/>

</head>

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >

<html:form action="secure/logon.do" name="logonForm" focus="username" type="com.ibm.ws.console.core.form.LogonForm">

<table border="0" cellpadding="0" cellspacing="0" width="100%" background="<%= request.getContextPath() + "/" %>images/background.jpg" >
  <tr >
      <td align="left" width="60%"><img src="<%= request.getContextPath() + "/" + "images/WS_logo_Admin" + "_" + localeStr + ".gif"%>"
    	width="395" height="52" alt="<bean:message key="websphere.admin.console"/>"></td>
 
    <td align="right" width="40%"><img src="<%= request.getContextPath() + "/" %>images/IBM-logo.jpg"
    	width="112" height="52" alt="<bean:message key="ibm.logo"/>"></td>
    </tr>
 </table>

 <table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%"  align="center" background="<%= request.getContextPath() + "/" %>images/login-background.jpg">
    <tr> 
      <td  class="login"> 
        <table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table">
        <tbody>
          <tr> 
            <th colspan="2" class="column-head" scope="rowgroup"><bean:message key="login.header"/>&nbsp;&nbsp;</th>
          </tr>
          <tr>
          <td valign=top  colspan="2" class="table-text"  nowrap > 
                <html:errors/>&nbsp;
          </td>
          </tr>

          <tr > 
            <td valign=top width="33%" class="table-text" nowrap > 
              <label for="name"><bean:message key="prompt.username"/></label> </td>
            <td valign=top class="table-text"> 
              <html:text property="username" styleClass="short" styleId="name"/>  
              <html:hidden property="password" value="password"/>  
            </td>
          </tr>
          <tr> 
            <td colspan="2" class="table-text"><bean:message key="logon.desc"/></td>
          </tr>
          <tr> 
            <td valign=top colspan="2" class="login-button-section" nowrap align="center"> 
              <html:submit property="submit" styleClass="buttons" styleId="navigation">
                &nbsp;<bean:message key="button.ok"/>&nbsp;
              </html:submit>
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
