<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute  name="readOnlyView" classname="java.lang.String"/>
<tiles:useAttribute  name="attributeList" classname="java.util.List"/>
<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="formFocus" classname="java.lang.String" />
<tiles:useAttribute name="propertyLabel" classname="java.lang.String" />
<tiles:useAttribute name="showButtons" classname="java.lang.String" />


<%  String renderReadOnlyView = "no";
if( (readOnlyView != null) && (readOnlyView.equalsIgnoreCase("yes")) ) {
  renderReadOnlyView = "yes";
}
// to be added check user role and decide rendering
%>
<% if (renderReadOnlyView.equalsIgnoreCase("yes")) { %>
<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>" focus="<%=formFocus%>">
<html:hidden property="action"/>

<%-- <table border="0" cellpadding="8" cellspacing="0" valign="top" width="100%" summary="Framing Table">
  <tr>
    <td class="layout-manager" > --%>
      <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tr>
          <th colspan="3" class="column-head" scope="rowgroup"><a name="generalProperties"></a>
            <bean:message key="config.general.properties"/></th>
        </tr>
        <logic:iterate id="item" name="attributeList" type="com.ibm.ws.console.core.item.PropertyItem">
          <%  String showDescription = item.getShowDescription();%>
 
        <tr valign="top">
          <td class="table-text"  scope="row" nowrap><label  for="{attributeName}"><bean:message key="<%= item.getLabel() %>"/></label></td>
          <td class="table-text" nowrap valign="top"><bean:write property="<%= item.getAttribute() %>" name="<%=formName%>"/></td>
          <% if (showDescription.equalsIgnoreCase("yes")) { %>
          <td class="table-text" valign="top"><a href="transformer.jsp?xml=was_page_help&xsl=was_page_help" target="WAS_Help">
            <img src="images/more.gif" width="10" height="12" border="0" alt="<bean:message key="view.more.info"/>" align="texttop"></a>
            <bean:message key="<%= item.getDescription() %>"/></td>
            <% } %>  
        </tr>
        </logic:iterate>
        <tr>
          <th class="button-section" colspan="3">
            <input type="submit" name="submitChanges" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
          </th>
        </tr>
      </table>
<%-- </table> --%>
</html:form>
<% } %>

<% if (renderReadOnlyView.equalsIgnoreCase("no")) { %>
<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
<html:hidden property="action"/>
    <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Properties Table" class="framing-table">
        <tr>
            <th colspan="3" class="column-head" scope="rowgroup"><a name="generalProperties"></a>
            <bean:message key="<%=propertyLabel %>"/></th>
        </tr>
        <logic:iterate id="item" name="attributeList" type="com.ibm.ws.console.core.item.PropertyItem">
            <tr valign="top">

                <% String isRequired = item.getRequired(); 
                String strType = item.getType();
                String isReadOnly = item.getReadOnly();
                String showDescription = item.getShowDescription();%>
                


                <% if (strType.equalsIgnoreCase("Label")) { %>
                        <th colspan="2" class="column-head-name" scope="rowgroup"><%=item.getLabel()%></th>
                <% } %>
 
                <% if (strType.equalsIgnoreCase("Link")) { %>
                        <tiles:insert page="/com.ibm.ws.console.probdetermination/linkBoxLayout.jsp" flush="true">
                            <tiles:put name="list" beanName="attributeList" beanScope="page"/>
                        </tiles:insert>
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
                        <tiles:put name="units" value=""/>
                        <tiles:put name="desc" value="<%=item.getDescription()%>"/>
                        <tiles:put name="bean" value="<%=formName%>" />
                    </tiles:insert>
                <% } %>
        
                <% if (strType.equalsIgnoreCase("checkbox")) { %>
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
        
        
                <% if (strType.equalsIgnoreCase("Select")) { 
                    
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
                    
                    <tiles:insert page="/secure/layouts/submitLayout.jsp" />
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
            </tr>
        </logic:iterate>
        <%if (showButtons.equalsIgnoreCase("true")); 
        { %>
        
        <tr>
              <th class="button-section" colspan="3">
                <input type="submit" name="apply" value="<bean:message key="button.apply"/>" class="buttons" id="navigation">
                <input type="submit" name="save" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
                <input type="reset" name="reset" value="<bean:message key="button.reset"/>" class="buttons" id="navigation">
                <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
              </th>
        </tr>
        <%}%>
    </table>
<%-- </table> --%>
</html:form>
<% } %>
