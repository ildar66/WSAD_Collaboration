<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@page contentType="text/html" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>


     


<% 
String myshow = "collapsed";
String expand = (String)request.getAttribute("expand");
if (expand != null)
    myshow = "expanded";
String submitS = request.getParameter("scopeSubmitS.x");
String show = request.getParameter("scopeShow");

if (submitS != null) {
    if (show.equals("collapsed")) {
        myshow = "expanded";
    } 
    else {
        myshow = "collapsed";
    }
} 

 
%>


<html:form action="selectscope.do" 
           name="contextScopeForm" 
           type="com.ibm.ws.console.core.form.ContextScopeForm"
           method="get"
           styleClass="nopad">
           

<jsp:useBean id="contextScopeForm" scope="session" class="com.ibm.ws.console.core.form.ContextScopeForm"/>

<%
        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
        String cellLabel = messages.getMessage(request.getLocale(),"label.cell");
        String nodeLabel = messages.getMessage(request.getLocale(),"label.node");
        String serverLabel = messages.getMessage(request.getLocale(),"label.server");
        String scopeLabel = messages.getMessage(request.getLocale(),"label.scope");
        String currentlySelected = messages.getMessage(request.getLocale(),"currently.selected.scope");
        String notSelected = messages.getMessage(request.getLocale(),"not.selected");
        
        String currentScope = contextScopeForm.getCurrentScope();
        String scopeOn = "<img src='/admin/images/arrow.gif' width='11' height='13' border='0' align='texttop' alt='"+currentlySelected+"'>";
        String scopeOff = "<img src='/admin/images/onepix.gif' width='11' height='13'  border='0' align='texttop' alt='"+notSelected+"'>";
        
        String serverScope = scopeOff;
        String nodeScope = scopeOff;
        String cellScope = scopeOff;
        String theCell = "";
        String theNode = "";
        String theServer = "";        

        int beginCell = currentScope.indexOf("cells/");
        int beginNode = currentScope.indexOf("/nodes/");
        int beginServer = currentScope.indexOf("/servers/");     
        
        if (beginServer > -1) { 
                serverScope = scopeOn;
                theCell = currentScope.substring(beginCell+6,beginNode);
                theNode = currentScope.substring(beginNode+7,beginServer);
                theServer = currentScope.substring(beginServer+9);
                currentScope = scopeLabel+": "+cellLabel+"=<strong>"+theCell+"</strong>, "+nodeLabel+"=<strong>"+theNode+"</strong>, "+serverLabel+"=<strong>"+theServer+"</strong>";                
        } else if (beginNode > -1) {
                nodeScope = scopeOn;
                theCell = currentScope.substring(beginCell+6,beginNode);
                theNode = currentScope.substring(beginNode+7); 
                currentScope = scopeLabel+": "+cellLabel+"=<strong>"+theCell+"</strong>, "+nodeLabel+"=<strong>"+theNode+"</strong>";                
        } else {
                cellScope = scopeOn;
                theCell = currentScope.substring(beginCell+6); 
                currentScope = scopeLabel+": "+cellLabel+"=<strong>"+theCell+"</strong>";               
        }
        
%>


<% if (myshow.equals("collapsed")) { %> 
<table border="0" cellpadding="2" cellspacing="0" >
    <tr>
        <td class="find-filter" valign="bottom" align="left"  nowrap>
            <input type="image" id="scope" src="<%= request.getContextPath() + "/" %>images/lplus.gif" name="scopeSubmitS"  alt="<bean:message key="show.search.scope"/>" align="texttop" border="0" tabindex="1">
            <%=currentScope%> &nbsp;
            
            <input type="hidden" name="scopeShow" value="collapsed">
        </td>
    </tr>
</table>
<% } else { %> 
<table border="0" cellpadding="2" cellspacing="0"  >
    <tr>
        <td class="find-filter" valign="bottom" align="left" nowrap >
            <input type="image" id="prefs" src="<%= request.getContextPath() + "/" %>images/lminus.gif" name="scopeSubmitS"  alt="<bean:message key="show.search.scope"/>" align="texttop" border="0" tabindex="1">
            <%=currentScope%>&nbsp;
            <input type="hidden" name="scopeShow" value="expanded">
        </td>
    </tr>
    <tr> 
</table>
<table border="0" cellpadding="2" cellspacing="0"  width="95%">    
        <td class="find-filter-expanded"  nowrap>
         
            <table border="0" cellpadding="4" cellspacing="1" width="99%">
            	<logic:equal name="contextScopeForm" property="isSingleSelect" value="true">
	                <tr valign="top"> 
	                    <td class="table-text"  valign="top"  nowrap>
		                    <html:radio property="selectedScope" value="cell" styleId="cellRadio"/>
                            <%=cellScope%> <LABEL for="cellRadio"><bean:message key="label.cell"/></LABEL>
	                    </td>
	                    <td class="table-text" valign="top"  nowrap > 
	                        <bean:write name="contextScopeForm" property="currentCell"/>
	                    </td>
                            <td class="find-filter" rowspan="4">
                            	<LABEL FOR="selectedScope"><bean:message key="msg.scope.desc.single"/></LABEL>
                            </td>
	                </tr>
	                <tr valign="top"> 
	                    <td class="table-text"   valign="top"  nowrap>
		                   <html:radio property="selectedScope" value="node" styleId="nodeRadio"/> 
                           <%=nodeScope%> <LABEL for="nodeRadio"><bean:message key="label.node"/></LABEL>
	                    </td>
	                    <td class="table-text" valign="top"  nowrap  > 
	                        <bean:write name="contextScopeForm" property="currentNode"/>
	                    </td>
	                </tr>
	            	<logic:notEqual name="contextScopeForm" property="currentServer" value="">
		                <tr valign="top">
		                    <td class="table-text" valign="top" nowrap >
			                    <html:radio property="selectedScope" value="server" styleId="serverRadio"/> 
                                <%=serverScope%> <LABEL for="serverRadio"><bean:message key="label.server"/></LABEL>
		                    </td>
		                    <td class="table-text" valign="top"  nowrap >
		                        <bean:write name="contextScopeForm" property="currentServer"/>
		                    </td>
		                </tr>
	            	</logic:notEqual>
            	</logic:equal>
                
                <logic:equal name="contextScopeForm" property="isSingleSelect" value="false">
	                <tr valign="top"> 
	                    <td class="table-text"  nowrap>
	                        <%=cellScope%>  <bean:message key="label.cell"/>
	                    </td>
	                    <td class="table-text" valign="top"  nowrap>
	                        <bean:write name="contextScopeForm" property="currentCell"/>
	                    </td>
                            <td class="find-filter" rowspan="4">
                            	<bean:message key="msg.scope.desc"/>
                            </td>
	                </tr>
	                <tr valign="top"> 
	                    <td class="table-text"  nowrap>
	                       <%=nodeScope%> <LABEL FOR="currentNode"><bean:message key="label.node"/></LABEL>
	                    </td>
	                    <td class="table-text" nowrap> 
	                        <html:text property="currentNode"/>
	                        <html:submit property="browseNodesAction" styleClass="buttons" styleId="other">
	                            <bean:message key="button.browse.nodes"/>
	                        </html:submit>
	                    </td>
	                </tr>
	                <tr valign="top">
	                    <td class="table-text"  nowrap>
	                       <%=serverScope%> <LABEL FOR="currentServer"><bean:message key="label.server"/></LABEL>
            	                    </td>
	                    <td class="table-text"   nowrap>
	                        <html:text property="currentServer"/>
	                        <html:submit property="browseServersAction" styleClass="buttons" styleId="other">
	                            <bean:message key="button.browse.servers"/>
	                        </html:submit>
	                    </td>
	                </tr>
            	</logic:equal>
                
                <tr> 
                    <th valign="top" class="button-section" colspan="2">
                        <html:submit property="applyAction" styleClass="buttons" styleId="navigation">
                            <bean:message key="button.apply"/>
                        </html:submit>
                    </th>
                </tr>
            </table>
        </td>
    </tr>
</table>
<br>
<% } %> 
</html:form>
