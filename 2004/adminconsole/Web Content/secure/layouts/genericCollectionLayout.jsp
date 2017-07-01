<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page language="java" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action,java.net.URLEncoder"%>
<%@ page  errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%
int chkcounter = 0;
%>


<tiles:useAttribute name="iterationName" classname="java.lang.String" />
<tiles:useAttribute name="iterationProperty" classname="java.lang.String" />
<tiles:useAttribute name="selectionType" classname="java.lang.String"/>
<tiles:useAttribute name="columnList" classname="java.util.List"/>
<tiles:useAttribute name="createButtons" classname="java.lang.String"/>
<tiles:useAttribute name="selectName" classname="java.lang.String"/>
<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="includeButtonTile" classname="java.lang.String"/>
<%  
    if ((includeButtonTile != null) && (includeButtonTile.equalsIgnoreCase("true"))) {
%>
<tiles:useAttribute name="buttonCount" classname="java.lang.String"/>
<tiles:useAttribute name="definitionName" classname="java.lang.String"/>
<tiles:useAttribute name="includeForm" classname="java.lang.String"/>
<tiles:useAttribute name="actionList" classname="java.util.List"/>
<%
    }
%>

<bean:define id="order" name="<%=iterationName%>" property="order" type="java.lang.String"/>

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
<%  
    if ((includeButtonTile != null) && (includeButtonTile.equalsIgnoreCase("true"))) {
%>
    <tiles:insert page="/secure/layouts/buttonLayout.jsp">
        <tiles:put name="buttonCount" beanName="buttonCount" beanScope="page"/>
        <tiles:put name="definitionName" beanName="definitionName" beanScope="page"/>
        <tiles:put name="includeForm" beanName="includeForm" beanScope="page"/>
        <tiles:put name="actionList" beanName="actionList" beanScope="page"/>
    </tiles:insert>
<%
    }
%>

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table" class="framing-table">
    <tr>
        <th class="column-head-name" scope="col" width="1%">
            <logic:equal name="selectionType" value="multiple">
                <LABEL>
                <input type="checkbox" 
                       name="allchecked" 
                       value="checkall" 
                       onclick="updateCheckAll(this.form,'<%=selectName%>')" 
                       onkeypress="updateCheckAll(this.form,'<%=selectName%>')"
                       TITLE="<bean:message key="collection.checkall.checkbox.alt"/>">
                </LABEL>
            </logic:equal>
            <logic:notEqual name="selectionType" value="multiple">
                &nbsp;
            </logic:notEqual>
         </th>
        <logic:iterate id="column" name="columnList" type="com.ibm.ws.console.core.item.CollectionItem">
            <%{%>
            <% 
            	String nextOrder = "ASC";
               	String imageName = "Sort_ascend2.gif";
            	if (order.equals("ASC")) {
            		nextOrder = "DSC";
               		imageName = "Sort_desc2.gif";
            	}
           	%>
            <th class="column-head-name" scope="col" nowrap>
                <bean:message key="<%=column.getTooltip()%>"/>
                <%
                	String theAction = formAction;
                	if (theAction.indexOf("?") != -1) {
                		theAction += "&";
                	}
                	else {
                		theAction += "?";
                	}
                %>	
                <a href="<%=theAction%>SortAction=sort&col=<%=column.getColumnField()%>&order=<%=nextOrder%>"> 
                    <logic:equal name="column" property="isSortable" value="true">
                        <img src="<%=request.getContextPath()%>/images/<%=imageName%>" border=0 align="texttop" alt="" width="8" height="12"> 
                    </logic:equal>
                    <logic:equal name="column" property="isSortable" value="false">
                        <img src="<%=request.getContextPath()%>/images/blank20.gif" BORDER="0" WIDTH="9" HEIGHT="13" ALT=""> 
                    </logic:equal>
                </a> 
            </th>
            <%}%>
        </logic:iterate>
    </tr>
    <logic:iterate id="collectionItem" name="<%=iterationName%>" property="<%=iterationProperty%>">
        <label for="aninput<%=chkcounter%>">
        <tr> 
            <% int collectionCount = 0; %>
            <logic:iterate id="column" name="columnList" type="com.ibm.ws.console.core.item.CollectionItem">
                <bean:define id="value" name="collectionItem" property="<%=column.getColumnField()%>" type="java.lang.String"/>
                <%  if (collectionCount++ < 1) { %>
                    <td class="table-text"  width="1%"> 
                        <logic:equal name="selectionType" value="single">
                            <html:radio property="<%=selectName%>" value="<%=value%>" styleId="aninput"/>
                        </logic:equal>
                        <logic:equal name="selectionType" value="multiple">
                            <html:multibox property="<%=selectName%>" 
                                           value="<%=value%>" 
                                           onclick="checkChecks(this.form,this)" 
                                           onkeypress="checkChecks(this.form,this)"
                                           styleId="aninput"/>
                        </logic:equal>
                        <logic:equal name="selectionType" value="none">
                            &nbsp;
                        </logic:equal>
                    </td>
                <% } %>
                <td class="table-text" >
                    <logic:notEqual name="column" property="link" value="">
                        <%
                            String link = column.getLink();
                            if (link.indexOf('?') != -1)
                                link = link+"&"+column.getColumnField()+"="+URLEncoder.encode(value);
                            else
                                link = link+"?"+column.getColumnField()+"="+URLEncoder.encode(value);
                        %>
                        <a href="<%=link%>">
                    </logic:notEqual>
                    <%=value%>
                    <logic:notEqual name="column" property="link" value="">
                        </a>
                    </logic:notEqual>
                </td>
            </logic:iterate>
            
        </tr>
        </label>
    <% chkcounter = chkcounter + 1; %>

    </logic:iterate>
    

        <logic:equal name="createButtons" value="true">
        <tr>
            <td class="button-section" colspan="4"> 
                <html:submit property="action" styleClass="buttons" styleId="navigation">
                    <bean:message key="button.ok"/>
                </html:submit>
                <input type="submit" name="org.apache.struts.taglib.html.CANCEL" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">
            </td>
        </tr>
    </logic:equal>
</table>
</html:form>

    <%  
        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
        String nonefound = messages.getMessage(request.getLocale(),"Persistence.none");
        if (chkcounter == 0) { 
        out.println("<table class='framing-table' cellpadding='3' cellspacing='1' width='100%'><tr><td class='table-text'>"+nonefound+"</td></tr></table>"); 
        }  
    %>

