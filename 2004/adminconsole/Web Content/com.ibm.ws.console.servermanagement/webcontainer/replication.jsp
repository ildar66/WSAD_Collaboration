<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page language="java" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>


<table border="0" cellspacing="0" cellpadding="3">
<tr> 
  <td class="table-text">
  
  <% String routingValue = (String)request.getAttribute("routingValue"); %>
  
  <% Vector domainVector = (Vector) request.getAttribute("domainVector");
    Vector brokerVector = (Vector) request.getAttribute("brokerVector");
    HashMap partitionVector = (HashMap) request.getAttribute("partitionVector");
    if (domainVector.size() > 0)
    { %>

<%    
        boolean brokersPresent = true;
        StringBuffer entries = new StringBuffer();
        try
        {

        for (int i=0 ; i < domainVector.size(); i++)
        {
            List brokers = (List) brokerVector.elementAt(i);
            if (brokers.size() == 0)
            {
                brokersPresent = false;
                domainVector.removeElementAt(i);
                continue;
            }
            entries.append((String) domainVector.elementAt(i));
            entries.append(",");
            entries.append((String)partitionVector.get(domainVector.elementAt(i)));
            entries.append(",");
            for (int j=0; j < brokers.size(); j++)
            {
                entries.append(brokers.get(j));
                entries.append(",");
            }
            entries.deleteCharAt(entries.length() - 1);
            entries.append(":::");
        }
        entries.delete(entries.length() - 3, entries.length());
    
        System.out.println("String : "+entries);
    }
    catch (Exception e)
    {
        e.printStackTrace();  

    }
    %>


<%  boolean val2 = false;
    if (domainVector.size() == 0)
    val2 = true;%>
   
    <html:radio property="routing" value="domain" disabled="<%=(val || val2)%>">
    <bean:message key="DRSSettings.replication.domainSelection"/> 
    </html:radio></td>
</tr>
<tr>
  <td class="complex-property" nowrap>
    <script>
        // theentries would be the huge multibroker/replicator string
        // selectDomain and selectReplicator form elements must be filled in on the server side
var theentries = "<%=entries.toString()%>";
var thedomains = theentries.split(":::");
        var howmanydomains = thedomains.length;                        
         
        
function changeServers(theform) {
                for (z=0;z<howmanydomains;z++) {

        if (theform.messageBrokerDomainName.options.selectedIndex == z) {
                                var thereps = thedomains[z].split(",");
                                var repslen = thereps.length;
                                var str = "";
                                for (i=1;i<thereps[1];i++) {
                                    str = str + i + ",";  
                                }
                                str = str + i;
                                theform.domainPartitions.value = str;  
                                //alert(theform.preferredLocalDRSBrokerName.options.length);
                                theform.preferredLocalDRSBrokerName.options.length = repslen-2;
                                repslen = theform.preferredLocalDRSBrokerName.options.length;
                                //alert(repslen);
                                
            for (i=0;i<repslen;i++) {
                theform.preferredLocalDRSBrokerName.options[i].text = thereps[i+2];
                theform.preferredLocalDRSBrokerName.options[i].value = thereps[i+2];
            }
        }
                        
                }

}


</script>

    <html:select property="messageBrokerDomainName" onchange="changeServers(this.form)" disabled="<%=val%>">
    <% for (int i=0; i < domainVector.size();i++)
    { %>
        <html:option value="<%=(String)domainVector.elementAt(i)%>"><%=(String) domainVector.elementAt(i)%></html:option>
    <% } %>
    </html:select>
    <NOSCRIPT>
        <input type="submit" name="Submit222222" value="Apply" class="buttons" id="other" disabled="<%=val%>">
    </NOSCRIPT>
  </td>
</tr>

<% System.out.println("Size = "+brokerVector.size()); %>      
      
<% if (!brokersPresent) {%>
<tr valign="top">
  <td class="complex-property" nowrap>
    <%
    ServletContext servletContext = (ServletContext)pageContext.getServletContext();
    MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
    String nonefound = messages.getMessage(request.getLocale(),"error.replicators.not.defined");
        out.println("<a href=\""+request.getContextPath()+"/navigatorCmd.do?forwardName=MultibrokerDomain.content.main\">"+nonefound+"</a>"); 
    %>
  </td>
</tr>
<% }%>


<tr valign="top">
  <td class="complex-property" nowrap>
    
    
    <bean:message key="DRSSettings.replication.replicators"/>
    <html:select property="preferredLocalDRSBrokerName" disabled="<%=val%>">
    
    <% List brokerList = (List) brokerVector.elementAt(0);
    for (int i=0;i < brokerList.size(); i++)
    {%>
        <html:option value="<%=(String) brokerList.get(i)%>"> <%=(String) brokerList.get(i)%></html:option>
    <% } %>
    </html:select>
  </td>
</tr>

<tr valign="top">
  <td class="complex-property" nowrap>
  <bean:message key="DRSSettings.partition.displayName"/>
  <html:text property="domainPartitions" size="20"/>
  </td>
</tr>


<tr> 
  <td  class="table-text"> 
    <html:radio property="routing" value="other" disabled="<%=val%>">
    <bean:message key="DRSSettings.replication.otherDomain"/>
    </html:radio>       </td>
</tr>
<tr> 
  <td class="complex-property" nowrap><bean:message key="DRSSettings.replication.address"/> 
    <html:text property="connectionPointAddress" size="20" disabled="<%=val%>"/>
    <br>
  </td>
</tr>
<tr> 
  <td class="complex-property" nowrap><bean:message key="DRSSettings.replication.port"/> 
    <html:text property="connectionPointPort" size="20" disabled="<%=val%>"/>
  </td>
</tr>

<tr valign="top">
  <td class="complex-property" nowrap>
  
  <bean:message key="DRSSettings.partition.displayName"/>
  <html:text property="otherPartitions" size="20" />
  </td>
</tr>

<% } 
    else {
        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
        String nonefound = messages.getMessage(request.getLocale(),"DRSSettings.noDomains.error");
        out.println("<a href=\""+request.getContextPath()+"/navigatorCmd.do?forwardName=MultibrokerDomain.content.main\">"+nonefound+"</a>"); 
}%>


</table>
