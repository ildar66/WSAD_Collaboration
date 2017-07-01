<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java" import="com.ibm.websphere.management.fileservice.*,com.ibm.ws.console.core.form.*, com.ibm.ws.console.core.*"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />

<ibmcommon:detectLocale/>

<html:html locale="true">

<head>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/menu_functions.js"></script>
<script language="JavaScript" src="<%=request.getContextPath()%>/scripts/collectionform.js"></script>

<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META HTTP-EQUIV="Expires" CONTENT="0">
</head>

<body>
<html:form action="browseRemoteNodes.do" name="BrowseRemoteForm" type="com.ibm.ws.console.core.form.BrowseRemoteForm">

<H1 id="bread-crumb"><bean:message key="<%=titleKey%>"/></H1>
<p class="instruction-text"><bean:message key="<%=descKey%>"/></p>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
   <td class="framing-table">
     <table border="0" cellpadding="0" cellspacing="0" width="100%" summary="List framing table">
       <tbody> 
         <tr> 
          <td  class="framing-table">
           <table cols=2 width="100%" cellpadding="3" cellspacing="1">
            <tr>
	      <td class="column-head-name" colspan="2">
		<img src="images/onepix.gif" width="1" height="16">
		<bean:message key="browse.contents" />&nbsp;<bean:write name="BrowseRemoteForm" property="selectedItem"/>
	      </td>
	    </tr>

			   
<bean:define id="thisForm" name="BrowseRemoteForm" type="com.ibm.ws.console.core.form.BrowseRemoteForm"/>
<bean:define id="parentList" name="BrowseRemoteForm" property="parentDir" type="java.util.ArrayList" />
<%
    String parentDir = (String) parentList.get(1); 
    String link = "browseRemoteNodes.do?parentName=" + ConfigFileHelper.urlEncode(parentDir);
    if (parentDir.equals("cellContext")) {
        link = "browseRemoteNodes.do?nodeName=";
    }
    else if (parentDir.equals("nodeContext")) {
        parentDir =  thisForm.getNode();
        link = "browseRemoteNodes.do?nodeName="  + ConfigFileHelper.urlEncode(parentDir);
    }
%>
	    <tr>
	      <td class="table-text" width="1%"><img src="images/onepix.gif" width="1" height="16"></td>
	      <td class="table-text">
		<img src="images/parent_dir.gif" width="16" height="16" align="absmiddle" border="0">
		   &nbsp;
                <a href="<%= link   %>"><%=(String) parentDir%></a>
	      </td>
	    </tr>
<%
    RemoteFile[] remoteFiles = thisForm.getRemoteFiles();
    for (int i=0; i<remoteFiles.length; i++) {
        RemoteFile remoteFile = remoteFiles[i];
        // System.out.println(remoteFile.dump());
        String fileName = remoteFile.getName();
        String filePath = remoteFile.getPath();
        if (remoteFile.isRoot()){
            fileName = remoteFile.getAbsolutePath();
        }
        if (remoteFile.isDirectory()) {
	   link = "browseRemoteNodes.do?remoteFileName=" + ConfigFileHelper.urlEncode(filePath);
%>
            <tr>
	      <td class="table-text" width="1%"><img src="images/onepix.gif" width="1" height="16"></td>
	      <td class="table-text">
		<img src="images/closed_folder.gif" width="16" height="16" align="absmiddle" border="0">
		     &nbsp;
                <a href="<%= link   %>"><%=(String) fileName%></a>
	      </td>
	    </tr>
<%
        } else {
            String foo = filePath + ":" + fileName;
	    String fileType = remoteFile.getType();
%>							  
              <logic:equal name="BrowseRemoteForm" property="fileExt" value="war">
<%
            if (fileType.equals(RemoteFile.EAR_FILE) || 
                fileType.equals(RemoteFile.EJBJAR_FILE) || 
	        fileType.equals(RemoteFile.WAR_FILE) ) {
%>
            <tr>
	      <td class="table-text" width="1%">
               <logic:equal name="BrowseRemoteForm" property="select" value="single">
		 <html:radio property="fileRadio" value="<%=(String) foo%>" />
               </logic:equal>
               <logic:equal name="BrowseRemoteForm" property="select" value="multi">
		 <html:checkbox property="checkBox" value="<%=(String) foo%>" />
               </logic:equal>
	      </td>
	      <td class="table-text">
		<img src="images/file.gif" width="24" height="22" align="absmiddle" border="0">
		     &nbsp;
                 <%=(String) fileName%>
	      </td>
	    </tr>
<%
	    }
%>
           </logic:equal>
              <logic:equal name="BrowseRemoteForm" property="fileExt" value="rar">
<%
            if (fileType.equals(RemoteFile.RAR_FILE)) 
           	{
%>
            <tr>
	      <td class="table-text" width="1%">
               <logic:equal name="BrowseRemoteForm" property="select" value="single">
		 <html:radio property="fileRadio" value="<%=(String) foo%>" />
               </logic:equal>
               <logic:equal name="BrowseRemoteForm" property="select" value="multi">
		 <html:checkbox property="checkBox" value="<%=(String) foo%>" />
               </logic:equal>
	      </td>
	      <td class="table-text">
		<img src="images/file.gif" width="24" height="22" align="absmiddle" border="0">
		     &nbsp;
                 <%=(String) fileName%>
	      </td>
	    </tr>
<%
	         }
%>
           </logic:equal>
             <logic:notEqual name="BrowseRemoteForm" property="fileExt" value="war">
				 <logic:notEqual name="BrowseRemoteForm" property="fileExt" value="rar">
              <tr>
		<td class="table-text" width="1%">
		<logic:equal name="BrowseRemoteForm" property="select" value="single">
		 <html:radio property="fileRadio" value="<%=(String) foo%>" />
                </logic:equal>
                <logic:equal name="BrowseRemoteForm" property="select" value="multi">
	      	  <html:checkbox property="checkBox" value="<%=(String) foo%>" />
                  </logic:equal>
	       </td>
		<td class="table-text">
	      <img src="images/file.gif" width="24" height="22" align="absmiddle" border="0">
		      &nbsp;
                    <%=(String) fileName%>
		 </td>
		</tr>
		          </logic:notEqual>
                </logic:notEqual>
<%
	     }
     }	
%>

    		<tr>
		  <td class="button-section" colspan="2"> 
	<html:submit property="applyAction" styleId="navigation" styleClass="buttons">
                   <bean:message key="button.apply"/>
                 </html:submit>
             <html:submit property="okAction" styleId="navigation" styleClass="buttons">
			    <bean:message key="button.ok"/>
             </html:submit>
             <html:cancel property="cancelAction" styleId="navigation" styleClass="buttons">
                     <bean:message key="button.cancel"/>
             </html:cancel>
                  </td>
	    </tr>
        	  </table>
			</td>
		  </tr>
         </tbody> 
  		</table>
       </td>
	</tr>
  </table>
</html:form>

</body>
</html:html>
