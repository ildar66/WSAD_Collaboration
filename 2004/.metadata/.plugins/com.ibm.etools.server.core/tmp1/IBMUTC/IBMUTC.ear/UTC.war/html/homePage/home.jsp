<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<!--
 Licensed Material - Property of IBM 
 (C) Copyright IBM Corp. 2001 - All Rights Reserved. 
 US Government Users Restricted Rights - Use, duplication or disclosure 
 restricted by GSA ADP Schedule Contract with IBM Corp. 
-->

<%@ page errorPage="/error.jsp" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/utc" prefix="utc" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel=stylesheet type="text/css" href="/UTC/css/dynamic.css">
  <title><utc:resource key="homePageTitle"/></title>
</head>

<body>

<div class=home>
<table cellpadding=4 cellspacing=1 border=0 width=100%>
<tr>
<td class=home><a href="/UTC/html/jndiPage/jndiFrame.jsp" target="main"><img src="/UTC/images/lpage/page_jndi.gif" width="32" height="32" border="0"><br><utc:resource key="jndiLookupPageTitle"/></a></td>
<td class=homeinfo><utc:resource key="jndiLookupPageDescription"/></td>
<td width=10></td>
<td class=home><a href="/UTC/html/ejbPage/ejbFrame.jsp" target="main"><img src="/UTC/images/lpage/page_ejb.gif" width="32" height="32" border="0"><br><utc:resource key="ejbPageTitle"/></a></td>
<td class=homeinfo><utc:resource key="ejbPageDescription"/></td>
</tr>
<tr height=15><td></td></tr>
<tr>
<td class=home><a href="/UTC/html/propertiesPage/propertiesFrame.jsp" target="main"><img src="/UTC/images/lpage/page_properties.gif" width="32" height="32" border="0"><br><utc:resource key="propertiesPageTitle"/></a></td>
<td class=homeinfo><utc:resource key="propertiesPageDescription"/></td>
<td></td>
<td class=home><a href="/UTC/html/helpPage/helpFrame.jsp" target="main"><img src="/UTC/images/lpage/page_help.gif" width="32" height="32" border="0"><br><utc:resource key="helpPageTitle"/></a></td>
<td class=homeinfo><utc:resource key="helpPageDescription"/></td>
</tr>
</table>
</div>
</body>
</html>