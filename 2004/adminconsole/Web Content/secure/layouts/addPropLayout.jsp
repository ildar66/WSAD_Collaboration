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

<tiles:useAttribute id="list" name="list" classname="java.util.List"/>
<tiles:useAttribute name="formName" classname="java.lang.String"/>

<bean:define id="resourceUri" name="<%=formName%>" property="resourceUri"/>
<bean:define id="action"    name="<%=formName%>" property="action"/>
<bean:define id="contextId"    name="<%=formName%>" property="contextId"/>
<bean:define id="perspective"    name="<%=formName%>" property="perspective"/>

<% if (list.size() > 0) { %>

  
<%if (!((String)action).equalsIgnoreCase("New")) { %>

<bean:define id="refId"    name="<%=formName%>" property="refId"/>

<table class="framing-table" border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" >
        <tbody>
          <tr> 
            <th colspan="2" class="column-head" scope="rowgroup">  
               <a name="additional"></a><bean:message key="config.additional.properties"/></th>
          </tr>
          
         <logic:iterate id="item" name="list" type="org.apache.struts.tiles.beans.SimpleMenuItem">
          
         <tr> 
        <td valign="top" class="table-text"  nowrap width="10%">
        <%  String linkStr = item.getLink();
            int index = linkStr.indexOf(":target=");
            String target = null;
            String href = "";
            if ( index != -1) {
                target = linkStr.substring(index + 8 );
                href = linkStr.substring(0,index);
            }
            if (target == null) { 
                String link = item.getLink() + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;%>

          <a href="<%= link   %>"><bean:message key="<%= item.getValue() %>"/></a>
          <% } else {
                String link = href + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;%>
                <a href="<%= link   %>" target=<%= target %>><bean:message key="<%= item.getValue() %>"/></a>
          <% } %>
        </td>
        <td valign="top" class="table-text"  >
        <bean:message key="<%= item.getTooltip() %>"/>
        </td>
        </tr>
        </logic:iterate>
        </tbody>
       </table>

<% } %>

<% } %>
        
