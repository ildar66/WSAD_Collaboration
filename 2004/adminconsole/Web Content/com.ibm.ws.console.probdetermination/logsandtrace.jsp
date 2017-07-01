<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>




<%@ page  errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="jvmLinkName" classname="java.lang.String" />

<ibmcommon:detectLocale/>


<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>Detail Page</TITLE><script language="JavaScript" src="scripts/menu_functions.js"></script>
<script language="JavaScript">
document.write("<link rel='stylesheet' href='css/was_style_common.css'>");
if (isNav4) {
document.write("<link rel='stylesheet' href='css/was_style_ns.css'>")
}
if (isIE) {
document.write("<link rel='stylesheet' href='css/was_style_ie.css'>")
}
</script>
</head>

<body CLASS="content" > 

<div class="accessibility-jumps" id="top">
    Skip directly to: 
    <a href="#title">title</a>,
    <a href="#tabs">properties tabs</a>,
	<a href="#functions">functions</a>, 
    <a href="#general">general properties</a>, 
    <a href="#additional">additional properties</a>,
	<a href="#related">related items</a>, or  
    <a href="#problem">input validation problem</a>     
</div>












<a name="pageHeader"></a>
  
    
    
       
       
	<H1 id="bread-crumb">
		View Logs and Trace 	</H1>
        




    
    

  
<p class="instruction-text"> Application Servers short description. View <a href="transformer.jsp?xml=was_page_help&amp;xsl=was_page_help" target='WASHelp'>more 
  information</a> about Application Servers.</p>








<form name="nodeForm" method="POST" action="">
  <input type="hidden" name="action" lang="en_US" value="Edit">
  
 

  
        <table border="0" cellpadding="1" cellspacing="0"  width="100%" id="tabs">
    <tr valign="top"> 
      <td class="tabs-off" width="1%" nowrap height="19"><a class="tabs-item" href="problem_determination_runtime.htm">Runtime</a></td>
      <td class="blank-tab" width="1%"><img src='images/blank20.gif'  align='texttop'  border='0' width='1' height='16'></td>
      <td class="tabs-on" width="1%" nowrap height="19">Configuration</td>
      <td class="blank-tab" width="99%"><img src='images/blank20.gif'  align='texttop'  border='0' width='1' height='16'></td>
    </tr>
  </table>






  <table border="0" cellpadding="10" cellspacing="0" valign="top" width="100%" summary="Framing Table">

    <tr> 
      <td class="layout-manager" >
       

<table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
  <tr> 
    <th colspan="2" class="column-head" scope="rowgroup"> <a name="additionalProperties"></a>Problem Determination</th>
  </tr>
  <tr> 
    <td valign="top" class="table-text" headers="header2" nowrap width="20%" > <a href="trace.htm">Diagnostic 
      Trace </a><br>
    </td>
	<td valign="top" class="table-text" headers="header2" >Specify the diagnostic 
      trace settings.</td>
  </tr>
  
  <%
        String resourceUri = request.getParameter("resourceUri");
		String hRef = jvmLinkName+"&resourceUri="+resourceUri;
   %>

  <tr> 
    <td valign="top" class="table-text" headers="header2" nowrap width="20%" > <a href="<%=hRef %>">JVM 
      Logs</a><br>
    </td>
	<td valign="top" class="table-text" headers="header2" >Specify the JVM log 
      settings.</td>
  </tr>
  <tr> 
    <td valign="top" class="table-text" headers="header2" nowrap width="20%" > <a href="process_logs.htm">Process 
      Logs</a><br>
    </td>
	<td valign="top" class="table-text" headers="header2" >Specify the process 
      log settings.</td>
  </tr>
  <tr> 
    <td valign="top" class="table-text" headers="header2" nowrap width="20%" > <a href="service_logs.htm">IBM 
      Service Logs</a><br>
    </td>
	<td valign="top" class="table-text" headers="header2" >Specify the service 
      log settings.</td>
  </tr>
</table>



<br>
                      
                
        
        

        

     


        <a name="preferences"></a>    
<table border="0" cellpadding="2" cellspacing="0" width="100%" class="framing-table" id="expandable" summary="Table for displaying searching and filtering function">



    <td class="find-filter" valign="top" align="left" width="99%" nowrap>
            <input type="image"  src="images/lplus.gif" name="submitP"  alt="<bean:message key="show.search.and.filter"/>" align="texttop" border="0">Preferences
     <input type="hidden" name="stateP" value="expand">
     <input type="hidden" name="prefs" value="#preferences">
    </td>
    </tr>

	  

</table> 




      </td>
    </tr>
  </table>
</form> 
 <br>
 









<div class="accessibility-jumps" id="bottom">
    Skip directly to: 
    <a href="#title">title</a>,
    <a href="#tabs">properties tabs</a>,
	<a href="#functions">functions</a>, 
    <a href="#general">general properties</a>, 
    <a href="#additional">additional properties</a>,
	<a href="#related">related items</a>, or  
    <a href="#problem">input validation problem</a>     
</div>







</body>
</html:html>





