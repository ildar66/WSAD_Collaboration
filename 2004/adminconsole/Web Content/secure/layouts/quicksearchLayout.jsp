<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="selectUri" classname="java.lang.String" />
<tiles:useAttribute name="openImage" classname="java.lang.String" />
<tiles:useAttribute name="closedImage" classname="java.lang.String" />
<tiles:useAttribute name="parameterName" classname="java.lang.String" />
<tiles:useAttribute name="collectionForm" classname="java.lang.String"/>
<tiles:useAttribute name="searchLabelKey" classname="java.lang.String"/>
<tiles:useAttribute name="searchInLabelKey" classname="java.lang.String"/>
<tiles:useAttribute name="searchWithStringLabelKey" classname="java.lang.String"/>
<tiles:useAttribute name="goLabelKey" classname="java.lang.String"/>

<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<tiles:useAttribute id="searchList" name="searchList" classname="java.util.List" />

          
<bean:define id="quickSearchState" name="<%=collectionForm%>" property="quickSearchState" scope="session"/>
<bean:define id="searchPattern"    name="<%=collectionForm%>" property="searchPattern" scope="session"/>
<bean:define id="searchFilter"     name="<%=collectionForm%>" property="searchFilter"scope="session"/>
<%--
//collectionForm: <%=collectionForm%><BR>
//<%=collectionForm%>.getQuickSearchState: <%=quickSearchState%><BR>
//<%=collectionForm%>.getSearchPattern: <%=searchPattern%><BR>
//<%=collectionForm%>.getSearchFilter: <%=searchFilter%><BR>
--%>


<%
   String hRef = "";
   if (selectUri.indexOf('?') != -1)
      hRef = selectUri + "&" + parameterName + "=" + quickSearchState;
   else
      hRef = selectUri + "?" + parameterName + "=" + quickSearchState;
%>

<form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>" class="nopad">

<A NAME="filter"></A>
<TABLE BORDER="0" CELLPADDING="2" CELLSPACING="0" WIDTH="75%" SUMMARY="Table for displaying filtering function" >
	<TR>
        <TD CLASS="find-filter" VALIGN="top" WIDTH="100%" NOWRAP>
         <A HREF="<%=hRef%>" tabindex="1">
<%
   if (quickSearchState.equals("open"))
   {
%>
            <IMG SRC="<%=request.getContextPath()+"/"+openImage%>" ALT="<bean:message key="hide.filter"/>" BORDER="0" ALIGN="texttop"></A> <bean:message key="<%=searchLabelKey%>"/>

<%
   }
   else
   {
%>
            <IMG SRC="<%=request.getContextPath()+"/"+closedImage%>" ALT="<bean:message key="show.filter"/>" BORDER="0" ALIGN="texttop"></A> <bean:message key="<%=searchLabelKey%>"/>
                                                                                                                                
<%
   }
%>
            
        </TD>
   </TR>
<%
   if (quickSearchState.equals ("open"))
   {
%>
   <TR>
		<TD CLASS="find-filter-expanded" >
                
                    <TABLE border="0" CELLPADDING="2" CELLSPACING="1" WIDTH="100%" SUMMARY="Properties Table">
                      <TR VALIGN="top"> 
                        
                        <TD CLASS="find-filter" nowrap VALIGN="top">
                        
			<label for="filterFieldset"><bean:message key="<%=searchInLabelKey%>"/></label><br> 
			<FIELDSET name="filterFieldset">
            <SELECT NAME="searchFilter">
<%
		Iterator iterSearchList = searchList.iterator();
		while (iterSearchList.hasNext())
		{
			String tempString = (String) iterSearchList.next();
			String optionText = tempString.substring (0, tempString.indexOf(':'));
			String optionValue = tempString.substring (tempString.indexOf(':')+1); 
			if (searchFilter.equals (optionValue))
			{
%>
				<OPTION VALUE="<%=optionValue%>" SELECTED="SELECTED"><bean:message key="<%=optionText%>"/></OPTION>
<%
			}
			else
			{
%>
				<OPTION VALUE="<%=optionValue%>"><bean:message key="<%=optionText%>"/></OPTION>
<%
			}
		}
%>
			</SELECT>
			<!--<bean:message key="<%=searchWithStringLabelKey%>"/>-->
         <INPUT TYPE="text" NAME="searchPattern" SIZE="10" VALUE="<%=searchPattern%>">
         <INPUT TYPE="submit" NAME="searchAction" VALUE="<bean:message key='<%=goLabelKey%>'/>" CLASS="buttons" ID="other">
         </FIELDSET> 
                        </TD>
                      </TR>
                    </TABLE>
          
          </TD>
	</TR>
<%
	}
%>
</TABLE>

</form>

