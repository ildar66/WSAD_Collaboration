<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.ibm.ws.workspace.query.*,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.bean.UserPreferenceBean,com.ibm.ws.security.core.SecurityContext"%>

<% 
String myshow = "collapsed";
String submitS = request.getParameter("submitS.x");
String show = request.getParameter("show");

if (submitS != null) {
    if (show.equals("collapsed")) {
            myshow = "expanded";
    } else {
            myshow = "collapsed";
    }
} 

String target = "detail";
String logoffAfterwards = (String)request.getAttribute("logoff");
String syncDescMsg = "sync.desc";
String cancelButtonMsg = "sync.button.cancel";
if (logoffAfterwards != null) {
	if (logoffAfterwards.equals("true")) {
		target = "_top";
		syncDescMsg = "sync.desc.logoff";
		cancelButtonMsg = "button.logout";
	}
}
else {
	logoffAfterwards = "false";	
}
%>


<%  int counter = 0; %>
<logic:iterate id="changeList" name="SyncChangeList">
<bean:define id="stateValue" name="changeList" property="state"/>
<%
    Integer stateIndex1 = (Integer)stateValue;
    counter += 1;
%>
</logic:iterate>


<form method=GET action="<%= request.getContextPath() + "/"%>syncworkspace.do" target="<%=target%>">

<input type="hidden" name="logoff" value="<%=logoffAfterwards%>">

            <table class="framing-table" border=0 cellpadding="3" cellspacing="1" width="100%" summary="Login Table" >
                
                <tr>
                    <td valign=top class="column-head"  ><bean:message key="sync.title"/></td>
                </tr>
                <tr> 
                    <td valign=top class="table-text"  >
                        <table border=0 cellpadding="3" cellspacing="0" width="100%" summary="Login Table" >                    
                                <tr> 
                                <td valign=top class="table-text"  >
                                    <bean:message key="<%=syncDescMsg%>"/> 
                                    </td>
                                </tr>
                                
                                <tr> 
                                    <td valign=top class="table-text"  ><bean:message key="total.changed.documents"/> <%=counter%> 
                                    </td>
                                </tr>
                
                
                                <tr>
                                    <td valign=top class="complex-property">
                    
<% if (myshow.equals("collapsed")) { %> 

                                        <% if (counter > 0) { %> 

                                            <input type="image" id="prefs" src="images/lplus.gif" name="submitS"  alt="<bean:message key="show.search.and.filter"/>" align="texttop" border="0">
                                            <bean:message key="sync.list"/>
                                            <input type="hidden" name="show" value="collapsed">
                                        </td>
                                    </tr>
                                        <% } %>
                                        
<% } else { %> 
                                            <input type="image" id="prefs" src="images/lminus.gif" name="submitS"  alt="<bean:message key="show.search.and.filter"/>" align="texttop" border="0">
                                            <bean:message key="sync.list"/>
                                            <input type="hidden" name="show" value="expanded">
                                        </td>
                                    </tr>
                                    <tr> 
                                        <td class="complex-property" valign="bottom" align="left" width="100%" nowrap>                             
                                                <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="List table" class="framing-table">
                                                    <tr> 
                                                        <th class="column-head-name" scope="col" nowrap>
                                                            <bean:message key="sync.changed"/>
                                                        </th>
                                                    </tr>
                                                    <logic:iterate id="changeList" name="SyncChangeList">
                                                        <bean:define id="stateValue" name="changeList" property="state"/>
                                                        <%
                                                            Integer stateIndex = (Integer)stateValue;
                                                            String state = com.ibm.ws.sm.workspace.WorkSpaceFileState.message[stateIndex.intValue()];		
                                                        %>
                                                        <tr> 
                                                            <td class="table-text" headers="header1">
                                                                <bean:write name="changeList" property="URI"/>
                                                                &nbsp;&nbsp;
                                                                <%=state%>
                                                            </td>
                                                        </tr>
                                                    </logic:iterate>
                                                </table>
                                        </td>
                                </tr>
                        
<% } %> 
                        </table>

                    </td>

                </tr>
<%
	try {
        RepositoryContext cellContext = (RepositoryContext)session.getAttribute(com.ibm.ws.console.core.Constants.CURRENTCELLCTXT_KEY);
	    WorkSpaceQueryUtil wsQuery = WorkSpaceQueryUtilFactory.getUtil();
    	if (wsQuery.isStandAloneCell(cellContext) == false) {
	        UserPreferenceBean uBean = (UserPreferenceBean) session.getAttribute(com.ibm.ws.console.core.Constants.USER_PREFS);
    	    String syncWithNodes = uBean.getProperty("System/workspace", "syncWithNodes", "false");
			String checked = "";
			if (syncWithNodes.equals("true")) {
				checked = "CHECKED";
			}
            boolean showSyncOption = true;
            if (SecurityContext.isSecurityEnabled()) {
                String[] roles = {"administrator", "operator"};
                showSyncOption = false;
                for (int idx = 0; idx < roles.length; idx++) {
                    if (request.isUserInRole(roles[idx])) {
                        showSyncOption = true;
                        break;
                    }
                }
            }
            if (showSyncOption == true) {
%>                            
                <tr align="left">
                    <td valign=top class="table-text"  colspan="2"> 
		                <input type="checkbox" name="savesyncaction" value="sync" <%=checked%>/>
                    	<bean:message key="sync.label.syncwithnodes"/>
                    </td>
                </tr>
<%
            }
		}
    }
    catch (WorkSpaceException e) {
    	throw new ServletException(e);
    }
%>                            
                <tr align="center" > 
                    <td valign=top class="button-section"  colspan="2"> 
                            <input type="submit" name="saveaction" class="buttons" id="navigation" value='<bean:message key="sync.button.save"/>'>
                            <input type="submit" name="discardaction" class="buttons" id="navigation" value='<bean:message key="sync.button.discard"/>'>
                            <input type="submit" name="cancelaction" class="buttons" id="navigation" value='<bean:message key="<%=cancelButtonMsg%>"/>'>
                    </td>
                </tr>
            </table>
            
            </td>
        </tr>
    </table>


</form>

