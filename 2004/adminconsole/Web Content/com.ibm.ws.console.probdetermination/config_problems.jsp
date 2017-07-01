<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ page import="com.ibm.ws.sm.workspace.*" %>
<%@ page import="com.ibm.ws.sm.workspace.metadata.*" %>
<%@ page import="com.ibm.ws.console.core.*" %>
<%@ page import="com.ibm.ws.sm.validation.*" %>
<%@ page import="com.ibm.etools.validation.*" %>
<%@ page import="java.util.*" %>


<%  
        WorkSpace workSpace = (WorkSpace) session.getAttribute(Constants.WORKSPACE_KEY);
        ValidationHelper helper = ValidationHelper.getInstance();
        ValidationManagerRegistry registry = helper.getRegistryInstance();
        ContextValidationMessages contextMessages = null; 

        String msg = "";

        if (workSpace == null)
        {
            msg="<invalid workspace>";
        }
        ValidationManager manager = registry.getValidationManager(workSpace);
        
        if (manager == null)
        {
            msg+="<null validator>";
        }
        else{ 
            msg = ""+manager.getMessageCount();
            contextMessages = manager.getResults();
        }

        int infoConfigCount = 0;
        int errorConfigCount = 0;
        int warningConfigCount = 0;

        for (Iterator i = contextMessages.getDocumentFullUris(); i.hasNext();) {
        	String documentFullUri = (String) i.next();
            DocumentValidationMessages documentMessages = contextMessages.getDocumentMessages(documentFullUri);
            for (Iterator j = documentMessages.getValidatorClassNames(); j.hasNext();) {
            	String validatorClass = (String) j.next();
                ValidatorValidationMessages validatorMessages = documentMessages.getValidatorMessages(validatorClass);

                Iterator objectIter = validatorMessages.getMessages();

                while (objectIter.hasNext()) {
                	IMessage message = (IMessage) objectIter.next();
                    int severity = message.getSeverity();
                    switch (severity) {
                    	case 0 :
                        	infoConfigCount++;
                            break;
                        case 1 :
                           	errorConfigCount++;
                            break;
                        case 2 :
                            warningConfigCount++;
                            break;
                       	default :
                            break;
                    }
                }
            }
       }
        


%>

<table border="0" cellpadding="2" cellspacing="1" summary="List table"
	class="framing-table" width="100%">
	<tr>
		<td class="column-head" colspan="4" nowrap><bean:message
			key="configproblems.status.title" /></td>
	</tr>

	<tr>
		<td class="table-text" width="293"><bean:message
			key="configproblems.status.totalcfgLabel" /> :<%=msg%></td>
		<td class="table-text" width="209"><img
			alt='<bean:message key="error.msg.error"/>'
			src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/error.gif"
			width="16" height="16" align="texttop">: <a
			href="<%=request.getContextPath()%>/com.ibm.ws.console.probdetermination.forwardCmd.do?forwardName=configproblems.content.main.list&init=false&type=errors"
			target="WAS_Status"><%=errorConfigCount%> <bean:message
			key="events.total" /> </a></td>
		<td class="table-text"><img
			alt='<bean:message key="error.msg.warning"/>'
			src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/warning.gif"
			width="16" height="16" align="texttop">: <a
			href="<%=request.getContextPath()%>/com.ibm.ws.console.probdetermination.forwardCmd.do?forwardName=configproblems.content.main.list&init=false&type=warnings"
			target="WAS_Status"><%=warningConfigCount%> <bean:message
			key="events.total" /> </a></td>
		<td class="table-text"><img
			alt='<bean:message key="error.msg.information"/>'
			src="<%=request.getContextPath()%>/com.ibm.ws.console.events/images/information.gif"
			width="16" height="16" align="texttop">: <a
			href="<%=request.getContextPath()%>/com.ibm.ws.console.probdetermination.forwardCmd.do?forwardName=configproblems.content.main.list&init=false&type=info"
			target="WAS_Status"><%=infoConfigCount%> <bean:message
			key="events.total" /> </a></td>
	</tr>
</table>