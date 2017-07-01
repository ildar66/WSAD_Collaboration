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
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   <tiles:useAttribute id="formType" name="formType" classname="java.lang.String"/>
   <tiles:useAttribute id="formAction" name="formAction" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>
   
   <bean:define id="totalRows"     name="<%=formBean%>" property="totalRows" type="java.lang.String"/>
   <bean:define id="filteredRows"  name="<%=formBean%>" property="filteredRows" type="java.lang.String"/>

   
    <html:form action="<%=formAction%>" name="<%=formBean%>" type="<%=formType%>" >
   
   <tr valign="top">

        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="atextfield"> <bean:message key="<%=label%>" /></label>                                
        </td>

        <td class="table-text" nowrap valign="top">
        
            <% if (isRequired.equalsIgnoreCase("yes")) { %>
                        <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
            <% } %>    
                    
            <% if (isReadOnly.equalsIgnoreCase("yes")) { %>
                <bean:write property="<%=property %>" name="<%=formBean%>"/>
            <% } else { %>
                 <%System.out.println("The Value of property text field button is "+property); %>
                 <%System.out.println("The Value ofform name is  is "+formBean); %>
                 <%System.out.println("The Value formActionis "+formAction); %>
                <html:text property="<%=property%>" name="<%=formBean%>" size="<%=size%>" styleId="atextfield"/>
            <% } %>
            
        </td>

        <td class="table-text" nowrap width="1%" COLSPAN="5"> 
              <input type="submit" name="Refresh"  value="<bean:message key="probdetermination.button.refresh"/>" class="buttons" id="navigation">
        </td>
 
       
   </tr>
</html:form>
      

   <tr valign="top">
   <BR>Total rows: <%=totalRows%>  Filtered rows: <%=filteredRows%><BR>
   </tr>

    

   
   
 
