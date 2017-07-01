<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

   <tiles:useAttribute  id="readOnly" name="readOnly" classname="java.lang.String"/>
   <tiles:useAttribute  name="label" classname="java.lang.String"/>
   <tiles:useAttribute  name="isRequired" classname="java.lang.String"/>
   <tiles:useAttribute  name="units" classname="java.lang.String"/>
   <tiles:useAttribute  name="desc" classname="java.lang.String"/>
   <tiles:useAttribute  name="property" classname="java.lang.String"/>
   <tiles:useAttribute  name="bean" classname="java.lang.String"/>

   <bean:define id="action" name="<%=bean%>" property="action" type="java.lang.String"/>
   <bean:define id="archivePathChoice" name="<%=bean%>" property="archivePathChoice" type="java.lang.String"/>
   <bean:define id="archivePathSelect" name="<%=bean%>" property="archivePathSelect" type="java.lang.String"/>
   <bean:define id="archivePathManual" name="<%=bean%>" property="archivePathManual" type="java.lang.String"/>

<%
    Vector archiveList = (Vector) session.getAttribute("j2CResourceAdapterArchivePath");
%>

   <td class="table-text"  scope="row" valign="top" nowrap>
       <label for="<%=label%>"><bean:message key="<%=label%>"/></label>
   </td>
        
<%
    if (action.equalsIgnoreCase ("edit"))
    {
%>
   <td class="table-text" nowrap> 
<%
        if (isRequired.equalsIgnoreCase("yes")) 
        { 
%>
       <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
<%     
        } 
%>
       <html:text property="<%=property%>" name="<%=bean%>" size="40"/>
   </td>
<%
    }
%>

<% 
    if (action.equalsIgnoreCase("new"))
    {
%>
    <td class="table-text" nowrap valign="top">
       <fieldset id="<%=label%>">
           <table  border="0" cellspacing="0" cellpadding="3">
               <tr valign="top"> 
                   <td class="table-text" nowrap> 
<%
        if (isRequired.equalsIgnoreCase("yes")) 
        { 
%>
                <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
<%     
        } 
%>
                       <html:radio property="archivePathChoice" name="<%=bean%>" value="SELECT" disabled="false">
                           <bean:message key="J2CResourceAdapter.install.rar.archivePath.choose"/>
                       </html:radio>
                   </td>
               </tr>
               <tr valign="top"> 
                   <td class="complex-property" nowrap> 
                       <html:select property="archivePathSelect" name="<%=bean%>" styleId="archivePathSelect">
<%
        for (int i=0; i < archiveList.size(); i++)
        { 
            String path = (String) archiveList.elementAt(i);
%>
                           <html:option value="<%=path%>"><%=path%></html:option>
<%
        }
%>

                       </html:select>
                   </td>
               </tr>

               <tr valign="top"> 
                   <td class="table-text" nowrap> 

<%
        if (isRequired.equalsIgnoreCase("yes")) 
        { 
%>
                <img src="images/blank10.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
<%     
        } 
%>
                       <html:radio property="archivePathChoice" name="<%=bean%>" value="MANUAL" disabled="false">
                           <bean:message key="J2CResourceAdapter.install.rar.archivePath.specify"/>
                       </html:radio>
                   </td>
               </tr>

               <tr valign="top"> 
                   <td class="complex-property" nowrap> 
                       <html:text property="archivePathManual" name="<%=bean%>" size="30"/>
                   </td>
               </tr>

           </table>
       </fieldset>
   </td>

<%
    }
%>
        

    

   
   
 
