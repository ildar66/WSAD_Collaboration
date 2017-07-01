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
   <tiles:useAttribute id="valueVector" name="valueVector" classname="java.util.Vector"/>
   <tiles:useAttribute id="descVector" name="descVector" classname="java.util.Vector"/>
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>
   

        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="{attributeName}"><bean:message key="<%=label%>"/></label>
        </td>
        
            <% if (isRequired.equalsIgnoreCase("true")) { %>
                <span class="required">
                *
                </span>
            <% } %>                                    

        <td class="table-text" nowrap valign="top">
            <html:select property="<%=property%>" name="<%=formBean%>">
                <%
                     if (valueVector == null || valueVector.isEmpty())
                      valueVector = (Vector)session.getAttribute("valueVector");
                     if (descVector == null || descVector.isEmpty())
                      descVector = (Vector)session.getAttribute("descVector");

                     for (int i=0; i < valueVector.size(); i++)
                     { %>
                     <%
                     String value = (String)valueVector.elementAt(i);

                     String descript = (String)descVector.elementAt(i);

			 descript=descript.trim();
				                         if (!descript.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><bean:message key="<%=descript%>"/></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=value%>"><bean:message key="none.text"/></html:option>

                 				         <%     
                         				         }
                     }
                %>
            </html:select>
        </td>
       


    

   
   
 
