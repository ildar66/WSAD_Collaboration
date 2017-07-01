<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.*,com.ibm.ws.security.core.SecurityContext,com.ibm.websphere.product.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<tiles:useAttribute  name="readOnlyView" classname="java.lang.String"/>
<tiles:useAttribute  name="attributeList" classname="java.util.List"/>
<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="formFocus" classname="java.lang.String" />
<tiles:useAttribute name="customJspName" classname="java.lang.String" />
<tiles:useAttribute name="showButtons" classname="java.lang.String" />
<tiles:useAttribute name="propertyLabel" classname="java.lang.String" />
<tiles:useAttribute name="showOkButton" classname="java.lang.String" />
<tiles:useAttribute name="showApplyButton" classname="java.lang.String" />
<tiles:useAttribute name="showCancelButton" classname="java.lang.String" />
<tiles:useAttribute name="showResetButton" classname="java.lang.String" />


<%  String renderReadOnlyView = "no";
if( (readOnlyView != null) && (readOnlyView.equalsIgnoreCase("yes")) ) {
  renderReadOnlyView = "yes";
} else if (SecurityContext.isSecurityEnabled()) {
    renderReadOnlyView = "yes";
    if (request.isUserInRole("administrator")) {
        renderReadOnlyView = "no";
    }
    else if (request.isUserInRole("configurator")) {
        renderReadOnlyView = "no";
    }
}
%>

<%  
		Boolean descriptionsOn = (Boolean) session.getAttribute("descriptionsOn");
        String numberOfColumns = "3";
        if (descriptionsOn.booleanValue() == false)
        	numberOfColumns = "2";	
        WASProduct productInfo = new WASProduct();
%>
<%  
        String showDescription;
%>

<%
   String fieldLevelHelpTopic = "";
   String fieldLevelHelpAttribute = "";
   String DETAILFORM = "DetailForm";
   String objectType = "";
   int index = formType.lastIndexOf ('.');
   if (index > 0)
   {
      String fType = formType.substring (index+1);
      if (fType.endsWith (DETAILFORM))
         objectType = fType.substring (0, fType.length()-DETAILFORM.length());
      else
         objectType = fType;
   }
   fieldLevelHelpTopic = objectType+".detail.";
   String topicKey = fieldLevelHelpTopic;
%>

<a name="general"></a>

<% if (renderReadOnlyView.equalsIgnoreCase("yes")) { %>

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>" focus="<%=formFocus%>">
<%--html:hidden property="action"/--%>

      
      <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        
        <tbody>
        <tr>
          <th colspan="<%= numberOfColumns %>" class="column-head" scope="rowgroup">
            <bean:message key="<%=propertyLabel %>"/></th>
        </tr>
        
        <logic:iterate id="item" name="attributeList" type="com.ibm.ws.console.core.item.PropertyItem">
        
<%  
        showDescription = item.getShowDescription();
        
        fieldLevelHelpAttribute = item.getAttribute();
        if (fieldLevelHelpAttribute.equals(" ") || fieldLevelHelpAttribute.equals(""))
            fieldLevelHelpTopic = item.getLabel();
        else
            fieldLevelHelpTopic = topicKey + fieldLevelHelpAttribute;
%>

           
        <tr valign="top">
            <% if (item.getType().equalsIgnoreCase("jsp")) { %>
                    <tiles:insert page="/secure/layouts/customLayout.jsp" flush="false">
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                        <tiles:put name="readOnly" value="true" />
                        <tiles:put name="jspPage" value="<%=item.getUnits()%>"/>
                    </tiles:insert>
                <% } else if (item.getType().equalsIgnoreCase("Custom")){%>
                    <tiles:insert name="<%=customJspName%>" flush="false" >

                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="isRequired" value="<%=item.getRequired()%>" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                        <tiles:put name="readOnly" value="true" />
                        <tiles:put name="formAction" value="<%=formAction%>" />
                        <tiles:put name="formType" value="<%=formType%>" />
                        <tiles:put name="size" value="5" />
                    </tiles:insert>
                <% } else if (item.getType().equalsIgnoreCase("Select")) { 
                    
                    try {
                        session.removeAttribute("valueVector");
                        session.removeAttribute("descVector");
                    }
                    catch (Exception e) {
                    }
                    
                    StringTokenizer st1 = new StringTokenizer(item.getEnumDesc(), ";,");
                    Vector descVector = new Vector();
                    while(st1.hasMoreTokens()) 
                    {
                        String enumDesc = st1.nextToken();
                        descVector.addElement(enumDesc);
                    }
                    StringTokenizer st = new StringTokenizer(item.getEnumValues(), ";,");
                    Vector valueVector = new Vector();
                    while(st.hasMoreTokens()) 
                    {
                        String str = st.nextToken();
                        valueVector.addElement(str);
                    }
        
                    session.setAttribute("descVector", descVector);
                    session.setAttribute("valueVector", valueVector);
                    %>
                    
                    <tiles:insert page="/com.ibm.ws.console.probdetermination/submitLayout.jsp"  flush="false">
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="isRequired" value="item.getRequired()" />
                        <tiles:put name="readOnly" value="true" />
                        <tiles:put name="valueVector" value="<%=valueVector%>" />
                        <tiles:put name="descVector" value="<%=descVector%>" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>

                <% } else  if (item.getType().equalsIgnoreCase("Label")) { %>
                        <th colspan="3" class="column-head-name" scope="rowgroup">
                        <bean:message key="<%=item.getLabel() %>"/>
                        </th>
                <% } else {%>
        
          <td class="table-text"  scope="row" nowrap><label  for="{attributeName}"><bean:message key="<%= item.getLabel() %>"/></label></td>
          <td class="table-text" nowrap valign="top"><bean:write property="<%= item.getAttribute() %>" name="<%=formName%>"/></td>
          
          <% } %>
          
          <% if (showDescription.equalsIgnoreCase("yes")) { %>

                  <td class="table-text" valign="top">

                  <ibmcommon:info image="help.additional.information.image.align" topic="<%=fieldLevelHelpTopic%>"/>
                  <bean:message key="<%= item.getDescription() %>"/>

                  </td>

          <% } %>  

        </tr>
        
        </logic:iterate>
        
        <tr>
          <th class="button-section" colspan="<%= numberOfColumns %>">
            <input type="submit" name="submitChanges" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
          </th>
        </tr>
        
        </tbody>
        
      </table>

</html:form>

<% } %>

<% if (renderReadOnlyView.equalsIgnoreCase("no")) { %>
<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
<html:hidden property="action"/>
    <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tbody>
        <tr valign="top">
            <th colspan="<%= numberOfColumns %>" class="column-head" scope="rowgroup">
            <bean:message key="<%=propertyLabel %>"/></th>
        </tr>
        
        <logic:iterate id="item" name="attributeList" type="com.ibm.ws.console.core.item.PropertyItem">
        
<%  
        showDescription = item.getShowDescription();
        
        fieldLevelHelpAttribute = item.getAttribute();
        if (fieldLevelHelpAttribute.equals(" ") || fieldLevelHelpAttribute.equals(""))
            fieldLevelHelpTopic = item.getLabel();
        else
            fieldLevelHelpTopic = topicKey + fieldLevelHelpAttribute;
        
%>

        <tr valign="top">    
            

                <% 
                String isRequired = item.getRequired(); 
                String strType = item.getType();
                String isReadOnly = item.getReadOnly();
                %>   
                
                <% if (strType.equalsIgnoreCase("Label")) { %>
                        <th colspan="3" class="column-head-name" scope="rowgroup">
                        <bean:message key="<%=item.getLabel() %>"/>
                        </th>
                <% } %>
                
                <% if (strType.equalsIgnoreCase("Text")) { %>
                    <tiles:insert page="/secure/layouts/textFieldLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>
                <% } %>
   
                <% if (strType.equalsIgnoreCase("TextArea")) { %>
                    <tiles:insert page="/secure/layouts/textAreaLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="5" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>
                <% } %>
        
                <% if (strType.equalsIgnoreCase("checkbox")) { %>
                    <tiles:insert page="/secure/layouts/checkBoxLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value="<%=item.getUnits()%>"/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>
                <% } %>
        
                <% if (strType.equalsIgnoreCase("Password")) { %>
                    <tiles:insert page="/secure/layouts/passwordLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="readOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>
                <% } %>
                
                <% if (strType.equalsIgnoreCase("Custom")) { %>
                    <tiles:insert name="<%=customJspName%>" flush="false" >

                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                        <tiles:put name="readOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="formAction" value="<%=formAction%>" />
                        <tiles:put name="formType" value="<%=formType%>" />
                        <tiles:put name="size" value="5" />
                    
                    </tiles:insert>
                <% } %>
        
                <% if (strType.equalsIgnoreCase("Select")) { 
                    
                    try {
                        session.removeAttribute("valueVector");
                        session.removeAttribute("descVector");
                    }
                    catch (Exception e) {
                    }
                    
                    StringTokenizer st1 = new StringTokenizer(item.getEnumDesc(), ",");
                    Vector descVector = new Vector();
                    while(st1.hasMoreTokens()) 
                    {
                        String enumDesc = st1.nextToken();
                        descVector.addElement(enumDesc);
                    }
                    StringTokenizer st = new StringTokenizer(item.getEnumValues(), ",");
                    Vector valueVector = new Vector();
                    while(st.hasMoreTokens()) 
                    {
                        String str = st.nextToken();
                        valueVector.addElement(str);
                    }
        
                    session.setAttribute("descVector", descVector);
                    session.setAttribute("valueVector", valueVector);
                    %>
                    
                    <tiles:insert page="/secure/layouts/submitLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="readOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
		    </tiles:insert>
                <% } %>

                <% if (strType.equalsIgnoreCase("Radio")) { 
                    
                    try {
                        session.removeAttribute("valueVector");
                        session.removeAttribute("descVector");
                    }
                    catch (Exception e) {
                    }
                    
                    StringTokenizer st1 = new StringTokenizer(item.getEnumDesc(), ",");
                    Vector descVector = new Vector();
                    while(st1.hasMoreTokens()) 
                    {
                        String enumDesc = st1.nextToken();
                        descVector.addElement(enumDesc);
                    }
                    StringTokenizer st = new StringTokenizer(item.getEnumValues(), ",");
                    Vector valueVector = new Vector();
                    while(st.hasMoreTokens()) 
                    {
                        String str = st.nextToken();
                        valueVector.addElement(str);
                    }
        
                    session.setAttribute("descVector", descVector);
                    session.setAttribute("valueVector", valueVector);
                    %>
                    
                    <tiles:insert page="/secure/layouts/radioButtonLayout.jsp" flush="false">
                        <tiles:put name="property" value="<%=item.getAttribute()%>" />
                        <tiles:put name="isReadOnly" value="<%=isReadOnly%>" />
                        <tiles:put name="isRequired" value="<%=isRequired%>" />
                        <tiles:put name="label" value="<%=item.getLabel()%>" />
                        <tiles:put name="size" value="30" />
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
		    </tiles:insert>
                <% } %>

<% if (showDescription.equalsIgnoreCase("yes")) { %>  

        <td class="table-text" valign="top">

           <ibmcommon:info image="help.additional.information.image.align" topic="<%=fieldLevelHelpTopic%>"/>
           <bean:message key="<%=item.getDescription()%>"/>

        </td>

<% } %>  

        </tr>

        </logic:iterate>
        
          <%if (showButtons.equals("true")){ %>
        <tr>
           
              <td class="button-section" colspan="<%= numberOfColumns %>">
              <%if (showApplyButton.equals("true")){ %>
                <input type="submit" name="apply" value="<bean:message key="button.apply"/>" class="buttons" id="navigation">
              <% } %>

              <%if (showOkButton.equals("true")){ %>
                <input type="submit" name="save" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
              <% } %>
              <%if (showResetButton.equals("true")){ %>
                <input type="reset" name="reset" value="<bean:message key="button.reset"/>" class="buttons" id="navigation">
              <% } %>
              <%if (showCancelButton.equals("true")){ %>
                <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
              <% } %>
              </td>
        </tr>
          <% } %>
        
        </tbody>
    </table>

</html:form>
<% } %>

<br>

