<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" buffer="1kb"%>
<%@ page import="java.util.*,org.apache.struts.util.MessageResources,org.apache.struts.action.Action,com.ibm.ws.sm.workspace.RepositoryContext,com.ibm.ws.console.core.*,com.ibm.ws.console.core.Constants,com.ibm.ws.security.core.SecurityContext"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<ibmcommon:detectLocale/>

<html:html locale="true">
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
<TITLE>WebSphere Task Navigation</TITLE> 
<jsp:include page = "browser_detection.jsp" flush="true"/>
<%@ include file = "navtree_js.jsp" %>
</head>

<tiles:useAttribute name="rootImage" classname="java.lang.String" />
<tiles:useAttribute name="navigatorList" classname="java.util.List" />

<body class="navtree">
<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/scripts/aptree.js"></script>
<script type="text/javascript" language="JavaScript1.2">

setShowExpanders(true);
setExpandDepth(1);
setKeepState(true);
setShowHealth(false);
setInTable(false);
setTargetFrame("detail");
openFolder = "<%=request.getContextPath()%>/images/open_folder.gif";
closedFolder = "<%=request.getContextPath()%>/images/closed_folder.gif";
plusIcon = "<%=request.getContextPath()%>/images/lplus.gif";
minusIcon = "<%=request.getContextPath()%>/images/lminus.gif";
blankIcon = "<%=request.getContextPath()%>/images/blank20.gif";

</script>
<% 
RepositoryContext cellContext = (RepositoryContext)session.getAttribute(Constants.CURRENTCELLCTXT_KEY);
String cellName = cellContext.getName();
String externalMarker = "";
%>


<%
ServletContext servletContext = (ServletContext)pageContext.getServletContext();
MessageResources messages = (MessageResources)servletContext.getAttribute(Action.MESSAGES_KEY);
String uid = messages.getMessage(request.getLocale(),"prompt.username");
%>


<% 
User thisuser = (User)session.getAttribute(Constants.USER_KEY);
String theuser = thisuser.getUserID();
%>

<div class='indent1'><b><%=uid%></b> <%=theuser%></div>

<script type="text/javascript" language="JavaScript1.2">
admin_domain = addRoot('<%=request.getContextPath()+"/"+rootImage%>', '<%=cellName%>','');



<%
    List staticTree = new ArrayList();
    staticTree.add("admin_domain");
    List staticTreeLevels = new ArrayList();
    int level = 0;
    staticTreeLevels.add(new Integer(level)); 
    int parIndex = 0;
    String strlevel = "";
%>

<logic:iterate id="item" name="navigatorList" type="com.ibm.ws.console.core.item.NavigatorItem" >
<% 

    
    
    String targetString = "detail";

    boolean showItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        String[] roles = item.getRoles();
        showItem = false;
        for (int i = 0; i < roles.length; i++) {
            if (request.isUserInRole(roles[i])) {
                showItem = true;
                break;
            }
        }
    }
    if (showItem == true) {
%>
<% 
        String parentId = item.getParentId();
        if (parentId.equals("root")) {
            parentId = "admin_domain";
        }
        String link = item.getLink();
        if (link.equals("") == false && link.startsWith("http://") == false) {
            link = request.getContextPath()+link;
        }
        String icon = item.getIcon();
        if (icon.equals("")) {
            icon = request.getContextPath()+"/images/onepix.gif";
        }
        else {
            if (icon.startsWith("//")) {
                icon = icon.substring(1);
            }
            else {
                if (icon.startsWith("/")) {
                    icon = request.getContextPath()+icon;
                }
                else {
                    icon = request.getContextPath()+"/"+icon;
                }
            }
        }
%>
<%  
        if (item.isExternalLink()) {
           externalMarker = "...";
           targetString = "_blank";
%>

            setTargetFrame("<%=targetString%>");
<%  
        } 
        
%>

<%=item.getNodeId()%> = addItem(<%=parentId%>, "<%=icon%>", "<bean:message key='<%=item.getTooltip()%>'/><%=externalMarker%>", "<%=link%>");

<%  
        if (item.isExternalLink()) {
            externalMarker = "";            
            targetString = "detail";
%>

            setTargetFrame("<%=targetString%>");       
<%  
        }
    }    
%>
</logic:iterate>
</script>


<script type="text/javascript" language="JavaScript1.2">
initialize()
</script>


<%  ////////////////  Begin NOSCRIPT path ///////////////  %>

<noscript>
<div class='indent1'><b><%=cellName%></b></div>


<logic:iterate id="item" name="navigatorList" type="com.ibm.ws.console.core.item.NavigatorItem" >
<% 

    
    
    String targetString = "detail";

    boolean showItem = true;
    if (SecurityContext.isSecurityEnabled()) {
        String[] roles = item.getRoles();
        showItem = false;
        for (int i = 0; i < roles.length; i++) {
            if (request.isUserInRole(roles[i])) {
                showItem = true;
                break;
            }
        }
    }
    if (showItem == true) {
%>
<% 
        String parentId = item.getParentId();
        if (parentId.equals("root")) {
            parentId = "admin_domain";
        }
        String link = item.getLink();
        if (link.equals("") == false && link.startsWith("http://") == false) {
            link = request.getContextPath()+link;
        }
        String icon = item.getIcon();
        if (icon.equals("")) {
            icon = request.getContextPath()+"/images/onepix.gif";
        }
        else {
            if (icon.startsWith("//")) {
                icon = icon.substring(1);
            }
            else {
                if (icon.startsWith("/")) {
                    icon = request.getContextPath()+icon;
                }
                else {
                    icon = request.getContextPath()+"/"+icon;
                }
            }
        }
%>
<%  
        if (item.isExternalLink()) {
           externalMarker = "...";
           targetString = "_blank";
%>

            
<%  
        } 
        
        if (link.equals("")) {
        
                if (staticTree.indexOf(parentId)>-1) {
                        parIndex = staticTree.indexOf(parentId);
                        level = ((Integer)staticTreeLevels.get(parIndex)).intValue();
                        level = level + 1;
                        staticTree.add(item.getNodeId());
                        staticTreeLevels.add(new Integer(level)); 
                
               
%>
                       
                        <div class='indent<%=level%>'><bean:message key='<%=item.getTooltip()%>'/></div>
                        
<%
                        
                }
                
        } else {
                if (staticTree.indexOf(parentId)>-1) {
                
                        parIndex = staticTree.indexOf(parentId);
                        level = ((Integer)staticTreeLevels.get(parIndex)).intValue();
                        level = level + 1;
 
                        
%>
                        
                        <div class='indent<%=level%>'> <a href="<%=link%>" target="<%=targetString%>"><bean:message key='<%=item.getTooltip()%>'/><%=externalMarker%></a></div>
                        
<%                
                
                }
                
        }


        
%>


<%  
        if (item.isExternalLink()) {
            externalMarker = "";            
            targetString = "detail";
%>

<%  
        }
    }    
%>

</logic:iterate>
</noscript>







</body>
</html:html>