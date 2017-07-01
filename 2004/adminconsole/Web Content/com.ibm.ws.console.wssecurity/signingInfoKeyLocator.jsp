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

<bean:define id="signingKey" name="<%=formName%>" property="signingKey"/>

<%
    if (((String)signingKey).length() == 0){
        signingKey = "SigningInfo.none";
    } else {
        signingKey = "SigningInfo." + signingKey;
    }
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
            <bean:message key="<%= (String)signingKey %>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top" colspan="2">
           <html:radio property="signingKey" value="none" styleId="signingKey"><bean:message key="SigningInfo.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text" nowrap valign="top">
           <html:radio property="signingKey" value="specified" styleId="signingKey"><bean:message key="SigningInfo.signingKey.displayName"/></html:radio>
          </td>
        </tr>
     <%} %>

     <% if ( (readOnly.equals("true") && ((String)signingKey).equals("SigningInfo.specified")) ||
              !readOnly.equals("true")) { %>

       <tr valign="top">
 
         <td class="complex-property"  scope="row" valign="top" nowrap>
            <label for="signingKeyName"> <bean:message key="SigningInfo.signingKeyName.displayName" /></label>                                
         </td>

         <td class="table-text" nowrap valign="top">
            <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                    
            <% if (isReadOnly.equalsIgnoreCase("yes")) { %>
                <bean:write property="signingKeyName" name="<%=formName%>"/>
            <% } else { %>
                <html:text property="signingKeyName" name="<%=formName%>" size="30" styleId="signingKeyName"/>
            <% } %>
         </td>

       </tr>

       <tr valign="top">
         <td class="complex-property"  scope="row" valign="top" nowrap>
            <label for="signingKeyRef"><bean:message key="SigningInfo.signingKeyRef.displayName" /></label>                                
         </td>

     <% if (readOnly.equalsIgnoreCase("true")) { %>
        <td class="table-text" nowrap valign="top">
            <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
            <bean:write property="signingKeyRef" name="<%=formName%>"/>
        </td>
     <%} else {%>
        <%
        Vector valueVector = (Vector) session.getAttribute("keyRefVal");
        Vector descVector = (Vector) session.getAttribute("keyRefDesc");
        %>

        <td class="table-text" nowrap valign="top">
           <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
           <html:select property="signingKeyRef" name="<%=formName%>" styleId="signingKeyRef">
           <% for (int i=0; i < valueVector.size(); i++) { 
                String val = (String) valueVector.elementAt(i);
                String descript = (String) descVector.elementAt(i);

                if (!descript.equals("")) { %>
                    <html:option value="<%=val%>"><%=descript%></html:option>
                <% } else { %>
                    <html:option value="<%=val%>"><bean:message key="none.text"/></html:option>
                <% } %>

           <% } %>
	     </html:select>
        </td>
     <%}%>
       </tr>

     <%} %>

   </table>
