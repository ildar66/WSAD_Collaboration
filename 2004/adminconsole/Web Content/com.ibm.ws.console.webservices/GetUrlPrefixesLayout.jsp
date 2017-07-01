<%-- @(#) 1.8 ws/code/webui.webservices/src/webservices/com.ibm.ws.console.webservices/GetUrlPrefixesLayout.jsp, WAS.webui.webservices, ASV51X, trial0343.04 5/8/03 12:31:57 [11/4/03 16:59:55]  --%>
<%-- IBM Confidential OCO Source Material  --%>
<%-- 5630-A36 (C) COPYRIGHT International Business Machines Corp. 2002, 2003  --%>
<%-- The source code for this program is not published or otherwise divested  --%>
<%-- of its trade secrets, irrespective of what has been deposited with the  --%>
<%-- U.S. Copyright Office.  --%>
<%--  --%>
<%-- Change History:  --%>
<%-- Date     UserId      Defect          Description  --%>
<%-- ----------------------------------------------------------------------------  --%>
<%-- mm/dd/yy <cmvcid>    <track #>       <brief explanation>  --%>
<%-- 05/06/03 donavonj    164003.1        handle modules assigned to clusters and with only JMS routers  --%>

<%@ page language="java" 
                import="com.ibm.ws.console.webservices.publish.*,
                		com.ibm.ws.webservices.deploy.ModuleData,
				com.ibm.ws.webservices.WSConstants,
                                java.util.*,
                                org.apache.struts.util.MessageResources,
                                org.apache.struts.action.Action"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="actionForm" classname="java.lang.String" />


<table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Virtual Host Table" class="framing-table">

<%

   GetUrlPrefixesForm theForm = (GetUrlPrefixesForm) session.getAttribute(actionForm);
   List vhAndServerList = theForm.getVhAndServerList();
   Map vhAndServerToDropDownListMap = theForm.getVhAndServerToDropDownListMap();
   Map vhAndServerToModuleDataListMap = theForm.getVhAndServerToModuleDataListMap();
   String[] customSoapValues = theForm.getCustomSoapValues();

   String[] initialSoapAddresses = theForm.getSoapAddresses();
   String[] initialJmsAddresses = theForm.getJmsAddresses(); 

   int vhAndServerCount = 0;
   int moduleCount = 0;   
   for(Iterator it = vhAndServerList.iterator(); it.hasNext(); vhAndServerCount++) {
                String vhAndServerName = (String) it.next();
                String selectButtonValue = vhAndServerName + ":" + "DROP";
                String customButtonValue = vhAndServerName + ":" + "CUSTOM";
                
                List dropDownList = (List) vhAndServerToDropDownListMap.get(vhAndServerName);
                Iterator dropDownListIterator = dropDownList.iterator();
                String submitButtonValue = vhAndServerName + ":" + "SUBMIT";
		
		int colonIndex = vhAndServerName.indexOf(':');
	
		String virtualHostName =vhAndServerName.substring(0, colonIndex );
                String serverName = vhAndServerName.substring(colonIndex + 1 );
		boolean modulesWithOnlyJmsRouter;
		if ( serverName.equals(WSConstants.MODULE_HAS_ONLY_JMS_ROUTER_TAG) ) {
	            modulesWithOnlyJmsRouter = true;
                } else {
		    modulesWithOnlyJmsRouter = false;
                }

		boolean modulesAssignedToCluster;
		if ( serverName.equals(WSConstants.MODULE_ASSIGNED_TO_CLUSTER_TAG) ) {
		    modulesAssignedToCluster = true;
                } else {
		    modulesAssignedToCluster = false;
                }
%>  


        <table border="0" cellpadding="2" cellspacing="1" width="100%" summary="Virtual Host Table" class="framing-table">
            <tr> 
                   <th colspan="4" class="column-head" scope="rowgroup">  
                   <a name="additional"></a> 
                 <% 
                  if ( !modulesAssignedToCluster && !modulesWithOnlyJmsRouter) {
                 %>                
                   <bean:message key="webservices.getUrlPrefixes.modulesAssignedTo"/>: <bean:message key="webservices.getUrlPrefixes.virtualHost"/>

                   = 
                   <%= (String) virtualHostName %>
                   ,
                   <a name="additional"></a>
                   <bean:message key="webservices.getUrlPrefixes.server"/>
                   = 
                   <%= (String) serverName %>
                  </th>
                <% 
                } else  if ( modulesWithOnlyJmsRouter ) {
                %>
                <bean:message key="webservices.getUrlPrefixes.jms" />
                <%
                  } else {
               %>
                <bean:message key="webservices.getUrlPrefixes.modulesAssignedTo"/> <bean:message key="webservices.getUrlPrefixes.cluster"/>
                <%
                  }
               %>
            </tr>

            <% 
               if ( !modulesWithOnlyJmsRouter ) {
            %>


            <tr>            
                 <td class="table-text" headers="header1" width="4%">
                 </td>
                 <td class="table-text" headers="header1" width="18%">   
                     <bean:message key="webservices.getUrlPrefixes.specifySoapaddress"/>
                 </td>

                 <td class="table-text" headers="header1" width="50%">
                     <table>
                       <tr> 
                          <td class="table-text" >
                               <html:radio property="radioButtonChoice" value="<%= selectButtonValue%>"/> 
                               <bean:message key="webservices.getUrlPrefixes.selectSoapAddress"/>
                               <html:select property="dropDownChoices">
                               <logic:iterate id="address" collection="<%= dropDownListIterator %>">
                                  <html:option value="<%= (String) address %>" >
                                    </html:option>
                                   </logic:iterate>
                            </html:select>
                        <br>
                        <html:radio property="radioButtonChoice" value="<%= customButtonValue%>" />
                        <bean:message key="webservices.getUrlPrefixes.customSoapAddress"/>
                        <html:text  property="customSoapValues"  value="<%= customSoapValues[vhAndServerCount]%>" size="16" />                       
                        </td>
                        <td class="table-text" >
                            <html:submit property="<%= (String) submitButtonValue%>" styleId="other" styleClass="buttons">
                            <bean:message key="button.apply"/>
                           </html:submit>
                        </td>

                       </tr>
                    </table>

                </td>
                <td class="table-text" headers="header1" width="28%" >
                </td>
                </tr>
		<%
                 } // if (!modulesWithOnlyJmsRouter)
                %>


        </table>  
        
       <table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
        
                <tr>
                    <th class="column-head-name" scope="col" width="4%">
                    </th>
                     <th class="column-head-name" scope="col" width="18%"> 
                        <bean:message key="webservices.getUrlPrefixes.modules"/>
                     </th>

	       <%
                    if ( !modulesWithOnlyJmsRouter ) {
               %>
                    <th class="column-head-name" scope="col" width="50%"> 
                       <bean:message key="webservices.getUrlPrefixes.soapAddresses"/>
                   </th>
              <%
		} else {
              %>
                    <th class="column-head-name" scope="col" width="50%"> 
                   </th>
              <%
		} 
              %>


	 
                    <th class="column-head-name" scope="col" width="28%"> 


                       <bean:message key="webservices.getUrlPrefixes.jmsAddresses"/>
                    </th>
                </tr>
                
        <%
                List list = (List) vhAndServerToModuleDataListMap.get(vhAndServerName);
                

                for(Iterator moduleIterator = list.iterator(); moduleIterator.hasNext();moduleCount++ ) {
                        ModuleData md = (ModuleData) moduleIterator.next();
                        String defaultAddress = md.getDefaultPrefix();
                        String name = md.getName();
                        String checkBoxValue = vhAndServerName  + ":" + name ;
			boolean moduleHasHTTPRouter = md.hasHTTPRouter();
			boolean moduleHasJMSRouter = md.hasJMSRouter();
			boolean needsHTTPUrl = md.isWebModule() || moduleHasHTTPRouter;
        %>
                        <tr>
                        <td class="table-text" headers="header1" width="4%">
                                <html:checkbox property="checkBoxesValues" value="<%=checkBoxValue%>"/>
                        </td>
                        <td class="table-text" headers="header1" width="18%" ><%= (String) name %> </td>
                        <td class="table-text" headers="header1" width="50%">
		      <% 
                         if (needsHTTPUrl) {
                      %>
                             <html:text property="soapAddresses"  value="<%=(String)initialSoapAddresses[moduleCount]%>"/>
                      <%
                        } else {
                      %>
                            <html:text property="soapAddresses"  value=""/>
                      <%
                        } 
                      %>
    
  
			</td>

                        <td class="table-text" headers="header1" width="28%">
                                <html:text property="jmsAddresses"  value="<%=(String)initialJmsAddresses[moduleCount]%>" />
			</td>

                        </tr>
         <%
                }
                
         %>
          
        </table>                
        <P></P>
        <P></P>

<%              
   }
   

  
%>
</table>




