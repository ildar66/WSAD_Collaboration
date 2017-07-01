<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator,org.apache.struts.util.MessageResources,org.apache.struts.action.Action,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.error.*"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

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
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>
<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

</HEAD>

<tiles:useAttribute name="descTitle" classname="java.lang.String" />
<tiles:useAttribute name="descImage" classname="java.lang.String" />
<tiles:useAttribute name="descText" classname="java.lang.String" />
<tiles:useAttribute id="formName" name="formName" classname="String"/>
<tiles:useAttribute id="formAction" name="formAction" classname="String"/>
<tiles:useAttribute id="formType" name="formType" classname="String"/>
<tiles:useAttribute name="contentList" classname="java.util.List" />

<body class="content">
    <tiles:insert page="/secure/layouts/descLayout.jsp">
        <tiles:put name="descTitle" beanName="descTitle" beanScope="page"/>
        <tiles:put name="descImage" beanName="descImage" beanScope="page"/>
        <tiles:put name="descText" beanName="descText" beanScope="page"/>
    </tiles:insert>

    <tiles:insert page="/com.ibm.ws.console.probdetermination/linkBoxLayout.jsp" flush="true">
        <tiles:put name="list" beanName="contentList" beanScope="page"/>
        <tiles:put name="formName" beanName="formName" beanScope="page"/>
        <tiles:put name="formType" beanName="formType" beanScope="page"/>
        <tiles:put name="formAction" beanName="formAction" beanScope="page"/>
    </tiles:insert>
</body>
</html:html>

