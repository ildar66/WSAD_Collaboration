<%@ page language = "java" %>
<%@ page contentType = "text/html; charset=windows-1251" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import = "com.hps.july.collaboration.formbean.*"%>
<%@ page import = "com.hps.july.sync.utility.*"%>
<%@ page import = "com.hps.july.sync.model.valueobject.*"%>

<%
	ReferencesForm listForm = (ReferencesForm)session.getAttribute("referencesForm");
	String sortBy = listForm.getSortBy();
	java.util.List list = (java.util.List)listForm.getResult();
	int pagesize = listForm.getPagesize();
	int pagenumber = listForm.getPagenumber();
	int pagecount = listForm.getPagecount(); 
%>

<html:html>

<HEAD>
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/collaboration/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>dbsPositions.jsp</TITLE>
</HEAD>

<BODY>
<html:errors />
<html:form action="/references" styleId="thisForm">

<html:hidden property="sortBy" />
<html:hidden property="comand" />
<html:hidden property="pagenumber" />
<html:hidden property="isNextPage" />

<script language="javascript">
function SortBy(field) 
{
	var e = document.all.sortBy;
	e.value = (e.value == field)?field + ' desc':field;
    document.body.style.cursor="wait";
    document.thisForm.isNextPage.value = false;
	document.thisForm.submit();
	return false;
}
function MoveTo(n)
{ 
	document.thisForm.pagenumber.value = n;
	document.thisForm.isNextPage.value = true;
	document.thisForm.submit();
}
function Refresh()
{ 
	document.thisForm.isNextPage.value = false;
    document.body.style.cursor="wait";
	document.thisForm.submit();
}
</script>
	<% if(pagecount > 0) { %>
	<TABLE class="gridfoot">
		<TBODY>
			<TR>
				<TD>
					<INPUT type=button value="  &lt;&lt;  " onclick="MoveTo(1)"	> 
					<INPUT type=button value="  &lt;   " onclick="MoveTo(<%=(pagenumber-1)%>)" > 
					<INPUT type=button value="   &gt;  " onclick="MoveTo(<%=(pagenumber+1)%>)" > 
					<INPUT type=button value="  &gt;&gt;  " onclick="MoveTo(<%=pagecount%>)"   >
				</TD>
				<TD align=right>Страница <%=pagenumber%> из <%=pagecount%>.</TD>
				<TD><INPUT type=button value="Обновить" onclick="Refresh()"></TD>
				<TD>Всего записей <%=list.size()%>.</TD>
			</TR>
		</TBODY>
	</TABLE>
	<%}%>
        
	<TABLE border="0">
		<TBODY>
			<!-- Table header -->
			<TR>
				<th nowrap><A title="Сортровать по полю 'name'"
					href="javascript:SortBy('name')">Name</A>  <%=ServletUtilities.isUp(sortBy, "name")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'gsmID'"
					href="javascript:SortBy('gsmID')">gsmID</A>  <%=ServletUtilities.isUp(sortBy, "gsmID")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'apparatType'"
					href="javascript:SortBy('apparatType')">apparatType</A>  <%=ServletUtilities.isUp(sortBy, "apparatType")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'apparatPlace'"
					href="javascript:SortBy('apparatPlace')">apparatPlace</A>  <%=ServletUtilities.isUp(sortBy, "apparatPlace")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'oporaPlace'"
					href="javascript:SortBy('oporaPlace')">oporaPlace</A>  <%=ServletUtilities.isUp(sortBy, "oporaPlace")%>
				</th>
				
			</TR>
			<!-- Table body -->
			<TR>
			<TR>
                   <% if(list.size() > 0) {
                         //int start = (pagenumber-1)*pagesize;
                         int start = 0;
                         int end = Math.min(pagenumber*pagesize, list.size());
                         DbsPositionVO vo = null;
                         for (int i=start; i  <  end; i++){ vo = (DbsPositionVO)list.get(i); %>
                          <TR>
                             <TD><%= vo.getName() %></TD>
                             <TD><%= vo.getGsmID() %></TD>
                             <TD><%= vo.getApparatType() %></TD>
                             <TD><%= vo.getApparatPlace() %></TD>
                             <TD><%= vo.getOporaPlace() %></TD>
                          </TR>
                      <% }
                       } else { %>
                          <TR> <TD align=center colspan=5>Нет данных для показа</TD>   </TR>
                    <% } %>				
			</TR>
		</TBODY>
	</TABLE>
	<% if(pagecount > 0) { %>
	<TABLE class="gridfoot">
		<TBODY>
			<TR>
				<TD>
					<INPUT type=button value="  &lt;&lt;  " onclick="MoveTo(1)"> 
					<INPUT type=button value="  &lt;   " onclick="MoveTo(<%=(pagenumber-1)%>)"> 
					<INPUT type=button value="   &gt;  " onclick="MoveTo(<%=(pagenumber+1)%>)"> 
					<INPUT type=button value="  &gt;&gt;  " onclick="MoveTo(<%=pagecount%>)">
				</TD>
				<TD align=right>Страница <%=pagenumber%> из <%=pagecount%>.</TD>
				<TD><INPUT type=button value="Обновить" onclick="Refresh()"></TD>
				<TD align=right>Размер страницы <html:text name="referencesForm" property="pagesize" size="3"/></TD>
			</TR>
		</TBODY>
	</TABLE>
	<%}%>
           <P align=right>Всего записей <%=list.size()%>.</P>	
</html:form>
</BODY>

</html:html>
