<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.ws.sm.workspace.*,com.ibm.ws.console.environment.Constants,com.ibm.ws.console.environment.namebindings.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action"%>
<%@ page errorPage="error.jsp" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%
   StringNameSpaceBindingDetailForm stringNameSpaceBindingDetailForm = null;
   EjbNameSpaceBindingDetailForm ejbNameSpaceBindingDetailForm = null;
   IndirectLookupNameSpaceBindingDetailForm indirectLookupNameSpaceBindingDetailForm = null;
   CORBAObjectNameSpaceBindingDetailForm cORBAObjectNameSpaceBindingDetailForm = null;

   RepositoryContext repContext = (RepositoryContext)session.getAttribute(com.ibm.ws.console.core.Constants.CURRENTCTXT_KEY);

   String type = (String) session.getAttribute ("NameSpaceBindingBindingType");
   if (type == null)
     type = Constants.STRING_BINDINGTYPE;
   int rows = 4;
   int typeInt = -1;

   if (type.equals (Constants.STRING_BINDINGTYPE))
      typeInt = 0;
   else if (type.equals (Constants.EJB_BINDINGTYPE))
      typeInt = 1;
   else if (type.equals (Constants.INDIRECTLOOKUP_BINDINGTYPE))
      typeInt = 2;
   else if (type.equals (Constants.CORBAOBJECT_BINDINGTYPE))
      typeInt = 3;

	ServletContext servletContext = (ServletContext)pageContext.getServletContext();
	MessageResources messages = (MessageResources) servletContext.getAttribute (Action.MESSAGES_KEY);

   StringBuffer summaryText= new StringBuffer ();
   summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.create.new")+"\n");
   summaryText.append (messages.getMessage (request.getLocale(),"environment.label.scope")+" ");
   summaryText.append (repContext.getURI()+"\n");
   summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.bindingType.displayName")+" ");
   summaryText.append (type+"\n");
   summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.name.displayName")+" ");
   switch (typeInt)
   {
      case 0:
         stringNameSpaceBindingDetailForm = (StringNameSpaceBindingDetailForm)session.getAttribute ("com.ibm.ws.console.environment.StringNameSpaceBindingDetailForm");
         if (stringNameSpaceBindingDetailForm == null)
            break;
         summaryText.append (stringNameSpaceBindingDetailForm.getName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.nameInNameSpace.displayName")+" ");
         summaryText.append (stringNameSpaceBindingDetailForm.getNameInNameSpace()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"StringNameSpaceBinding.stringToBind.displayName")+" ");
         summaryText.append (stringNameSpaceBindingDetailForm.getStringToBind()+"\n");
         rows = 6;
         break;
      case 1:
         ejbNameSpaceBindingDetailForm = (EjbNameSpaceBindingDetailForm)session.getAttribute ("com.ibm.ws.console.environment.EjbNameSpaceBindingDetailForm");
         if (ejbNameSpaceBindingDetailForm == null)
            break;
         summaryText.append (ejbNameSpaceBindingDetailForm.getName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.nameInNameSpace.displayName")+" ");
         summaryText.append (ejbNameSpaceBindingDetailForm.getNameInNameSpace()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"EjbNameSpaceBinding.ejbJndiName.displayName")+" ");
         summaryText.append (ejbNameSpaceBindingDetailForm.getEjbJndiName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"EjbNameSpaceBinding.applicationServerName.displayName")+" ");
         summaryText.append (ejbNameSpaceBindingDetailForm.getApplicationServerName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"EjbNameSpaceBinding.bindingLocation.displayName")+" ");
         summaryText.append (ejbNameSpaceBindingDetailForm.getBindingLocation()+"\n");
         rows = 8;
         if (ejbNameSpaceBindingDetailForm.getBindingLocation().equals ("SINGLESERVER"))
         {
            rows++;
            summaryText.append (messages.getMessage (request.getLocale(),"EjbNameSpaceBinding.applicationNodeName.displayName")+" ");
            summaryText.append (ejbNameSpaceBindingDetailForm.getApplicationNodeName()+"\n");
         }
         break;
      case 2:
         indirectLookupNameSpaceBindingDetailForm = (IndirectLookupNameSpaceBindingDetailForm)session.getAttribute ("com.ibm.ws.console.environment.IndirectLookupNameSpaceBindingDetailForm");
         if (indirectLookupNameSpaceBindingDetailForm == null)
            break;
         summaryText.append (indirectLookupNameSpaceBindingDetailForm.getName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.nameInNameSpace.displayName")+" ");
         summaryText.append (indirectLookupNameSpaceBindingDetailForm.getNameInNameSpace()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"IndirectLookupNameSpaceBinding.providerURL.displayName")+" ");
         summaryText.append (indirectLookupNameSpaceBindingDetailForm.getProviderURL()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"IndirectLookupNameSpaceBinding.jndiName.displayName")+" ");
         summaryText.append (indirectLookupNameSpaceBindingDetailForm.getJndiName()+"\n");
         rows = 7;
         break;
      case 3:
         cORBAObjectNameSpaceBindingDetailForm = (CORBAObjectNameSpaceBindingDetailForm)session.getAttribute ("com.ibm.ws.console.environment.CORBAObjectNameSpaceBindingDetailForm");
         if (cORBAObjectNameSpaceBindingDetailForm == null)
            break;
         summaryText.append (cORBAObjectNameSpaceBindingDetailForm.getName()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"NameSpaceBinding.nameInNameSpace.displayName")+" ");
         summaryText.append (cORBAObjectNameSpaceBindingDetailForm.getNameInNameSpace()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"CORBAObjectNameSpaceBinding.corbanameUrl.displayName")+" ");
         summaryText.append (cORBAObjectNameSpaceBindingDetailForm.getCorbanameUrl()+"\n");
         summaryText.append (messages.getMessage (request.getLocale(),"CORBAObjectNameSpaceBinding.federatedContext.displayName")+" ");
         summaryText.append (cORBAObjectNameSpaceBindingDetailForm.getFederatedContext()+"\n");
         rows = 7;
         break;
   }
%>

<table border="0" cellpadding="3" cellspacing="1" width="100%" summary="List table">
  <tbody>

   <tr valign="top">

        <td class="table-text" nowrap valign="top">
                <textarea name="summary" rows="<%=rows%>" styleId="atextarea" disabled><%=summaryText%></textarea>
        </td>
       
   </tr>

  </tbody>
</table>

