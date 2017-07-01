<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="java.util.*,com.ibm.ws.console.servermanagement.server.*,com.ibm.websphere.management.*,javax.management.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

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


<head>
<script language="JavaScript">
if (window != top)
 top.location.href = location.href;
</script>
</head>

<STYLE TYPE="text/css">
a           {  text-decoration: none}
a:hover     {  text-decoration: underline}
</STYLE>

<html:html locale="true">

<body topmargin="0" leftmargin="0" marginwidth="0" marginheight="0" >

<table border="0" cellpadding="0" cellspacing="0" width="100%" background="<%=request.getContextPath()+"/"%>images/background.jpg" >
  <tr >
		<td align="left" width="60%"><img src="<%= request.getContextPath() + "/" + "images/WS_logo_Admin" + "_" + localeStr + ".gif"%>"
    	width="433" height="53" alt="<bean:message key="websphere.admin.console"/>"></td>
 
    <td align="right" width="40%"><img src="<%=request.getContextPath()+"/"%>images/IBM-logo.jpg"
    	width="110" height="53" alt="<bean:message key="ibm.logo"/>"></td>
    </tr>
 </table>
	
 <table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%"  align="center" background="<%= request.getContextPath() + "/" %>images/login-background.jpg">
    <tr> 
      <td  class="login" valign="middle" align="center"> 


    <bean:message key="goodbye.message1"/>
    <br/>
    <bean:message key="server.stop.warning3"/>
        </td>
     </tr>
  </table>

</body>
</html:html>
<%
    //StopServerCmd stopServerCmd = (StopServerCmd) session.getAttribute(Constants.STOPSERVERCMD_KEY);
    //if (stopServerCmd != null)
    //{   
    //    com.ibm.ejs.sm.web.applicationserver.StopAppServerThread killerThread = new com.ibm.ejs.sm.web.applicationserver.StopAppServerThread(stopServerCmd);
    //    killerThread.start();
    //}
    String mbeanId = (String) session.getAttribute(Constants.ADMINSERVERMBEAN_KEY);
    try {
        if (!mbeanId.equalsIgnoreCase("")) {
            AdminService adminService = AdminServiceFactory.getAdminService();
            ObjectName objectName = new ObjectName(mbeanId);
            adminService.invoke (objectName, "stop", null , null); 
        }
    }
    catch (Exception e) {
        System.out.println("Exception occured while stopping server " + e.toString());           
    }
%>
