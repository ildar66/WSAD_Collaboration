<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
      <td class="wizard-step" id="current" headers="header1" width="99%" align="left"> 
          <bean:message key="appserver.selectAppServer.msg1"/>
      </td>
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="top"> 
          <td class="table-text"  scope="row" nowrap width="25%">
		      <label  for="{attributeName}"><bean:message key="appserver.selectAppServer.msg3"/></label>
          </td>
          <td class="table-text" id="current"> 
                <html:select property="selectedNode" size="1">
                   <logic:iterate id="node" name="<%=actionForm%>" property="nodePath">
<%
String value = (String) node;
value=value.trim();

if (!value.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=value%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=value%>"><bean:message key="<none.text"/></html:option>
                 				         <%     
                         				         }
%>
      

			      </logic:iterate>
                </html:select>   
          </td>
          <td class="table-text" valign="top" width="33%"> 
              <bean:message key="appserver.selectAppServer.msg5"/>
          </td>
     </tr>
     <tr valign="top"> 
          <td class="table-text"  scope="row" nowrap><bean:message key="appserver.selectAppServer.serverName"/></td>
          <td class="table-text" nowrap>
		       <img src="images/attend.gif" width="6" height="15" align="texttop"> 
                <html:text property="serverName" size="30" />
                &nbsp;
		  </td>
          <td class="table-text" valign="top">
              <bean:message key="appserver.selectAppServer.msg6"/>
		  </td>
     </tr>
     <tr valign="top">
          <td class="table-text"  nowrap valign="top"><bean:message key="generate.unique.ports.label"/></td>
          <td class="table-text"  nowrap valign="top"> 
               <html:checkbox property="generatePort" value="true"/><bean:message key="generate.unique.ports"/>
		   </td>
           <td class="table-text" valign="top">
              <bean:message key="generate.unique.ports.desc"/>
		  </td>    
     </tr>
     
     <tr valign="top"> 
          <td class="table-text"  scope="row" nowrap>
		       <label  for="{attributeName}"><bean:message key="appserver.selectAppServer.msg7"/></label>
		  </td>
          <td class="table-text"><label> 
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <logic:equal name="<%=actionForm%>" property="showDefault" value="true">
                <tr> 
                  <td width="1%" valign="top" class="table-text"> 
                    <html:radio property="radioButton" value="default"/>
                  </td>
                  <td class="table-text"><bean:message key="appserver.selectAppServer.msg10"/></td>
                </tr>
                <tr> 
                  <td width="1%" valign="top" class="table-text">&nbsp;</td>
                  <td class="table-text" id="current"> 
                    <html:select property="defaultServer" size="1">
                      <logic:iterate id="defaultServer" name="<%=actionForm%>" property="defaultServerPath">
                      <% String serverStr = (String) defaultServer;
                       int index = serverStr.indexOf("/");
                       String serverName = serverStr.substring(index+1); 

String value = (String) defaultServer;

value=value.trim();

if (!value.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=value%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=value%>"><bean:message key="none.text"/></html:option>
                 				         <%                              				         
}
%>


			          </logic:iterate>
                    </html:select>   
                  </td>
                </tr>
                </logic:equal>
                <tr> 
                  <td width="1%" valign="top" class="table-text"> 
                    <html:radio property="radioButton" value="existing"/>
                  </td>
                  <td class="table-text"><bean:message key="appserver.selectAppServer.msg8"/></td>
                </tr>
                <tr> 
                  <td width="1%" valign="top" class="table-text">&nbsp;</td>
                  <td class="table-text" id="current"> 
                    <html:select property="selectedItem" size="1">
                      <logic:iterate id="server" name="<%=actionForm%>" property="serverPath">
<%
String value = (String) server;

if (!value.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=value%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value=""><bean:message key="none.text"/></html:option>
                 				         <%     
                         				         }
%>      

			          </logic:iterate>
                    </html:select>   
                  </td>
                </tr>
              </table>
              </label>
		  </td>
          <td class="table-text" valign="top">
              <bean:message key="appserver.selectAppServer.msg9"/>
		  </td>
     </tr>
     <%-- commented for release 5.0 per defect 143522 
     <tr valign="top">
          <td class="table-text"   valign="top"><bean:message key="appserver.selectAppServer.copyApps1"/></td>
          <td class="table-text"   valign="top"> 
              <html:checkbox property="copyApps"/><bean:message key="appserver.selectAppServer.copyApps2"/></td>
          <td class="table-text" valign="top">
              <bean:message key="appserver.selectAppServer.copyApps3"/>
		  </td>    
     </tr>
     --%>
</table>
