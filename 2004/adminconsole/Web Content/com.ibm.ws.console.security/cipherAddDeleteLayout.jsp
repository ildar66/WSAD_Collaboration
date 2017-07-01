<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<%  boolean val = false;
    if (readOnly != null && readOnly.equalsIgnoreCase("true"))
        val = true;
    String formBean = "com.ibm.ws.console.security.SSLConfigDetailForm";
%>

   <bean:define id="cipherOptionValues" name="<%=formBean%>" property="cipherOptionValues" type="java.util.List"/>
   <bean:define id="selectedCiphers" name="<%=formBean%>" property="selectedCiphers" type="java.util.List"/>
   <bean:define id="perspective" name="<%=formBean%>" property="perspective" type="java.lang.String"/>

   <table width="100%" border="0" cellspacing="0" cellpadding="2">
       <tr valign="top">
           <% if (!val) { %>
              <td class="table-text" nowrap width="1%">
              <html:select property="addCipherOptionValues" name="<%=formBean%>" size="5" multiple="true">
                 <%
                 for (int i=0; i < cipherOptionValues.size(); i++)  {
                    String descr = (String)cipherOptionValues.get(i);
                 %>
                    <html:option value="<%=descr%>"><%=descr%></html:option>
                 <%
                 }
                 %>
              </html:select>
              </td>

              <td class="table-text" nowrap width="1%" >
                 <input type="submit" name="AddCiphers"  value="<bean:message key="SecureSocketLayer.button.add"/>" class="buttons" id="other">
                 <br><br>
                 <input type="submit" name="RemoveCiphers"  value="<bean:message key="SecureSocketLayer.button.remove"/>" class="buttons" id="other">
              </td>
           <% } %>

              <td class="table-text" nowrap width="1%">
              <html:select property="removeSelectedCiphers" name="<%=formBean%>" size="5" multiple="true" disabled="<%=val%>">
                <%
                String descr = null;
                try {
                  for (int i=0; i < selectedCiphers.size(); i++) {
                    descr = (String)selectedCiphers.get(i);
                %>
                    <html:option value="<%=descr%>"><%=descr%></html:option>
                <%
                  }
                } catch (Exception e) {
                   System.out.println("cipherAddDeleteLayout.jsp - Ignoring invalid cipher value: " + descr);
                }
                %>
              </html:select>
              </td>
       </tr>
   </table>
