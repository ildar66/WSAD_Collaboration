<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
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
          <td class="wizard-step-text"> 
            <html:radio property="selectedItem" value="recommitted" />
	       <bean:message key="appinstall.isolation.transaction.recommitted"/>
	<BR>
            <html:radio property="selectedItem" value="serializable" />
	      <bean:message key="appinstall.isolation.transaction.serializable"/>
          </td>
        </tr>

        <tr>
          <td class="wizard-step-text">
            <html:submit property="ApplyReset" styleId="other" styleClass="buttons">
              <bean:message key="appmanagement.button.apply"/>
            </html:submit>
          </td>
        </tr>
</table>


<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">

<%
    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] column2 = thisForm.getColumn2();

    for (int i=0; i < column0.length; i++) {
	if (i == 0) {
%>

  <tr>
    <th class="column-head-name" scope="col" width="1%">
        <LABEL><input type="checkbox" name="allchecked" value="checkall" onclick="updateCheckAll(this.form)" TITLE="<bean:message key="collection.checkall.checkbox.alt"/>"></LABEL>
       <!-- <a href="javascript:updateCheckAll()"><img src="images/checkAll.gif" width="16" height="16" border="0" alt="Check / Uncheck All Checkboxes"></a>-->
    </th>
    <th class="column-head-name" scope="col"><%= (String)column0[i]%> 
     <!-- <img src="images/sort_up.gif" width="7" height="12" align="absmiddle" alt="Sorted ascending - click to sort descending"> -->
    </th>
    <th class="column-head-name" scope="col"><%= (String)column1[i]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column2[i]%> </th>
  </tr>
<%	} else {%>

  <tr>
     <td class="table-text"  width="1%">
         <html:checkbox property="checkBoxes" value="<%=Integer.toString(i)%>"/>
     </td>
     <td class="table-text" ><%=(String)column0[i]%></td>
     <td class="table-text" ><%=(String)column1[i]%></td>
     <td class="table-text" ><%=(String)column2[i]%></td>
   </tr>

<%  } }%>

</table>
