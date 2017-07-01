<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.websphere.management.*,javax.management.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>                 


<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="propertyLabel" classname="java.lang.String" />
<tiles:useAttribute  name="attributeList" classname="java.util.List"/>
<tiles:useAttribute name="modelType" classname="java.lang.String" />

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
<bean:define id="mbeanId" name="<%= formName %>" property="mbeanId"/>
<% 
            String managedbeanId = (String) mbeanId;
            AdminClient adminClient = null;
            if (!managedbeanId.equalsIgnoreCase("")) {
            AdminService adminService = AdminServiceFactory.getAdminService();
            ObjectName objectName = new ObjectName(managedbeanId);
 %>


    <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tbody>
        <tr valign="top">
            <th colspan="3" class="column-head" scope="rowgroup">
            <bean:message key="<%=propertyLabel %>"/></th>
        </tr>
        
        <logic:iterate id="item" name="attributeList" type="org.apache.struts.tiles.beans.SimpleMenuItem">
        <% 
            String attribute = item.getValue();
            String attribType = item.getLink();
            String enumClass = item.getTooltip();
            String attributeValue = null;
            Object attributeObj = null;
            String enumKey = null;
            String booleanKey = null;
            boolean attribNotFound = false;
            String attribNotFoundMsg = "attribute.not.found";
            String label =  modelType + ".mbean." +  attribute + ".displayName";
            String description = modelType + ".mbean." +  attribute + ".description";
            try {
                 attributeObj = adminService.getAttribute(objectName , attribute); 
                 attributeValue = attributeObj.toString();
            } catch(Exception e) {
                  attribNotFound = true;
            }                           
            if (attribType.equalsIgnoreCase("Enum")) {
                 enumKey =  enumClass + "." + attributeValue;
            }
            if (attribType.equalsIgnoreCase("boolean")) {
                 boolean retValue = ((Boolean)attributeObj).booleanValue();
                 if (retValue == true) {
                    booleanKey = "boolean.true";
                 } else {
                    booleanKey = "boolean.false";
                 }
            }
        %>
        <tr valign="top">
         <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="atextfield"> <bean:message key="<%=label%>"/></label>                                
        </td>
        <td class="table-text" nowrap valign="top">
        <% if (attribNotFound) { %>
            <label  for="atextfield"> <bean:message key="<%= attribNotFoundMsg%>"/></label>
        <% } else { 
            if (attribType.equalsIgnoreCase("Enum")) {  %>
              <label  for="atextfield"> <bean:message key="<%= enumKey%>"/></label>  
            
          <% } else if (attribType.equalsIgnoreCase("boolean")) { %>   
              <label  for="atextfield"> <bean:message key="<%= booleanKey%>"/></label>  
          <% } else {%>
              <label  for="atextfield"><%=attributeValue %></label>    
          <% } %>
            
        <% } %>
         </td>        
         <td class="table-text"  scope="row" valign="top">
            <label  for="atextfield"> <bean:message key="<%=description%>"/></label>                                
        </td>    
        </tr>
        </logic:iterate>
        <tr>
              <td class="button-section" colspan="3">
                <input type="submit" name="save" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
              </td>
        </tr>

       </tbody>
    </table>

 <% } %>                                                       
</html:form>
<br/>
