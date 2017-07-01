<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%
String target = "detail";
String logoffAfterwards = (String)request.getAttribute("logoff");
if (logoffAfterwards != null) {
	if (logoffAfterwards.equals("true"))
		target = "_top";
}
else {
	logoffAfterwards = "false";	
}
%>
<form name="conflictForm" method="POST" action="<%= request.getContextPath() + "/"%>syncworkspace.do" target="<%=target%>">
    <input type="hidden" name="logoff" value="<%=logoffAfterwards%>">
    <table border=0 cellpadding="0" cellspacing="0" width="100%" summary="Framing Table" >
    <tr>
        <td class="framing-table" >
            <table border=0 cellpadding="4" cellspacing="1" width="100%" summary="Login Table" >
            <tr>
                <td valign=top class="column-head" header="header1" >Save Conflict</td>
            </tr>
            <tr> 
                <td valign=top class="table-text" header="header1">
                    <bean:message key="sync.conflict.desc"/>
                    <br>
                    <br>
                    <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="List table" class="framing-table">
                    <tr> 
                        <th class="column-head-name" scope="col" nowrap>
                            <bean:message key="sync.conflict.items"/>
                        </th>
                        <th class="column-head-name" scope="col" nowrap>
                            <bean:message key="sync.conflict.action"/>
                        </th>
                    </tr>
                    <logic:iterate id="conflict" name="SyncConflictList">
                    <tr id="datarow0"> 
                        <td class="table-text" headers="header1">
                            <bean:write name="conflict" property="key"/>
                        </td>
                        <td class="table-text" headers="header1">
                            <select name="conflictresolution">
                                <option selected><bean:message key="sync.conflict.overwrite"/></option>
                                <option><bean:message key="sync.conflict.discard"/></option>
                            </select>
                        </td>
                    </tr>
                    </logic:iterate>
                    </table>
                </td>
            </tr>
            <tr align="center" > 
                <td valign=top class="button-section" header="header1"  colspan="2"> 
                    <input type="submit" name="saveaction" class="buttons" id="navigation" value='<bean:message key="sync.button.save"/>'>
                    <input type="submit" name="discardaction" class="buttons" id="navigation" value='<bean:message key="sync.button.discard"/>'>
                    <input type="submit" name="cancelaction" class="buttons" id="navigation" value='<bean:message key="sync.button.cancel"/>'>
                </td>
            </tr>
            </table>
        </td>
    </tr>
    </table>
</form> 
