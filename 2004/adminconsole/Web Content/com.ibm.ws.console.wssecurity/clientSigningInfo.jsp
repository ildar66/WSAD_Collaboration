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

<bean:define id="signingInfo" name="<%=formName%>" property="signingInfo"/>

<%
    if (((String)signingInfo).length() == 0){
        signingInfo = "SigningInfo.none";
    } else {
        signingInfo = "SigningInfo." + (String)signingInfo;
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
            <bean:message key="<%= (String)signingInfo %>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top">
           <html:radio property="signingInfo" value="none" styleId="signingInfo"><bean:message key="SigningInfo.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text"  nowrap valign="top">
           <html:radio property="signingInfo" value="specified" styleId="signingInfo"><bean:message key="SigningInfo.specified"/></html:radio>
          </td>
     <%} %>
     <% if ( (readOnly.equals("true") && ((String)signingInfo).equals("SigningInfo.specified")) ||
             !readOnly.equals("true")) {%>
        </tr>
        <tr valign="top">
          <td class="complex-property" nowrap valign="top">
          <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" >
           <tr>
<!--item  value="SigningInfo.method.displayName:method:no:DynamicSelect:SigningInfo.method.description:no:yes:smRefVal:smRefDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           Vector valVector = (Vector) session.getAttribute("smRefVal");
           Vector descriptVector = (Vector) session.getAttribute("smRefDesc");
           if (valVector == null) {
               valVector = new Vector();
           }
           if (descriptVector == null) {
               descriptVector = new Vector();
           }

           session.setAttribute("descVector", descriptVector);
           session.setAttribute("valueVector", valVector);
         %>

           <tiles:insert page="/secure/layouts/dynamicSelectionLayout.jsp" flush="false">
               <tiles:put name="property" value="method" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="false" />
               <tiles:put name="label" value="SigningInfo.method.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="SigningInfo.digestMethod.displayName:digestMethod:no:DynamicSelect:SigningInfo.digestMethod.description:no:yes:dmRefVal:dmRefDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           valVector = (Vector) session.getAttribute("dmRefVal");
           descriptVector = (Vector) session.getAttribute("dmRefDesc");
           if (valVector == null) {
               valVector = new Vector();
           }
           if (descriptVector == null) {
               descriptVector = new Vector();
           }

           session.setAttribute("descVector", descriptVector);
           session.setAttribute("valueVector", valVector);
         %>

           <tiles:insert page="/secure/layouts/dynamicSelectionLayout.jsp" flush="false">
               <tiles:put name="property" value="digestMethod" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="false" />
               <tiles:put name="label" value="SigningInfo.digestMethod.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="SigningInfo.canonMethod.displayName:canonMethod:no:DynamicSelect:SigningInfo.canonMethod.description:no:yes:cmRefVal:cmRefDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           valVector = (Vector) session.getAttribute("cmRefVal");
           descriptVector = (Vector) session.getAttribute("cmRefDesc");
           if (valVector == null) {
               valVector = new Vector();
           }
           if (descriptVector == null) {
               descriptVector = new Vector();
           }

           session.setAttribute("descVector", descriptVector);
           session.setAttribute("valueVector", valVector);
         %>

           <tiles:insert page="/secure/layouts/dynamicSelectionLayout.jsp" flush="false">
               <tiles:put name="property" value="canonMethod" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="false" />
               <tiles:put name="label" value="SigningInfo.canonMethod.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="SigningInfo.signingKey.displayName:keyType:yes:Custom:SigningInfo.signingKey.description:no:yes: : :/com.ibm.ws.console.wssecurity/signingInfoKeyLocator.jsp" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/customLayout.jsp" flush="false">
               <tiles:put name="label" value="SigningInfo.signingKey.displayName"/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="jspPage" value="/com.ibm.ws.console.wssecurity/signingInfoKeyLocator.jsp"/>
               <tiles:put name="desc" value="SigningInfo.signingKey.description"/>
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="property" value="signingInfo" />
               <tiles:put name="isRequired" value="no" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="SigningInfo.certificatePath.displayName:callbackHandler:yes:Custom:SigningInfo.certificatePath.description:no:yes: : :/com.ibm.ws.console.wssecurity/signingInfoCertPath.jsp" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/customLayout.jsp" flush="false">
               <tiles:put name="label" value="SigningInfo.certificatePath.displayName"/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="jspPage" value="/com.ibm.ws.console.wssecurity/signingInfoCertPath.jsp"/>
               <tiles:put name="desc" value="SigningInfo.certificatePath.description"/>
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="property" value="callbackHandler" />
               <tiles:put name="isRequired" value="no" />
           </tiles:insert>
          </td>
          </tr></table>
     <%} %>
     </tr>
   </table>
