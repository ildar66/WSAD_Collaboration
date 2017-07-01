<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%-- Layout component 
  Render a list of tiles in a vertical column
  @param : list List of names to insert 
  
--%>

<tiles:useAttribute id="list" name="list" classname="java.util.List" />
<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<html:form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>">
<html:hidden property="action"/>
<%
Iterator i=list.iterator();
while( i.hasNext() )
  {
  String name= (String)i.next();
%>
<tiles:insert name="<%=name%>" flush="true" />

<%
  } // end loop
%>    
</html:form>
 
