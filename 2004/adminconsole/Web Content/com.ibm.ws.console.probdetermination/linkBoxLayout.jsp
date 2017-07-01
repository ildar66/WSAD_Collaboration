<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<ibmcommon:detectLocale/>


<tiles:useAttribute id="list" name="list" classname="java.util.List"/>
<tiles:useAttribute id="formName" name="formName" classname="String"/>
<tiles:useAttribute id="formAction" name="formAction" classname="String"/>
<tiles:useAttribute id="formType" name="formType" classname="String"/>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>Detail Page</TITLE><script language="JavaScript" src="scripts/menu_functions.js"></script>
<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>


</head>


        <%
	ServletContext servletContext = (ServletContext)pageContext.getServletContext();
	MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
	String thisTitle = messages.getMessage(request.getLocale(),"Domain.logsandtrace.displayName");   
        %>
        
<html:html locale="true">

<table border="0" cellpadding="10" cellspacing="0" width="100%" summary="Properties Framing Table" >
<TR>
<TD class="layout-manager"  ID="notabs">

<table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" >
  <TBODY>
  <tr> 
    <th colspan="2" class="column-head" scope="rowgroup"> <a name="generalProperties"></a><%=thisTitle%></th>
  </tr>

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
          
    <logic:iterate id="item" name="list" type="org.apache.struts.tiles.beans.SimpleMenuItem">
  <%
        String contextId = request.getParameter("contextId");
        if (contextId == null || contextId.trim().length() == 0) 
        {
         
         com.ibm.ws.console.probdetermination.form.LogsAndTraceDetailForm detailForm = (com.ibm.ws.console.probdetermination.form.LogsAndTraceDetailForm)session.getAttribute(formName);
          contextId = detailForm.getContextId();
        }
		String hRef = item.getLink()+"&contextId="+contextId;
   %>
    <td valign="top" class="table-text" headers="header2" nowrap width="20%" > 
    <a href="<%=hRef%>"><bean:message key="<%= item.getValue() %>"/></a><br>
    </td>
        
        <td valign="top" class="table-text" headers="header2" >
        <bean:message key="<%= item.getTooltip() %>"/>
        </td>
        </tr>
        </logic:iterate>
        </TBODY>
       </table>
       </TD>
       </TR>
       </TABLE>
</html:form>

</html:html>   
