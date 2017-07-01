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

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>
<tiles:useAttribute id="formName" name="formBean" classname="java.lang.String"/>

<bean:define id="trustedIDEvaluator" name="<%=formName%>" property="trustedIDEvaluator"/>

<%
    if (((String)trustedIDEvaluator).length() == 0){
        trustedIDEvaluator = "TrustedIDEvaluator.none";
    } else {
        trustedIDEvaluator = "TrustedIDEvaluator." + (String)trustedIDEvaluator;
    }
    String isReadOnly;
    if (readOnly.equalsIgnoreCase("true")){
        isReadOnly = "yes";
    } else {
        isReadOnly = "no";
    }
    Vector valueVector = (Vector)session.getAttribute("evalRefVal");
    Vector descVector = (Vector)session.getAttribute("evalRefDesc");
%>

<bean:define id="action"    name="<%=formName%>" property="myAction"/>

   <table width="100%" border="0" cellspacing="0" cellpadding="2">

        <tr valign="top">
     <% if (readOnly.equals("true")) { %>
          <td class="table-text" nowrap valign="top" colspan="2">
            <bean:message key="<%= (String)trustedIDEvaluator %>"/>
          </td>
          <% if (((String)trustedIDEvaluator).equals("TrustedIDEvaluator.existing")) {
              String d = (String) descVector.elementAt(0);%>
          <td class="table-text" nowrap valign="top" >
              <bean:write property="existingRef" name="<%=formName%>"/>
          <%}%>
          </td>
     <%} else {%>
          <td class="table-text" nowrap valign="top" colspan="2">
           <html:radio property="trustedIDEvaluator" value="none" styleId="encryptionInfo"><bean:message key="TrustedIDEvaluator.none"/></html:radio>
          </td>
        </tr>
        <tr valign="top">
          <td class="table-text"  nowrap valign="top">
           <html:radio property="trustedIDEvaluator" value="existing" styleId="encryptionInfo"><bean:message key="TrustedIDEvaluator.existing"/></html:radio>
          </td>
        </tr>

        <tr valign="top">
          <td class="complex-property"  scope="row" valign="top" nowrap>
            <label  for="existingRef"><bean:message key="TrustedIDEvaluator.existing.select"/></label>
              <html:select property="existingRef" name="<%=formName%>" styleId="existingRef">
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
        </tr>

        <tr valign="top">
          <td class="table-text"  nowrap valign="top" colspan="2">
           <html:radio property="trustedIDEvaluator" value="specified" styleId="encryptionInfo"><bean:message key="TrustedIDEvaluator.specified"/></html:radio>
          </td>
     <%} %>
     <% if ( (readOnly.equals("true") && ((String)trustedIDEvaluator).equals("TrustedIDEvaluator.specified")) ||
             !readOnly.equals("true")) {%>
        </tr>
        <tr valign="top">
          <td class="complex-property" nowrap valign="top" colspan="2">
          <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" >
          <tr>
<!--item  value="TrustedIDEvaluator.name.displayName:name:yes:Text:TrustedIDEvaluator.name.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="name" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="TrustedIDEvaluator.name.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr>
          <tr>
<!--item  value="TrustedIDEvaluator.className.displayName:classname:yes:Text:TrustedIDEvaluator.className.description:no:yes" link="" icon="" tooltip="" classtype="com.ibm.ws.console.core.item.PropertyItem"/-->
           <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
               <tiles:put name="property" value="classname" />
               <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
               <tiles:put name="isRequired" value="yes" />
               <tiles:put name="label" value="TrustedIDEvaluator.className.displayName" />
               <tiles:put name="size" value="30" />
               <tiles:put name="units" value=""/>
               <tiles:put name="desc" value=""/>
               <tiles:put name="bean" value="<%=formName%>" />
           </tiles:insert>
          </tr></table>
        </tr>
   </table>

    <%if (!((String)action).equalsIgnoreCase("New")) { %>
    <bean:define id="refId"    name="<%=formName%>" property="refId"/>
    <bean:define id="resourceUri" name="<%=formName%>" property="resourceUri"/>
    <bean:define id="contextId"    name="<%=formName%>" property="contextId"/>
    <bean:define id="perspective"    name="<%=formName%>" property="perspective"/>

    <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" >
        <tbody>
          <tr> 
            <th colspan="2" class="column-head" scope="rowgroup">  
               <a name="additional"></a><bean:message key="config.additional.properties"/></th>
          </tr>
          
<!--item  value="WSSecurity.property.displayName" link="com.ibm.ws.console.wssecurity.forwardCmd.do?forwardName=WsWSSecurity.Property.content.main&#38;sfname=properties" tooltip="WSSecurity.property.description" classtype="org.apache.struts.tiles.beans.SimpleMenuItem"/-->
          <tr> 
            <td valign="top" class="table-text"  nowrap width="10%">
            <%  String linkStr = "com.ibm.ws.console.wssecurity.forwardCmd.do?forwardName=WsWSSecurity.Property.content.main&#38;sfname=properties";
                String link = linkStr + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;%>
              <a href="<%= link   %>"><bean:message key="WSSecurity.property.displayName"/></a>
            </td>
            <td valign="top" class="table-text"  >
              <bean:message key="WSSecurity.property.description"/>
            </td>
          </tr>
        </tbody>
    </table>

    <% }  // end if action != new %>

<%} else { %>
    </tr>
  </table>
<%} %>
