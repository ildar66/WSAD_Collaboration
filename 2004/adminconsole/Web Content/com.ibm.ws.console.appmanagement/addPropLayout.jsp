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

<tiles:useAttribute id="list" name="list" classname="java.util.List"/>
<tiles:useAttribute id="taskList" name="taskList" classname="java.util.List"/>
<tiles:useAttribute name="formName" classname="java.lang.String"/>

<bean:define id="resourceUri" name="<%=formName%>" property="resourceUri"/>
<bean:define id="refId"    name="<%=formName%>" property="refId"/>
<bean:define id="action"    name="<%=formName%>" property="action"/>
<bean:define id="contextId"    name="<%=formName%>" property="contextId"/>
<bean:define id="perspective"    name="<%=formName%>" property="perspective"/>

<%if (!((String)action).equalsIgnoreCase("New")) { %>


<table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tbody>
          <tr> 
            <th colspan="2" class="column-head" scope="rowgroup">  
               <a name="additional"></a><bean:message key="config.additional.properties"/></th>
          </tr>
          
         <logic:iterate id="item" name="list" type="org.apache.struts.tiles.beans.SimpleMenuItem">
          
         <tr> 
        <td valign="top" class="table-text"  nowrap >
        <% String link = item.getLink() + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;  %>
        <a href="<%= link   %>"><bean:message key="<%= item.getValue() %>"/></a><br>
        </td>
        <td valign="top" class="table-text"  >
        <bean:message key="<%= item.getTooltip() %>"/>
        </td>
        </tr>
        </logic:iterate>
	
	<logic:iterate id="task" name="taskList" type="org.apache.struts.tiles.beans.SimpleMenuItem">
	   <% StringTokenizer st = new StringTokenizer(task.getLink(), ":");
	      String form = (String)st.nextElement();
	      if ( session.getAttribute(form) != null ) {
		  String forward = (String)st.nextElement();
		  %>
           <tr> 
           <td valign="top" class="table-text"  nowrap >
           <% String link = forward + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;  %>
           <a href="<%= link   %>"><bean:message key="<%= task.getValue() %>"/></a><br>
           </td>
           <td valign="top" class="table-text"  >
           <bean:message key="<%= task.getTooltip() %>"/>
           </td>
           </tr>	
	   <% } //end if form is in context%>  
	</logic:iterate>
        </tbody>
       </table>

<% } %>
        
