<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%@ page import="com.ibm.ws.console.servermanagement.webcontainer.*"%>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>
		

<% TuningParamsDetailForm form = (TuningParamsDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.TuningParamsDetailForm"); %>

              
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr> 
  <td class="table-text" nowrap> 
    <input type="checkbox" id="inv" name="scheduleInvalidation" value="on" <%= (form.getScheduleInvalidation()) ? "checked" : "" %> <%=(val) ? "disabled" : ""%>>
    <label for="inv"><bean:message key="TuningParams.database.displayName"/></label>
  </td>
</tr>
<tr> 
  <td class="complex-property">    
    <label for="short"><bean:message key="TuningParams.database.first"/></label>
    <html:text property="firstHour" disabled="<%=val%>" size="8" styleId="short"/>
  </td>
</tr>
<tr> 
  <td class="complex-property">
    <label for="short"><bean:message key="TuningParams.database.second"/></label>
    <html:text property="secondHour" size="8" styleId="short" disabled="<%=val%>"/>
  </td>
</tr>
</table>

