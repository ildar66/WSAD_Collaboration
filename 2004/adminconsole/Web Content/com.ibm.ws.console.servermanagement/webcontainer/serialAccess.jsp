<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.webcontainer.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

                    
<%--tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/--%>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>

<%--bean:define id="bean" name="<%=formBean%>"/--%>

<% SessionManagerDetailForm form = (SessionManagerDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.SessionManagerDetailForm"); %>

<% String resourceUri = form.getResourceUri();
    String refId = form.getRefId();
    String contextId = form.getContextId();
    String perspective = form.getPerspective(); %>

<% String link = "com.ibm.ws.console.servermanagement.forwardCmd.do?forwardName=Cookie.config.view&sfname=defaultCookieSettings" + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;  %>



<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr> 
  <td width="1%" class="table-text"> 
    <html:checkbox property="allowSerializedSessionAccess" styleId="acheckbox1" disabled="<%=val%>"/>
    <label for="acheckbox1"><bean:message key="SessionManager.serialAccess.units"/></label>
  </td>
</tr>
<tr> 
  <td width="1%" class="complex-property" nowrap> 
  		<bean:message key="SessionManager.maxWaitTime.displayName"/> :
        <html:text property="maxWaitTime" size="8" styleId="atextfield" disabled="<%=val%>"/>
        <bean:message key="SessionManager.maxWaitTime.units"/>
   </td>
</tr>

<tr> 
  <td width="1%" class="complex-property" nowrap> 
    <html:checkbox property="accessSessionOnTimeout" styleId="acheckbox4" disabled="<%=val%>"/>
    <label for="acheckbox4"><bean:message key="SessionManager.accessOnTimeout.units"/></label>
</td>
</tr>                
</table>