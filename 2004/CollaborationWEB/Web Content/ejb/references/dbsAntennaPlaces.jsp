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
<LINK href="../../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>dbsAntennaPlaces.jsp</TITLE>
</HEAD>

<BODY>
<html:errors />
<html:form action="/references" styleId="thisForm">

<html:hidden property="sortBy" />
<html:hidden property="comand" />
<html:hidden property="pagenumber" />

<script language="javascript">
function SortBy(field) 
{
	var e = document.all.sortBy;
	e.value = (e.value == field)?field + ' desc':field;
    document.body.style.cursor="wait";
	document.thisForm.submit();
	return false;
}
function MoveTo(n)
{ 
	document.thisForm.pagenumber.value = n;
	document.thisForm.submit();
}
</script>

	<TABLE border="0">
		<TBODY>
			<!-- Table header -->
			<TR>
				<th nowrap><A title="Сортровать по полю 'name'"
					href="javascript:SortBy('name')">Name</A>  <%=ServletUtilities.isUp(sortBy, "name")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'isUseRecode'"
					href="javascript:SortBy('isUseRecode')">isUseRecode</A>  <%=ServletUtilities.isUp(sortBy, "isUseRecode")%>
				</th>
				<th nowrap><A title="Сортровать по полю 'idNri'"
					href="javascript:SortBy('idNri')">idNri</A>  <%=ServletUtilities.isUp(sortBy, "idNri")%>
				</th>
			</TR>
			<!-- Table body -->
			<TR>
			<TR>
                   <% if(list.size() > 0) {
                         int start = (pagenumber-1)*pagesize;
                         int end = Math.min(pagenumber*pagesize, list.size());
                         DbsAntennaPlaceVO vo = null;
                         for (int i=start; i  <  end; i++){ vo = (DbsAntennaPlaceVO)list.get(i); %>
                          <TR>
                             <TD><%= vo.getName() %></TD>
                             <TD><%= vo.getIsUseRecode() %></TD>
                             <TD><%= vo.getIdNri() %></TD>
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
					<INPUT type=button value="  &lt;&lt;  " onclick="MoveTo(1)"	<%=ServletUtilities.disabled(pagenumber, 1)%>> 
					<INPUT type=button value="  &lt;   " onclick="MoveTo(<%=(pagenumber-1)%>)" <%=ServletUtilities.disabled(pagenumber, 1)%>> 
					<INPUT type=button value="   &gt;  " onclick="MoveTo(<%=(pagenumber+1)%>)" <%=ServletUtilities.disabled(pagenumber, pagecount)%>> 
					<INPUT type=button value="  &gt;&gt;  " onclick="MoveTo(<%=pagecount%>)"   <%=ServletUtilities.disabled(pagenumber, pagecount)%>>
				</TD>
				<TD align=right>Страница <%=pagenumber%> из <%=pagecount%>.</TD>
				<TD align=right>Размер страницы <html:text name="referencesForm" property="pagesize" size="3"/></TD>
			</TR>
		</TBODY>
	</TABLE>
	<%}%>
           <P align=right>Всего записей <%=list.size()%>.</P>	
</html:form>
</BODY>

</html:html>
