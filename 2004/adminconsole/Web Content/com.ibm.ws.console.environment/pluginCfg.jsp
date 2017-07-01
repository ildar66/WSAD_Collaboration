<%-- IBM Confidential OCO Source Material --%>
<%-- 5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business Machines Corp. 1997, 2002 --%>
<%-- The source code for this program is not published or otherwise divested --%>
<%-- of its trade secrets, irrespective of what has been deposited with the --%>
<%-- U.S. Copyright Office. --%>

<%@ page language="java"%>
<%@ taglib uri="/WEB-INF/ibmcommon.tld" prefix="ibmcommon" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="titleKey" classname="java.lang.String" />
<tiles:useAttribute name="descKey" classname="java.lang.String" />
<tiles:useAttribute name="actionHandler" classname="java.lang.String" />
<tiles:useAttribute name="actionForm" classname="java.lang.String" />
<tiles:useAttribute name="formType" classname="java.lang.String" />
<tiles:useAttribute name="descImage" classname="java.lang.String"/>
<tiles:useAttribute name="instanceDescription" classname="java.lang.String"/>

<ibmcommon:detectLocale/>


<html:html locale="true">
<HEAD>
<jsp:include page = "/secure/layouts/browser_detection.jsp" flush="true"/>
</HEAD>

<BODY CLASS="content"> 


<html:form action="<%=actionHandler%>" name="<%=actionForm%>" type="<%=formType%>">

<p class="instruction-text"><bean:message key="<%=descKey%>"/></p>


<input type="submit" name="ok" value="<bean:message key="button.ok"/>" class="buttons" id="navigation">
<input type="submit" name="cancel" value="<bean:message key="button.cancel"/>" class="buttons" id="navigation">

<logic:equal name="<%=actionForm%>" property="fileExists" value="true">
<BR>
<BR>
<A HREF="/admin/secure/downloadFile/plugin-cfg.xml?pluginCfg=true"><bean:message key="download.plugincfg"/></A>
</logic:equal>

</html:form>

</BODY>
</html:html>
