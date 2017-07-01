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
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>

   <%-- // defect 126608
   <tr valign="top">
   --%>

        <td class="table-text"  scope="row" nowrap>
            <label  for="<%= property%>"><bean:message key="<%=label%>"/></label>
        </td>
       
       <td class="table-text" nowrap valign="top">
       
            <% if (isReadOnly.equalsIgnoreCase("yes")) { %>
                <bean:write property="<%=property %>" name="<%=formBean%>"/>
            <% } else { %>
    	        <html:checkbox property="<%= property%>" name="<%=formBean%>" styleId="<%= property%>"/>
            <% } %>

            <% if (units != null && !units.equals("") && !units.equals(" ")) { %><bean:message key="<%=units%>"/> <% } %>

        </td>

   <%-- // defect 126608    
        <td class="table-text" valign="top"><a href="transformer.jsp?xml=was_page_help&xsl=was_page_help" target="WAS_Help">
            <img src="<%=request.getContextPath()%>/images/more.gif" border="0" alt="View more information about this field" align="texttop"></a>
            <bean:message key="<%=desc%>"/>
       </td>
   </tr>
   --%>
    

   
   
 
