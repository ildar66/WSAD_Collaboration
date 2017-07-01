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
  
    
    
       
       
	
<H1 id="bread-crumb"> JVM Logs</H1>
        




    
    

  
<p class="instruction-text"> Template short description. View <a href="transformer.jsp?xml=was_page_help&amp;xsl=was_page_help" target='WASHelp'>more 
  information</a> about Template.</p>








<form name="nodeForm" method="POST" action="">
  <input type="hidden" name="action" lang="en_US" value="Edit">
        <table border="0" cellpadding="1" cellspacing="0"  width="100%" id="tabs">
    <tr valign="top"> 
      <td class="tabs-off" width="1%" nowrap height="19"><a class="tabs-item" href="jvm_logs_runtime.htm">Runtime</a></td>
      <td class="blank-tab" width="1%"><img src='images/blank20.gif'  align='texttop'  border='0' width='1' height='16'></td>
      <td class="tabs-on" width="1%" nowrap height="19">Configuration</td>
      <td class="blank-tab" width="99%"><img src='images/blank20.gif'  align='texttop'  border='0' width='1' height='16'></td>
    </tr>
  </table>
  <table border="0" cellpadding="10" cellspacing="0" valign="top" width="100%" summary="Framing Table">

    <tr> 
      <td class="layout-manager" > <a name="generalProperties"> </a> 
        <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="General Properties Table" class="framing-table">
          <tr> 
            <th colspan="3" class="column-head" scope="rowgroup"> General Properties 
            </th>
          </tr>
          <tr> 
            <th colspan="3" class="column-head-name" scope="rowgroup" height="16"> 
              System.out</th>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">File 
              name: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield3" class="long">
            </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description. </td>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att2">File 
              formatting :</label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <select name="select">
                <option selected>Basic (compatible)</option>
                <option>Advanced</option>
              </select>
            </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description. </td>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">Maximum 
              file size: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield">
              MB </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description. </td>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">Maximum 
              number of <br>
              historical log files: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield2">
              files </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description.. </td>
          </tr>
          <tr> 
            <th colspan="3" class="column-head-name" scope="rowgroup"> System.err</th>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">File 
              name: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield32" class="long">
            </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description. </td>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">Maximum 
              file size: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield">
              MB </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description. </td>
          </tr>
          <tr> 
            <td class="table-text"  nowrap valign="top" scope="row" width="33%"><label  for="att1">Maximum 
              number of <br>
              historical log files: </label></td>
            <td class="table-text" nowrap valign="top" width="33%"> 
              <input type="text" name="textfield2">
              files </td>
            <td class="table-text" valign="top" width="33%"> <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">Property 
              short description.</td>
          </tr>
          <tr> 
            <td class="button-section" colspan="3"> 
              <input type="submit" name="submit2" value="Apply" class="buttons" id="navigation">
              <input type="submit" name="submit" value="&nbsp;&nbsp;OK&nbsp;&nbsp;" class="buttons" id="navigation" onClick="history.go(-1)">
              <input type="reset" name="reset" value="Reset" class="buttons" id="navigation">
              <input type="submit" name="org.apache.struts.taglib.CANCEL" value="Cancel" class="buttons" id="navigation" onClick="history.go(-1)">
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






