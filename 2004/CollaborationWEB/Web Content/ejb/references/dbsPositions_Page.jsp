<%@ page language = "java" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import = "com.hps.july.collaboration.formbean.*"%>
<%@ page import = "com.hps.july.sync.utility.*"%>
<%@ page import = "com.hps.july.sync.model.valueobject.*"%>

<%
/**
	ReferencesForm_Page listForm = (ReferencesForm_Page)session.getAttribute("referencesForm_Page");
	String sortBy = listForm.getSortBy();
*/
%>

<html:html>

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/collaboration/theme/Master.css" rel="stylesheet"
	type="text/css">
<TITLE>dbsPositions_Page.jsp</TITLE>
</HEAD>

<BODY>
<html:errors />
<html:form action="/references_page" styleId="thisForm">

	<html:hidden property="sortBy" />
	<html:hidden property="comand" />
	<html:hidden property="school" />

	<script language="javascript">
function SortBy(field) 
{
	var e = document.all.sortBy;
	e.value = (e.value == field)?field + ' desc':field;
    document.thisForm.school.value = "";	
    document.body.style.cursor="wait";
	document.thisForm.submit();
	return false;
}
function MoveTo(school)
{ 
	document.thisForm.school.value = school;
    document.body.style.cursor="wait";	
	document.thisForm.submit();
	return false;	
}
</script>
	<logic:present name="referencesForm_Page" property="page">
	
		<TABLE class="gridfoot">
			<TBODY>
				<TR>
					<TD>
						<logic:equal name="referencesForm_Page" property="page.previousPageAvailable" value="true">
							<INPUT type=button value="  &lt;   " onclick="MoveTo('previous')">
						</logic:equal>
						<logic:equal name="referencesForm_Page" property="page.nextPageAvailable" value="true">						
							<INPUT type=button value="   &gt;  " onclick="MoveTo('next')">
						</logic:equal>
					</TD>
					<TD>номер старт. записи: <html:text name="referencesForm_Page"
						property="start" size="5" />.</TD>
					<TD>записей на странице: <html:text name="referencesForm_Page"
						property="count" size="5" />.</TD>
					<TD><INPUT type=button value="Обновить" onclick="MoveTo('')"></TD>
				</TR>
			</TBODY>
		</TABLE>

	<TABLE border="1">
		<TBODY>
			<!-- Table header -->
			<TR>
				<logic:iterate id="it" name="referencesForm_Page"
					property="page.headers">
					<th><A title="Сортровать по полю '<%=it%>'"
						href="javascript:SortBy('<%=it%>')"> <bean:write name="it" /></A></th>
				</logic:iterate>
			</TR>
			<!-- Table body -->
			<logic:iterate id="it" name="referencesForm_Page"
				property="page.list">
				<%Object[] items = (Object[]) it;%>
				<TR>
					<%for (int i = 0; i < items.length; i++) {%>
					<td><%=items[i]%></td>
					<%}%>
				</TR>
			</logic:iterate>
		</TBODY>
	</TABLE>

		<TABLE class="gridfoot">
			<TBODY>
				<TR>
					<TD>
						<logic:equal name="referencesForm_Page" property="page.previousPageAvailable" value="true">
							<INPUT type=button value="  &lt;   " onclick="MoveTo('previous')">
						</logic:equal>
						<logic:equal name="referencesForm_Page" property="page.nextPageAvailable" value="true">						
							<INPUT type=button value="   &gt;  " onclick="MoveTo('next')">
						</logic:equal>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		
	</logic:present>

</html:form>
</BODY>

</html:html>
