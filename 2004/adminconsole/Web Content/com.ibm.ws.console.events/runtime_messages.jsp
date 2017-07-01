<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="com.ibm.ws.console.core.*,com.ibm.ws.console.events.EventManagerBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>

<% 	String userName = ((User) session.getAttribute(Constants.USER_KEY)).getUsername();
%>

<table border="0" cellpadding="2" cellspacing="1" summary="List table" class="framing-table" width="100%">
    <tr> 
      <td class="column-head" colspan="4" nowrap><bean:message key="events.heading"/>
      </td>
      <td rowspan=2 class="function-button-section" nowrap valign="top">
            <input type="button" name="button.clear" value="<bean:message key="button.clear"/>" class="buttons" id="navigation" onClick="location='<%=request.getContextPath()%>/eventCollection.do?button.clear=Clear'">
       </td>
    </tr>

    <tr> 
        <td class="table-text"  ><bean:message key="events.totalMessageString"/><%=EventManagerBean.getTotalAllMessagesCount()%></td>
        <td class="table-text"  ><img alt='<bean:message key="error.msg.error"/>' src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/error.gif" width="16" height="16" align="texttop">: 
        <a href="<%=request.getContextPath()%>/com.ibm.ws.console.events.forwardCmd.do?forwardName=events.content.main&type=errors" target="WAS_Status" ><%=EventManagerBean.getNewErrorsCount(userName)%> <bean:message key="events.new"/>, <%=EventManagerBean.getTotalErrorsCount()%> <bean:message key="events.total"/></a></td>
        <td class="table-text" ><img alt='<bean:message key="error.msg.warning"/>' src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/warning.gif" width="16" height="16" align="texttop">: 
        <a href="<%=request.getContextPath()%>/com.ibm.ws.console.events.forwardCmd.do?forwardName=events.content.main&type=warnings" target="WAS_Status"><%=EventManagerBean.getNewWarningsCount(userName)%> <bean:message key="events.new"/>, <%=EventManagerBean.getTotalWarningsCount()%> <bean:message key="events.total"/></a></td>
        <td class="table-text" ><img alt='<bean:message key="error.msg.information"/>' src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/information.gif" width="16" height="16" align="texttop">: 
        <a href="<%=request.getContextPath()%>/com.ibm.ws.console.events.forwardCmd.do?forwardName=events.content.main&type=info" target="WAS_Status"><%=EventManagerBean.getNewInformationCount(userName)%> <bean:message key="events.new"/>, <%=EventManagerBean.getTotalInformationCount()%> <bean:message key="events.total"/></a> </td>
    </tr>
</table>

