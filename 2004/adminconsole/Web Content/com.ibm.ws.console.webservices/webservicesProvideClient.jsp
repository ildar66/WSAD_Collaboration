<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.webservices.editbind.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />


<bean:define id="contextId" name="<%=actionForm%>" property="contextId" type="java.lang.String"/> 

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">

<%
    ProvideClientDetailForm thisForm = (ProvideClientDetailForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();
    String[] column4 = thisForm.getColumn4();
    String editString = thisForm.getEditString();

    // Column 1 is only used for EJB modules
    boolean showColumn1 = true;
    if ((column1[0] != null) && (column1[0].length() == 0))
       showColumn1 = false;

    for (int i=0; i < column0.length; i++) {
		if (i == 0) {
%>


  <tr>
    <th class="column-head-name" scope="col"><%= (String)column0[i]%> 
     <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>

  <%
    if (showColumn1 == true) {
  %>
    <th class="column-head-name" scope="col"><%= (String)column1[i]%> </th>
  <%
    }
  %>
    <th class="column-head-name" scope="col"><%= (String)column2[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column3[i]%> </th>
    <html:hidden property="column3" value="<%= (String)column3[i]%>" />
    <th class="column-head-name" scope="col" NOWRAP><%= (String)column4[i]%> </th>
  </tr>
<%	} else  {%>

  <tr>
    <td class="table-text" ><%= (String)column0[i]%> </td>

  <%
    if (showColumn1 == true) {
  %>
    <td class="table-text" ><%= (String)column1[i]%> </td>
  <%
    }
  %>

    <td class="table-text" ><%= (String)column2[i]%> </td>
    <td class="table-text" >
       <html:text property="column3" size="25" value="<%=(String)column3[i]%>" />
    </td>

    <td class="table-text" >
        <%
            String fileResource = (String)column2[i];
            String link = "com.ibm.ws.console.webservices.editbind.forwardCmd.do?forwardName=Webservices.TypeMap.content.main";
            link = link + "&sfname=defaultMappings&parentRefId=" + fileResource;
            link = link + "&webServiceId=" + column0[i];
            link = link + "&resourceUri=" + fileResource + "&contextId=" + contextId;
        %>
       <a href="<%=link%>"><%= (String)editString%></a>
    </td>
  </tr>

  <%
          }
	}
        if (column0.length == 1) {
  %>
    <tr>
      <td class="table-text" ><bean:message key="PersistenceWebSvc.none"/></td>
      <td class="table-text"> </td>
  <%
    if (showColumn1 == true) {
  %>
      <td class="table-text"> </td>
  <%
    }
  %>
      <td class="table-text"> </td>
      <td class="table-text"> </td>
    </tr>
  <%
        }
  %>

</table>
