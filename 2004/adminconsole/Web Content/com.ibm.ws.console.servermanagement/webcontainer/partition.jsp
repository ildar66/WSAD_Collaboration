<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.webcontainer.*"%>



<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>


<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr valign="top"> 
  <td class="table-text" nowrap width="1%"> 
  
<% DRSSettingsDetailForm form = (DRSSettingsDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.DRSSettingsDetailForm"); %>
  
    <html:select property="leftSelection" size="5" multiple="true" disabled="<%=val%>">
    <% Object[] objArr = form.getLeftList().toArray(); %>
    <% for (int i=0; i < objArr.length; i++) {%>
        <html:option value="<%=objArr[i].toString()%>"> <%=objArr[i].toString()%> </html:option>
    <% } %>
    </html:select>
  </td>
  <td class="table-text" nowrap width="1%" align="center"> 
    <input type="submit" name="submit2222" value="Add >" class="buttons" id="other" <%=(val) ? "disabled" : ""%>>
    <br>
    <br>
    <input type="submit" name="submit2222" value="< Remove" class="buttons" id="other" <%=(val) ? "disabled" : ""%>>
  </td>
  <td class="table-text" nowrap> 
    <html:select property="rightSelection" size="5" multiple="true" disabled="<%=val%>">
    <% Object[] objArr = form.getRightList().toArray(); %>
    <% for (int i=0; i < objArr.length; i++) {%>
        <html:option value="<%=objArr[i].toString()%>"> <%=objArr[i].toString()%> </html:option>
    <% } %>
    </html:select>
  </td>
</tr>

</table>

