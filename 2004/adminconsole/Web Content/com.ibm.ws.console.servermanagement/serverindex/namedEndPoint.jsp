<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.serverindex.NamedEndPointDetailForm"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

                    

<% NamedEndPointDetailForm form = (NamedEndPointDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.NamedEndPointDetailForm"); 
  Collection list = form.getAvailableEndPoints();
  String showEndPoints = form.getShowEndPoints();  
  %>
        
                                             
<table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr valign="top"> 
                <% if (showEndPoints.equals("true")) { %>
                  <td class="table-text" nowrap width="1%"> 
                  <html:select property="endPointName" styleId="aselect">
                  <%

                     Iterator listIterate = list.iterator();
                     while (listIterate.hasNext())
                     { 
                        String descr = (String)listIterate.next();
                        String value = descr;   
                     %>
                        <html:option value="<%=value%>"><%=descr%></html:option>
                     <%
                     }
                     %>
                </html:select>
                 </td>               
               <% } else { %>
                  <td class="table-text" nowrap>
                    <bean:write name="com.ibm.ws.console.servermanagement.NamedEndPointDetailForm" property="endPointName"/>
                  </td>
		<% }%>
                </tr>
        </table>
  
        
