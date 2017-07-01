<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.ws.security.core.SecurityContext"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
<tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>
<tiles:useAttribute id="property" name="property" classname="java.lang.String"/>

<bean:define id="bean" name="<%=formBean%>"/>

<%  boolean disable = false;
    if (readOnly != null && readOnly.equalsIgnoreCase("true")) {
        disable = true;
    } else if (SecurityContext.isSecurityEnabled()) {
        disable = true;
        if (request.isUserInRole("administrator")) {
            disable = false;
        }
    }
%>

<script language="JavaScript">
<!-- hide from old browsers
function enableJava2(theForm) {
  if (theForm.enabled.checked){
    theForm.enforceJava2Security.checked = true;
  }
}
// end script hiding -->
</script>

    <td class="table-text"  scope="row" nowrap>
        <label  for="<%= property%>"><bean:message key="<%=label%>"/></label>
    </td>

    <td class="table-text" nowrap valign="top">

        <% if (disable) { %>
            <bean:write property="<%=property %>" name="<%=formBean%>"/>
        <% } else { %>
            <% if (property.equals("enabled")) { %>
                <html:checkbox property="<%=property%>" name="<%=formBean%>" styleId="<%=property%>"onclick="enableJava2(this.form)" onkeypress="enableJava2(this.form)"/>
            <% } else { %>
                <html:checkbox property="<%=property%>" name="<%=formBean%>" styleId="<%=property%>"/>
            <% } %>
        <% } %>
    </td>

