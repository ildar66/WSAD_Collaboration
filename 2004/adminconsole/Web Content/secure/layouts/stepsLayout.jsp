<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*, com.ibm.ws.console.core.item.*"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<tiles:useAttribute name="stepsList" classname="java.util.List" />
<tiles:useAttribute name="stepArraySessionKey" classname="java.lang.String" />
<tiles:useAttribute name="reqdTaskSessionKey" classname="java.lang.String" />

<ibmcommon:errors/>
<%
// stepArray keeps the ordering of the valid steps as ordered by the "stepsList" iterator in the definition
// this is needed to jump to desired steps.
// if not in session, create it.
ArrayList stepArray;
if ((stepArray = (ArrayList) session.getAttribute(stepArraySessionKey)) == null) {
    stepArray = new ArrayList();
    stepArray.add(0, null);
    Iterator j = stepsList.iterator();
    while( j.hasNext() ) {
        AppInstallItem step= (AppInstallItem)j.next();
        String strTok = step.getLink();
        int index = strTok.lastIndexOf("/");
        strTok = strTok.substring(index+1);

        if (session.getAttribute(strTok + "Form") != null)
            stepArray.add(step.getValue());
    }
    session.setAttribute(stepArraySessionKey, stepArray);
}
%>
<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="disableStepsLink" classname="java.lang.String" />

<ibmcommon:detectLocale/>


<html:html locale="true">
<HEAD>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>


<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">

</HEAD>


<BODY CLASS="content">

<%
  // .do of the action handler in definition was omitted for easier comparison.
  // Appending the .do now.
  String actionPath = actionHandler + ".do";
%>
<html:form action="<%=actionPath%>" name="<%=actionForm%>" type="<%=formType%>">

<H1 id="bread-crumb">  
<bean:message key="<%=titleKey%>"/>
</H1>
<p class="instruction-text"> 
<bean:message key="<%=descKey%>"/>
</p>

<P>

<%-- Iterate over names.
  We don't use <iterate> tag because it doesn't allow insert (in JSP1.1)
 --%>
<%
HashMap reqdTaskMap = (HashMap) session.getAttribute(reqdTaskSessionKey);

int stepNumber = 1;
Iterator i = stepsList.iterator();
while( i.hasNext() ) {
    // Iterate thro' the item list in the main definition
    AppInstallItem step= (AppInstallItem)i.next();

    if (!stepArray.contains(step.getValue()))
        continue;

    // Strip out the path from the item[i].getLink(), get the jsp filename at the end of the path
    String strTok = step.getLink();
    int index = strTok.lastIndexOf("/");
    strTok = strTok.substring(index+1);

    // now compare the jsp filename with the actionHandler coming from definition.
    // If equal, insert jsp into this step - current step
    if (actionHandler.equalsIgnoreCase(strTok)) {

        // .jsp of the jsp filename in item[i].link was omitted for easier comparison.
        // Appending the .jsp now.
        String jspPath = step.getLink() + ".jsp";
 %>

    <html:hidden property="currentStep" value="<%=step.getValue()%>" />

    <table class="wizard-table" border="0" cellpadding="8" cellspacing="0" width="100%" summary="Multiple step process table">
      <tbody>
      <tr valign="top">
        <th class="wizard-step" scope="rowgroup">
          <a name="<%=Integer.toString(stepNumber)%>"></a>
          <img src="<%=request.getContextPath()%>/images/arrow.gif" width="11" height="13" align="texttop" alt="<bean:message key="current.step"/>">
          <bean:message key="step.string"/>&nbsp;<%=Integer.toString(stepNumber)%>&nbsp;:
          &nbsp;&nbsp;<bean:message key="<%=step.getValue()%>"/>
        </th>
      </tr>
      <tr>
        <td class="wizard-step-expanded">
          <tiles:insert page="<%=jspPath%>" flush="true" >
             <tiles:put name="actionForm" beanName="actionForm" beanScope="page"/>
          </tiles:insert>
        </td>
      </tr>
      <tr>
        <td class="wizard-button-section">
          <% if (stepNumber > 1) { %>
          <html:submit property="installAction" styleId="navigation" styleClass="buttons">
            <bean:message key="button.previous"/>
          </html:submit>
          <% } %>
          <html:submit property="installAction" styleId="navigation" styleClass="buttons">
            <% if (stepNumber < (stepArray.size() -1)) { %>
              <bean:message key="button.next"/>
            <% } else { %>
              <bean:message key="button.finish"/>
            <% } %>
          </html:submit>
          <html:cancel property="installAction" styleId="navigation" styleClass="buttons">
            <bean:message key="button.cancel"/>
          </html:cancel>
        </td>
      </tr>
      </tbody>
    </table>
<%
    } // end if (actionHandler.equalsIgnoreCase(strTok)) {
    else {
            boolean disable = (new Boolean(disableStepsLink)).booleanValue();
     // comparison of the jsp name with that of actionHandler failed.
     // display step button, step title and step description.
%>
    <table border="0" cellpadding="3" cellspacing="0" width="100%" summary="List table">
      <tr valign="top">
        <td class="wizard-step" nowrap>
<%
            if (reqdTaskMap != null) {
                if (!((Boolean)reqdTaskMap.get(strTok)).booleanValue()) {
%>
              <img src="<%=request.getContextPath()%>/images/attend.gif" width="15" height="15" align="texttop">
          <% } else { %>
              <img src="<%=request.getContextPath()%>/images/blank20.gif" width="15" height="15" align="texttop">
          <% } } else { %>
              <img src="<%=request.getContextPath()%>/images/blank20.gif" width="15" height="15" align="texttop">
          <% } %>
          <a name="<%=Integer.toString(stepNumber)%>"></a>
<% if (disable) {  %>
           <bean:message key="step.string"/> <%=Integer.toString(stepNumber)%>

<% } else { %>

           <html:submit property="stepSubmit" styleId="steps" styleClass="buttons">
             <bean:message key="step.string"/> <%=Integer.toString(stepNumber)%>
           </html:submit>
<% } %>
       &nbsp;&nbsp;<bean:message key="<%=step.getValue()%>"/>
           
        </td>
      </tr>
    </table>
<%
  } // end of else
        stepNumber++;
} // end while loop
%>

</html:form>

</body>
</html:html>
