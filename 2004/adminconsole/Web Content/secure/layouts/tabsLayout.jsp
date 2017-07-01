<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="com.ibm.ws.security.core.SecurityContext,java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="selectUri" classname="java.lang.String" />
<tiles:useAttribute name="tabList" classname="java.util.List" />
<tiles:useAttribute name="formName" classname="java.lang.String" />

<bean:define id="perspectiveValue" name="<%= formName %>" property="perspective"/>
<% String selectedBody = null;%>
<table border="0" cellpadding="0" cellspacing="0"  width="100%" >
<tr valign="top"> 
<logic:iterate id="tab" name="tabList" type="org.apache.struts.tiles.beans.MenuItem" >
<% 

String tabValue = tab.getValue();
if ( tabValue.equals("tab.runtime")) { %>
<bean:define id="showRuntimeTab" name="<%=formName%>" property="showRuntimeTab"/>	
<%
if ( showRuntimeTab.equals("false") ) 
    continue;
	}

    boolean showItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        showItem = false;

        String[] roles = {"administrator", "operator", "configurator", "monitor"};
        if (tab.getTooltip() != null && tab.getTooltip().equals("") == false) {
            StringTokenizer st = new StringTokenizer(tab.getTooltip(), ",");
            ArrayList al = new ArrayList();
            while(st.hasMoreTokens()) {
                al.add(st.nextToken());
            }
            roles = new String[al.size()];
            roles = (String[])al.toArray(roles);
        }

        for (int idx = 0; idx < roles.length; idx++) {
            if (request.isUserInRole(roles[idx])) {
                showItem = true;
                break;
            }
        }
    }
    if (showItem == true) {
        String value = (String) perspectiveValue;
        String href = selectUri + "?EditAction=true&perspective=" + tabValue ;
%>


    <% 
        if (tabList.size() == 1)  {
            selectedBody = tab.getLink();
    %>	
    <td class="tabs-on" width="1%" nowrap height="19">
        <bean:message key='<%=tab.getValue()%>'/>
    </td>
    
     <% } else if ((tabList.size() > 1)  && (value.equalsIgnoreCase(tabValue))) {  
		selectedBody = tab.getLink();
	%>
	<td class="tabs-on" width="1%" nowrap height="19">
        <bean:message key='<%=tab.getValue()%>'/>
        </td> 
        
        <td class="blank-tab" width="1%" nowrap height="19">
        <img src="<%=request.getContextPath()+"/"%>images/onepix.gif" width="2" height="27" align="absmiddle" alt=""> 
        </td>
  
    <%  } else if ((tabList.size() > 1)  && (!value.equalsIgnoreCase(tabValue))) {   %>
	<td class="tabs-off" width="1%" nowrap height="19">
        <a class="tabs-item" href="<%=href%>" title="<bean:message key='<%=tab.getValue()%>'/>"><bean:message key='<%=tab.getValue()%>'/></a>
        </td> 
        
        <td class="blank-tab" width="1%" nowrap height="19">
        <img src="<%=request.getContextPath()+"/"%>images/onepix.gif" width="2" height="27" align="absmiddle" alt="">
        </td>
    
   <%   } %>
<% 
    }
%>
</logic:iterate>
    <td class="blank-tab" width="99%" nowrap height="19">
        <img src="<%=request.getContextPath()+"/"%>images/onepix.gif" width="1" height="27" align="absmiddle" alt="">
    </td>

</tr>
</table>

<table border="0" cellpadding="10" cellspacing="0" valign="top" width="100%" summary="Framing Table">
<tr> 
<td class="layout-manager">
<tiles:insert name="<%=selectedBody%>" flush="true" />
</td> 
</tr>
</table>
