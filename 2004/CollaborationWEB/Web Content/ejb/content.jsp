<%@ page language="java" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%--@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" --%>
<%--@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" --%>

<% //session.removeAttribute (com.ibm.ws.console.core.Constants.CURRENT_FORMTYPE); %>

<ibmcommon:detectLocale/>

<TABLE border="1">
	<TBODY>
		<TR>
			<TD>asdf</TD>
			<TD>sdfg</TD>
		</TR>
		<TR>
			<TD>asdf</TD>
			<TD>asdfga</TD>
		</TR>
	</TBODY>
</TABLE>

<tiles:insert definition="console.content.main" flush="true" />