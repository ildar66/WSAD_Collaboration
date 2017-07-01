<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />

<bean:define id="globalForm" name="globalForm" scope="session" type="GlobalForm"/>

<%
    AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
    String myShow = thisForm.getShow();
    MessageResources messages = (MessageResources) application.getAttribute(Action.MESSAGES_KEY);
    String showPrefs = messages.getMessage(request.getLocale(),"show.preferences");
    if (request.getParameter("submitS.x") != null) {
        if (myShow.equals("collapse"))
            myShow = "expand";
        else
            myShow = "collapse";
    }
%>
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
<% if (myShow.equals("collapse")) { %>
<TR>
<td CLASS="find-filter">
              <html:image page="/images/lplus.gif" property="submitS" alt="<%=showPrefs %>" border="0" align="texttop"/>
              <bean:message key="appinstall.preferences.label" />
              <html:hidden property="show" value="collapse" />
</td>
</TR>
<% } else { %>
<TR>
<td class="find-filter">
              <html:image page="/images/lminus.gif" property="submitS" alt="<%=showPrefs %>" border="0" align="texttop"/>
              <bean:message key="appinstall.preferences.label" />
              <html:hidden property="show" value="expand" />
</td>
</TR>
<TR>
<td class="find-filter-expanded">
      <bean:message key="appinstall.preferences.steps" />
<OL>
    <LI> <bean:message key="appinstall.preferences.step1" /></LI>
    <LI> <bean:message key="appinstall.preferences.step2" />
<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
      <tr valign="baseline" >
          <td class="wizard-step-text">
          <bean:write name="<%=actionForm%>" property="goalString" />
          <br>
          </td>
      </tr>
	  <tr>
        <td class="wizard-step-text"> 
	   <bean:message key="appinstall.user.name" />&nbsp;
	   <html:text property="userName" size="25" />&nbsp;
	   <bean:message key="appinstall.password" />&nbsp;
	   <html:password property="password" size="25" />&nbsp;
	</td>
      </tr>
      <tr>
        <td class="wizard-step-text"> 
           <html:submit property="Apply2" styleId="other" styleClass="buttons">
              <bean:message key="appmanagement.button.apply"/>
           </html:submit>            
        </td>
      </tr>
</table>
</LI>
</OL>
</td>
</TR>
<% } %>
</TABLE>
	  
<%
    String[] column0 = thisForm.getColumn0();
    String[] column1 = thisForm.getColumn1();
    String[] column2 = thisForm.getColumn2();
    String[] column3 = thisForm.getColumn3();
    String[] column4 = thisForm.getColumn4();
    String[] column5 = thisForm.getColumn5();
    String[] column6 = thisForm.getColumn6();
%>

	<html:hidden property="column5" value="<%= (String)column5[0]%>" />

<%
    String prevVerType = "startType";
    for (int i=1; i < column0.length; i++) {
		String thisVerType = column1[i];
		if (!prevVerType.equals(thisVerType)) {
			String dataSources = "CMP40DataSources";
			if (column0[1].equals("J2EE 1.3")) {
				if (thisVerType.equals("EJB 2.x"))
					dataSources = "CMP50DataSources";
			}
%>
<table border="0" cellpadding="3" cellspacing="1" summary="List table">
   <tr>
        <td class="wizard-step-text"> 
	  <bean:message key="appinstall.specify.jndi.name"/>
            <html:select property="selectedList">
              <html:option value="" >
		<bean:message key="appinstall.select"/>
	      </html:option>
		<logic:iterate id="dataSource" name="globalForm" property="<%=dataSources%>" type="java.lang.String">
                <%
                    int index = dataSource.indexOf(":");
                    String node = dataSource.substring(0, index+1);
                    if (dataSources.equals("CMP50DataSources")) {
		      int beginIndex = dataSource.indexOf("/");
		      int endIndex = dataSource.lastIndexOf("_");
                      dataSource = dataSource.substring(beginIndex+1, endIndex);
		    }
                    else
                      dataSource = dataSource.substring(index+1);
                 %>
                <html:option value="<%= dataSource %>" >
		   <%= node + dataSource %>
		</html:option>
		</logic:iterate>
            </html:select>
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
      
<table border="0" cellpadding="3" cellspacing="1" summary="List table">
  <tr>
    <th class="column-head-name" scope="col" width="1%">
        <LABEL><input type="checkbox" name="allchecked" value="checkall" onclick="updateCheckAll(this.form)" TITLE="<bean:message key="collection.checkall.checkbox.alt"/>"></LABEL>
    </th>
    <th class="column-head-name" scope="col"><%= (String)column3[0]%> 
    </th>
    <th class="column-head-name" scope="col"><%= (String)column2[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column4[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column5[0]%> </th>
    <th class="column-head-name" scope="col"><%= (String)column6[0]%> </th>
  </tr>
<%	} %>

  <tr>
     <td class="table-text"  width="1%">
         <html:checkbox property="checkBoxes" value="<%=Integer.toString(i)%>"/>
     </td>
     <td class="table-text" ><%=(String)column3[i]%></td>
     <td class="table-text" ><%=(String)column2[i]%></td>
     <td class="table-text" ><%=(String)column4[i]%></td>
    <td class="table-text" >
       <html:text property="column5" size="25" value="<%=(String)column5[i]%>" />
    </td>
     <td class="table-text" ><%=(String)column6[i]%></td>
   </tr>

			 
<%  
 	   prevVerType = thisVerType;
	} 
%>
	<%-- the hidden field will ensure that selectedList will always be a String[] --%>
	<html:hidden property="selectedList"/>

</table>


