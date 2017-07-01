<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator,org.apache.struts.util.MessageResources,org.apache.struts.action.Action,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.error.*"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%-- Layout component 
  Render a list of tiles in a vertical column
  @param : list List of names to insert 
  
--%>


<ibmcommon:detectLocale/>

<%
	WorkSpace workSpace = (WorkSpace)session.getAttribute(com.ibm.ws.console.core.Constants.WORKSPACE_KEY);
	if (workSpace != null) {
		if (workSpace.getModifiedList().size() > 0) {
			if (request.getRequestURL().toString().indexOf("syncworkspace.do") == -1 && request.getRequestURL().toString().indexOf("logoff.do") == -1) {
		        java.util.Locale locale = (java.util.Locale)session.getAttribute(Action.LOCALE_KEY);
	    	    MessageResources messages = (MessageResources)application.getAttribute(Action.MESSAGES_KEY);
				IBMErrorMessage errorMessage1 = IBMErrorMessages.createWarningMessage(locale, messages, "warning.config.changes.made", null);
				IBMErrorMessage errorMessage2 = IBMErrorMessages.createInfoMessage(locale, messages, "info.restart.server", null);
	        	Object obj = request.getAttribute(Action.ERROR_KEY);
	        	if (obj != null) {
		        	if (obj instanceof IBMErrorMessage[]) {
			        	IBMErrorMessage[] errorArray = (IBMErrorMessage[])obj;
		        		IBMErrorMessage[] newErrorArray = new IBMErrorMessage[errorArray.length + 2];
		        		newErrorArray[0] = errorMessage1;
		        		newErrorArray[1] = errorMessage2;
		        		System.arraycopy(errorArray, 0, newErrorArray, 2, errorArray.length);
				        request.setAttribute(Action.ERROR_KEY, newErrorArray);
					}
		        }
	        	else {
					IBMErrorMessage[] newErrorArray = {errorMessage1, errorMessage2};
			        request.setAttribute(Action.ERROR_KEY, newErrorArray);
				}
		    }
		}
	}
%>
<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE><bean:message key="detail.page.title"/></TITLE> 

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<SCRIPT LANGUAGE="javascript" SRC="scripts/menu_functions.js"></script>
<SCRIPT LANGUAGE="javascript" SRC="scripts/collectionform.js"></script>
</head>
                                  
<body CLASS="content" > 


<jsp:include page="/secure/layouts/content_accessibility.jsp" flush="true"/>


   
<tiles:useAttribute id="list" name="list" classname="java.util.List" />

<%-- Iterate over names.
  We don't use <iterate> tag because it doesn't allow insert (in JSP1.1)
 --%>
<%
Iterator i=list.iterator();
while( i.hasNext() )
  {
  String name= (String)i.next();
%>
<tiles:insert name="<%=name%>" flush="true" />

<%
  } // end loop
%>    


 </body>
 </html:html>
