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

<bean:define id="loginBinding" name="<%=formName%>" property="loginBinding"/>

<%
    if (((String)loginBinding).length() == 0){
        loginBinding = "LoginBinding.none";
    } else {
        loginBinding = "LoginBinding." + (String)loginBinding;
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
            <bean:message key="<%= (String)loginBinding %>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top">
           <html:radio property="loginBinding" value="none" styleId="signingInfo"><bean:message key="LoginBinding.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text"  nowrap valign="top">
           <html:radio property="loginBinding" value="specified" styleId="signingInfo"><bean:message key="LoginBinding.specified"/></html:radio>
          </td>
     <%} %>

<% if ( (readOnly.equals("true") && ((String)loginBinding).equals("LoginBinding.specified")) ||
        !readOnly.equals("true")) {%>
        </tr>
        <tr valign="top">
          <td class="complex-property" nowrap valign="top">
          <table border="0" cellpadding="2" cellspacing="1" summary="Properties Table" >
           <tr>
<!--item  value="LoginBinding.authMethod.displayName:authMethod:no:Text:LoginBinding.authMethod.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="authMethod" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="LoginBinding.authMethod.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="LoginBinding.callbackHandler.displayName:callbackHandler:no:Text:LoginBinding.callbackHandler.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="callbackHandler" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="LoginBinding.callbackHandler.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="LoginBinding.basicAuthID.displayName:basicAuthID:no:Text:LoginBinding.basicAuthID.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="basicAuthID" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="no" />
               <tiles:put name="label" value="LoginBinding.basicAuthID.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="LoginBinding.basicAuthPwd.displayName:basicAuthPwd:no:Text:LoginBinding.basicAuthPwd.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/passwordLayout.jsp" flush="false">
               <tiles:put name="property" value="basicAuthPwd" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="no" />
               <tiles:put name="label" value="LoginBinding.basicAuthPwd.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="LoginBinding.tokenTypeURI.displayName:tokenTypeURI:no:Text:LoginBinding.tokenTypeURI.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="tokenTypeURI" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="no" />
               <tiles:put name="label" value="LoginBinding.tokenTypeURI.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="LoginBinding.tokenTypeName.displayName:tokenTypeName:no:Text:LoginBinding.tokenTypeName.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="tokenTypeName" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="no" />
               <tiles:put name="label" value="LoginBinding.tokenTypeName.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          </table>
          </td>
<%} %>
     </tr>
   </table>
