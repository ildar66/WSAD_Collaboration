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

<bean:define id="certificatePath" name="<%=formName%>" property="certificatePath"/>

<%
    if (((String)certificatePath).length() == 0){
        certificatePath = "SigningInfo.none";
    } else {
        certificatePath = "SigningInfo." + certificatePath;
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
            <bean:message key="<%= (String)certificatePath %>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top" colspan="2">
           <html:radio property="certificatePath" value="none" styleId="certificatePath"><bean:message key="SigningInfo.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text" nowrap valign="top" colspan="2">
           <html:radio property="certificatePath" value="any" styleId="certificatePath"><bean:message key="SigningInfo.any"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text" nowrap valign="top">
           <html:radio property="certificatePath" value="specified" styleId="certificatePath"><bean:message key="SigningInfo.specified"/></html:radio>
          </td>
        </tr>
     <%} %>

     <% if ( (readOnly.equals("true") && ((String)certificatePath).equals("SigningInfo.specified")) ||
              !readOnly.equals("true")) { %>
        <tr valign="top">
           <td class="complex-property"  scope="row" valign="top" nowrap>
               <label for="trustAnchor"><bean:message key="SigningInfo.trustAnchor.displayName"/></label>
           </td>

           <td class="table-text" nowrap valign="top">
               <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
         <% if (readOnly.equalsIgnoreCase("true")) { %>
               <bean:write property="trustAnchor" name="<%=formName%>"/>
           </td>
         <%} else {%>
           <%
           Vector valueVector = (Vector) session.getAttribute("taRefVal");
           Vector descVector = (Vector) session.getAttribute("taRefDesc");
           %>
                <html:select property="trustAnchor" name="<%=formName%>" styleId="trustAnchor">
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
         <% } %>
        </tr>

        <tr valign="top">
           <td class="complex-property"  scope="row" valign="top" nowrap>
               <label for="certificateStore"><bean:message key="SigningInfo.certificateStore.displayName"/></label>
           </td>

           <td class="table-text" nowrap valign="top">
         <% if (readOnly.equalsIgnoreCase("true")) { %>
               <bean:write property="certificateStore" name="<%=formName%>"/>
           </td>
         <%} else {%>
           <%
           Vector valueVector = (Vector) session.getAttribute("certRefVal");
           Vector descVector = (Vector) session.getAttribute("certRefDesc");
           %>
                <html:select property="certificateStore" name="<%=formName%>" styleId="certificateStore">
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
         <% } %>
        </tr>
     <%} %>
     </tr>
   </table>
