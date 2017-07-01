<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page import="java.util.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>

   <tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
   <tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   <tiles:useAttribute id="formAction" name="formAction" classname="java.lang.String"/>
   <tiles:useAttribute id="formType" name="formType" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>
   <bean:define id="traceOptionValuesMap" name="<%=formBean%>" property="traceOptionValuesMap" type="java.util.List"/>
   <bean:define id="traceGroupsMap" name="<%=formBean%>" property="traceGroupsMap" type="java.util.HashMap"/>
   <bean:define id="selectedComponents" name="<%=formBean%>" property="selectedComponents" type="java.lang.String"/>
   <bean:define id="perspective" name="<%=formBean%>" property="perspective" type="java.lang.String"/>

   

<td class="table-text"  scope="row" valign="top" nowrap>
    <label  for="{attributeName}"><bean:message key="<%=label%>"/></label>
</td>
        
<% if (isRequired.equalsIgnoreCase("yes")) { %>
            <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
<% } %>    
                                   
<td class="table-text" nowrap valign="top">

<NOSCRIPT>

	<table border="0" cellspacing="0" cellpadding="3">
		<tr valign="top">
			<th class="column-head-name" nowrap > 
                  <bean:message key="trace.component.header"/>
			</th>
			<th class="column-head-name" nowrap > 
				<bean:message key="trace.level.header"/>
			</th>             
			<th class="column-head-name" nowrap > 
				<bean:message key="trace.specification.header"/>
			</th>           
        </tr>
        		
		<tr valign="top"> 
			  <td class="table-text" nowrap > 
				  <html:select property="<%=property%>" name="<%=formBean%>" size="5" multiple="true" disabled="<%=val%>">
				  <%
                      TreeSet groups = new TreeSet(traceGroupsMap.values());

                      for (Iterator i = groups.iterator(); i.hasNext();)
                      {
                          String descript = (String) i.next(); 

			 descript=descript.trim();
				                         if (!descript.equals("")) {
					         %>
					                        <html:option value="<%=descript%>"><%=descript%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=descript%>"><bean:message key="none.text"/></html:option>

                 				         <%     
                         				         }

                      }
                      
				 
                 for (int i=0; i < traceOptionValuesMap.size(); i++)
				 { 
				     String descr = (String)traceOptionValuesMap.get(i);
				
				                         if (!descr.equals("")) {
					         %>
					                        <html:option value="<%=descr%>"><%=descr%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=descr%>"><bean:message key="none.text"/></html:option>
                 				         <%     
                         				         }
				 }

%>                  
                  
				  </html:select>
				
			  </td>
  
			  <td class="table-text" nowrap >
					<table border="0" cellspacing="0" cellpadding="3">
					 <tr valign="top">
					  <td class="table-text" nowrap  > 
					 
					  <html:select property="traceLevel" name="<%=formBean%>"  styleId="aselect" disabled="<%=val%>">
					  <%
					  	 HashMap traceLevelsMap = (HashMap) request.getAttribute("traceLevelsMap");
						 for (Iterator i = traceLevelsMap.keySet().iterator(); i.hasNext();)
					     {
					     	 String value = (String) i.next(); 
					         String descr = (String) traceLevelsMap.get(value);
					
						   if (!descr.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=descr%></html:option>
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
					  </tr>
					  
					 <tr valign="top">
					  <td class="table-text" nowrap  > 
					<input type="submit" name="Add"  value="<bean:message key="probdetermination.button.add"/>" class="buttons" id="other">
					  </td>
					 </tr>
					</table> 
			   </td>

			  <td class="table-text" nowrap >
			   <% if (perspective.equalsIgnoreCase("tab.runtime")){ %>
			<html:textarea property="selectedComponentsRuntime" name="<%=formBean%>" rows="5" cols="40" disabled="<%=val%>"/>
			   <% }else { %>
			<html:textarea property="selectedComponents" name="<%=formBean%>" rows="5" cols="40" disabled="<%=val%>"/>
			   <% }%>
			  </td>
		</tr>
    </table>
</NOSCRIPT>

<SCRIPT>


                   <% if (perspective.equalsIgnoreCase("tab.runtime")){ %>
                   
		       document.write('<html:textarea property="selectedComponentsRuntime" name="<%=formBean%>" rows="5" disabled="<%=val%>"/>');
                   <% }else { %>
		       document.write('<html:textarea property="selectedComponents" name="<%=formBean%>" rows="5" cols="20" disabled="<%=val%>"/>');
                   <% }%>
<%
	ServletContext servletContext = (ServletContext)pageContext.getServletContext();
	MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
	String modifyString = messages.getMessage(request.getLocale(),"Tuning.button.modify");   
%>
                        document.write("<br><input type='button' value='<%=modifyString%>' class='buttons' id='other' onclick='modifyTrace()' >");
                        


<%
       session.setAttribute("traceOptionValuesMap", traceOptionValuesMap);
       session.setAttribute("traceGroupsMap", traceGroupsMap);

%>
    
      
var tempTraceSpec = "";
function modifyTrace() {

        if (document.forms[0].selectedComponents == null) {
                tempTraceSpec =  document.forms[0].selectedComponentsRuntime.value;
        }
        else {
                tempTraceSpec =  document.forms[0].selectedComponents.value;
        }

        if (!document.layers) {
                var tracewin = window.open("<%=request.getContextPath()%>/com.ibm.ws.console.probdetermination/trace_tree.jsp?view=groups","tracewin","status=yes toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,width=550,height=600");        
        } else {
                var tracewin = window.open("<%=request.getContextPath()%>/com.ibm.ws.console.probdetermination/trace_tree_ns.jsp?view=groups","tracewin","status=yes toolbar=no,location=yes,directories=yes,menubar=no,scrollbars=yes,resizable=yes,width=550,height=600");        
        }

}


</SCRIPT>
</td>
        


    

   
   
 

