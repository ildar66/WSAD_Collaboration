<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="java.util.Locale" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<ibmcommon:detectLocale/>

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


<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title><bean:message key="login.title"/></title>

<%@ include file = "/secure/layouts/browser_detection.jsp" %>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<form name="logonForm" action="logon.jsp" method="post">

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
          	<br>
				<span class='validation-error'><img align="baseline" height="16" width="16" src="<%= request.getContextPath() + "/" %>images/Error.gif"/>
					<bean:message key="error.login.failure"/>
				</span>
			<br>
	     	<br>
          </td>
          </tr>

          <tr> 
            <td valign=top colspan="2" class="login-button-section" nowrap align="center"> 
              <input type="submit" name="action" class="buttons" value='<bean:message key="button.ok"/>'>
            </td>
          </tr>
          </tbody>
        </table>
      </th>
  </tr>
</table>
   
</form>
</body>
</html:html>
