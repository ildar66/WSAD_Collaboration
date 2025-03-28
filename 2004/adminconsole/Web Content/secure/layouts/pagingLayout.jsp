<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="pagingTotalLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="displayLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="pagingFilteredTotalLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="pagingPreviousLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="pagingNextLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="pagingPageLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="pagingOfLabelKey" classname="java.lang.String" />
<tiles:useAttribute name="collectionForm" classname="java.lang.String"/>

<tiles:useAttribute name="formAction" classname="java.lang.String" />
<tiles:useAttribute name="formName" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<%-- <jsp:useBean id="user" scope="session" class="com.ibm.ws.console.core.User"/>
<bean:define id="numberPerPage" name="user" property="range" type="java.lang.String"/>
--%>
<% String  numberPerPage = (String)session.getAttribute("paging.visibleRows");  %>

<bean:define id="searchPattern" name="<%=collectionForm%>" property="searchPattern"/>
<bean:define id="totalRows"     name="<%=collectionForm%>" property="totalRows" type="java.lang.String"/>
<bean:define id="filteredRows"  name="<%=collectionForm%>" property="filteredRows" type="java.lang.String"/>
<bean:define id="currentPage"   name="<%=collectionForm%>" property="pageNumber" type="java.lang.String"/>

<%--Number Per Page: <%=numberPerPage%><BR>Total rows: <%=totalRows%><BR>Filtered rows: <%=filteredRows%><BR>Current page: <%=currentPage%><BR>
--%>
<%
	int totalCount;
   try
   {
		totalCount = Integer.parseInt (totalRows);
      if (totalCount < 0)
      {
			totalCount = 0;
      }
	}
   catch (NumberFormatException nfe)
   {
		totalCount = 0;
   }
	int filteredCount;
   try
   {
		filteredCount = Integer.parseInt (filteredRows);
      if (filteredCount < 0)
      {
			filteredCount = 0;
      }
	}
   catch (NumberFormatException nfe)
   {
		filteredCount = 0;
   }
	int numPerPageCount;
   try
   {
		numPerPageCount = Integer.parseInt (numberPerPage);
      if (numPerPageCount < 0)
      {
			numPerPageCount = 20;
      }
	}
   catch (NumberFormatException nfe)
   {
		numPerPageCount = 20;
   }
	int currentPageCount;
   try
   {
		currentPageCount = Integer.parseInt (currentPage);
      if (currentPageCount < 0)
      {
			currentPageCount = 0;
      }
	}
   catch (NumberFormatException nfe)
   {
		currentPageCount = 0;
   }
	int totalPages = 0;
	if (numPerPageCount > 0)
		totalPages = (int)(Math.ceil ((float)filteredCount/(float)numPerPageCount));
%>

<form action="<%=formAction%>" name="<%=formName%>" type="<%=formType%>" class="nopad">

<TABLE BORDER="0" CELLPADDING="5" CELLSPACING="0" WIDTH="50%" SUMMARY="Table for displaying paging function">
	<TR>
		<TD CLASS="find-filter" VALIGN="baseline" ALIGN="left" WIDTH="10%" NOWRAP>
			
			<bean:message key="<%=pagingTotalLabelKey%>"/>: <%=totalRows%>
			
<%
         if ((!(searchPattern.equals("*") || searchPattern.equals("?") || searchPattern.equals("%")))
				|| (filteredCount < totalCount))
         {
%>
				
				, &nbsp;<bean:message key="<%=pagingFilteredTotalLabelKey%>"/>: <%=filteredRows%>	
				
<%
         }
%>
                </TD>
                <TD CLASS="find-filter" VALIGN="baseline" ALIGN="left" WIDTH="10%" NOWRAP>
<%
	if (filteredCount > numPerPageCount && numPerPageCount > 0)
	{
%>                
                              
                        <bean:message key="<%=pagingPageLabelKey%>"/>: <%=currentPageCount%>, 
			<bean:message key="<%=pagingOfLabelKey%>"/>: <%=totalPages%> 
                         
                              
<%
                String prevDisabled = "DISABLED";
                String nextDisabled = "DISABLED";
		if (currentPageCount > 1)
		{
                  prevDisabled = "";
%>
                  <SCRIPT language="javascript">
                    var printPre = false;
                    var printNext= false;
                    if (document.layers) {
                        printPre = true;
                    }
                  </SCRIPT>
<%                  
		}
		if (currentPageCount < totalPages)
		{
                  nextDisabled = "";
%>
                  <SCRIPT language="javascript">
                    if (document.layers) {
                        printNext = true;
                    }                  
                  </SCRIPT>
<%                  
		}
%>
                </TD>
                <TD CLASS="was-message-item" VALIGN="baseline" ALIGN="left" WIDTH="10%" NOWRAP>
                  <SCRIPT language="javascript">
                    if (document.layers) {
                        if (printPre) {
                            document.write("<INPUT TYPE=\"submit\" NAME=\"previousAction\" value=\"<bean:message key='statustray.prev.button'/>\" CLASS=\"buttons\" ID=\"paging\" <%=prevDisabled%>>");
                        } 
                    } else {
                        document.write("<INPUT TYPE=\"submit\" NAME=\"previousAction\" value=\"<bean:message key='statustray.prev.button'/>\" CLASS=\"buttons\" ID=\"paging\" <%=prevDisabled%>>");
                    }
                  </SCRIPT>
                  <NOSCRIPT>		    
                  <INPUT TYPE="submit" NAME="previousAction" value="<bean:message key='statustray.prev.button'/>" CLASS="buttons" ID="paging" <%=prevDisabled%>>
                  </NOSCRIPT>		    
                </TD>    
                <TD CLASS="was-message-item" VALIGN="baseline" ALIGN="left" WIDTH="10%" NOWRAP>
                  <SCRIPT language="javascript">
                    if (document.layers) {
                        if (printNext) {
                            document.write("<INPUT  TYPE=\"submit\" NAME=\"nextAction\" VALUE=\"<bean:message key='statustray.next.button'/>\" CLASS=\"buttons\" ID=\"paging\" <%=nextDisabled%>>");
                        } 
                    } else {
                        document.write("<INPUT  TYPE=\"submit\" NAME=\"nextAction\" VALUE=\"<bean:message key='statustray.next.button'/>\" CLASS=\"buttons\" ID=\"paging\" <%=nextDisabled%>>");
                    }
                  </SCRIPT>
                  <NOSCRIPT>		    
                  <INPUT  TYPE="submit" NAME="nextAction" VALUE="<bean:message key='statustray.next.button'/>" CLASS="buttons" ID="paging" <%=nextDisabled%>>
                  </NOSCRIPT>                    
<%
	}
%>

		</TD>
	</TR>

</TABLE>

</form>
