<%-- IBM Confidential OCO Source Material --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 1997, 2003 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="java.util.*" %>
<%@ page import="com.ibm.ws.console.core.item.*" %>
<%@ page import="com.ibm.ws.console.core.bean.*" %>
<%@ page import="com.ibm.ws.console.core.*" %>
<%@ page import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action" %>

<!--tiles:useAttribute id="preferences" name="preferences" classname="java.util.List" /-->


<% 
   String myshow = "collapsed";
   String submitS = request.getParameter("submitS.x");
   String show = request.getParameter("show");
   
   MessageResources messages = (MessageResources) application.getAttribute(Action.MESSAGES_KEY);
   String showPrefs = messages.getMessage(request.getLocale(),"show.preferences");

   if (submitS != null) {
       if (show.equals("collapsed")) {
              myshow = "expanded";
       } else {
              myshow = "collapsed";
       }
   } 
             
%>

<html:form action="/preferenceAction.do" method="get" styleClass="nopad">

<% if (myshow.equals("collapsed")) { %> 

        <table border="0" cellpadding="2" cellspacing="0" valign="top" width="50%" summary="Framing Table" >
        <tbody>
          <tr valign="top"> 
            <td class="find-filter" nowrap scope="rowgroup"> 
              <html:image page="/images/lplus.gif" property="submitS" alt="<%=showPrefs%>" border="0" align="texttop" tabindex="1"/>
              <bean:message key="statustray.preferences.label" />
              <html:hidden property="show" value="collapsed" />
            </td>
          </tr>
          </tbody>
        </table>
        
        
      
<% } else { %> 


        <table border="0" cellpadding="2" cellspacing="0" valign="top" width="50%" summary="Framing Table" >
          <tbody>
          
              <tr valign="top"> 
                <td class="find-filter" nowrap scope="rowgroup"> 
                <html:image src="images/lminus.gif" property="submitS"  alt="<%=showPrefs%>" border="0" align="texttop" tabindex="1"/>
                <bean:message key="statustray.preferences.label" /> 
                <html:hidden property="show" value="expanded"/>
                </td>
              </tr>
          
              <tr> 
                <td class="find-filter-expanded"> 
                  <table border="0" cellpadding="4" cellspacing="1" width="100%" summary="Properties Table">
                  <% 
                   int i = 0;  
                   List prefsList = (List)request.getAttribute("preferences");
                   UserPreferenceBean uBean = (UserPreferenceBean)session.getAttribute(Constants.USER_PREFS);
                   String dataType="";
                   String node="";
                   String defaultValue="";
                   String units = "";
                   String HTMLFormElementName=""; 
                   String HTMLDataTypeName=""; 
                   String HTMLNodeName=""; 
                   String HTMLDefaultValueName="";


                   for (Iterator iter = prefsList.iterator(); iter.hasNext();) {
                   i++;
                   PreferenceItem item = (PreferenceItem) iter.next();
                                                  
                   %>
                      <tr>
                      <%
                        if (item.getPrefType().equals("text")) {
                            dataType = item.getDataType();          
                            node = item.getPrefEntry(); 
                            defaultValue = item.getDefaultValue();
                            units = item.getUnits(); 
                            HTMLFormElementName = "text"+i;
                            HTMLDataTypeName = "dataType"+i; 
                            HTMLNodeName = "node"+i;
                            HTMLDefaultValueName = "defaultValue"+i;
                  
                       %>
                            <td class="table-text"  scope="row" valign="top" nowrap>
                           <label FOR="<%=HTMLFormElementName%>"><bean:message key="<%=item.getLabel()%>" /></label>                                
                        </td>

                        
                        
                        <% if (node != null) { %>
                             <% if (defaultValue != null) {%>
                                <td class="table-text" nowrap valign="top">
                                    <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" value="<%=uBean.getProperty(node, defaultValue)%>" />
                                    <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                                </td>                                 
                             <%} else {%>
                                <td class="table-text" nowrap valign="top">
                                    <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" value="<%=uBean.getProperty(node, "")%>" />
                                    <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                                </td>
                             <%}%>
                        <%} else { %>
                             <td class="table-text" nowrap valign="top">
                                 <INPUT type="text" name="<%=HTMLFormElementName%>" size="<%=item.getSize()%>" />
                                 <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                             </td>  
                        <%}%>

                        <INPUT type="hidden"  name="<%=HTMLDataTypeName%>"     value="<%=dataType%>" />
                        <INPUT type="hidden"  name="<%=HTMLNodeName%>"         value="<%=node%>" />
                        <INPUT type="hidden"  name="<%=HTMLDefaultValueName%>" value="<%=defaultValue%>" /> 
                                           <% 
                       } else if (item.getPrefType().equals("checkbox")) {
                           node = item.getPrefEntry(); 
                           defaultValue = item.getDefaultValue(); 
                           units = item.getUnits(); 
                           HTMLFormElementName = "checkbox"+i;
                           HTMLNodeName = "node"+i; 
                           HTMLDefaultValueName = "defaultValue"+i; 
                      %>
                             <td class="table-text"  scope="row" nowrap>
                           <label FOR="<%=HTMLFormElementName%>"><bean:message key="<%=item.getLabel()%>"/></label>
                       </td>
                       
                       
                  <%if (node != null) { %> 
                        
                           <%if (defaultValue == null) {
                               defaultValue = "false";
                             }

                           String val = uBean.getProperty(node, defaultValue); %>
                            <td class="table-text" nowrap valign="top">
                                    <INPUT type="checkbox" name="<%=HTMLFormElementName%>"  <%=((val.equals("true")) ? "CHECKED" : "")%> /> 
                                    <% if (units != null && !units.equals(" ") && !units.equals("")) { %> <bean:message key="<%=units%>"/> <% } %>
                            </td>
                       <%}%>
                                                                            
                        <INPUT type="hidden"   name="<%=HTMLNodeName%>"         value="<%=node%>" />
                        <INPUT type="hidden"   name="<%=HTMLDefaultValueName%>" value="<%=defaultValue%>" />
                        
                    
                        
            
                    <%}%>
                  
                    </tr>
                
               
          <%}%>
  
          <INPUT type="hidden" name="counter" VALUE="<%=i%>" />
          
                   <tr> 
                  <th valign="top" class="button-section" colspan="3"> 
                    <html:submit property="submit2" styleClass="buttons" styleId="navigation"><bean:message key="button.apply"/></html:submit>
                    <html:reset styleClass="buttons" styleId="navigation"><bean:message key="button.reset"/></html:reset>
                  </th>
                </tr>

              </table>
            </td>
          </tr>
          </tbody>
        </table>
        <%}%>

</html:form>

                 
                                        


