<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<tiles:useAttribute name="slotList" classname="java.util.List" />
<ibmcommon:detectLocale/>

<table border="0" cellpadding="20" cellspacing="0" width="94%">

<%
int slotCount = 1;
Iterator i = slotList.iterator();
while( i.hasNext() ) {
	String name = (String)i.next();
%>

<%if ((slotCount % 2) != 0) {%>
<tr>
<%}%>

	<td  width="50%" valign="top">
		<tiles:insert name="<%=name%>" flush="true"/>
	</td>
	
<%if ((slotCount++ % 2) == 0) {%>
</tr>
<%}%>	
<%
}
%>

</table>
