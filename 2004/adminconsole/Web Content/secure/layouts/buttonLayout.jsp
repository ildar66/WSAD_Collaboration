<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.Iterator,com.ibm.ws.console.core.item.ActionSetItem,com.ibm.ws.security.core.SecurityContext"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>


<tiles:useAttribute name="buttonCount" classname="java.lang.String" />
<tiles:useAttribute name="definitionName" classname="java.lang.String" />
<tiles:useAttribute name="includeForm" classname="java.lang.String" />
<%
int count = 0;

try {
        count = Integer.parseInt(buttonCount);
    }
    catch( java.lang.NumberFormatException ex){
        count = 8;
        }
%>

<%-- Layout component 
  Render a list of tiles in a vertical column
  @param : list List of names to insert 
  
--%>  

<% if (includeForm.equals("yes")) {%> 
<form method="POST" action="toolbar.do" class="nopad">
<% } %>
<table border="0" cellpadding="5" cellspacing="0" valign="top" width="100%" summary="Framing Table">

    <tr valign="top">
      
<td class="function-button-section"  nowrap>  

<tiles:useAttribute id="list" name="actionList" classname="java.util.List" />

<%-- Iterate over names.
  We don't use <iterate> tag because it doesn't allow insert (in JSP1.1)
 --%>

<%
Iterator i=list.iterator();
int listsize = list.size();
int excessItems = 0;
String buttonName = "";
if (listsize > (count+1) ) {
   excessItems = listsize - count;     
} else {
   count = listsize;
}

for (int ctr=0; ctr < count ; ++ctr) {
    ActionSetItem item = (ActionSetItem)i.next();
    String action = item.getAction();
    buttonName = action; 
    boolean showItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        String[] roles = item.getRoles();
        showItem = false;
        for (int idx = 0; idx < roles.length; idx++) {
            if (request.isUserInRole(roles[idx])) {
                showItem = true;
                break;
            }
        }
    }
    if (showItem == true) {
%>

<input type="submit" name="<%=action%>" value="<bean:message key="<%=buttonName %>"/>" class="buttons" id="functions"/>

<%
    } //end if
%>
<%
  } // end loop
%>  

<% if (excessItems > 0) { %>

&nbsp;&nbsp;

<select name="excessAction">

<% for (int ctr=0; ctr < excessItems ; ++ctr) {
    ActionSetItem item2 = (ActionSetItem)i.next();
    String action2 = item2.getAction();
    buttonName = action2; 
    boolean showExcessItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        String[] roles = item2.getRoles();
        showExcessItem = false;
        for (int idx = 0; idx < roles.length; idx++) {
            if (request.isUserInRole(roles[idx])) {
                showExcessItem = true;
                break;
            }
        }
    }
    if (showExcessItem == true) {
%>
<option value="<%=action2 %>">
<bean:message key="<%=buttonName%>"/>
</option> 
<%
    } //end if
%>
<%
  } // end loop
%>  

</select>
<input type="submit" name="go" value="<bean:message key="button.go"/>" class="buttons" id="functions"/>
<% } %>
</td>
</tr>
</table>
<input type="hidden" name="definitionName" value="<%=definitionName%>"/>  
<% if (includeForm.equals("yes")) {%> 
  </form>
<% } %>

