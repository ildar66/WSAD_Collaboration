<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<%@ page import="java.util.*" %>
<%@ page import="com.ibm.ws.console.core.item.*" %>
<%@ page import="com.ibm.ws.console.core.bean.*" %>
<%@ page import="com.ibm.ws.console.core.*" %>


<%--tiles:useAttribute id="preferences" name="preferences" classname="java.util.List"/--%>
<html:form action="/preferenceAction.do" method="get" styleClass="nopad" target="_top">

        <table  border="0" cellpadding="0" cellspacing="0" valign="top" width="100%" summary="Framing Table" >
          <tbody>
          <tr> 
            <td class="find-filter"> 
              <table class="framing-table" border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table">
                <tr>
                    <td valign=top class="column-head"  colspan="3"><bean:message key="desc.preferences.title"/></td>
                </tr>
                
              <% 
                int i = 0;  
                List prefsList = (List)request.getAttribute("preferences");
                UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(Constants.USER_PREFS);
                String dataType="";
                String node="";
                String defaultValue="";
                String units = ""; 
                String HTMLFormElementName=""; 
                String HTMLDataTypeName=""; 
                String HTMLNodeName=""; 
                String HTMLDefaultValueName="";

                for (Iterator iter = prefsList.iterator(); iter.hasNext();) {
                  i++;
                  PreferenceItem item = (PreferenceItem) iter.next();
              %>
              
                <tr>
                  <%
                    if (item.getPrefType().equals("text")) {
                        dataType = item.getDataType();          
                        node = item.getPrefEntry(); 
                        defaultValue = item.getDefaultValue();
                        units = item.getUnits(); 
                        HTMLFormElementName = "text"+i;
                        HTMLDataTypeName = "dataType"+i; 
                        HTMLNodeName = "node"+i;
                        HTMLDefaultValueName = "defaultValue"+i;
                   %>    
                        
                        <td class="table-text"  scope="row" valign="top" nowrap>
                           <label> <bean:message key="<%=item.getLabel()%>" /></label>                                
                        </td>

                        
                        
                        <% if (node != null) { %>
                             <% if (defaultValue != null) {%>
                                <td class="table-text" nowrap valign="top">
                                    <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" value="<%=uBean.getProperty(node, defaultValue)%>" />
                                    <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                                </td>                                 
                             <%} else {%>
                                <td class="table-text" nowrap valign="top">
                                    <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" value="<%=uBean.getProperty(node, " ")%>" />
                                    <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                                </td>
                             <%}%>
                        <%} else { %>
                             <td class="table-text" nowrap valign="top">
                                 <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" />
                                 <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                             </td>  
                        <%}%>

                        <INPUT type="hidden"  name="<%=HTMLDataTypeName%>"     value="<%=dataType%>" />
                        <INPUT type="hidden"  name="<%=HTMLNodeName%>"         value="<%=node%>" />
                        <INPUT type="hidden"  name="<%=HTMLDefaultValueName%>" value="<%=defaultValue%>" /> 
                        
                  <% 
                  } else if (item.getPrefType().equals("checkbox")) {
                        node = item.getPrefEntry(); 
                        defaultValue = item.getDefaultValue(); 
                        HTMLFormElementName = "checkbox"+i;
                        HTMLNodeName = "node"+i; 
                        HTMLDefaultValueName = "defaultValue"+i; 
                  %>   
                   
                       <td class="table-text"  scope="row" nowrap>
                           <label><bean:message key="<%=item.getLabel()%>"/></label>
                       </td>
                       
                       
                       <%if (node != null) { %> 
                        
                           <%if (defaultValue == null) {
                               defaultValue = "false";
                             }

                           String val = uBean.getProperty(node, defaultValue); %>
                            <td class="table-text" nowrap valign="top">
                                    <INPUT type="checkbox" name="<%=HTMLFormElementName%>"  <%=((val.equals("true")) ? "CHECKED" : "")%> /> 
                            </td>
                       <%}%>
                                                  
                        <INPUT type="hidden"   name="<%=HTMLNodeName%>"         value="<%=node%>" />
                        <INPUT type="hidden"   name="<%=HTMLDefaultValueName%>" value="<%=defaultValue%>" />
                        
                    
                        
               <%}%>
                   
                  <td class="table-text" valign="top" width="33%">
				      <bean:message key="<%=item.getDesc()%>"/>
			      </td>
               
                </tr>
           <%}%>
       


                   
              <INPUT type="hidden" name="counter" VALUE="<%=i%>" />
                                    <tr> 
                  <th valign="top" class="button-section" colspan="3"> 
                    <html:submit property="submit2" styleClass="buttons" styleId="navigation"><bean:message key="button.apply"/></html:submit>
                    <html:reset styleClass="buttons" styleId="navigation"><bean:message key="button.reset"/></html:reset>
                  </th>
                </tr>
              </table>
            </td>
          </tr>
          </tbody>
        </table>
</html:form>

                 
                                        

            