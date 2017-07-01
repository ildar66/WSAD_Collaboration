<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.webcontainer.*,com.ibm.websphere.models.config.applicationserver.webcontainer.*"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>


<% SessionManagerDetailForm form = (SessionManagerDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.SessionManagerDetailForm"); %>
<% 

        TuningDetailForm form2 = (TuningDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.TuningDetailForm"); 
        
        TuningParams tParms = form2.getTuningParams();
        int WF = tParms.getWriteFrequency().getValue();
        int WI = tParms.getWriteInterval();
        int WC = tParms.getWriteContents().getValue();
        boolean SI = tParms.isScheduleInvalidation();
        
        String theMode = form2.getMode();
        
%>

<%  
    String resourceUri = form.getResourceUri();
    String refId = form.getRefId();
    String contextId = form.getContextId();
    String perspective = form.getPerspective(); 
    
%>

<% String link = "com.ibm.ws.console.servermanagement.forwardCmd.do?forwardName=TuningParams.config.view&sfname=tuningParams" + "&resourceUri=" + resourceUri + "&parentRefId=" + refId + "&contextId=" + contextId + "&perspective=" + perspective ;  %>


<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr> 
  <td class="table-text" nowrap> 
    <html:radio property="mode" value="veryhigh" styleId="vh" disabled="<%=val%>">
    <label for="vh"><bean:message key="Tuning.setting.veryHigh"/></label>
    </html:radio>
  </td>
</tr>
<tr> 
  <td class="complex-property" nowrap> 
    <bean:message key="TuningParams.writeFrequency.displayName"/> <bean:message key="TuningParams.frequency.timeBased"/> 300 <bean:message key="TuningParams.frequency.units"/><br>
    <bean:message key="TuningParams.writeContents.displayName"/> <bean:message key="TuningParams.contents.updated"/><br>
    <bean:message key="TuningParams.cleanup.displayName"/> <bean:message key="boolean.true"/><br>
  </td>
</tr>

<tr> 
  <td class="table-text"> 
    <html:radio property="mode" value="high" styleId="h" disabled="<%=val%>">
    <label for="h"><bean:message key="Tuning.setting.high"/></label>
    </html:radio>
    </td>
</tr>
<tr> 
  <td class="complex-property" nowrap> 
    <bean:message key="TuningParams.writeFrequency.displayName"/> <bean:message key="TuningParams.frequency.timeBased"/> 300 <bean:message key="TuningParams.frequency.units"/><br>
    <bean:message key="TuningParams.writeContents.displayName"/> <bean:message key="TuningParams.contents.all"/><br>
    <bean:message key="TuningParams.cleanup.displayName"/> <bean:message key="boolean.false"/><br>
  </td>
</tr>
<tr> 
  <td class="table-text"> 
    <html:radio property="mode" value="medium" styleId="m" disabled="<%=val%>">
    <label for="m"><bean:message key="Tuning.setting.medium"/></label>
    </html:radio>
</td>
</tr>
<tr> 
  <td class="complex-property" nowrap> 
    <bean:message key="TuningParams.writeFrequency.displayName"/> <bean:message key="TuningParams.frequency.servlet"/><br>
    <bean:message key="TuningParams.writeContents.displayName"/> <bean:message key="TuningParams.contents.updated"/><br>
    <bean:message key="TuningParams.cleanup.displayName"/> <bean:message key="boolean.false"/><br>
  </td>
</tr>

<tr> 
  <td class="table-text"> 
    <html:radio property="mode" value="low" styleId="l" disabled="<%=val%>">
    <label for="l"><bean:message key="Tuning.setting.low"/></label> 
    </html:radio>
    </td>
</tr>
<tr> 
  <td class="complex-property" nowrap> 
    <bean:message key="TuningParams.writeFrequency.displayName"/> <bean:message key="TuningParams.frequency.servlet"/><br>
    <bean:message key="TuningParams.writeContents.displayName"/> <bean:message key="TuningParams.contents.all"/><br>
    <bean:message key="TuningParams.cleanup.displayName"/> <bean:message key="boolean.false"/><br>
  </td>
</tr>
<tr> 
  <td class="table-text"> 
    <html:radio property="mode" value="custom" styleId="c" disabled="<%=val%>">
    <label for="c"><a href="<%=link%>"><bean:message key="Tuning.setting.custom"/></a></label>
    </html:radio>
    </td>
</tr>
<tr> 
  <td class="complex-property" nowrap> 
    <% if (theMode.equals("custom")) { %>
            <bean:message key="TuningParams.writeFrequency.displayName"/> 
            <% if (WF == 0) { %> 
            <bean:message key="TuningParams.frequency.servlet"/>
            <% } else if (WF == 1) { %>
            <bean:message key="TuningParams.frequency.manual"/>
            <% } else { %>
            <bean:message key="TuningParams.frequency.timeBased"/> <%=WI%> <bean:message key="TuningParams.frequency.units"/>   
            <% } %>
            <br>
            <bean:message key="TuningParams.writeContents.displayName"/> 
            <% if (WC == 0) {   %>
            <bean:message key="TuningParams.contents.updated"/>
            <% } else { %>
            <bean:message key="TuningParams.contents.all"/>
            <% } %>
            <br>
            <bean:message key="TuningParams.cleanup.displayName"/> <%=SI%><br>
    <% } %>
  </td>
</tr>

</table>






