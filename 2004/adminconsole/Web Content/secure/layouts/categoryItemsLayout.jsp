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
<tiles:useAttribute name="formName" classname="java.lang.String"/>
<tiles:useAttribute name="categoryName" classname="java.lang.String"/>

<bean:define id="resourceUri" name="<%=formName%>" property="resourceUri"/>
<bean:define id="refId"    name="<%=formName%>" property="refId"/>
<bean:define id="action"    name="<%=formName%>" property="action"/>
<bean:define id="contextId"    name="<%=formName%>" property="contextId"/>
<bean:define id="perspective"    name="<%=formName%>" property="perspective"/>

<BR>

<table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
   <tbody>
   <tr> 
       <th colspan="2" class="column-head" scope="rowgroup"> <a name="additionalProperties"></a><bean:message key="<%=categoryName%>"/></th>
   </tr>

   <logic:iterate id="item" name="list" type="org.apache.struts.tiles.beans.SimpleMenuItem">
     <TR>
     <TD VALIGN="TOP" CLASS="table-text" NOWRAP width="10%"> 
     <% String link = item.getLink() + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;  %>     
     <A HREF= <%= link %> ><bean:message key="<%= item.getValue() %>"/></A>
     </TD>
     <TD VALIGN="TOP" CLASS="table-text" ><bean:message key="<%= item.getTooltip() %>"/>
     </TD>
     </TR>
   </logic:iterate>
 </tbody>   
 </table>
