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

<bean:define id="encryptionInfo" name="<%=formName%>" property="encryptionInfo"/>

<%
    if (((String)encryptionInfo).length() == 0){
        encryptionInfo = "EncryptionInfo.none";
    } else {
        encryptionInfo = "EncryptionInfo." + (String)encryptionInfo;
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
            <bean:message key="<%= (String)encryptionInfo %>"/>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top">
           <html:radio property="encryptionInfo" value="none" styleId="encryptionInfo"><bean:message key="EncryptionInfo.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text"  nowrap valign="top">
           <html:radio property="encryptionInfo" value="specified" styleId="encryptionInfo"><bean:message key="EncryptionInfo.specified"/></html:radio>
          </td>
     <%} %>
     <% if ( (readOnly.equals("true") && ((String)encryptionInfo).equals("EncryptionInfo.specified")) ||
             !readOnly.equals("true")) {%>
        </tr>
        <tr valign="top">
          <td class="complex-property" nowrap valign="top">
          <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" >
           <tr>
<!--item  value="EncryptionInfo.name.displayName:name:yes:Text:EncryptionInfo.name.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="name" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="EncryptionInfo.name.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="EncryptionInfo.keyReference.displayName:keyLocatorRef:no:DynamicSelect:EncryptionInfo.keyReference.description:no:yes:refVal:refDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
    <!-- dynamicSelectionLayout.jsp crashes on empty vector for read only,
         so special case it here. -->
     <% if (readOnly.equals("true")) { %>
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="keyLocatorRef" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="EncryptionInfo.keyReference.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
     <%} else {%>
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           Vector valVector = (Vector) session.getAttribute("keyRefVal");
           Vector descriptVector = (Vector) session.getAttribute("keyRefDesc");
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
               <tiles:put name="property" value="keyLocatorRef" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="EncryptionInfo.keyReference.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
     <%} %>
          </tr>
          <tr>
<!--item  value="EncryptionInfo.keyName.displayName:keyName:no:Text:EncryptionInfo.keyName.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="keyName" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="no" />
               <tiles:put name="label" value="EncryptionInfo.keyName.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="EncryptionInfo.keyAlgorithm.displayName:keyEncryption:no:DynamicSelect:EncryptionInfo.keyAlgorithm.description:no:yes:keaRefVal:keaRefDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           Vector valVector = (Vector) session.getAttribute("keaRefVal");
           Vector descriptVector = (Vector) session.getAttribute("keaRefDesc");
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
               <tiles:put name="property" value="keyEncryption" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="false" />
               <tiles:put name="label" value="EncryptionInfo.keyAlgorithm.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="EncryptionInfo.dataAlgorithm.displayName:dataEncryption:yes:DynamicSelect:EncryptionInfo.dataAlgorithm.description:no:yes:deaRefVal:deaRefDesc" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
         <% try {
               session.removeAttribute("valueVector");
               session.removeAttribute("descVector");
           }
           catch (Exception e) {
           }
           valVector = (Vector) session.getAttribute("deaRefVal");
           descriptVector = (Vector) session.getAttribute("deaRefDesc");
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
               <tiles:put name="property" value="dataEncryption" />
               <tiles:put name="readOnly" value="<%=readOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="EncryptionInfo.dataAlgorithm.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
               <tiles:put name="multiSelect" value="false" />
           </tiles:insert>
          </tr>
          </td>
          </tr></table>
     <%} %>
     </tr>
   </table>
