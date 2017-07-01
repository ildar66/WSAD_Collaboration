<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<ibmcommon:detectLocale/>


<html:html locale="true">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<!-- contains browser detection script --> 
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript">
document.write("<link rel='stylesheet' href='<%=request.getContextPath()%>/css/was_style_common.css'>");
if (isNav4) {
	document.write("<link rel='stylesheet' href='<%=request.getContextPath()%>/css/was_style_ns.css'>")
}
if (isIE) {
     document.write("<link rel='stylesheet' href='<%=request.getContextPath()%>/css/was_style_ie.css'>")
}

var timer = null;
function doRefresh() {
	document.location="console_messages.jsp";	
}
</script>
<NOSCRIPT> 
<link rel='stylesheet' href='<%=request.getContextPath()%>/css/was_style_ns.css'>
</NOSCRIPT> 
<style type="text/css">
<!--
#refresh {  color: #FFFFFF; }
-->
</style>
</HEAD>
<body class="was-system-info">
<tiles:importAttribute />
<tiles:useAttribute name="messageItems" classname="java.util.List" />
<div style="align:center;font-family: sans-serif; width=100%">
  <table border="0" cellpadding="0" cellspacing="0" width="100%" summary="List framing table">
    <tbody> 
    <tr> 
      <td  class="framing-table"> 
        <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
          <tr align="left"> 
            <th  nowrap colspan="<%=messageItems.size()%>" class="top-navigation"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td class="was-message-item" width="34%">WebSphere System Information</td>
                  <td class="was-message-item" align="right" width="32%" nowrap><a href="console_messages.jsp" target="console_messages" id="refresh">Last 
                    Refreshed</a>: <%@ page import = "java.util.*" %><%= new Date() %></td>
                  <td class="was-message-item" align="right" width="34%"><img src="<%=request.getContextPath()%>/images/edit.gif" width="28" height="14" align="absmiddle" usemap="#Map" border="0"><map name="Map"><area shape="rect" coords="-2,0,27,15" href="console_messages_detail.htm" alt="<bean:message key="edit.display.websphere.system.info"/>" title="Edit the Display of WebSphere System Information" target="detail"></map> 
                  </td>
                </tr>
              </table>
            </th>
          </tr>
          <tr> 
            <logic:iterate id="item" name="messageItems" type="com.ibm.ws.console.core.item.MessageItem" >
                <td class="table-text" headers="header1" > 
                    <img src="<%=request.getContextPath()+"/"+item.getIcon()%>" width="19" height="19" align="texttop" alt="<bean:message key="runtime.messages"/>"> 
                    <a href="<%=request.getContextPath()+"/"+item.getLink()%>" target="detail"><%=item.getValue()%></a> 
                </td>
            </logic:iterate>
          </tr>
        </table>
      </td>
    </tr>
    </tbody> 
  </table>
</div>
</body>
</html:html>
