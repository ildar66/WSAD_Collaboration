<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.environment.topology.*,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.*,com.ibm.ws.console.core.mbean.*,javax.management.*"%>
<%@ page import="com.ibm.ws.security.core.SecurityContext"%>
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

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>


<SCRIPT LANGUAGE="javascript" SRC="scripts/collectionform.js"></script>
</head>
                                  
<body CLASS="content" > 


<jsp:include page="/secure/layouts/content_accessibility.jsp" flush="true"/>


<html:form action="removeNode.do" name="com.ibm.ws.console.environment.NodeCollectionForm" type="com.ibm.ws.console.environment.topology.NodeCollectionForm">

<p class="description-text">
<bean:message key="remove.node.confirm"/>  
<ul>
<% 
	NodeCollectionForm collectionForm = (NodeCollectionForm) session.getAttribute("com.ibm.ws.console.environment.NodeCollectionForm");		
	String selectedObjectIds[]= collectionForm.getSelectedObjectIds();
	for (int i = 0; ((selectedObjectIds != null) && (i < selectedObjectIds.length)); i++) {
		    	String nodeName =  selectedObjectIds[i];
 %>
<li><%=nodeName%></li>
<% } %>
</ul> 
<BR>
<%    if (SecurityContext.isSecurityEnabled()) { %>
<table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" >
<TBODY>
     <tr valign="top"> 
          <td class="table-text"  nowrap valign="top" scope="row">
	      <label  for="{attributeName}"> <bean:message key="add.node.user.displayName"/></label>
		  </td>
          <td class="table-text" nowrap valign="top"> 
              <html:text property="user" size="30" />
                    &nbsp;
		  </td>
          <td class="table-text" valign="top"> 
	  <bean:message key="add.node.user.description"/>
		  </td>
     </tr>
     
     <tr valign="top"> 
          <td class="table-text"  scope="row"><label  for="{attributeName}">
	  <label for="{attributeName}"><bean:message key="add.node.password.displayName"/></label>
		  </td>
          <td class="table-text"><label> 
              <input type="password" class="short" id="password" name="password" size="30"/>	  
		  </td>
          <td class="table-text" valign="top">
	       <bean:message key="add.node.password.description" />	  
	  </td>
     </tr>
	 </TBODY>
	 </TABLE>
	 
<% } %>     

</p>
         
          <input type="submit" name="removeNode" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
          <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
	
</html:form>
</BODY>
</html:html>
