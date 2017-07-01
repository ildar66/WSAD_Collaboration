<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.servermanagement.applicationserver.*,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.*,com.ibm.ws.console.core.mbean.*,javax.management.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<ibmcommon:detectLocale/>

<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE><bean:message key="deleteserver.page.title"/></TITLE> 

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>


<SCRIPT LANGUAGE="javascript" SRC="scripts/collectionform.js"></script>
</head>
                                  
<body CLASS="content" > 


<jsp:include page="/secure/layouts/content_accessibility.jsp" flush="true"/>

<H3 id="bread-crumb"> 
        <bean:message key="nav.view.ApplicationServer"/> >
       </H3>
	<H1 id="bread-crumb">
		<bean:message key="button.delete"/>
 	</H1>
<html:form action="com.ibm.ws.console.servermanagement.deleteAppServerConf.do" name="com.ibm.ws.console.servermanagement.GenericServerCollectionForm" type="com.ibm.ws.console.servermanagement.applicationserver.GenericServerCollectionForm">

<p class="description-text">
<bean:message key="deleteserver.page.msg1"/>  
<ul>
<% 
	GenericServerCollectionForm collectionForm = (GenericServerCollectionForm) session.getAttribute("com.ibm.ws.console.servermanagement.GenericServerCollectionForm");		
	WorkSpace workSpace = (WorkSpace)session.getAttribute(Constants.WORKSPACE_KEY);
	String selectedObjectIds[]= collectionForm.getSelectedObjectIds();
	for (int i = 0; ((selectedObjectIds != null) && (i < selectedObjectIds.length)); i++) {
		    	String contextStr =  selectedObjectIds[i];
			RepositoryContext serverContext = null;
			String serverName = null;
        		contextStr = ConfigFileHelper.decodeContextUri(contextStr);
       		 try {
         		   serverContext = workSpace.findContext(contextStr);
			 } catch(Exception e) {					
				System.out.println("Exception while finding serverContext in jsp..");				
			 }
			if (serverContext != null) {				 		   			
			    serverName = serverContext.getName();
			} 
            
            ServerMBeanHelper helper = ServerMBeanHelper.getServerMBeanHelper();
            String nodeName = serverContext.getParent().getName();
            //check for the NodeAgent Process 
            ObjectName nodeAgentMbean = helper.getNodeAgentMBean(nodeName);
            boolean nodeAgentFound = true;
            if (nodeAgentMbean == null) {
                nodeAgentFound = false;
            } else {
                nodeAgentFound = true;
            }
            String qualifiedServerName = nodeName + "/" + serverName;
 %>
<li><%=qualifiedServerName%></li>
<% if (!nodeAgentFound) { %>
    <H5><bean:message key="NodeAgent.not.found"/></H5>
<% } %>    
<% } %>
</ul> 
</p>
         
          <input type="submit" name="deleteServer" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
          <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
	
 <br/>
</html:form>
</BODY>
</html:html>
