<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>                 

<ibmcommon:detectLocale/>


<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE><bean:message key="detail.page.title"/></TITLE> 

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<SCRIPT LANGUAGE="javascript" SRC="scripts/collectionform.js"></script>
</head>
     
<title><bean:message key="server.stop.title"/></title>
<body CLASS="content"> 
   <form action="com.ibm.ws.console.servermanagement.stopServer.do">
    
	<H1 id="bread-crumb">           
        <bean:message key="server.stop.title"/>     
 	</H1>
        
         
   <p class="instruction-text">
                            <img src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/warning.gif" width="16" height="16" align="baseline"><bean:message key="server.stop.warning1"/><br/>
                            <bean:message key="server.stop.warning2"/><br/>
                            <bean:message key="server.stop.warning3"/><br/>
      
   </p>  
   
         
   <p>
                        <input type="submit" name="stopServer" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
                        <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
                       
      
   </p>      
   
           
 
  </form>
</body>
</html:html>


 
