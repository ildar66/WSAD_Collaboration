<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.Locale, org.apache.struts.action.Action, java.io.StringWriter" %>

<ibmcommon:detectLocale/>

<html:html locale="true">
<HEAD>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>WebSphere Task Navigation</TITLE> 

<jsp:include page = "layouts/browser_detection.jsp" flush="true"/>
<%@ include file = "layouts/navtree_js.jsp" %>

<%
	String currentLocale = "en";
   String[] availableLocales = new String [] {"en","de","es","fr","it","ja","ko","pt","zh_TW","zh"};
   Locale locale = (Locale) session.getAttribute (Action.LOCALE_KEY);
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
	String pathToMasterToc = "nl/" + currentLocale + "/toc.xml";
	String contextPath = request.getContextPath() + "/secure/help_transformation.jsp";
%>

<jsp:include page="/secure/help_transformation.jsp" flush="true">
	<jsp:param name="xmlFile" value="<%=pathToMasterToc%>" />
	<jsp:param name="xslFile" value="/WEB-INF/com.ibm.ws.console.core/toc.xsl" />
</jsp:include>
<%
    StringWriter transform1;
    if (request.getAttribute ("HelpTransformation") == null)
        transform1 = new StringWriter();    
    else
        transform1 = (StringWriter) request.getAttribute ("HelpTransformation");
%>

<jsp:include page="/secure/help_transformation.jsp" flush="true">
	<jsp:param name="xmlFile" value="<%=pathToMasterToc%>" />
	<jsp:param name="xslFile" value="/WEB-INF/com.ibm.ws.console.core/toc2.xsl" />
</jsp:include>
<%
    StringWriter transform2; 
    if (request.getAttribute ("HelpTransformation") == null)
        transform2 = new StringWriter();    
    else
        transform2 = (StringWriter) request.getAttribute ("HelpTransformation");
%>

<STYLE TYPE="text/css">
.help-docset {  padding-left: 5px; padding-right: 5px;   font-family: sans-serif; }
a {  text-decoration: none}
a:hover {  text-decoration: underline}
div.navtree {  font-size: 70%; font-family: sans-serif; }
layer.navtree { font-size: 70%; font-family: sans-serif; }
.helptree {  font-size: 70%; font-family: sans-serif; border-right: 1px solid #000000; background-color: #E2E2E2 }
FORM { padding-left: 10px }
</STYLE>


  <BODY CLASS="helptree">    
    <table border="0" cellpadding="0" cellspacing="0"  width="108%" >
      <tr valign="top"> 
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-on" width="1%" nowrap height="19">
          <bean:message key="help.navigation.index"/>
        </td>  
        <td class="blank-tab" width="1%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
        </td>
        <td class="tabs-off" width="1%" nowrap height="19">
          <a class="tabs-item" href="/admin/secure/help_search.jsp"><bean:message key="help.navigation.search"/></a>
        </td> 
        <td class="blank-tab" width="99%" nowrap height="19">
          <img src="/admin/images/onepix.gif" width="1" height="27" align="absmiddle">
        </td>
      </tr>
    </table>

<form action="help_navigation.jsp" name="pageform" method="POST">

<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/scripts/helptree_synch.js"></SCRIPT>
<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript" SRC="<%=request.getContextPath()%>/scripts/aptree.js"></SCRIPT>
<SCRIPT TYPE="text/javascript" LANGUAGE="JavaScript1.2">
/***  Set up display parameters   ***/
	setShowExpanders(true);
	setExpandDepth(1);
	setKeepState(false);
	setShowHealth(false);
	setInTable(false);
	setTargetFrame("HelpDetail");
	setImagePath("<%=request.getContextPath()%>");
	setHelpPath ("<%=request.getContextPath()%>/");
	openFolder = "/images/open_folder.gif";
	closedFolder = "/images/closed_folder.gif";
	plusIcon = "/images/lplus.gif";
	minusIcon = "/images/lminus.gif";
	blankIcon = "<%=request.getContextPath()%>/images/blank20.gif";

	helptop = addRoot("/images/onepix.gif", "<a href='/admin/secure/help_console.jsp' target='WAS_help'><bean:message key='help.navigation.home'/></a>","");
</SCRIPT>

<%=transform1%>
<%=transform2%>

<SCRIPT>           
	setImagePath ("");
   setHelpPath ("");
	initialize();
   top.nodeIndex = nodeIndex;
</SCRIPT>

<SCRIPT>
   selectCheck();
</SCRIPT>

</form>
  
</BODY>
</html:html>
