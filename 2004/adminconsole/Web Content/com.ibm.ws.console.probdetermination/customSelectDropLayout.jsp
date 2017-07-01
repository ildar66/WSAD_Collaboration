<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>


<%@ page import="java.util.*, com.ibm.ws.console.core.form.AbstractDetailForm,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

   <tiles:useAttribute id="label" name="label" classname="java.lang.String"/>
   <tiles:useAttribute id="isRequired" name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute id="units" name="units" classname="java.lang.String"/>
   <tiles:useAttribute id="desc" name="desc" classname="java.lang.String"/>
   <tiles:useAttribute id="property" name="property" classname="java.lang.String"/>
   <tiles:useAttribute id="formBean" name="bean" classname="java.lang.String"/>
   <tiles:useAttribute id="formAction" name="formAction" classname="java.lang.String"/>
   <tiles:useAttribute id="formType" name="formType" classname="java.lang.String"/>
   
   <bean:define id="bean" name="<%=formBean%>"/>
   <bean:define id="errFileOptionVector" name="<%=formBean%>" property="errFileOptionVector" type="java.util.Vector"/>
   <bean:define id="outFileOptionVector" name="<%=formBean%>" property="outFileOptionVector" type="java.util.Vector"/>

<% 
                  	boolean noFiles = true;
%>   
   
        <td class="table-text"  scope="row" valign="top" nowrap>
            <label  for="{attributeName}"><bean:message key="<%=label%>"/></label>
        </td>
        
            <% if (isRequired.equalsIgnoreCase("true")) { %>
                <span class="required">
                *
                </span>
            <% } %>                                    
        <td class="table-text" nowrap valign="top">
        <table width="100%" border="0" cellspacing="0" cellpadding="2">
                <tr valign="top"> 
                  <td class="table-text" nowrap width="1%"> 
                  <html:select property="<%=property%>" name="<%=formBean%>" >
                  <%
                  

                     Iterator vectorIterate = null;


                     if (property.equals("runtimeOutFileName") || property.equals("stdoutFilename"))
                        vectorIterate = outFileOptionVector.iterator();
                     else if (property.equals("runtimeErrFileName") || property.equals("stderrFilename"))
                        vectorIterate = errFileOptionVector.iterator();
 
                     while (vectorIterate.hasNext())
                     { 
                     	noFiles = false;
                     String value = (String)vectorIterate.next();

value = value.trim();

if (!value.equals("")) {
					         %>
					                        <html:option value="<%=value%>"><%=value%></html:option>
					         <%
				                         } else {
                 				         %>
					                        <html:option value="<%=value%>"><bean:message key="none.text"/></html:option>
                 				         <%     
                         				         }
                     }
                %>
                </html:select>

                  </td>
                </tr>
                <tr valign="left"> 
                  <td class="table-text" nowrap width="10%"> 
                    <%
                     if (property.equals("runtimeOutFileName") || property.equals("stdoutFilename"))
                     {
                     %>
                        <input type="submit" name="View.Out"  value="<bean:message key="probdetermination.button.view"/>" class="buttons" id="other" <%=(noFiles) ? "disabled" : ""%>>
                    <% } else {
                     %>
                        <input type="submit" name="View.Err"  value="<bean:message key="probdetermination.button.view"/>" class="buttons" id="other" <%=(noFiles) ? "disabled" : ""%>>
                    <% }
                    
                    if (noFiles)
                    {
                    // display message
                    AbstractDetailForm form = (AbstractDetailForm) session.getAttribute(formBean);
                    
                    String context = form.getContextId();
                    int index = context.lastIndexOf(":");
                    String server = context.substring(index+1);
                    
				    ServletContext servletContext = (ServletContext)pageContext.getServletContext();
				    MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
				    String nonefound = messages.getMessage(request.getLocale(),"application.server.not.started", server);
			        out.println("<br><a href=\""+request.getContextPath()+"/navigatorCmd.do?forwardName=ApplicationServer.content.main\">"+nonefound+"</a>"); 
                    }
                    
                     %>                                     
                  </td>
                </tr>

                <tr valign="top"> 
                  <td class="table-text" nowrap colspan="3"> 
                    <p>&nbsp; </p>
                  </td>
                </tr>
        </table>

        </td>
        

    

   
   
 
