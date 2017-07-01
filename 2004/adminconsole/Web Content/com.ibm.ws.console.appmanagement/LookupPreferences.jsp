<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page  errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%@ page import="java.util.*" %>


<tiles:useAttribute name="collectionPreferenceList" classname="java.util.List" />
<tiles:useAttribute name="parent" classname="java.lang.String"/>


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

