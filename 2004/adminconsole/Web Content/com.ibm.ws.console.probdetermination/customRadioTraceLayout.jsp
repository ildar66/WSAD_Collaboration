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

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>
   <tiles:useAttribute  name="label" classname="java.lang.String"/>
   <tiles:useAttribute  name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute  name="units" classname="java.lang.String"/>
   <tiles:useAttribute  name="desc" classname="java.lang.String"/>
   <tiles:useAttribute  name="property" classname="java.lang.String"/>
   <tiles:useAttribute  name="bean" classname="java.lang.String"/>
   <tiles:useAttribute  name="formAction" classname="java.lang.String"/>
   <tiles:useAttribute  name="formType" classname="java.lang.String"/>
   
   <bean:define id="dumpFileName" name="<%=bean%>" property="dumpFileName" type="java.lang.String"/>
   <bean:define id="memoryBufferSize" name="<%=bean%>" property="memoryBufferSize" type="java.lang.String"/>
   <bean:define id="traceFileName" name="<%=bean%>" property="traceFileName" type="java.lang.String"/>
   <bean:define id="rolloverSize" name="<%=bean%>" property="rolloverSize" type="java.lang.String"/>
   <bean:define id="maxNumberOfBackupFiles" name="<%=bean%>" property="maxNumberOfBackupFiles" type="java.lang.String"/>


        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="<%=label%>"><bean:message key="<%=label%>"/></label>
        </td>
        
            <% if (isRequired.equalsIgnoreCase("yes")) { %>
                        <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
            <% } %>    
                                  
        <td class="table-text" nowrap valign="top">
        <FIELDSET id="<%=label%>">
          <table  border="0" cellspacing="0" cellpadding="3">
                <tr valign="top"> 
                  <td class="table-text" nowrap> 
                  <html:radio property="<%=property%>" name="<%=bean%>" value="MEMORY_BUFFER" disabled="<%=val%>">
                        <bean:message key="trace.memory.buffer"/>
                  </html:radio>
                   </td>
                </tr>

               <% if (property.trim().endsWith("Runtime")){ %>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.buffer.size"/>
                    <html:text property="memoryBufferSizeRuntime" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                    <bean:message key="Trace.ringBuffer.units"/>
                  </td>
                </tr>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.dump.file.name"/>
                    <html:text property="dumpFileName" name="<%=bean%>" size="30" disabled="<%=val%>"/>
                  </td>
                </tr>
                <%} else {%>
                
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.buffer.size"/>
                    <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                    <html:text property="memoryBufferSize" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                    <bean:message key="Trace.ringBuffer.units"/>
                  </td>
                </tr>
                <%}%>
               <% if (property.trim().endsWith("Runtime")){ %>
                <tr valign="left"> 
                  <td class="complex-property" nowrap> 
                        <input type="submit" name="Dump"  value="<bean:message key="probdetermination.button.dump"/>" class="buttons" id="other" <%=(val) ? "disabled" : ""%>>
                  </td>
                </tr>

                <%}%>

                <tr valign="top"> 
                  <td class="table-text" nowrap> 
                  <html:radio property="<%=property%>" name="<%=bean%>" value="SPECIFIED_FILE" disabled="<%=val%>">
                        <bean:message key="trace.file.label"/>
                  </html:radio>
                   </td>
                </tr>
                
               <% if (!property.trim().endsWith("Runtime")){ %>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.file.size"/>
                    <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                    <html:text property="rolloverSize" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                    <bean:message key="JVMLogs.maxSize.MB"/>
                  </td>
                </tr>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.historical.files"/>
                    <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                    <html:text property="maxNumberOfBackupFiles" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                  </td>
                </tr>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.file.name"/>
                    <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                    <html:text property="traceFileName" name="<%=bean%>" size="40" disabled="<%=val%>"/>
                  </td>
                </tr>
                <%} else { %>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.file.size"/>
                    <html:text property="rolloverSizeRuntime" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                    <bean:message key="JVMLogs.maxSize.MB"/>
                  </td>
                </tr>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.max.historical.files"/>
                    <html:text property="maxNumberOfBackupFilesRuntime" name="<%=bean%>" size="15" disabled="<%=val%>"/>
                  </td>
                </tr>
                <tr valign="top"> 
                  <td class="complex-property" nowrap> 
                    <bean:message key="trace.file.name"/>
                    <html:text property="traceFileNameRuntime" name="<%=bean%>" size="40" disabled="<%=val%>"/>
                  </td>
                </tr>
                <tr valign="left"> 
                  <td class="complex-property" nowrap > 
                        <input type="submit" name="View"  value="<bean:message key="probdetermination.button.view"/>" class="buttons" id="other" <%=(val) ? "disabled" : ""%>>
                  </td>
                </tr>

                <%}%>
              </table>

         </FIELDSET>
        </td>
        

    

   
   
 

