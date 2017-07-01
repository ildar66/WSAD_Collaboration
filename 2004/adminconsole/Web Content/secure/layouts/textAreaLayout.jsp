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

   <tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
   <tiles:useAttribute id="isReadOnly" name="isReadOnly" classname="java.lang.String"/>
   <tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute id="size" name="size" classname="java.lang.String"/>
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>
   

        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="<%=property%>"><bean:message key="<%=label%>"/></label>
        </td>    

    <% if (isReadOnly.equalsIgnoreCase("yes")) { %>
        <td class="table-text" valign="top">
        <% if (isRequired.equalsIgnoreCase("yes")) { %>
             <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
        <% } %>    
             <bean:write property="<%=property %>" name="<%=formBean%>"/>
    <% } else { %>
        <td class="table-text" nowrap valign="top">
        <% if (isRequired.equalsIgnoreCase("yes")) { %>
             <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
        <% } %>    
             <html:textarea property="<%=property%>" name="<%=formBean%>" cols="38" rows="<%=size%>" styleId="atextarea"/>
        <% } %>
        <% if (units != null && !units.equals("") && !units.equals(" ")) { %> <bean:message key="<%=units%>"/> <% } %>
        </td>
       

    

   
   
 
