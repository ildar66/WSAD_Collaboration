<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.environment.Constants"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
  String type = (String)session.getAttribute ("NameSpaceBindingBindingType");
  if (type == null)
    type = Constants.STRING_BINDINGTYPE;
%>

<table border="0" cellpadding="2" cellspacing="0" width="100%" id="expandable">
  <tbody>
  <tr>
    <th class="column-head-name" scope="col" width="20%" valign="TOP"><bean:message key="NameSpaceBinding.bindingType.displayName"/>
    </th>
    <td class="table-text" scope="col">
<% if (type.equals (Constants.STRING_BINDINGTYPE)) {  %>
        <input type="radio" name="bindingType" value="<%=Constants.STRING_BINDINGTYPE%>" checked><bean:message key="environment.label.string"/><BR>
<% } else { %>
        <input type="radio" name="bindingType" value="<%=Constants.STRING_BINDINGTYPE%>"><bean:message key="environment.label.string"/><BR>
<% } if (type.equals (Constants.EJB_BINDINGTYPE)) { %> 
        <input type="radio" name="bindingType" value="<%=Constants.EJB_BINDINGTYPE%>" checked><bean:message key="environment.label.ejb"/><BR>
<% } else { %>
        <input type="radio" name="bindingType" value="<%=Constants.EJB_BINDINGTYPE%>"><bean:message key="environment.label.ejb"/><BR>
<% } if (type.equals (Constants.CORBAOBJECT_BINDINGTYPE)) { %> 
        <input type="radio" name="bindingType" value="<%=Constants.CORBAOBJECT_BINDINGTYPE%>" checked><bean:message key="environment.label.corba"/><BR>
<% } else { %>
        <input type="radio" name="bindingType" value="<%=Constants.CORBAOBJECT_BINDINGTYPE%>"><bean:message key="environment.label.corba"/><BR>
<% } if (type.equals (Constants.INDIRECTLOOKUP_BINDINGTYPE)) { %> 
        <input type="radio" name="bindingType" value="<%=Constants.INDIRECTLOOKUP_BINDINGTYPE%>" checked><bean:message key="environment.label.indirect"/>
<% } else { %>
        <input type="radio" name="bindingType" value="<%=Constants.INDIRECTLOOKUP_BINDINGTYPE%>"><bean:message key="environment.label.indirect"/>
<% } %>
    </td>
  </tr>
</table>

