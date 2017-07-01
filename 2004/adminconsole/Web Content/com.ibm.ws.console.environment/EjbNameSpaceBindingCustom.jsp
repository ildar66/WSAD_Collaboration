<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*,com.ibm.ws.console.core.*,com.ibm.websphere.product.*,com.ibm.ws.sm.workspace.RepositoryContext,com.ibm.ws.workspace.query.*"%>

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

   <!--<tiles:useAttribute  name="formAction" classname="java.lang.String"/>
   <tiles:useAttribute  name="formType" classname="java.lang.String"/>-->
   
   <bean:define id="applicationNodeName" name="<%=bean%>" property="applicationNodeName" type="java.lang.String"/>


<% 	

      WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();    
      RepositoryContext cellContext = (RepositoryContext) session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
      boolean isND = false;
      try {
          if (util.isStandAloneCell(cellContext)) {
              isND = false;
          }
          else {
              isND = true;
          }
      }
      catch (Exception e) {
          isND = true;
      }

   boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>

   <td class="table-text"  scope="row" valign="top" nowrap>
       <label for="<%=label%>"><bean:message key="<%=label%>"/></label>
   </td>
        
   <td class="table-text" nowrap valign="top">
       <FIELDSET id="<%=label%>">

           <table  border="0" cellspacing="0" cellpadding="3">

<%
   if (isND)
	{
%>
               <tr valign="top"> 
                   <td class="table-text" nowrap> 
                       <html:radio property="<%=property%>" name="<%=bean%>" value="SERVERCLUSTER" disabled="<%=val%>">
                           <bean:message key="EjbNameSpaceBinding.serverCluster.displayName"/>
                       </html:radio>
                   </td>
               </tr>
                
               <tr valign="top"> 
                   <td class="table-text" nowrap> 
                       <html:radio property="<%=property%>" name="<%=bean%>" value="SINGLESERVER" disabled="<%=val%>">
                           <bean:message key="EjbNameSpaceBinding.singleServer.displayName"/>
                       </html:radio>
                   </td>
               </tr>

               <tr valign="top"> 
                   <td class="complex-property" nowrap> 
                       <bean:message key="EjbNameSpaceBinding.applicationNodeName.displayName"/>
                       <html:text property="applicationNodeName" name="<%=bean%>" size="30" disabled="<%=val%>"/>
                   </td>
               </tr>
<%
   }
	else
	{
%>                
               <tr valign="top"> 
                   <td class="table-text" nowrap> 
                       <img src="images/attend.gif" width="6" height="15" align="absmiddle" alt="<bean:message key="information.required"/>">
                       <bean:message key="EjbNameSpaceBinding.applicationNodeName.displayName"/>
                       <html:text property="applicationNodeName" name="<%=bean%>" size="30" disabled="<%=val%>"/>
                   </td>
               </tr>
               <html:hidden property="<%=property%>" value="SINGLESERVER"/>
<%
   }
%>                
           </table>

       </FIELDSET>
   </td>
        

    

   
   
 
