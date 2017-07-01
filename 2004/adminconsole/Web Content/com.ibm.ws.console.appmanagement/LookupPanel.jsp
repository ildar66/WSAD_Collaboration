<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ page import="com.ibm.ws.console.appmanagement.*, com.ibm.ws.console.appmanagement.form.*"%>
<%@ page import="org.apache.struts.action.Action, org.apache.struts.util.MessageResources"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

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

<BODY>
<ibmcommon:errors/>
<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

<%
   ServletContext servletContext = (ServletContext)pageContext.getServletContext();
   MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
   String groupsLabel = messages.getMessage(request.getLocale(), "appmanagement.lookup.groups");
   String list = "users";
   AppInstallForm thisForm = (AppInstallForm) session.getAttribute(actionForm);
   String[] checkBoxes =  thisForm.getCheckBoxes();
   String[] roles = thisForm.getColumn0();
   if (thisForm.getLookup().equalsIgnoreCase(groupsLabel))
	   list = "groups";
%>


 <bean:define id="lookupForm" name="LookupUsersGroupsForm" scope="session" type="com.ibm.ws.console.appmanagement.form.LookupUsersGroupsForm"/>


<table border="0" class="wizard-table" cellpadding="8" cellspacing="0" width="100%" summary="User Mapping table">
  <tbody>
  <tr>
     <td class="wizard-step-text" colspan="3">
     
       <bean:message key="appinstall.lookup.roles" />
         <br>
         <select name="roleList" size=5"  disabled>
<%   for (int ind=0; ind < checkBoxes.length; ind++) { 
                 int rowNumber = Integer.parseInt(checkBoxes[ind]);  %>
                 <option>
                     <%= roles[rowNumber]%>
                  </option>
<%   }   %>
         </select>
         <br>

         <bean:message key="appinstall.lookup.search" />
         <br>
              <bean:message key="appinstall.search.limit"/>&nbsp;<html:text property="resAuth" size="10" />
              <BR>
              <bean:message key="appinstall.search.string"/>&nbsp;<html:text property="selectedItem" size="25" />
              <html:submit property="arrow" styleId="navigation" styleClass="buttons">
                  <bean:message key="appmanagement.button.search"/>
              </html:submit>
            </td>
          </tr>
      <tr>  
        <td class="wizard-step-text" colspan="3">
          <bean:message key="appinstall.lookup.select"/>
        </td>
      </tr>

        <tr valign="top">
        
          <td class="wizard-step-text"> <bean:message key="appinstall.lookup.available"/><br>
            <html:select property="selectedList" size="20" multiple="yes">
	      <logic:iterate id="user" name="lookupForm" property="<%=list%>">
                <option value='<%= (String) user %>' >
		   <%= (String) user %>
		</option>
	      </logic:iterate>
            </html:select>
          </td>
          <td class="wizard-step-text" valign="middle">
		      <INPUT TYPE="submit" NAME="arrow" VALUE=">>" />
			  <br><br>
		      <INPUT TYPE="submit" NAME="arrow" VALUE="<<" />
          </td>
		  
          <td class="wizard-step-text" ><bean:message key="appinstall.lookup.selected"/><br>
            <html:select property="selectedList2" size="20" multiple="yes">
	       <logic:iterate id="user" name="<%=actionForm%>" property="backupList">
                <option value='<%= (String) user %>' >
		   <%= (String) user %>
		</option>
	       </logic:iterate>
            </html:select>
          </td>
        </tr>



   <tr>
      <td class="wizard-button-section"  colspan="3">
        <html:submit property="arrow" styleId="navigation" styleClass="buttons">
          <bean:message key="appmanagement.button.ok"/>
        </html:submit>
        <html:cancel property="arrow" styleId="navigation" styleClass="buttons">
          <bean:message key="appmanagement.button.cancel"/>
        </html:cancel>
      </td>
   </tr>
   </tbody>
 </table>

</html:form>

</BODY>
</html:html>
