<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<tiles:useAttribute name="slotImage" classname="java.lang.String" />
<tiles:useAttribute name="slotTitle" classname="java.lang.String" />
<tiles:useAttribute name="slotLink" classname="java.lang.String" />
<tiles:useAttribute name="slotLinkText" classname="java.lang.String" />
<tiles:useAttribute name="slotDesc" classname="java.lang.String" />
<tiles:useAttribute name="slotUrl" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<table  border="0" cellpadding="7" cellspacing="0" width="100%" >
	<tr> 
    	<td class="nolines" align="top">
    		<img src="<%=request.getContextPath()%>/<%=slotImage%>" align="left" alt="">
    		<% if (slotLink != null && slotLink.equals("") == false) { %>
	            <a href="<%=slotLink%>" target="_blank">
    			<bean:message key="<%=slotTitle%>"/>
    			</a>
    		<% } else {%>
    			<bean:message key="<%=slotTitle%>"/>
    		<% }%>
		</td>
  	</tr>
</table>

<table border="0" cellpadding="5" cellspacing="0" width="100%" >
	<tr>                   
    	<td class="linesmost" align="top">
    		<% if (slotUrl != null && slotUrl.equals("") == false) { %>
    			<tiles:insert name="<%=slotUrl%>" flush="true"/>
    		<% } else {%>
		   		<bean:message key="<%=slotDesc%>"/>
    		<% }%>
     	</td>
	</tr>
</table>

