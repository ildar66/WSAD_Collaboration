<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page  errorPage="error.jsp"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%@ page import="java.util.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action" %>
<%@ page import="java.net.*" %>

<%
MessageResources statusMessages = (MessageResources)application.getAttribute(Action.MESSAGES_KEY);
String statusAlt = statusMessages.getMessage(request.getLocale(),"accessibility.needs");
%>

<% try {
%>

<tiles:useAttribute name="iterationName" classname="java.lang.String" />
<tiles:useAttribute name="iterationProperty" classname="java.lang.String"/>
<tiles:useAttribute name="showCheckBoxes" classname="java.lang.String"/>
<tiles:useAttribute name="sortIconLocation" classname="java.lang.String"/>
<tiles:useAttribute name="columnWidth" classname="java.lang.String"/>
<tiles:useAttribute name="buttons" classname="java.lang.String"/>
<tiles:useAttribute name="collectionList" classname="java.util.List" />
<tiles:useAttribute name="collectionPreferenceList" classname="java.util.List" />
<tiles:useAttribute name="parent" classname="java.lang.String"/>

<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />


<bean:define id="order" name="<%=iterationName%>" property="order" type="java.lang.String"/>
<bean:define id="sortedColumn" name="<%=iterationName%>" property="column"/>
<bean:define id="perspective" name="<%=iterationName%>" property="perspective" type="java.lang.String"/> 


<%--
	NOTES:
			The sorting icons are specified in defaultIconList.  The icons are assumed to be located in the images folder.
			Checkboxes are NOT displayed for all objects whose refIds start with "builtin_"
--%>
<%
	String sortable = "true";
	String columnField = "name";
	String defaultIconList [] = {"Sort_ascend2.gif", "Sort_desc2.gif", "Sort_none.gif"};
 %>   

            
            
        <% session.removeAttribute("preferences"); %>                   
        <jsp:useBean id="preferences" class="java.util.ArrayList" scope="session"/>
           
        <% for (Iterator i = collectionPreferenceList.iterator(); i.hasNext();)
        {
            preferences.add(i.next());
        }%>
        
        <tiles:insert page="/secure/layouts/PreferencesLayout.jsp" controllerClass="com.ibm.ws.console.core.controller.PreferenceController">
            <tiles:put name="parent" value="<%=parent%>"/>
            <tiles:put name="preferences" beanName="preferences" beanScope="session" />
        </tiles:insert>



<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">

				<tiles:insert definition="<%=buttons%>" flush="true"/>


                                
				<TABLE BORDER="0" CELLPADDING="3" CELLSPACING="1" WIDTH="100%" SUMMARY="List table" CLASS="framing-table">
                   
					<TR>
					<%	
						if (showCheckBoxes.equals ("true"))
						{
					%>
						<TH VALIGN="TOP" CLASS="column-head-name" SCOPE="col" WIDTH="1%">
                     <LABEL><INPUT TYPE="CHECKBOX" NAME="allchecked" VALUE="checkall" ONCLICK="updateCheckAll(this.form)" TITLE="<bean:message key="collection.checkall.checkbox.alt"/>"></LABEL>
						</TH>
					<%
						}
					%>
                    
                      <%--               --%>
						<logic:iterate id="cellItem" name="collectionList" type="com.ibm.ws.console.core.item.CollectionItem">
					<%
						sortable = (String)cellItem.getIsSortable();
						columnField = (String)cellItem.getColumnField();
					%>
						<TH VALIGN="MIDDLE" CLASS="column-head-name" SCOPE="col" NOWRAP  WIDTH="<%=columnWidth%>">
					<%
						if (sortIconLocation.equalsIgnoreCase ("right"))
						{
					%>
							<bean:message key="<%=cellItem.getTooltip()%>"/>
					<%
						}
						if (sortable.equals ("false"))
						{
					%>
							<IMG SRC="<%=request.getContextPath()%>/images/blank20.gif" BORDER="0" WIDTH="9" HEIGHT="13"> 
					<%
						}
						if (sortable.equals ("true"))
						{
							if (columnField.equals (sortedColumn))
							{
								String nextOrder;
								if (order.equalsIgnoreCase ("ASC"))
								{
									nextOrder = "DSC";
								}
								else
								{
									nextOrder = "ASC";
								}
					%>
							<A HREF="<%=formAction%>?SortAction=true&col=<%=columnField%>&order=<%=nextOrder%>">
					<%
								if (order.equalsIgnoreCase ("ASC"))
								{
					%>
								<IMG SRC="<%=request.getContextPath()%>/images/<%=defaultIconList[0]%>" BORDER="0" WIDTH="9" HEIGHT="13" ALT="<bean:message key="sorted.ascending"/>"> 
					<%
								}
								else if (order.equalsIgnoreCase ("DSC"))
								{
					%>
                        <IMG SRC="<%=request.getContextPath()%>/images/<%=defaultIconList[1]%>" BORDER="0" WIDTH="9" HEIGHT="13" ALT="<bean:message key="sorted.descending"/>"> 
					<%
								}
					%>
							</A>
					<%
							}
							else
							{
					%>
							<A HREF="<%=formAction%>?SortAction=true&col=<%=columnField%>&order=ASC">
								<IMG SRC="<%=request.getContextPath()%>/images/<%=defaultIconList[2]%>" BORDER="0" WIDTH="9" HEIGHT="13" ALT="<bean:message key="not.sorted"/>"></A>&nbsp;
					<%
							}
							if (sortIconLocation.equalsIgnoreCase ("left"))
							{
					%>
							<bean:message key="<%=cellItem.getTooltip()%>"/>
					<%
							}
							if (sortable.equals ("false"))
							{
					%>
							<IMG SRC="<%=request.getContextPath()%>/images/blank20.gif" BORDER="0" WIDTH="9" HEIGHT="13"> 
					<%
							}
						}
                        
					%>
                    <% //add refresh.gif after status column to refresh page
                        if (columnField.equals("status")) { %>
                        <A HREF="<%=parent%>">
                            <IMG SRC="<%=request.getContextPath()%>/images/refresh.gif"  ALT="<bean:message key="refresh.view"/>" align="texttop" border="0"/> 
                        </A>    
                    <%  } %>
						</TH>
						</logic:iterate>
					</TR>
                    
                      <%-- --%>
               
					<logic:iterate id="listcheckbox" name="<%=iterationName%>" property="<%=iterationProperty%>">
                    <bean:define id="resourceUri" name="listcheckbox" property="resourceUri" type="java.lang.String"/>
					<bean:define id="contextId" name="listcheckbox" property="contextId" type="java.lang.String"/>
                    <bean:define id="status" name="listcheckbox" property="status" type="java.lang.String"/>
                  
					<TR BGCOLOR="#FFFFFF">
					<%
						if (showCheckBoxes.equals("true"))
						{
                            //if ( !refId.startsWith ("builtin_"))
                            //{
                            //    String delId = (String)contextId; 
					%>
						<TD VALIGN="top" CLASS="table-text" WIDTH="1%" HEADERS="header1">
                        <html:multibox name="listcheckbox" property="selectedObjectIds" value="<%=contextId%>" onclick="checkChecks(this.form)" onkeypress="checkChecks(this.form)"/>
						</TD>
					<%
						  //  } else {
					%>
                    
                    
						<!-- <TD VALIGN="top" CLASS="table-text" WIDTH="1%" HEADERS="header1"> &nbsp;</TD> -->
					<%
						  //  } 
                        }
                    %>
						<logic:iterate id="cellItem" name="collectionList" type="com.ibm.ws.console.core.item.CollectionItem" >
					<%
						columnField = (String)cellItem.getColumnField();
					%>
						<TD VALIGN="top" CLASS="table-text" HEADERS="header2">
					<%
						if (cellItem.getIcon().length() > 0)
						{
					%>    		   
							<IMG SRC="<%=request.getContextPath()%>/<%=cellItem.getIcon()%>" ALIGN="BASELINE"></IMG>&nbsp;
					<%
						}		
						if (cellItem.getLink().length() > 0)
						{
							String hRef = cellItem.getLink()+ "&contextId=" + contextId + "&resourceUri="+URLEncoder.encode(resourceUri) + "&perspective=" + URLEncoder.encode(perspective);
					%>
							<A HREF="<%=hRef%>">
								<bean:write name="listcheckbox" property="<%=columnField%>"/>&nbsp;
							</A>
					<%
						}
                        else if (columnField.equalsIgnoreCase("status")) { 
                     %>
                    <bean:define id="node" name="listcheckbox" property="node"/>
                    <bean:define id="name" name="listcheckbox" property="name"/>
<A target="statuswindow" href="/admin/status?text=true&type=server&node=<%=node%>&name=<%=name%>"><IMG name="statusIcon" border="0" SRC="/admin/status?type=server&node=<%=node%>&name=<%=name%>" ALT="<%=statusAlt%>"></A>

                     <%  } else  { %>
                          <bean:write name="listcheckbox" property="<%=columnField%>"/>&nbsp;
					<% } %>
						</TD>
						</logic:iterate>
					</TR>
					</logic:iterate>
                    
                    
</html:form>
<% } catch (Exception e) {e.printStackTrace();
} %>
				</TABLE>  

