<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="com.ibm.ws.security.core.SecurityContext"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<tiles:importAttribute/>

<%@ page import="com.ibm.ws.console.core.bean.*" %>
<%@ page import="com.ibm.ws.console.core.*" %>

<table class="noframe-framing-table" border="0" cellpadding="3" cellspacing="0" width="100%" >
    <tr valign="top">
        <td class="top-navigation">
            <logic:iterate id="item" name="menuBarItems" type="com.ibm.ws.console.core.item.MenuBarItem" >
<% 
    boolean showItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        String[] roles = item.getRoles();
        showItem = false;
        for (int i = 0; i < roles.length; i++) {
            if (request.isUserInRole(roles[i])) {
                showItem = true;
                break;
            }
        }
    }
    if (showItem == true) {
%>
            <%
                String link = item.getLink();
                if (link.startsWith("//")) {
                    link = link.substring(1);
                }
                else if (link.startsWith("/")) {
                    link = request.getContextPath() + link;
                }
                else {
                    link = request.getContextPath() + "/" + link;
                }
            %>
                <a class="top-nav-item" href="<%=link%>" target="<%=item.getTarget()%>">
                <bean:message key='<%=item.getValue()%>'/>
                </a>&nbsp;&nbsp; | &nbsp;&nbsp; 
<%  
    } 
%>
            </logic:iterate>
        </td>
        <td class="top-navigation" align=right>
			<form name="descriptionsForm" action="<%=request.getContextPath()%>/menuBarCmd.do" method="post">
			<% 
                    UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(Constants.USER_PREFS);
					Boolean descriptionsOn = (Boolean) session.getAttribute("descriptionsOn");
					if (descriptionsOn == null) {
                        //obtain show/hide descriptions preference from preferences.xml
						descriptionsOn = new Boolean(uBean.getProperty("System/workspace#descriptionsShow","true"));
						session.setAttribute("descriptionsOn", descriptionsOn);
					}
					if (descriptionsOn.booleanValue() == true) {
			%>		
			            <input type="image" name="descriptionsON" src="<%=request.getContextPath()%>/images/descriptionsON.gif" alt="<bean:message key="hide.field.page.descriptions"/>" align="texttop" border="0" onclick="top.detail.location.reload(true)">&nbsp;&nbsp;
			 <%
			 		} else {
			 %>		
			            <input type="image" name="descriptionsOFF" src="<%=request.getContextPath()%>/images/descriptionsOFF.gif" alt="<bean:message key="show.field.page.descriptions"/>" align="texttop" border="0" onclick="top.detail.location.reload(true)">&nbsp;&nbsp;
			 <%
			 		}

   			 %>		
            </form>
        </td>
    </tr>
</table>
