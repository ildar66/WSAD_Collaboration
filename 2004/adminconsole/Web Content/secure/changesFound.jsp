<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="com.ibm.ws.security.core.SecurityContext,java.util.Locale" errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
</head>

<% 
String myshow = "collapsed";
String submitS = request.getParameter("submitS.x");
String show = request.getParameter("show");

if (submitS != null) {
    if (show.equals("collapsed")) {
        myshow = "expanded";
    } 
    else {
        myshow = "collapsed";
    }
} 
%>

<body  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<% 
    String link = "secure/logon.do";
    if (SecurityContext.isSecurityEnabled()) 
        link = "secure/securelogon.do";
%>
<html:form action="<%=link%>" name="logonForm" type="com.ibm.ws.console.core.form.LogonForm" method="get">
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
        <table border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table">
          <tr> 
            <th class="column-head" ID="header1"><bean:message key="login.header"/>&nbsp;&nbsp;<html:errors/></th>
          </tr>
          <tr > 
            <td valign=top class="table-text" header="header1" bgcolor="#FFFFFF" ><bean:message key="ChangesFound.information"/></td>
          </tr>
          <tr > 
            <td valign=top class="table-text" header="header1" bgcolor="#FFFFFF" >
              <table border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table" class="framing-table">
                <tr valign="top" > 
                  <td class="table-text" header="header1" bgcolor="#FFFFFF" width="1%"> 
                    <html:radio property="action" value="restore" styleId="aradio"/>
                  </td>
                  <td class="table-text" header="header1" bgcolor="#FFFFFF"><label for="aradio"><bean:message key="ChangesFound.restore"/></label></td>
                </tr>
                <tr valign="top" > 
                  <td class="table-text" header="header1" bgcolor="#FFFFFF" width="1%"> 
                    <html:radio property="action" value="recover" styleId="aradio"/>
                  </td>
                  <td class="table-text" header="header1" bgcolor="#FFFFFF"><label for="aradio"><bean:message key="ChangesFound.recover"/></label>
                  <table border="0" cellpadding="0" cellspacing="0" width="100%" summary="List framing table"> 
                  <tr valign="top"> 
                    <td class="framing-table" width="100%" > 
<% if (myshow.equals("collapsed")) { %> 
                      <table border="0" cellpadding="3" cellspacing="0" width="100%"  summary="Table for displaying changed items in configuration">
                        <tr>
                          <td class="table-text" valign="bottom" align="left" width="100%" nowrap> 
                           <input type="image" id="prefs" src="<%= request.getContextPath() + "/" %>images/lplus.gif" name="submitS"  alt="<bean:message key="show.search.and.filter"/>" align="texttop" border="0">
                            <bean:message key="ChangesFound.view"/> 
                           <input type="hidden" name="show" value="collapsed">
                         </td>
                        </tr>
                      </table>
<% } else { %> 
                      <table border="0" cellpadding="3" cellspacing="0" width="100%"  summary="Table for displaying changed items in configuration">
                        <tr>
                          <td class="table-text" valign="bottom" align="left" width="100%" nowrap> 
                            <input type="image" id="prefs" src="<%= request.getContextPath() + "/" %>images/lminus.gif" name="submitS"  alt="<bean:message key="show.search.and.filter"/>" align="texttop" border="0">
                             <bean:message key="ChangesFound.view"/> 
                            <input type="hidden" name="show" value="expanded">
                          </td>
                        </tr>
                      </table>                          
                      <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="List table" class="framing-table">
                      <tr> 
                        <th class="column-head-name" scope="col" nowrap>
                          <bean:message key="ChangesFound.changed"/>
                        </th>
                      </tr>
                      <logic:iterate id="changeList" name="ChangeList">
                        <bean:define id="stateValue" name="changeList" property="state"/>
                        <%
                            Integer stateIndex = (Integer)stateValue;
                            String state = com.ibm.ws.sm.workspace.WorkSpaceFileState.message[stateIndex.intValue()];		
                        %>
                        <tr> 
                            <td class="table-text" headers="header1">
                                <bean:write name="changeList" property="URI"/>
                                &nbsp;&nbsp;
                                <%=state%>
                            </td>
                        </tr>
                      </logic:iterate>
                      </table>
<% } %> 
                    </td>
                  </tr>
                  </table>
                </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr align="center" > 
            <td valign=top class="login-button-section" header="header1" bgcolor="#FFFFFF" colspan="2"> 
              <html:submit property="submit" styleClass="buttons" styleId="navigation">
                <bean:message key="button.ok"/>
              </html:submit>
            </td>
          </tr>
        </table>
    </td>
  </tr>
</table>

</html:form>

</body>
</html:html>
