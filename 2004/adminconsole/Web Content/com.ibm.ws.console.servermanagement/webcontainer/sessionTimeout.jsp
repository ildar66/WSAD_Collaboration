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

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>
                    
<table width="100%" border="0" cellspacing="2" cellpadding="0">
<tr> 
  <td width="1%" class="table-text"> 
    <html:radio property="timeout" value="none" styleId="vnone" disabled="<%=val%>">
    <label for="vnone"><bean:message key="SessionManager.sessionTimeout.noTimeout"/></label>
    </html:radio>
   </td>
</tr>
<tr> 
  <td width="1%" class="table-text"> 
    <html:radio property="timeout" value="value" styleId="vto" disabled="<%=val%>">
    <label for="vto"><bean:message key="SessionManager.sessionTimeout.setTimeout"/></label>
    </html:radio>
  </td>
</tr>
<tr> 
  <td width="1%" class="complex-property"> 
    <html:text property="invalidationTimeout" size="20" styleId="atextfield" disabled="<%=val%>"/>
    <label for="atextfield"><bean:message key="SessionManager.sessionTimeout.units"/></label>
    </td>
</tr>
</table>

