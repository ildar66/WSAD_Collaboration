<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page import="java.util.*"%>



<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

   <tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
   <tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   <tiles:useAttribute id="multiSelect" name="multiSelect" classname="java.lang.String"/>
   <tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>
   
   
   <bean:define id="bean" name="<%=formBean%>"/>

   <% Vector valueVector = (Vector)session.getAttribute("valueVector");
      Vector descVector = (Vector)session.getAttribute("descVector"); %>


        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="<%=property%>"><bean:message key="<%=label%>"/></label>
        </td>
        
        <% if (readOnly.equals("true"))
        {
            String d = (String) descVector.elementAt(0);%>
        <td class="table-text" nowrap valign="top" >
                  <bean:write property="<%=property%>" name="<%=formBean%>"/>
        </td>
        <%} else {%>
    
        <td class="table-text" nowrap valign="top">
            <% if (isRequired.equalsIgnoreCase("yes")) { %>
                <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
            <% } %> 
            <% if (multiSelect.equalsIgnoreCase("true")) { %>   
	            <html:select property="<%=property%>" name="<%=formBean%>" styleId="<%=property%>" multiple="true">
                <%
                     for (int i=0; i < valueVector.size(); i++)
                     { 
			 String val = (String) valueVector.elementAt(i);
			 String descript = (String) descVector.elementAt(i);
descript = descript.trim();
				                         if (!descript.equals("")) {
					         %>
									<html:option value="<%=val%>"><%=descript%></html:option>
					         <%
				                         } else {
                 				         %>
									<html:option value="<%=val%>"><bean:message key="none.text"/></html:option>

                 				         <%     
                         				         }
                     }
                %>
	            </html:select>
            <% } else { %> 
	            <html:select property="<%=property%>" name="<%=formBean%>" styleId="<%=property%>">
                <%
                     for (int i=0; i < valueVector.size(); i++)
                     { 
			 String val = (String) valueVector.elementAt(i);
			 String descript = (String) descVector.elementAt(i);
			descript = descript.trim();


				                         if (!descript.equals("")) {
					         %>
									<html:option value="<%=val%>"><%=descript%></html:option>
					         <%
				                         } else {
                 				         %>
									<html:option value="<%=val%>"><bean:message key="none.text"/></html:option>


                 				         <%     
                         				         }

                     }
                %>
	            </html:select>
            <% } %> 
            <% if (units != null && !units.equals("") && !units.equals(" ")) { %> <bean:message key="<%=units%>"/> <% } %>
        </td>
        
        <% } %>

    

   
   
 
