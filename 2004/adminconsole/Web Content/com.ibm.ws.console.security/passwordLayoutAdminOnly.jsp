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
<tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String"/>
<tiles:useAttribute id="size" name="size" classname="java.lang.String"/>
<tiles:useAttribute id="units" name="units" classname="java.lang.String"/>

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
        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="<%=property%>"> <bean:message key="<%=label%>" /></label>                                
        </td>

        <td class="table-text" nowrap valign="top">

            <% if (isRequired.equalsIgnoreCase("yes")) { %>
                        <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
            <% } %>

            <% if (disable) { %>
				******
            <% } else { %>
                <html:password property="<%=property%>" name="<%=formBean%>" size="<%=size%>" styleId="<%=property%>"/>
            <% } %>

            <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
        </td>

