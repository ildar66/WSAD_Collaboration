<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="rootImage" classname="java.lang.String" />
<tiles:useAttribute name="rootName" classname="java.lang.String" />
<tiles:useAttribute name="treeList" classname="java.util.List" />

<script type="text/javascript" language="JavaScript" src="<%=request.getContextPath()%>/scripts/aptree.js"></script>
<script type="text/javascript" language="JavaScript1.2">

setShowExpanders(true);
setExpandDepth(1);
setKeepState(false);
setShowHealth(false);

var thispage = document.location.href;
if (thispage.indexOf("perspective=tab.topology") > -1) {
        setInTable(true);
}
else {
        setInTable(false);
}


setTargetFrame("detail");
openFolder = "<%=request.getContextPath()%>/images/open_folder.gif";
closedFolder = "<%=request.getContextPath()%>/images/closed_folder.gif";
plusIcon = "<%=request.getContextPath()%>/images/lplus.gif";
minusIcon = "<%=request.getContextPath()%>/images/lminus.gif";
blankIcon = "<%=request.getContextPath()%>/images/blank20.gif";

// setImagePath('<%=request.getContextPath()+"/images/"%>');

root = addRoot('<%=rootImage%>', '<b><bean:message key='<%=rootName%>'/></b>','');
<logic:iterate id="item" name="treeList" type="com.ibm.ws.console.core.item.TreeItem" >
<% 
    String link = ""; 
    if(item.getLink() != null) {
        link = item.getLink();
        if (link.equals("") == false && link.startsWith("http://") == false) {
            link = request.getContextPath()+link;
        }
     }
    String icon = "/images/onepix.gif";
    if (item.getIcon() != null) {
       icon = item.getIcon();
        if (icon.equals("")) {
            icon = request.getContextPath()+"/images/onepix.gif";
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
<% if (!(item.isTranslated())) { %>
<%=item.getNodeId()%> = addItem(<%=item.getParentId()%>, "<%=icon%>", '<%=item.getTooltip()%>', "<%=link%>");
<%} else { %>
<%=item.getNodeId()%> = addItem(<%=item.getParentId()%>, "<%=icon%>", '<bean:message key='<%=item.getTooltip()%>'/>', "<%=link%>");
<%} %>
</logic:iterate>

</script>

<script>
initialize()
</script>

