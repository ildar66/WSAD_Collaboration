<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page import="java.util.*"%>
<%@ page import="com.ibm.ws.console.servermanagement.webcontainer.*"%>
<%@ page import="com.ibm.ws.sm.workspace.*"%>
<%@ page import="com.ibm.ws.workspace.query.*"%>
<%@ page import="com.ibm.ws.console.core.*"%>
<%@ page import="com.ibm.websphere.product.*"%>
<%@ page import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>



<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<% PersistenceDetailForm form = (PersistenceDetailForm) session.getAttribute("com.ibm.ws.console.servermanagement.webcontainer.PersistenceDetailForm"); %>

<tiles:useAttribute id="readOnly" name="readOnly" classname="java.lang.String"/>

<% 	boolean val = false;
	if (readOnly != null && readOnly.equals("true"))
		val = true;
%>


<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr> 
  <td class="table-text"> 
    <input type="radio" name="pers" value="none" <%= (form.getMode().equals("none")) ? "checked" : "" %> <%=(val) ? "disabled" : ""%>>
    <bean:message key="Persistence.none"/></td>
                </tr>
<tr> 
  <td class="table-text"> 
    <input type="radio" name="pers" value="database" <%= (form.getMode().equals("database")) ? "checked" : "" %> <%=(val) ? "disabled" : ""%>>
    <a href="<%=request.getContextPath()%>/persistenceDetail.do?action=modify"><bean:message key="Persistence.database"/></a></td>
</tr>
<% 
    WorkSpaceQueryUtil util = WorkSpaceQueryUtilFactory.getUtil();
    boolean ndEnabled = false;
    RepositoryContext cellContext = (RepositoryContext)session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
        try {
            if (util.isStandAloneCell(cellContext)) {
                ndEnabled = false;
            }
            else {
                ndEnabled = true;
            } 
        }
        catch (Exception e) {
            System.out.println("exception in util.isStandAloneCell " + e.toString());
            ndEnabled = false;
        }

    if (ndEnabled){   
         
        String contextId = form.getContextId();
        System.out.println("Context oid = "+contextId);

        WorkSpace workSpace = (WorkSpace)session.getAttribute(Constants.WORKSPACE_KEY);
        String contextUri = ConfigFileHelper.decodeContextUri(contextId); 
        RepositoryContext repositoryContext = null;
        if (contextUri != null) {
            try {
                repositoryContext = workSpace.findContext(contextUri);
            }
            catch (Exception we) {
                repositoryContext = null;   
            }
        }

        if (repositoryContext != null)
        {
            String type = repositoryContext.getType().getName();
            if (type.equalsIgnoreCase("servers"))
            {
                repositoryContext = repositoryContext.getParent().getParent();
            }
            else if (type.equalsIgnoreCase("applications"))
            {
                repositoryContext = repositoryContext.getParent();
            }
        }

        WorkSpaceQueryUtil utils = WorkSpaceQueryUtilFactory.getUtil();
        Collection domainNames = utils.getMultiBrokerDomainNames(repositoryContext);                              

        boolean val2 = true;
        ServletContext servletContext = (ServletContext)pageContext.getServletContext();
        MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
        String nonefound = messages.getMessage(request.getLocale(),"DRSSettings.noDomains.error");
        
        if (domainNames.size() > 0)
        {
            for (Iterator i = domainNames.iterator(); i.hasNext(); )
            {
                String d = (String) i.next();
                List l = utils.getMultiBrokerEntries(repositoryContext, d);
                if (l.size() > 0)
                    val2 = false;
            }

            if (val2)
                nonefound = messages.getMessage(request.getLocale(),"error.replicators.dont.exist");
        }
        %>
<tr> 
  <td class="table-text"> 
    <input type="radio" name="pers" value="messaging" <%= (form.getMode().equals("messaging")) ? "checked" : "" %> <%=(val || val2) ? "disabled" : ""%>>

<% if (val2)
{ %>
 
  

  <a href="<%=request.getContextPath()%>/navigatorCmd.do?forwardName=MultibrokerDomain.content.main"><bean:message key="Persistence.messaging"/></a> (<%=nonefound%>)
    
<% } else { %>
        <a href="<%=request.getContextPath()%>/persistenceDetail.do?action=advanced"><bean:message key="Persistence.messaging"/></a>
<% } %>
    
<% } %>
   </td>
</tr>
</table>


