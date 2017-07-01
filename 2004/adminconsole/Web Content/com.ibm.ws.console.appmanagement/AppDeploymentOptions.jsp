<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.websphere.management.application.*,com.ibm.websphere.management.application.client.*, com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr valign="baseline" >
      <td class="wizard-step-text">
          <bean:write name="<%=actionForm%>" property="goalString" />
          <br>
      </td>
  </tr>
</table>
	  
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tr>
    <th class="column-head-name" scope="col"> <bean:message key="appinstall.appDeployment.options" />
      <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>
    <th class="column-head-name" scope="col"><bean:message key="appinstall.enable" /></th>
  </tr>


  <%
    AppDeploymentTaskMessages appM = new  AppDeploymentTaskMessages(request.getLocale());
    String earDestination = appM.getColumnName(AppConstants.APPDEPL_INSTALL_DIR, "AppDeploymentOptions");
    String appName = appM.getColumnName(AppConstants.APPDEPL_APPNAME, "AppDeploymentOptions");
    String reloadInterval = appM.getColumnName(AppConstants.APPDEPL_RELOADINTERVAL, "AppDeploymentOptions");
    String yesString = AppConstants.YES_KEY;

    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] checkBoxes = new String[column1.length];

    checkBoxes[0] = "";
    for (int i=1; i < column1.length; i++) {
        if (column1[i].equals(yesString))
            checkBoxes[i] = Integer.toString(i);
        else
            checkBoxes[i] = "";
    }
    thisForm.setCheckBoxes(checkBoxes);

    for (int i=1; i < column0.length; i++) {
  %>

  <tr>
    <td class="table-text" headers="header1"><%= (String)column0[i]%> </td>
    <td class="table-text" headers="header1">
	   <% if (column0[i].equals(earDestination))  { %>
               <html:text property="selectedList" size="25" value="<%=(String)column1[i]%>" />
	   <% } else if (column0[i].equals(reloadInterval))  {  %>
               <html:text property="selectedList" size="25" value="<%=(String)column1[i]%>" />
	       <% } else if (column0[i].equals(appName))  { 
                 if (session.getAttribute(Constants.APPMANAGEMENT_INSTALL_TYPE).equals("redeployApplication")) { %>
                     <%=(String)column1[i]%>
                     <html:hidden property="selectedList" />
                 <% } else { %>
                     <html:text property="selectedList" size="25" value="<%=(String)column1[i]%>" />
	   <% } } else {%>
	        <html:multibox property="checkBoxes" value="<%=Integer.toString(i)%>" />
	   <% }  %>
    </td>
  </tr>

  <%
	}
  %>
</table>

