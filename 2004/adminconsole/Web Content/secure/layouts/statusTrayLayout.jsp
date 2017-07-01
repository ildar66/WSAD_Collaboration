<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.ibm.ws.console.core.bean.StatusBean" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ibm.ws.console.core.bean.UserPreferenceBean" %>
<%@ page import="com.ibm.ws.console.core.*" %>
<%@ page import="com.ibm.ws.console.core.item.PreferenceItem" %>
<%@ page import="com.ibm.ws.security.core.SecurityContext"%>

<% 
	String tile = request.getParameter("tile");
%>


<% 
			String myshow = "collapsed";
 			String submitS = request.getParameter("submitS.x");
			String show = request.getParameter("show");
%>

<%
			if (submitS != null) {
				if (show.equals("collapsed")) {
					myshow = "expanded";
				} else {
					myshow = "collapsed";
				}
			} 
%>

<jsp:useBean id="statusBean" class="com.ibm.ws.console.core.bean.StatusBean" scope="session"/>
<jsp:useBean id="list" class="java.util.ArrayList" scope="session" />

<ibmcommon:detectLocale/>

<ibmcommon:setPluginInformation pluginIdentifier="com.ibm.ws.console.core" pluginContextRoot=""/>

<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>WebSphere System Tray</TITLE> 

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<script language="JavaScript">
var timer = null;
var topurl = top.location;
function doRefresh() {

        if (top.isloaded == 1) {
                //alert("waiting "+top.isloaded);
                clearTimeout(timer);
                //alert("timer is "+timer);
                setTimeout("doRefresh()",60000);       
        }
        else {

            <% 
                        if (ConfigFileHelper.isSessionValid(request) == false) {
            %>
                            top.location.href="<%=request.getContextPath()%>/logoff.do";
            <%
                        } 
            %>
    
            <%
                        if (statusBean.getCycleMode()) { 
            %>
                            document.location="<%=request.getContextPath()%>/statusTray.do?action=next>";	
            <% 
                        } else {
            %> 
                            document.location="<%=request.getContextPath()%>/statusTray.do";	
            <%
                        }
            %>
        }
}

</script>
<style type="text/css">
.column-head {  text-indent: .15em; font-family: Arial,Helvetica, sans-serif; font-size: 70%; font-weight:bold; text-align: left; color: #FFFFFF; background-color:#6B7A92;}  
</style>
</head>
<tiles:useAttribute id="refresh" name="refresh" classname="java.lang.String" />
<tiles:useAttribute name="statusPanels" classname="java.util.List" />

<%if (!statusBean.isInitialized()) {  %>
<logic:iterate id="item" name="statusPanels" type="org.apache.struts.tiles.beans.SimpleMenuItem">
        <% statusBean.setNameText(item.getLink()); %>
        <% statusBean.setValueText(item.getValue()); %>
        <% statusBean.setIconText("DS"+item.getIcon()+"DS"); %>
        <% statusBean.store(); %>
</logic:iterate>

<% statusBean.setInitialized(true); 
 statusBean.cycle(); } %>
 
<% int refreshInSeconds = Integer.parseInt(refresh) /  1000; %>
 
<% UserPreferenceBean uBean = (UserPreferenceBean)session.getAttribute(Constants.USER_PREFS);
    Boolean refreshOn = new Boolean(uBean.getProperty("UI/statusTray#refreshEnabled", "true"));
    String refreshRate = uBean.getProperty("UI/statusTray#refreshRate", ""+refreshInSeconds); 
    Boolean cycleOn =  new Boolean(uBean.getProperty("UI/statusTray#cycle", "true")); 
    statusBean.setCycleMode(cycleOn.booleanValue());
%>
 

<% if (refreshOn.booleanValue()) { 
		int refreshVal = Integer.parseInt(refreshRate);
		if (refreshVal > 2147483)
			refreshVal = 2147483;
	%>
            <body class="system-tray" onload="timer=setTimeout('doRefresh()',<%=1000 * refreshVal%>)" >  
<%} else { %>
    <body class="system-tray">
<% } %>

<form method="post" action="<%=request.getContextPath()%>/statusTray.do" name="myform">
  <table border="0" cellpadding="3" cellspacing="0" valign="top" width="100%" summary="Framing Table" >
    <tr> 
      <td class="layout-manager" id="notabs"> 


        <table border="0" cellpadding="2" cellspacing="0" valign="top" width="100%" summary="Framing Table" >
          <tr> 
            <td class="was-message-item" nowrap><bean:message key="statustray.title" />
            	<ibmcommon:info image="help.additional.information.image" topic="statusTray"/>
            </td>
			<td class="was-message-item" nowrap><input type="submit" name="action" value='<bean:message key="statustray.prev.button"/>' class="buttons" id="paging"><input type="submit" name="action" value='<bean:message key="statustray.next.button"/>' class="buttons" id="paging"> </td>
            <td class="tray-text" nowrap width="10%"> 
            <%@ page import = "java.util.*, java.text.*"%><%= DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,request.getLocale()).format(new Date()) %> 
              <a href="<%=request.getContextPath()%>/statusTray.do" target="_self"> 
              <img src="<%=request.getContextPath()%>/images/refresh.gif"  alt="<bean:message key="refresh.data"/>" align="texttop" border="0"></a></td>
          </tr>
         </table>
         
           <% if (statusBean.getRendererUri() != null && !statusBean.getRendererUri().equals(""))  {%>
           
               <table border="0" cellpadding="2" cellspacing="0" valign="top" width="100%" summary="Framing Table" >

                <!--<tr> 
                  <td class="column-head" nowrap> <a href="<%=statusBean.getLink() %>" target="WAS_Status"> <%= statusBean.getTitle() %> </a>
                  </td>
                </tr>-->
               <tr align="center"> 
                 <td nowrap>
                       
                        <tiles:insert page="<%=statusBean.getRendererUri()%>"/>
                </td>
               </tr>
               </table>
               
            <% } %>
        


<% if (myshow.equals("collapsed")) { %> 
        <table border="0" cellpadding="2" cellspacing="0" valign="top" width="100%" summary="Framing Table" >
          <tr valign="top"> 
            <td class="find-filter" valign="bottom" align="left" width="33%" nowrap > 
              <input type="image" id="prefs" src="<%=request.getContextPath()%>/images/lplus.gif" name="submitS"  alt="<bean:message key="show.preferences"/>" align="texttop" border="0">
              <bean:message key="consolehome.preferences" />
              <input type="hidden" name="show" value="collapsed">
            </td>
          </tr>
        </table>

<% } else { %> 

        <table border="0" cellpadding="2" cellspacing="0" valign="top" width="100%" summary="Framing Table" >
          <tr valign="top"> 
            <td class="find-filter" valign="bottom" align="left" width="33%" nowrap > 
              <input type="image" id="prefs" src="<%=request.getContextPath()%>/images/lminus.gif" name="submitS"  alt="<bean:message key="show.preferences"/>" align="texttop" border="0">
              <bean:message key="consolehome.preferences" />
              <input type="hidden" name="show" value="expanded">
            </td>
          </tr>
          <tr> 
            <td class="find-filter-expanded"> 
              <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table">
                <tr valign="top"> 
                  <td class="table-text"    nowrap valign="top"> 
                    <bean:message key="statusTray.preference.refresh" /></td>
                  <td class="table-text"    nowrap valign="top" width="30%"> 
                    <table width="100%" border="0" cellspacing="1" cellpadding="2">
                      <tr> 
                        <td nowrap class="table-text"> 
                            <%Boolean cycle = new Boolean(uBean.getProperty("UI/statusTray#cycle", "true")); 
                            statusBean.setCycleMode(cycle.booleanValue()); %>
                          <input type="checkbox" name="refreshOn" value="<%= (refreshOn.booleanValue()) ? "true" : "false" %>" <%= (refreshOn.booleanValue()) ? "checked" : ""%>>
                          <bean:message key="statusTray.preference.enable" /></td>
                      </tr>
                      <tr> 
                        <td nowrap class="complex-property"><bean:message key="statusTray.preference.interval" />
                          <input type="text" name="name2" size="15" lang="en_US" value="<%=refreshRate%>">
                          <bean:message key="statusTray.preference.units" /></td>
                      </tr>
                      <tr> 
                        <td nowrap class="complex-property"> 
                          <input type="checkbox" name="cycleOn" value="<%= (cycle.booleanValue()) ? "true" : "false" %>" <%= (cycle.booleanValue()) ? "checked" : ""%>>
                          <bean:message key="statusTray.preference.cycle" /></td>
                      </tr>
                    </table>
                  </td>
                  <td class="table-text" valign="top" > <ibmcommon:info image="help.additional.information.image.align" topic="statusTray.preference.description"/>
                    <bean:message key="statusTray.preference.description" />
                  </td>
                </tr>
                <tr> 
                  <th valign="top" class="button-section" colspan="3"> 
                    <input type="submit" name="submit2" value="<bean:message key="button.apply"/>" class="buttons" id="navigation">
                    <input type="reset" name="reset" value="<bean:message key="button.reset"/>" class="buttons" id="navigation">
                  </th>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <% } %> 


      </td>
    </tr>
  </table>
</form>
</body>
</html:html>
