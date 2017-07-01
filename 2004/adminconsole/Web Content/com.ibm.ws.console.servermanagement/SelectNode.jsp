<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
      <td class="wizard-step" id="current" headers="header1" width="99%" align="left"> 
        Select the node where the new application server will reside. 
		View <a  href="help_xml_transform.jsp" target='_blank'>more information</a> about this step. 
	  </td>
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
     <tr valign="top"> 
          <td class="table-text"  scope="row" nowrap width="25%">
		      <label  for="{attributeName}">Select node:</label>
          </td>
          <td class="table-text" id="current"> 
                <html:select property="selectedItem" size="1">
                   <logic:iterate id="node" name="<%=actionForm%>" property="serverPath">
<%
String value = (String) node;
value = value.trim();

if (!value.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=value%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=value%>"><bean:message key="<none.text"/></html:option>
                 				         <%     
                         				         }%>
      
			      </logic:iterate>
                </html:select>   
          </td>
          <td class="table-text" valign="top" width="33%"> 
		      <a href="transformer.jsp?xml=was_page_help&xsl=was_page_help" target="WAS_Help">
			    <img src="images/more.gif" width="15" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop">
			  </a>
			  The node that is selected on this step will determine the server processes available from which to choose on the next step.
          </td>
     </tr>
</table>
