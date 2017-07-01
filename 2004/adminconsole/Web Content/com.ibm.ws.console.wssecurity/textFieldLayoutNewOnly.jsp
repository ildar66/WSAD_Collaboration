<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.ws.security.core.SecurityContext"%>
<%@ page import="com.ibm.ws.console.core.form.AbstractDetailForm"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
<tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
<tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
<tiles:useAttribute name="readOnly" classname="java.lang.String"/>
<tiles:useAttribute name="property" classname="java.lang.String"/>
<tiles:useAttribute name="isRequired" classname="java.lang.String"/>
<tiles:useAttribute name="size" classname="java.lang.String"/>
<tiles:useAttribute name="units" classname="java.lang.String"/>

<bean:define id="bean" name="<%=formBean%>"/>

<% 	boolean disable = false;
	if (readOnly != null && readOnly.equals("true")) {
		disable = true;
      } else {
          AbstractDetailForm form = (AbstractDetailForm) session.getAttribute(formBean);		
          String action = null;
          if (form != null) {
              action = form.getAction();
          }
          else {
              System.out.println("ERROR: no " + formBean + " found in session for textFieldLayoutNewOnly.jsp");
          }
          if (action != null && !action.equalsIgnoreCase("NEW")) {
              disable = true;
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
                <bean:write property="<%=property %>" name="<%=formBean%>"/>
            <% } else { %>
                <html:text property="<%=property%>" name="<%=formBean%>" size="<%=size%>" styleId="<%=property%>"/>
            <% } %>

            <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
        </td>
       
