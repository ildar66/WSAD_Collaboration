<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.appdeployment.ApplicationDeploymentDetailForm"%>
<script language="JavaScript">
document.write("<link rel='stylesheet' href='css/was_style_common.css'>");
if (isNav4) {
document.write("<link rel='stylesheet' href='css/was_style_ns.css'>")
}
if (isIE) {
document.write("<link rel='stylesheet' href='css/was_style_ie.css'>")
}
</script>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<%ApplicationDeploymentDetailForm form = (ApplicationDeploymentDetailForm) session.getAttribute("com.ibm.ws.console.appdeployment.ApplicationDeploymentDetailForm"); 
//String availableLibraries = form.getAvailableLibraries();
//String libraries = form.getLibraries();
%>   
<bean:define id="availableLibraries" name="com.ibm.ws.console.appdeployment.ApplicationDeploymentDetailForm" property="availableLibraries"/>
<bean:define id="libraries" name="com.ibm.ws.console.appdeployment.ApplicationDeploymentDetailForm" property="libraries"/>

        <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr valign="top"> 
                  <td class="table-text" nowrap width="1%"> Available Libraries:<BR>
		
                  <html:select property="availableLibraryBox" size="5">
		  <% StringTokenizer st = new StringTokenizer((String)availableLibraries);
		  if (!st.hasMoreElements()) {%>
		      <html:option value="None Available">None Available</html:option>
<%		  }
		  while (st.hasMoreElements()) {
		      String library = (String)st.nextElement();%>
		      <html:option value="<%=library%>"><%=library%></html:option>
<%		  }%>
                  </html:select>

                  </td>
                  <td valign="MIDDLE" class="table-text" nowrap width="1%" > 
                        <input type="submit" name="button.add"  value="<bean:message key="button.add"/>>>" class="buttons" id="navigation"> <BR>
                        <input type="submit" name="button.remove"  value="<bean:message key="button.remove"/><<" class="buttons" id="navigation">			
                  </td>

                  <td class="table-text" nowrap>Selected Libraries:<BR>
		
		  <html:select property="libraryBox" size="5">
		  <% StringTokenizer st = new StringTokenizer((String)libraries);
		  if (!st.hasMoreElements()) {%>
		      <html:option value="None">None</html:option>
		      <%		  }
		  while (st.hasMoreElements()) {
		      String l = (String)st.nextElement();%>
		      <html:option value="<%=l%>"><%=l%></html:option>
<%		  }%>	
</html:select>
                  </td>
                </tr>
        </table>

        </td>
        


    

   
   
 
