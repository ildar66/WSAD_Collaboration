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
	if (logoffAfterwards.equals("true")) {
		target = "_top";
	}
}
else {
	logoffAfterwards = "false";	
}
%>
<table border="0" cellpadding="0" cellspacing="0" valign="top" width="100%" summary="Framing Table">
    <tr> 
        <td class="framing-table"> 
            <table border=0 cellpadding="2" cellspacing="1" width="100%" summary="Login Table" class="framing-table">
                <form method=GET action="<%= request.getContextPath() + "/"%>syncworkspace.do" target="<%=target%>">
	            <input type="hidden" name="logoff" value="<%=logoffAfterwards%>">
                <tr>
                    <td valign=top class="column-head" header="header1" >
                    	<bean:message key="confirm.discard.title"/>
                    </td>
                </tr>
                <tr>
                    <td valign=top class="table-text" header="header1" ><bean:message key="confirm.discard.text"/>
                        <table border="0" cellpadding="0" cellspacing="0" width="100%"  summary="">
                             <tr> 
                                <td class="table-text" valign="bottom" align="left" width="100%" nowrap> 
			                        <input type="checkbox" name="noDiscardConfirm" value="true">
			                        <bean:message key="label.no.confirm.discard"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr align="center" > 
                    <td valign=top class="button-section" header="header1" bgcolor="#FFFFFF" colspan="2"> 
                            <input type="submit" name="okdiscardaction" class="buttons" id="navigation" value='<bean:message key="button.yes"/>'>
                            <input type="submit" name="canceldiscardaction" class="buttons" id="navigation" value='<bean:message key="button.no"/>'>
                    </td>
                </tr>
                </form>
            </table>
        </td>
    </tr>
</table>
