<%@ page contentType = "text/html; charset=windows-1251" %>
<B>здесь будет HELP</B>
<%--
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
	String helpfile = request.getParameter ("helpfile");

	String anchor = request.getParameter ("anchor");

   String search = request.getParameter ("search");

	String contentFile = "help_content.jsp";
   String navigationFile = "help_navigation.jsp";    

	if (helpfile != null)
	{
	   contentFile = "/admin/"+helpfile;
	   if (anchor != null)
		   contentFile = contentFile + "#"+anchor;
	}

   if (search != null) {
        navigationFile = "help_search.jsp?searchTerm="+ search;     
   }
%>

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
   String framesize = "";
   switch (getBrowser(request)) {
   case NETSCAPE:
        browserJava = "NETSCAPE";
        framesize = "55";
        break;
   case IE:
        browserJava = "IE";
        framesize = "50";   
        break;
   case Gecko:
        browserJava = "GECKO";
        framesize = "49";
        break;
   case Opera:
        browserJava = "OPERA";
        framesize = "40";

        }
%>

<ibmcommon:detectLocale/>


<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">

<TITLE><bean:message key="help.main.console.title"/></TITLE>

<SCRIPT LANGUAGE="JavaScript"> 
function restore() { 
        self.navigation_tree.location.reload() 
        self.navigation_tree.location.href = ContentURL 
} 

var nodeIndex = null;
var prioron = "";
var currenton = "";    


function init(e) {
        //alert(e.target.name);
        if (e.target.name == "HelpDetail") {
                synchTree();
        }
}

function synchTree() {
        if (top.HelpNavigation.location.href.indexOf("help_navigation") > -1) {
                if (top.HelpNavigation.nodeIndex != null) {
                        top.HelpNavigation.selectCheck();
                }
        }
} 


if (document.layers) {
        window.captureEvents(Event.LOAD);
        window.onload = init;

}
</SCRIPT> 

</head>



<% 
if (browserJava.equals("GECKO")) {
%>

<frameset rows="<%= framesize %>,*" FRAMEBORDER="YES" BORDER="2" BORDERCOLOR="#000000">
   <frame name="header" title="Header Frame" scrolling="NO" noresize src="banner.jsp" marginwidth="0" marginheight="0" >
   <frameset cols="25%,*" resize="yes" FRAMEBORDER="YES" BORDER="2" BORDERCOLOR="#000000">
      <FRAME title="Task Navigation Frame" src="<%=navigationFile%>" name="HelpNavigation"  marginwidth="0" marginheight="0" onload="synchTree()">
		<FRAME title="Work Area Frame" SRC="<%=contentFile%>"  name="HelpDetail"  marginwidth="15" marginheight="10" onload="synchTree()">
  </frameset>
</frameset>

<%
} else {
%>

<frameset rows="<%= framesize %>,*" FRAMEBORDER="1" BORDER="1" resize="yes">
   <frame name="header" scrolling="NO" title="Header Frame" noresize src="banner.jsp" frameborder="NO" marginwidth="0" marginheight="0" >
   <frameset cols="25%,*" resize="yes"  FRAMESPACING="3" >
	   <FRAME title="Task Navigation Frame" src="<%=navigationFile%>" name="HelpNavigation" resize="yes" marginwidth="0" marginheight="0" onload="synchTree()">
      <FRAME title="Work Area Frame" SRC="<%=contentFile%>"  name="HelpDetail"  marginwidth="15" marginheight="10" resize="yes" onload="synchTree()">
   </frameset>
</frameset>

<%
  } 
%>


<noframes>
You must use a browser that supports frames for the WebSphere Application Server (Standard Edition) Administrative Console.
</noframes> 




</html:html>
--%>