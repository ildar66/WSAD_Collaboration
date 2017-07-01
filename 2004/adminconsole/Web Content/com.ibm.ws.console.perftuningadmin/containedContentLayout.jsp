<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="org.apache.struts.util.MessageResources,org.apache.struts.action.Action,com.ibm.ws.sm.workspace.*,com.ibm.ws.console.core.error.*"%>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>


<tiles:useAttribute name="contentList" classname="java.util.List" />

 
<%
String statusStarted = "null";
String statusStopped = "null";
String statusUnavailable = "null";
String statusUnknown = "null";
String statusPartialStart = "null";
String statusPartialStop = "null";
String statusSynchronized = "null";
String statusNotSynchronized = "null";
String statusHeader = "null";
MessageResources statusMessages = (MessageResources)application.getAttribute(Action.MESSAGES_KEY);
try { statusStarted = statusMessages.getMessage(request.getLocale(),"ExecutionState.STARTED"); } catch (Exception e) { } 
try { statusStopped = statusMessages.getMessage(request.getLocale(),"ExecutionState.STOPPED"); } catch (Exception e) { } 
try { statusUnavailable = statusMessages.getMessage(request.getLocale(),"ExecutionState.UNAVAILABLE"); } catch (Exception e) { }
try { statusUnknown = statusMessages.getMessage(request.getLocale(),"ExecutionState.UNsKNOWN"); } catch (Exception e) { } 
try { statusPartialStart = statusMessages.getMessage(request.getLocale(),"ExecutionState.PARTIAL_START"); } catch (Exception e) { } 
try { statusPartialStop = statusMessages.getMessage(request.getLocale(),"ExecutionState.PARTIAL_STOP"); } catch (Exception e) { } 
try { statusSynchronized = statusMessages.getMessage(request.getLocale(),"Node.synchronized"); } catch (Exception e) { } 
try { statusNotSynchronized = statusMessages.getMessage(request.getLocale(),"Node.not.synchronized"); } catch (Exception e) { } 
try { statusHeader = statusMessages.getMessage(request.getLocale(),"Node.status.displayName"); } catch (Exception e) { } 
%>
<script>
top.statusCollection = "yes";
var statusStarted = "<%=statusStarted%>";
var statusStopped = "<%=statusStopped%>";
var statusUnavailable = "<%=statusUnavailable%>";
var statusUnknown = "<%=statusUnknown%>";
var statusPartialStart = "<%=statusPartialStart%>";
var statusPartialStop = "<%=statusPartialStop%>";
var statusSynchronized = "<%=statusSynchronized%>";
var statusNotSynchronized = "<%=statusNotSynchronized%>";
var statusHeader = "<%=statusHeader%>";
if (statusStarted == "null") { statusStarted = "Started" }
if (statusStopped == "null") { statusStopped = "Stopped" }
if (statusUnavailable == "null") { statusUnavailable = "Unavailable" }
if (statusUnknown == "null") { statusUnknown = "Unknown" }
if (statusPartialStart == "null") { statusPartialStart = "Partial Start" }
if (statusPartialStop == "null") { statusPartialStop = "Partial Stop" }
if (statusSynchronized == "null") { statusSynchronized = "Synchronized" }
if (statusNotSynchronized == "null") { statusStarted = "Not Synchronized" }
if (statusHeader == "null") { statusStarted = "Status" }
</script>
 
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>


<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="10" WIDTH="100%" SUMMARY="List layout table">

	<TBODY>
		<TR>
			<TD >
                        

    <tiles:insert page="/secure/layouts/vboxLayout.jsp" flush="true">
        <tiles:put name="list" beanName="contentList" beanScope="page"/>
    </tiles:insert>
    
                        </TD>
               </TR>
        </TBODY>
</TABLE>


 
