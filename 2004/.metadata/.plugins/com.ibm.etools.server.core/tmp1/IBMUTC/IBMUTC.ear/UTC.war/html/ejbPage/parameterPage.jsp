<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<!--
 Licensed Material - Property of IBM 
 (C) Copyright IBM Corp. 2001 - All Rights Reserved. 
 US Government Users Restricted Rights - Use, duplication or disclosure 
 restricted by GSA ADP Schedule Contract with IBM Corp. 
-->

<html>

<%@ page errorPage="/error.jsp" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="/utc" prefix="utc" %>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <meta http-equiv="Pragma" content="no-cache">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <link rel=stylesheet type="text/css" href="/UTC/css/windows.css">
  <title><utc:resource key="pageTitle"/></title>
</head>

<body id="margin" bgcolor="#ECE9D8">

<div id="border">
  <iframe id="toolbar" src="/UTC/html/toolbar/toolbar.jsp?page=3" frameborder="0" scrolling="no" marginheight="1" marginwidth="1"></iframe>
</div>
<div id="shadow">
  <iframe id="content" name="formParameters" src="/UTC/html/ejbPage/parameter.jsp?rand=<%= Math.random() %>" frameborder="0" scrolling="auto" marginheight="2" marginwidth="3"></iframe>
</div>

</body>
</html>