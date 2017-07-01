<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
</HEAD>

<% List list = (List) session.getAttribute("exportList");%>

<BODY>
<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

<H1 id="bread-crumb"><bean:message key="<%=titleKey%>"/></H1>

<p class="instruction-text"><bean:message key="<%=descKey%>"/></p>
  
   <TABLE class="framing-table" BORDER=0 CELLPADDING="4" CELLSPACING="1" WIDTH="100%" SUMMARY="Content Table">
        <tr>
	  <TD VALIGN="TOP" CLASS="column-head">
            <bean:message key="<%=titleKey%>"/></TD>
        </tr>

<% if ( list != null) {
    Iterator i = list.iterator();
    while ( i.hasNext() ) {
	String name = (String)i.next();
%>

        <tr>
          <td valign=top class="table-text" HEADER="header1"><label for="checkbox"><A HREF='secure/downloadFile/<%=name%>'> <%=name%></A></LABEL></td>
        </tr>
 <%
      }
    }
%>	

	
        <tr>
          <th class="button-section">
            <input type="submit" name="ok" value="<bean:message key="button.back"/>" class="buttons" id="navigation">
          </th>
        </tr>
		
      </TABLE>
	
</html:form>

</BODY>
</html:html>
