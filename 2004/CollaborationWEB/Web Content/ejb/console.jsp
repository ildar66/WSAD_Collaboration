<%@ page language="java" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%--@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%--<ibmcommon:detectLocale />--%>
<%--@ page import="com.ibm.ws.console.core.bean.*" --%>
<%--@ page import="com.ibm.ws.console.core.*" --%>

<%!
public final static int IE = 0;
public final static int NETSCAPE = 1; 
public final static int Gecko = 2;
public final static int Opera = 3;

String browserJava = "";

public int getBrowser(HttpServletRequest request) {
   
   String agent = request.getHeader("USER-AGENT");
   if (null != agent && -1 !=
agent.indexOf("MSIE")) {
     return IE;
   }
   
   if (null != agent && -1 !=
agent.indexOf("Gecko")) {
     return Gecko;
   }

   if (null != agent && -1 !=
agent.indexOf("Opera")) {
     return Opera;
   }
   
     return NETSCAPE;
}
%>

<%
    
   //UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(Constants.USER_PREFS);

   //String banner = uBean.getProperty("System/workspace#bannerShow", "true");
   String banner = "true";
   String framesize = "";
   int size = 0; 
   
   switch (getBrowser(request)) {
   case NETSCAPE:
        browserJava = "NETSCAPE";
        framesize = "80";
        break;
   case IE:
        browserJava = "IE";
        framesize = "75";   
        break;
   case Gecko:
        browserJava = "GECKO";
        framesize = "74";
        break;
   case Opera:
        browserJava = "OPERA";
        framesize = "75";

        }

   //If the user has set the show/hide banner preference to false, then the
   //height of the banner is subtracted from the framesize FRAME attribute
   //since the banner will not be shown 

   if (banner.equals("false")) {
        Integer frameInt1 = new Integer(framesize); 
        size = frameInt1.intValue() - 52;    //52 is height from \tiles\ejb\banner.jsp
        Integer frameInt2 = new Integer(size); 
        framesize = frameInt2.toString();
    }

%>


<%-- <ibmcommon:detectLocale /> --%>


<html:html locale="true">
<HEAD>
<title>Console</title>

<script>
var isloaded = 0;
var treeopen = new Array();
var treecontent = new Array();
var tmptreecontent = new Array();
var tmptreeopen = new Array();
var treecounter = 0;
var pleaseWait = '<%--bean:message key="trace.tree.pleaseWaitLabel" /--%>';
var statusCollection = "no";
</script>

</HEAD>

<%
//absolute path references necessary in SRC attribute of frames because 
//entire console.jsp will be refreshed from preferences.jsp
%>

<% 
if (browserJava.equals("GECKO")) {
%>

<frameset rows="<%= framesize %>,*" FRAMEBORDER="YES" BORDER="2"
	BORDERCOLOR="#000000">

	<frame name="header" title='<%--bean:message key="header.frame" /--%>'
		scrolling="NO" noresize
		src="<%=request.getContextPath() + "/ejb/" %>banner.jsp"
		marginwidth="0" marginheight="0">
	<frameset cols="25%,*" resize="yes" FRAMEBORDER="YES" BORDER="2"
		BORDERCOLOR="#000000">
		<FRAME title='<%--bean:message key="task.nav.frame" /--%>'
			src="<%=request.getContextPath() + "/ejb/" %>navigator.jsp"
			name="navigation_tree" marginwidth="10" marginheight="0"
			onload="this.focus()">
		<frameset rows="*,100" resize="yes" FRAMEBORDER="YES" BORDER="2"
			BORDERCOLOR="#000000">
			<FRAME title='<%--bean:message key="work.area.frame" /--%>'
				SRC="<%=request.getContextPath() + "/ejb/" %>content.jsp"
				name="detail" marginwidth="15" marginheight="10"
				onload="isloaded=0;this.focus()">

			<FRAME title='<%--bean:message key="status.area.frame" /--%>'
				src="<%=request.getContextPath() + "/ejb/" %>status.jsp"
				name="console_messages" marginwidth="10" marginheight="10">
		</frameset>
	</frameset>

</frameset>

<%
} else {
%>


<frameset rows="<%= framesize %>,*" FRAMEBORDER="1" BORDER="1"
	resize="yes">

	<frame name="header" title='<%--bean:message key="header.frame" /--%>'
		scrolling="NO" noresize
		src="<%=request.getContextPath() + "/ejb/" %>banner.jsp"
		marginwidth="0" marginheight="0">
	<frameset cols="23%,*" resize="yes" FRAMESPACING="3">
		<FRAME title='<%--bean:message key="task.nav.frame" /--%>'
			src="<%=request.getContextPath() + "/ejb/" %>navigator.jsp"
			name="navigation_tree" resize="yes" marginwidth="10" marginheight="0"
			onload="this.focus()">
		<frameset rows="*,100" resize="yes" FRAMESPACING="2" BORDER="1">
			<FRAME title='<%--bean:message key="work.area.frame" /--%>'
				SRC="<%=request.getContextPath() + "/ejb/" %>content.jsp"
				name="detail" marginwidth="15" marginheight="10" resize="yes"
				onload="isloaded=0;this.focus()">
			<FRAME title='<%--bean:message key="status.area.frame" /--%>'
				src="<%=request.getContextPath() + "/ejb/" %>status.jsp"
				resize="yes" name="console_messages" marginwidth="10"
				marginheight="10">
		</frameset>
	</frameset>

</frameset>

<%
} 
%>

<noframes>You must use a browser that supports frames.
</noframes>

</html:html>
