<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.debugservice.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

                    
<%--tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/--%>

<%--bean:define id="bean" name="<%=formBean%>"/--%>

<% DebugServiceDetailForm form = (DebugServiceDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.DebugServiceDetailForm"); 
  List debugOptionValuesList = form.getDebugOptionValuesList(); %>
        
<table border="0" cellspacing="0" cellpadding="2">
                <tr valign="top"> 
                  <td class="table-text" nowrap> 
                  <html:select property="debugSpecification"  size="5" multiple="true">
                  <%

                     Iterator listIterate = debugOptionValuesList.iterator();
                     while (listIterate.hasNext())
                     { 
                        String descr = (String)listIterate.next();
                        String value = descr + "0";   
                     %>
                        <html:option value="<%=value%>"><%=descr%></html:option>
                     <%
                     }
                %>
                </html:select>

                  </td>
                  <td class="table-text" nowrap> 
                        <input type="submit" name="add"  value="<bean:message key="servermanagement.button.add"/>" class="buttons" id="other">
                  </td>

                  <td class="table-text" nowrap>
                    <html:textarea property="debugClassFilters"  rows="5" cols="10"/>
                  </td>
                </tr>
        </table>
  
        
