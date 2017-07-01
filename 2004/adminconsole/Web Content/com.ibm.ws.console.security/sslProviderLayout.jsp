<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="readOnly" classname="java.lang.String"/>
<tiles:useAttribute id="formName" name="formBean" classname="java.lang.String"/>

<bean:define id="provider" name="<%=formName%>" property="provider"/>

<%
    String isReadOnly;
    if (readOnly.equalsIgnoreCase("true")){
        isReadOnly = "yes";
    } else {
        isReadOnly = "no";
    }
%>

   <table width="100%" border="0" cellspacing="0" cellpadding="2">

        <tr valign="top">
     <% if (readOnly.equals("true")) { %>
          <td class="table-text" nowrap valign="top">
              <bean:write property="provider" name="<%=formName%>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top">
           <html:radio property="selectedProvider" value="known" styleId="selectedProvider"><bean:message key="SecureSocketLayer.provider.predefined"/></html:radio><BR>
          </td>
        </tr>
        <tr valign="top">
          <td class="complex-property"  scope="row" valign="top" nowrap>
            <label  for="providerList"><bean:message key="SecureSocketLayer.provider.predefined.select"/></label>
            <html:select property="providerList" name="<%=formName%>" styleId="providerList">
                  <html:option value="IBMJSSE"><bean:message key="SecureSocketLayer.provider.IBMJSSE"/></html:option>
                  <html:option value="IBMJSSEFIPS"><bean:message key="SecureSocketLayer.provider.IBMJSSEFIPS"/></html:option>
            </html:select>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text" nowrap valign="top">
           <html:radio property="selectedProvider" value="custom" styleId="selectedProvider"><bean:message key="SecureSocketLayer.provider.custom"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="complex-property"  scope="row" valign="top" nowrap>
            <label  for="provider"><bean:message key="SecureSocketLayer.provider.custom.select"/></label>
                <html:text property="provider" name="<%=formName%>" size="30" styleId="provider"/>
          </td>
     <%} %>

     </tr>
   </table>
